package src;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

public class ImportasXML implements ImporterInterface{

    iPersonalCollection collection;

    public ImportasXML(iPersonalCollection personalCollection) {
        this.collection = personalCollection;
        // this.collection.initializeComics();
    }



    @Override
    public void Import(String filename) throws IOException {
        File file = new File(filename);
        DocumentBuilderFactory documentBuilder = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = documentBuilder.newDocumentBuilder();
            try {
                Document doc = db.parse(file);
                doc.getDocumentElement().normalize();
                // System.out.println(doc.getDocumentElement().getNodeName());
                NodeList nodelist = doc.getElementsByTagName("comic");
                for(int i=0; i<nodelist.getLength();i++){
                    Node node = nodelist.item(i);
                    if(node.getNodeType() == node.ELEMENT_NODE){
                        Element element = (Element)node;
                        String seriesTitle = element.getElementsByTagName("seriesTitle").item(0).getTextContent();
                        // System.out.println(element.getElementsByTagName("publisher").item(0).getTextContent());
                        String publisher = element.getElementsByTagName("publisher").item(0).getTextContent();
                        String storyTitle = element.getElementsByTagName("storyTitle").item(0).getTextContent();
                        int volumeNumber = Integer.parseInt(element.getElementsByTagName("volumeNumber").item(0).getTextContent());
                        String issueNumber = element.getElementsByTagName("issueNumber").item(0).getTextContent();
                        String creators = element.getElementsByTagName("creators").item(0).getTextContent();
                        creators = creators.substring(1, creators.length() - 1);
                        String description = element.getElementsByTagName("description").item(0).getTextContent();
                        String value = element.getElementsByTagName("value").item(0).getTextContent();


                        collection.addComicManually(publisher, seriesTitle, storyTitle, volumeNumber, issueNumber, "", creators, description, value);

                    }

                }
            } catch (SAXException e) {
            }
        } catch (ParserConfigurationException e) {
        }
        
            
    }

    
}
