package com.xframework.web.demo;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 完美解析一个ued页面
 * @author root
 *
 */
public class JsoupDemo
{

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException
    {

        uxcTest();
    }

    public static void mxdTest() throws IOException
    {
        // TODO Auto-generated method stub
        Document doc = Jsoup.connect("http://mxd.tencent.com/2013/04/%E4%B9%9D%E5%AE%AB%E6%A0%BC%E4%B8%89%E6%9C%9F.html").get();
        Elements elements = doc.select("section");
        Element body = doc.select("body").get(0);
        Element section = elements.get(0);
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
        
        System.err.println(doc.html());
    }
    
    public static void cdcTest() throws IOException
    {
        Document doc = Jsoup.connect("http://cdc.tencent.com/?p=7406").get();
        Element body = doc.select("body").get(0);
        Element section =  doc.select("div#container").get(0);
        Element content = section.select("div#content").get(0);
        Element article = content.select("div.content_text2").get(0);
        Element blog = content.select("div.content_banner").get(0);
        
        Elements es = blog.children();
        blog.empty();
        blog.append(es.get(0).outerHtml());
        blog.append(es.get(1).outerHtml());
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

        System.err.println(doc.html());
    }
    
    public static void uxcTest() throws IOException
    {
        Document doc = Jsoup.connect("http://uxc.360.cn/archives/887.html").get();
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

        Elements list = doc.select("head").get(0).select("link[type=text/css]");
        Element css = list.get(0);
        String href = css.attr("href");
        href = "http://uxc.360.cn" + href;
        css.attr("href", href);
        
        System.err.println(doc.html());
    }
}
