package com.yelloco.sampletask.pizza_activity.controller;

import android.util.Log;

import com.yelloco.sampletask.pizza_activity.model.Pizza;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Aya on 5/27/2019.
 */

public class XmlParser {

    ArrayList<Pizza>pizzas= new ArrayList<>();

    public ArrayList<Pizza> parseXml(String response) {

        Document doc = getDomElement(response);
        NodeList nl = doc.getElementsByTagName("item");

        pizzas.clear();

        // looping through all item nodes <item>
        for (int i = 0; i < nl.getLength(); i++)
        {
            Element e = (Element) nl.item(i);
            String name = getValue(e, "name");
            String cost = getValue(e, "cost");
            String description = getValue(e, "description");
            String id = getValue(e, "id");

            pizzas.add(new Pizza(Integer.parseInt(id),name, description, cost));
            Log.i("nodes", "node" + i + name + " " + cost + " " + description);

        }

        return pizzas;
    }

    public Document getDomElement(String xml){
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            doc = db.parse(is);

        } catch (ParserConfigurationException e) {
            return null;
        } catch (SAXException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return doc;
    }

    public String getValue(Element item, String str) {
        NodeList node = item.getElementsByTagName(str);
        return this.getElementValue(node.item(0));
    }

    public final String getElementValue( Node element ) {
        Node child;
        if( element != null){
            if (element.hasChildNodes()){
                for( child = element.getFirstChild(); child != null; child = child.getNextSibling() ){
                    if( child.getNodeType() == Node.TEXT_NODE  ){
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }
}
