package src;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ImportasJson implements ImporterInterface{

    private iPersonalCollection collection;

    public ImportasJson(iPersonalCollection personalCollection) {
        this.collection = personalCollection;
        this.collection.setFlag(true);
    }

    @Override
    public void Import(String filename) throws IOException {
        JSONParser parser = new JSONParser();
        try {
            File file = new File(filename);
            FileReader reader = new FileReader(file);
            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    Publisher publisher = new Publisher((String) jsonObject.get("publisher"));
                    String seriesTitle = (String) jsonObject.get("seriestitle");
                    String storyTitle = (String) jsonObject.get("storytitle");
                    long volumeNumber = (Long) jsonObject.get("volumenumber");
                    String issueNumber = (String) jsonObject.get("issuenumber");
                    String publicationDate = (String) jsonObject.get("publicationdate");
                    String creators = (String) jsonObject.get("creators");
                    String[] creatorsArr = creators.strip().split(",");
                    List<Creator> creatorsList = new ArrayList<>();
                    for (String creator : creatorsArr) {
                        creatorsList.add(new Creator(creator));
                    }
                    String description = (String) jsonObject.get("description");
                    double value = (Double) jsonObject.get("value");
                    Boolean isGraded = (Boolean) jsonObject.get("isGraded");
                    Boolean isSlabbed = (Boolean) jsonObject.get("isSlabbed");
                    String signatures = (String) jsonObject.get("signatures");
                    String[] signaturesArr = signatures.strip().split(",");
                    List<String> signatureList = Arrays.asList(signaturesArr);
                    ArrayList<String> arrAigList = new ArrayList<>(signatureList);
                    Boolean authenticated = (Boolean) jsonObject.get("authenticated");
                    long gradeNumber = (Long) jsonObject.get("gradenumber");
            
                    this.collection.addComicAllFields(publisher, seriesTitle, storyTitle, (int) volumeNumber, issueNumber, publicationDate, creatorsList, description, value, isGraded, isSlabbed, arrAigList, authenticated, (int) gradeNumber);
            }

        } 
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}