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
 * Searches comics by comparing titles and the search term
 */
public class SearchByTitle implements CollectionSearcher{

    private boolean exactMatch;

    public SearchByTitle(boolean exactMatch) {
        this.exactMatch = exactMatch;
    }
    
    
    /** 
     * Searches through the list of given comics for any matches with a comic's
     * title and the given search term
     * @param comics
     * @param searchTerm
     * @return List<Comic>
     */
    @Override
    public List<Comic> search(List<Comic> comics, String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        List<Comic> searchComics = new ArrayList<>();
        if(this.exactMatch) {
            for(Comic comic : comics) {
                if(searchTerm.equals(comic.getStoryTitle().toLowerCase())) {
                    searchComics.add(comic);
                }
            }
        } else {
            for(Comic comic : comics) {
                if(comic.getStoryTitle().toLowerCase().contains(searchTerm)) {
                    searchComics.add(comic);
                }
            }
        }
        return searchComics;
    }

    /** 
     * Searches the database for any comics having a description with
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
                        if(searchTerm.equals(split[2].toLowerCase())) {
                            searchComics.add(generateComic(split));
                        }
                    } else {
                        if(split[2].toLowerCase().contains(searchTerm)) {
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
    
}