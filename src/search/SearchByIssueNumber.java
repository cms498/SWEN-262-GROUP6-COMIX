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
 * Searches comics comparing the search term with the comic's issue number
 */
public class SearchByIssueNumber implements CollectionSearcher{

    private boolean exactMatch;

    public SearchByIssueNumber(boolean exactMatch) {
        this.exactMatch = exactMatch;
    }

    /** 
     * Searches through the list of given comics for any matches with a comic's
     * issue number and the given search term
     * @param comics
     * @param searchTerm
     * @return List<Comic>
     */
    @Override
    public List<Comic> search(List<Comic> comics, String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        List<Comic> searchComics = new ArrayList<>();
        for(Comic comic: comics) {
            if(exactMatch) {
                if(comic.getIssueNumber().toLowerCase().equals(searchTerm)) {
                    searchComics.add(comic);
                }
            } else {
                if(comic.getIssueNumber().toLowerCase().contains(searchTerm)) {
                    searchComics.add(comic);
                }
            }
        }
        return searchComics;
    }

    /** 
     * Searches the database for any comics having an issue number with
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
                        if(searchTerm.equals(split[1].toLowerCase())) {
                            searchComics.add(generateComic(split));
                        }
                    } else {
                        if(split[1].toLowerCase().contains(searchTerm)) {
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
