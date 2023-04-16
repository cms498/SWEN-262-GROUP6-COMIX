package src.search;

import java.util.ArrayList;
import java.util.List;

import src.Comic;

/*
 * A implementation of Collection Searcher
 * Searches comics by seeing if they're signed
 */
public class SearchBySignedComics implements CollectionSearcher{

    /** 
     * Searches through the list of given comics for any comics that are signed
     * @param comics
     * @param searchTerm
     * @return List<Comic>
     */
    @Override
    public List<Comic> search(List<Comic> comics, String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        List<Comic> searchComics = new ArrayList<>();
        
        for (Comic comic : comics) {
            if (comic.getSignatures().get(0) != "") {
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
        return new ArrayList<Comic>();
    }

    @Override
    public void setExactMatch(boolean exact) {
    }
}