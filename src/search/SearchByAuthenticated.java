package src.search;

import java.util.ArrayList;
import java.util.List;

import src.Comic;

/*
 * Searcher that looks for authenticated comics
 */
public class SearchByAuthenticated implements CollectionSearcher{

    
    
    /** 
     * @param comics
     * @param searchTerm
     * @return List<Comic> of authenticated comics in personal collection
     */
    @Override
    public List<Comic> search(List<Comic> comics, String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        List<Comic> searchComics = new ArrayList<>();

        for (Comic comic : comics) {
            if (comic.getIsAuthenticated()) {
                searchComics.add(comic);
            }
        }
        return searchComics;
    }

    /*
     * N/A
     */
    @Override
    public List<Comic> databaseSearch(String searchTerm) {
        throw new UnsupportedOperationException("Unimplemented method 'databaseSearch'");
    }

    @Override
    public void setExactMatch(boolean exact) {
    }
}
