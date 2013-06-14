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
        
        // TODO Auto-generated method stub
        Document doc1 = Jsoup.connect("http://www.baidu.com/").get();
    }

}
