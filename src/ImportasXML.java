package src;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

public class ImportasXML implements ImporterInterface{

    private iPersonalCollection collection;

    public ImportasXML(iPersonalCollection personalCollection) {
        this.collection = personalCollection;
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

                        Publisher publisher = new Publisher(element.getElementsByTagName("publisher").item(0).getTextContent());
                        String seriesTitle = element.getElementsByTagName("seriesTitle").item(0).getTextContent();
                        String storyTitle = element.getElementsByTagName("storyTitle").item(0).getTextContent();
                        int volumeNumber = Integer.parseInt(element.getElementsByTagName("volumeNumber").item(0).getTextContent());
                        String issueNumber = element.getElementsByTagName("issueNumber").item(0).getTextContent();
                        String description = element.getElementsByTagName("description").item(0).getTextContent();
                        double value = Double.parseDouble(element.getElementsByTagName("value").item(0).getTextContent());
                        boolean isGradded = Boolean.parseBoolean(element.getElementsByTagName("isGraded").item(0).getTextContent());
                        boolean isSlabbed = Boolean.parseBoolean(element.getElementsByTagName("isSlabbed").item(0).getTextContent());
                        boolean isAuthenticated = Boolean.parseBoolean(element.getElementsByTagName("isAuthenticated").item(0).getTextContent());
                        int gradeNumber = Integer.parseInt(element.getElementsByTagName("gradeNumber").item(0).getTextContent());
                        String publicationDate = element.getElementsByTagName("publicationDate").item(0).getTextContent();
                        String creatorString = element.getElementsByTagName("creators").item(0).getTextContent();
                        String[] creatorsArr = creatorString.strip().split(",");
                        List<Creator> creatorsList = new ArrayList<>();
                        for (String creator : creatorsArr) {
                            creatorsList.add(new Creator(creator));
                        }
                        String signatures = element.getElementsByTagName("signatures").item(0).getTextContent();
                        String[] signaturesArr = signatures.strip().split(",");
                        ArrayList<String> signatureList = new ArrayList<>();
                        for(String sign : signaturesArr){
                            signatureList.add(sign);
                        }

                        collection.addComicAllFields(publisher, seriesTitle, storyTitle, volumeNumber, issueNumber, publicationDate, creatorsList, description, value, isGradded, isSlabbed, signatureList, isAuthenticated, gradeNumber);
                    }
                }
            } catch (SAXException e) {
            }
        } catch (ParserConfigurationException e) {
            
        }
    }
}