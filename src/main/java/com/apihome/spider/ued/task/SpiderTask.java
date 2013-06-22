package com.apihome.spider.ued.task;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.select.Elements;

import com.apihome.dao.ued.JadeDAO;
import com.apihome.model.ued.Article;
import com.apihome.model.ued.ArticleTag;
import com.apihome.spider.ued.model.SpiderRule;

/**
 * @ClassName: SpiderTask 
 * @Description: TODO(爬虫任务接口) 
 * @author david.wang 
 * @date 2013-1-2 下午9:14:23 
 * @version 1.0
 */
public abstract class SpiderTask
{
    public abstract void startup(SpiderRule rule, JadeDAO dao);

    public abstract List<Article> parseListView(Elements elements);

    public abstract void parseDetailView(Article article);
    
    /**
     * @Title: saveArticles 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param arts    设定文件 
     * @return void    返回类型 
     * @throws
     */
    public void saveArticles(List<Article> arts, JadeDAO dao)
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
            dao.saveArticles(articles);
            if (tags.size() > 0)
            {
                dao.saveArticleTags(tags);
            }
        }
    }
}
