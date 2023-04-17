package src;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ImportasJson implements ImporterInterface{

    iPersonalCollection collection;

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
                String publisher = (String) jsonObject.get("publisher");
                String seriesTitle = (String) jsonObject.get("seriestitle");
                String storyTitle = (String) jsonObject.get("storytitle");
                int volumeNumber = Math.toIntExact((long) jsonObject.get("volumenumber"));
                String issueNumber = (String) jsonObject.get("issuenumber");
                String publicationDate = (String) jsonObject.get("publicationdate");
                String creators = (String) jsonObject.get("creators");
                creators = creators.replaceAll(" \\|", ",");
                // System.out.println(creators);
                String description = (String) jsonObject.get("description");
                String value = String.valueOf(jsonObject.get("value"));
                

                collection.addComicManually(publisher, seriesTitle, storyTitle, volumeNumber, issueNumber, publicationDate, creators, description, value);
            }

        } 
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    
}
