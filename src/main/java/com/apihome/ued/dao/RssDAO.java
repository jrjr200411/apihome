package com.apihome.ued.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.apihome.ued.po.LinkPO;
import com.apihome.ued.po.RssPO;

@DAO
public interface RssDAO 
{
    @SQL("insert ignore into t_ued_article(url, encode_url, surl, encode_surl, title, author, pub_time, kind, summary, content, create_time, thumbnail) values" +
            " (:1.url, :1.encodeUrl, :1.surl, :1.encodeSurl, :1.title, :1.author, :1.pubTime, :1.kind, :1.summary, :1.content, :1.createTime, :1.thumbnail)")
	public void saveArticle(List<RssPO> pos);

	@SQL("select rss from t_ued_rss where is_deleted = 0")
	public List<String> queryRssWebsites();
	
    @SQL("insert ignore into t_ued_rss_link(rss, website, is_deleted, is_all, create_time) values" +
    " (:1.rss, :1.website, :1.isDeleted, :1.isAll, :1.createTime)")
    public void saveLinks(List<LinkPO> pos);
}
