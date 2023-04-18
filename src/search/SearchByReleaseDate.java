package src.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import src.Comic;

/*
 * A implementation of Collection Searcher
 * Searches comics by comparing the release date and the search term
 */
public class SearchByReleaseDate implements CollectionSearcher{

    private boolean exactMatch;

    public SearchByReleaseDate(boolean exactMatch) {
        this.exactMatch = exactMatch;
    }

    /** 
     * Searches through the list of given comics for any matches with a comic's
     * release date and the given search term
     * @param comics
     * @param searchTerm
     * @return List<Comic>
     * 
     * This method is a little more complicated than the others, because the release date is a string and not a number or date object like the other fields are.
     * This method is also a little wrong, fix the bug! the searchComics list is not being populated correctly and it is empty when it is returned.
     */
    @Override
    public List<Comic> search(List<Comic> comics, String searchTerm) {
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    private String getDateFromObject(Object obj) {

        if (obj instanceof Date) {
            // If the object is already a date, simply format it to "yyyy-MM-dd" format
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format((Date) obj);
        } else if (obj instanceof String) {
            // If the object is a string, parse the date based on its format
            String dateString = (String) obj;
            //"Apr 1994"
            DateFormat dateFormat = null;

            if(dateString.length() != 0){

            String[] splitted = dateString.split(" ");

            if(splitted.length == 3) {
                dateFormat = new SimpleDateFormat("MMM dd, yyyy");
            } else if(splitted.length == 2) {
                dateFormat = new SimpleDateFormat("MMM yyyy");
            } else if(splitted.length == 1) {
                dateFormat = new SimpleDateFormat("yyyy");
            }
            try {
                //remove the quotes 
                dateString = dateString.replaceAll("\"", "");
               return dateFormat.format(dateFormat.parse(dateString)).toLowerCase();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        }
        // Return null if the object is not a date or a string with a valid date format
        return "";
    }

    /** 
     * Searches the database for any comics having a release date with
     * the matching search term
     * @param filename
     * @param searchTerm
     * @return List<Comic>
     */
    @Override
    public List<Comic> databaseSearch(String searchTerm) {
       searchTerm = searchTerm.toLowerCase();
         List<Comic> searchComics = new ArrayList<Comic>();
            try {
                File file = new File("data/comics.csv");
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    line = br.readLine(); // Skip the first line
                    String[] split = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                    // System.out.println(getDateFromObject(split[5]) + "___" + searchTerm);

                    if (exactMatch) {
                        if (searchTerm.equals(getDateFromObject(split[5]))) {
                            searchComics.add(generateComic(split));
                        }
                    } else {
                        if (getDateFromObject(split[5]).contains(searchTerm)) {
                            searchComics.add(generateComic(split));
                        }
                    }

                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        // System.out.println(searchComics);
        return searchComics;
    }
    
    @Override
    public void setExactMatch(boolean exact) {
        this.exactMatch = exact;
    }    
}
