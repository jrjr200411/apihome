package com.apihome.dao.ued;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.apihome.model.ued.Article;
import com.apihome.model.ued.ArticleTag;

@DAO
public interface ArticleDAO extends JadeDAO
{
	@SQL("insert ignore into t_ued_article(surl, encode_surl, title, author, pub_time, kind, status, summary, content, create_time, thumbnail) values" +
			" (:1.surl, :1.encodeSurl, :1.title, :1.author, NOW(), :1.kind, :1.status, :1.summary, :1.content, :1.createTime, :1.thumbnail)")
	public void saveArticle(Article art);
	
	@SQL("insert ignore into t_ued_article(surl, encode_surl, title, author, pub_time, kind, status, summary, content, create_time, thumbnail) values" +
			" (:1.surl, :1.encodeSurl, :1.title, :1.author, NOW(), :1.kind, :1.status, :1.summary, :1.content, :1.createTime, :1.thumbnail)")
	public void saveArticles(List<Article> arts);
	
	@SQL("insert ignore into t_ued_article_tag(encode_surl, tag) values (:1.encodeSurl,  :1.tag)")
	public void saveArticleTag(ArticleTag tag);
	
	@SQL("insert ignore into t_ued_article_tag(encode_surl, tag) values (:1.encodeSurl,  :1.tag)")
	public void saveArticleTags(List<ArticleTag> tags);
}
