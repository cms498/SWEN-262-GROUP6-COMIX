package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ImportasCSV implements ImporterInterface {
    iPersonalCollection collection;

    public ImportasCSV(iPersonalCollection personalCollection) {
        this.collection = personalCollection;
        this.collection.setFlag(true);
        // this.collection.initializeComics();
    }

    @Override
    public void Import(String filename) throws IOException{
        


        File file = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        reader.readLine();
        reader.readLine();
        reader.readLine();
        String line = reader.readLine();
        while (line != null) {
            

            String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

            String seriesTitle = values[0].toString().replaceAll("\"", "");
            String issueNumber = values[1].toString().replaceAll("\"", "");
            String title = values[2].toString().replaceAll("\"", "");
            String description = values[3].toString().replaceAll("\"", "");
            String publisher = values[4].toString().replaceAll("\"", "");
            String releaseDate= values[5].toString().replaceAll("\"", "");
            if(values.length==8){
                collection.addComicManually(publisher, seriesTitle, title, 0, issueNumber, releaseDate, "", description, "0");
                
            }
            else{
                String value = values[8].replaceAll("\"", "");
                String creatorSplit = value.replace(" \\| ", ",");
                collection.addComicManually(publisher, seriesTitle, title, 0, issueNumber, releaseDate, creatorSplit, description, "0");
                
            }
   
            line = reader.readLine(); 

        }
        reader.close();
    }

}