package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for importing a csv file, creating a comic
 * object and adding it to the personal collection
 */
public class ImportasCSV implements ImporterInterface {
    private iPersonalCollection collection;

    public ImportasCSV(iPersonalCollection personalCollection) {
        this.collection = personalCollection;
        this.collection.setFlag(true);
    }

    @Override
    public void Import(String filename) throws IOException{
        File file = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        reader.readLine();
        reader.readLine();
        //reader.readLine();
        String line = reader.readLine();
        while (line != null) {
            

            String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

            String seriesTitle = values[1].toString().replaceAll("\"", "");
            String issueNumber = values[7].toString().replaceAll("\"", "");
            String title = values[2].toString().replaceAll("\"", "");
            String description = values[6].toString().replaceAll("\"", "");
            String publisher = values[0].toString().replaceAll("\"", "");
            if(values.length==8){
                String releaseDate= values[5].toString().replaceAll("\"", "");
                collection.addComicManually(publisher, seriesTitle, title, 0, issueNumber, releaseDate, "", description, "0");
            }
            else{
                String releaseDate= values[13].toString().replaceAll("\"", "");
                Publisher p = new Publisher(publisher);
                String valueCreator = values[5].replaceAll("\"", "");
                valueCreator = valueCreator.substring(1, valueCreator.length() - 1);
                String creatorSplit = valueCreator.replace(" | ", ",");
                String[] creatorArrayString = creatorSplit.split(",");
                List<Creator> creators = new ArrayList<>();
                for(int i = 0; i < creatorArrayString.length; i++){
                    Creator c = new Creator(creatorArrayString[i]);
                    creators.add(c);
                }
                String comicValue = values[4].replaceAll("\"", "");
                String volumeNumber = values[3].replaceAll("\"", "");
                String isGraded = values[8].replaceAll("\"", "");
                String isSlabbed = values[9].replaceAll("\"", "");
                String sign = values[10].replaceAll("\"", "");
                ArrayList<String> signatures = new ArrayList<>();
                String[] signString = sign.split(",");
                for(int i = 0; i < signString.length; i++){
                    signatures.add(signString[i]);
                }
                String authenticated = values[11].replaceAll("\"", "");
                String gradeNumber = values[12].replaceAll("\"", "");
                collection.addComicAllFields(p, seriesTitle, title, Integer.parseInt(volumeNumber), issueNumber, releaseDate, creators, description, Double.parseDouble(comicValue), Boolean.parseBoolean(isGraded), Boolean.parseBoolean(isSlabbed), signatures, Boolean.parseBoolean(authenticated), Integer.parseInt(gradeNumber));
             }
   
            line = reader.readLine(); 

        }
        reader.close();
    }
}