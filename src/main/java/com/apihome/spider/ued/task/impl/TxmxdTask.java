package com.apihome.spider.ued.task.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.apihome.dao.ued.JadeDAO;
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

@HashUrl(md5="http://mxd.tencent.com/p/") //http://mxd.tencent.com/p/
public class TxmxdTask extends SpiderTask
{
    private static Logger logger = Logger.getLogger(TxmxdTask.class);

    @Override
    public void startup(SpiderRule rule, JadeDAO dao)
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
		        this.saveArticles(arts, dao);
			} 
            catch (IOException e)
			{
            	logger.error("根据url="+urlStr+"获取网页信息出错：",e);
			} 

		}
    }

	@Override
    public List<Article> parseListView(Elements elements)
    {
    	List<Article> list = new ArrayList<Article>();
    	
    	for (Element element : elements)
		{
    		Element headElement = element.select("h2.blog-title > a").get(0);
    		String title = headElement.text();
    		String url = headElement.attr("href");
       		Element infoElement = element.select("div.author-info").get(0);
       		String info = infoElement.text();
       		String[] arr = info.split("\\|");
       		Element summaryElement = element.select("div.blog-content").get(0);
       		String summary = summaryElement.lastElementSibling().text();
       		
			List<ArticleTag> tagList = new ArrayList<ArticleTag>();
       		if (arr.length > 2)
			{
				String tagStr = arr[2];
				String[] tagArr = tagStr.split(",");
				for (int i = 0; i < tagArr.length; i++)
				{
					ArticleTag articleTag = new ArticleTag();
					articleTag.setTag(StringUtils.trim(tagArr[i]));
					articleTag.setEncodeSurl(StringTool.hashString(url));
					tagList.add(articleTag);
				}
			}

       		Article article = new Article();
       		article.setSurl(url);
       		article.setEncodeSurl(StringTool.hashString(url));
       		article.setTitle(title);
       		article.setAuthor(StringUtils.trim(arr[0]));
       		article.setCreateTime(DateTool.convert(StringUtils.trim(arr[1]), "yyyy-MM-dd"));
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
            Element section = doc.select("section").get(0);
            Element content = section.select("div.content").get(0);
            Element article = content.select("article").get(0);
            Element blog = content.select("div.blog-content").get(0);
            
            article.empty();
            article.append(blog.outerHtml());
            content.empty();
            content.append(article.outerHtml());
            section.empty();
            section.append(content.outerHtml());
            section.attr("style", "width:660px;");
            body.empty();
            body.append(section.outerHtml());
            body.attr("style", "background:white;");
            
            Elements scripts = doc.select("script");
            for (Element script : scripts)
            {
                script.remove();
            }
            
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
