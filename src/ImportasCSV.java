package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import src.Comic;

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
     * @throws IOException
     */

    @Override
    public void Import(String filename) throws IOException{
        


        File file = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        reader.readLine();
        String line = reader.readLine();
        while (line != null) {
            

            String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

            String seriesTitle = values[0].toString();
            String issueNumber = values[1].toString();
            String title = values[2].toString();
            String description = values[3].toString();
            String publisher = values[4].toString();
            // Publisher publisher = new Publisher(values[4].toString());
            String releaseDate= values[5].toString();
            if(values.length==8){
                collection.addComicManually(publisher, seriesTitle, title, 0, issueNumber, issueNumber, "", description, releaseDate);
                // Comic comic = new Comic(publisher, seriesTitle, title, 0, issueNumber, releaseDate, null, description, 0, false, false, null, false);
                // collection.addComic(comic);
            }
            else{
                String creatorSplit = values[8].replace(" \\| ", ",");
                // List<Creator> creators = new ArrayList<>();
                // for(String c: creatorSplit){
                //     Creator creator = new Creator(c);
                //     creators.add(creator);
                // }
                collection.addComicManually(publisher, seriesTitle, title, 0, issueNumber, issueNumber, creatorSplit, description, releaseDate);
                // Comic comic = new Comic(publisher, seriesTitle, title, 0, issueNumber, releaseDate, creators, description, 0, false, false, null, false);
                // collection.addComic(comic);
            }
   
            line = reader.readLine(); 

        }
        reader.close();
    }

    public static void main(String[] args) throws IOException {
        ImportasCSV importCSV = new ImportasCSV();
        importCSV.Import("data/comics.csv");
    }

}