package com.company;

import com.company.model.HTTPSampler;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.ArrayList;

public class ParserJMX {

    ArrayList<HTTPSampler> httpSamplers = new ArrayList<>();

    public static void main(String[] args) {
        ParserJMX parser = new ParserJMX();
        parser.readXML ();
    }

    private void readXML() {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("Исходник.jmx");

            getDataFromDoc(document);

        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException ex) {
            ex.printStackTrace(System.out);
        }

        CheckNoCollerateValue.findNoCollerateValueInBody(httpSamplers);
        CheckNoCollerateValue.findNoColerateDateInBody(httpSamplers);
    }


    private void getDataFromDoc (Document document) throws DOMException, XPathExpressionException {

        NodeList nodeListSamplerProxy = document.getElementsByTagName("HTTPSamplerProxy");
        Node nodeSamplerProxy;

        for (int id = 0; true; id++) {
            nodeSamplerProxy = nodeListSamplerProxy.item(id);

            if (nodeSamplerProxy == null) {
                break;
            }

            Node nodeTestName = nodeSamplerProxy.getAttributes().getNamedItem("testname");

            Node nodeBody = findBody(nodeSamplerProxy);
            HTTPSampler HttpSampler = new HTTPSampler(id, nodeTestName.getTextContent(), nodeBody.getTextContent());
            httpSamplers.add(HttpSampler);
        }
    }


    private Node findBody(Node rootNode) {

        Element rootElement = (Element) rootNode;
        NodeList nodeListStringProp = rootElement.getElementsByTagName("stringProp");

        return nodeListStringProp.item(0); // 10.250.217.200 или {"username":"${login}","password":"${password}"}
    }
}



