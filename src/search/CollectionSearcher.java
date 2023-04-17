package src.search;
import java.util.ArrayList;
import java.util.List;

import src.Comic;
import src.Creator;
import src.Publisher;

/*
 * CollectionSearcher interface that acts as the Strategy interface
 * for all future implementations of search
 */
public interface CollectionSearcher {

    /*
     * Local location of the comic database
     */
    public static final String COMIC_DATABASE = "data/comics.csv";

    public List<Comic> search(List<Comic> comics, String searchTerm);
    public List<Comic> databaseSearch(String searchTerm);

    /*
     * Sets the field of exact match if applicable in their search
     */
    public void setExactMatch(boolean exact);

    /*
     * Helper method that will generate a method from the COMIC_DATABASE csv
     * by taking in a line from the csv and inputting all the necessary data
     * into making a comic book
     */
    public default Comic generateComic(String[] comicData) {
        if(comicData.length == 9) {
            String[] creatorSplit = comicData[8].split(" \\| ");
            List<Creator> creators = new ArrayList<>();
            for(int i = 0; i < creatorSplit.length; i++) {
                String creatorName = creatorSplit[i].replace("\"", "");
                creators.add(new Creator(creatorName));
            }
            Publisher publisher = new Publisher(comicData[4].replace("\"", ""));
            int volumeNumber = 0;
            String[] seriesAndVolume = comicData[0].replace("\"", "").split(", Vol. ");
            if(seriesAndVolume.length > 1) {
                volumeNumber = Integer.parseInt(seriesAndVolume[1].charAt(0) + "");
            }
            return new Comic(publisher, seriesAndVolume[0], comicData[2].replace("\"", ""), volumeNumber, comicData[1].replace("\"", ""), 
                comicData[5].replace("\"", ""), creators, comicData[3].replace("\"", ""), 0, false, false, new ArrayList<>(), false, 0);
        } else {
            return null;
        }
    }
}