package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVAdapter implements ExporterInterface {

    PersonalCollection collection;

    public CSVAdapter() {
        this.collection = new PersonalCollection();
        this.collection.initializeComics();
    }

    @Override
    public void export() {
       List<Comic> comics = collection.getComics();

         try {
              File file = new File("data/personalCollection.csv");
              FileWriter writer = new FileWriter(file);
              //        return publisher + "," +seriesTitle + "," +storyTitle + "," +volumeNumber + "," +value + "," +creators + "," +description + "," +issueNumber + "," + isGraded + "," +isSlabbed;
                writer.write("Publisher, Series Title, Story Title, Volume Number, Value, Creators, Description, Issue Number, Is Graded, Is Slabbed \n");
                for (Comic comic : comics) {
                    String fixer = "";
                    if (comic.getPublisher().toString().contains(",")) {
                        comic.setPublisher("\"" + comic.getPublisher() + "\"");
                    }
                    if (comic.getSeriesTitle().contains(",")) {
                        comic.setSeriesTitle("\"" + comic.getSeriesTitle() + "\"");
                    }
                    if (comic.getStoryTitle().contains(",")) {
                        comic.setStoryTitle("\"" + comic.getStoryTitle() + "\"");
                    }
                    if (comic.getDescription().contains(",")) {
                        comic.setDescription("\"" + comic.getDescription() + "\"");
                    }
                    
                    if(comic.getCreators().toString().contains(",")) {
                        fixer = comic.getCreators().toString().replace(", ", " | ");
                    } else {
                        fixer = comic.getCreators().toString();
                    }

                    writer.write(comic.getPublisher() + "," + comic.getSeriesTitle() + "," + comic.getStoryTitle() + "," + comic.getVolumeNumber() + "," + comic.getValue() + "," + fixer + "," + comic.getDescription() + "," + comic.getIssueNumber() + "," + comic.getIsGraded() + "," + comic.getIsSlabbed() + "\n");
                }
              writer.close();
         } catch (IOException e) {
              e.printStackTrace();
         }
    }   
}