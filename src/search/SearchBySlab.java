package src.search;

import java.util.ArrayList;
import java.util.List;

import src.Comic;

/*
 * A implementation of Collection Searcher
 * Searches comics by seeing if they're slabbed
 */
public class SearchBySlab implements CollectionSearcher {

    private boolean exactMatch;

    public SearchBySlab(boolean exactMatch){
        this.exactMatch = exactMatch;
    }

    /** 
     * Searches through the list of given comics for any comics that are slabbed
     * @param comics
     * @param searchTerm
     * @return List<Comic>
     */
    @Override
    public List<Comic> search(List<Comic> comics, String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        List<Comic> searchComics = new ArrayList<>();
        if (this.exactMatch) {
            for (Comic comic : comics) {
                if (searchTerm.equals(Boolean.toString(comic.getIsSlabbed()))) {
                    searchComics.add(comic);
                }
            }
        } else {
            for (Comic comic : comics) {
                if (Boolean.toString(comic.getIsSlabbed()).toLowerCase().contains(searchTerm)) {
                    searchComics.add(comic);
                }
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
        this.exactMatch = exact;
    }

}