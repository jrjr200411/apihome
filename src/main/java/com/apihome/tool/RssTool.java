package com.apihome.tool;

import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RssTool
{
	private static RssTool instance = null;

	static
	{
		if (instance == null)
			instance = new RssTool();
	}

	public static void writeNews(String url)
	{
		try
		{
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			URL u = new URL(url); 
			Document doc = builder.parse(u.openStream());
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++)
			{
				Element element = (Element) nodes.item(i);
				System.out.println("Title: " + getElementValue(element, "title"));
				System.out.println("Link: " + getElementValue(element, "link"));
				System.out.println("Publish Date: " + getElementValue(element, "pubDate"));
				System.out.println("Author: " + getElementValue(element, "dc:creator"));
				System.out.println("Description: " + getElementValue(element, "description"));
				System.out.println();
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	private static String getCharacterDataFromElement(Element e)
	{
		try
		{
			Node child = e.getFirstChild();
			if (child instanceof CharacterData)
			{
				CharacterData cd = (CharacterData) child;
				return cd.getData();
			}
		}
		catch (Exception ex)
		{

		}
		return "";
	} 

	protected float getFloat(String value)
	{
		if (value != null && !value.equals(""))
			return Float.parseFloat(value);
		else
			return 0;
	}

	protected static String getElementValue(Element parent, String label)
	{
		return getCharacterDataFromElement((Element) parent.getElementsByTagName(label).item(0));
	}

	public static void main(String[] args)
	{
		String url = "http://feed.feedsky.com/uedc";
		writeNews(url);
	}
}