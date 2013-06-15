package com.apihome.spider.ued.task.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apihome.dao.ued.ArticleDAO;
import com.apihome.model.ued.Article;
import com.apihome.model.ued.ArticleTag;
import com.apihome.spider.ued.annotation.HashUrl;
import com.apihome.spider.ued.model.SpiderRule;
import com.apihome.spider.ued.task.SpiderTask;
import com.apihome.web.ued.constants.WebConstant;
import com.xframework.tools.DateTool;
import com.xframework.tools.DwTool;
import com.xframework.tools.ImageTool;
import com.xframework.tools.MutliParseTool;
import com.xframework.tools.StringTool;

@Component("QihuuxcTask")
@HashUrl(md5="http://uxc.360.cn/page/") //http://uxc.360.cn/page/
public class QihuuxcTask implements SpiderTask
{
    private static Logger logger = Logger.getLogger(QihuuxcTask.class);
	
    @Autowired
    private ArticleDAO articleDAO;
    
    @Override
    public void startup(SpiderRule rule)
    {
    	Set<String> urlSet = new HashSet<String>();
        int pageFrom = rule.getPageFrom();
        int pageTo = rule.getPageTo();
        String url = rule.getUrl();
        if (pageFrom == pageTo)
		{
			url = url+pageFrom;
			urlSet.add(url);
		}
        else 
        {
			for (int i = pageFrom; i < pageTo+1; i++)
			{
				String urlTemp = url+i;
				urlSet.add(urlTemp);
			}
		}

        for (String urlStr : urlSet)
		{
            try
			{
				Document doc = Jsoup.connect(urlStr).get();
		        Elements elements = doc.select("article");
		        List<Article> arts = MutliParseTool.mutliParse(elements, this);
		        this.saveArticles(arts);
			} 
            catch (IOException e)
			{
            	logger.error("根据url="+urlStr+"获取网页信息出错：",e);
			} 

		}
    }
    
    
    /**
     * @Title: saveArticles 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param arts    设定文件 
     * @return void    返回类型 
     * @throws
     */
    public void saveArticles(List<Article> arts)
	{
    	List<Article> articles = new ArrayList<Article>();
    	List<ArticleTag> tags = new ArrayList<ArticleTag>();
    	for (Article article : arts)
		{
			articles.add(article);
			tags.addAll(article.getTags());
		}
    	
    	if (articles.size() > 0)
		{
			articleDAO.saveArticles(articles);
			if (tags.size() > 0)
			{
				articleDAO.saveArticleTags(tags);
			}
		}
	}

	@Override
    public List<Article> parseListView(Elements elements)
    {
    	List<Article> list = new ArrayList<Article>();
    	
    	for (Element element : elements)
		{
    		Element headElement = element.select("div.hd > h1 > a").get(0);
    		String title = headElement.text();
    		String url = headElement.attr("href");
       		Element infoElement = element.select("div.details").get(0);
       		String info = infoElement.text();
       		String[] arr = info.split("\\/");
       		Element summaryElement = element.select("div.bd").get(0);
       		String summary = summaryElement.lastElementSibling().text();
       		
			List<ArticleTag> tagList = new ArrayList<ArticleTag>();
       		if (arr.length > 2)
			{
				String tagStr = arr[2];
				String[] tagArr = tagStr.split("-");
				for (int i = 0; i < tagArr.length; i++)
				{
					ArticleTag articleTag = new ArticleTag();
					articleTag.setTag(tagArr[i]);
					articleTag.setEncodeSurl(StringTool.hashString(url));
					tagList.add(articleTag);
				}
			}

       		Article article = new Article();
       		article.setSurl(url);
       		article.setEncodeSurl(StringTool.hashString(url));
       		article.setTitle(title);
       		article.setAuthor(arr[0]);
       		article.setCreateTime(DateTool.convert(arr[1], "yyyy.MM.dd"));
       		article.setSummary(summary);
       		article.setKind(WebConstant.ARTICLE_KIND_TEAM);
       		article.setStatus(WebConstant.ARTICLE_STATUS_NORMAL);
       		article.setTags(tagList);
       		
       		list.add(article);
		}
    	
    	return list;
    }
    
    @Override
    public void parseDetailView(Article art)
    {
    	try
		{
            Document doc = Jsoup.connect(art.getSurl()).get();
            Element body = doc.select("body").get(0);
            
            Element main =  doc.select("div#main").get(0);
            Element inner =  doc.select("div.inner").get(0);
            Element cont =  doc.select("div#content").get(0);
            Element section =  doc.select("div.articles").get(0);
            Element content = section.select("article").get(0);
            Element article = content.select("div.bd").get(0);
            Element blog = content.select("div.entry-content").get(0);

            article.empty();
            article.append(blog.outerHtml());
            content.empty();
            content.append(article.outerHtml());
            section.empty();
            section.append(content.outerHtml());
            cont.empty();
            cont.append(section.outerHtml());
            inner.empty();
            inner.append(cont.outerHtml());
            main.empty();
            main.append(inner.outerHtml());
            main.attr("style", "width:660px;");
            body.empty();
            body.append(main.outerHtml());
            body.attr("style", "background:white;");
            
            Elements links = doc.select("head").get(0).select("link[type=text/css]");
            Element css = links.get(0);
            String href = css.attr("href");
            href = "http://uxc.360.cn" + href;
            css.attr("href", href);
            
            art.setContent(doc.html());
            
            List<String> list = new ArrayList<String>();
            Elements es = blog.select("img");
            if (null != es)
			{
                for (Element e : es)
    			{
                	list.add(e.attr("src"));
    			}
                
                List<String> desList = DwTool.batchSaveFile(list, WebConstant.IMGS_SAVE_PATH);
                String targetPath = ImageTool.resizeImage(desList.get(0), 
                		WebConstant.IMG_WIDTH, WebConstant.IMG_HEIGHT);
                art.setThumbnail(targetPath);
			}
		} 
    	catch (Exception e)
		{
    		logger.error("Jsoup抓取网页过程中出错：", e);
		}
    }
}
