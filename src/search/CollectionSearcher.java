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

    public static final String COMIC_DATABASE = "data/comics.csv";

    public List<Comic> search(List<Comic> comics, String searchTerm);
    public List<Comic> databaseSearch(String searchTerm);

    /*
     * Helper method that will generate a method from the COMIC_DATABASE csv
     * by taking in a line from the csv and inputting all the necessary data
     * into making a comic book
     */
    public default Comic generateComic(String[] comicData) {
        if(comicData.length == 9) {
            String[] creatorSplit = comicData[8].split(" | ");
            List<Creator> creators = new ArrayList<>();
            for(int i = 0; i < creatorSplit.length; i++) {
                creators.add(new Creator(creatorSplit[i]));
            }
            Publisher publisher = new Publisher(comicData[4]);
            String[] volumeSplit = comicData[0].split(",");
            int volumeNumber;
            if(volumeSplit.length == 1) {
                volumeNumber = 0;
            } else {
                volumeNumber = Integer.parseInt(volumeSplit[1]);
            }
            return new Comic(publisher, comicData[0], comicData[2], volumeNumber, comicData[1], comicData[5], creators, comicData[3], 0);
        } else {
            System.out.println("Faulty line String[] given.");
            return null;
        }
    }
}