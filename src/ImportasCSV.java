package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportasCSV implements ImporterInterface {
    PersonalCollection collection;

    public ImportasCSV() {
        this.collection = new PersonalCollection();
        this.collection.initializeComics();
    }

    /**
     * 
     * 
     *     public Comic(Publisher publisher, String seriesTitle, String storyTitle, int volumeNumber,
            String issueNumber, String publicationDate, List<Creator> creators,
            String description, double value, boolean isGraded, boolean isSlabbed, 
            List<String> signatures, boolean authenticated)
     */

    @Override
    public void Import(String filename) throws IOException {
        File file = new File(filename);
       BufferedReader reader = new BufferedReader(new FileReader(file));
       reader.readLine();
        String line = reader.readLine();
        while (line != null) {

            String[] values = line.split(",");
            String publisher = values[0];
            Publisher newPublisher = new Publisher(publisher);
            
            String seriesTitle = values[1];
            String storyTitle = values[2];
            String volumeNumber = values[3];
            String value = values[4];
            String creators = values[5];
            String description = values[6];
            String issueNumber = values[7];
            String isGraded = values[8];
            String isSlabbed = values[9];

           
            line = reader.readLine();
        }
       
    }

    public static void main(String[] args) throws IOException {
        ImportasCSV importCSV = new ImportasCSV();
        importCSV.Import("data/personalCollection.csv");
    }

}
