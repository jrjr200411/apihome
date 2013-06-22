package com.apihome.dao.ued;

import java.util.List;

import com.apihome.model.ued.Article;
import com.apihome.model.ued.ArticleTag;

public interface JadeDAO
{
    public void saveArticles(List<Article> arts);
    
    public void saveArticleTags(List<ArticleTag> tags);
}
