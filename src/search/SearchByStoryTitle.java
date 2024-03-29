package src.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import src.Comic;

/*
 * A implementation of Collection Searcher
 * Searches comics by comparing the story title and the search term
 */
public class SearchByStoryTitle implements CollectionSearcher {

    private boolean exactMatch;

    public SearchByStoryTitle(boolean exactMatch) {
        this.exactMatch = exactMatch;
    }

    /** 
     * Searches through the list of given comics for any matches with a comic's
     * story title and the given search term
     * @param comics
     * @param searchTerm
     * @return List<Comic>
     */
    @Override
    public List<Comic> search(List<Comic> comics, String searchTerm) {
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    /** 
     * Searches the database for any comics having a story title with
     * the matching search term
     * @param filename
     * @param searchTerm
     * @return List<Comic>
     */
    @Override
    public List<Comic> databaseSearch(String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        List<Comic> searchComics = new ArrayList<>();
        File file = new File(COMIC_DATABASE);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine(); //skipping a line
            while((line = br.readLine()) != null) {
                String[] split = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                    if(exactMatch) {
                        if(searchTerm.equals(split[2].replace("\"", "").toLowerCase())) {
                            searchComics.add(generateComic(split));
                        }
                    } else {
                        if(split[2].replace("\"", "").toLowerCase().contains(searchTerm)) {
                           searchComics.add(generateComic(split));
                        }
                    }
                }
                br.close();
        } catch (IOException e){
            System.out.println("Invalid filename.");
        }
        return searchComics;
    }

    @Override
    public void setExactMatch(boolean exact) {
        this.exactMatch = exact;
    }

}