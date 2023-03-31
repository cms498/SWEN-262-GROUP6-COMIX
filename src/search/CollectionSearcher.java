package src.search;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            Publisher publisher = new Publisher(comicData[4]);
            int volumeNumber = 0;
            Pattern pattern = Pattern.compile ("\\d+");
            Matcher matcher = pattern.matcher(comicData[0]);
            if(matcher.find()) {
                volumeNumber = Integer.parseInt(matcher.group());
            }
            return new Comic(publisher, comicData[0].replace("\"", ""), comicData[2].replace("\"", ""), volumeNumber, comicData[1], comicData[5], creators, comicData[3], 0, false, false);
        } else {
            System.out.println("Faulty line String[] given.");
            return null;
        }
    }
}