package src.search;
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

    @Override
    public List<Comic> databaseSearch(String filename, String searchTerm) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'databaseSearch'");
    }
    
}