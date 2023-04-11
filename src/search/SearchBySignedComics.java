package src.search;

import java.util.ArrayList;
import java.util.List;

import src.Comic;

public class SearchBySignedComics implements CollectionSearcher{
    private boolean exactMatch;

    public SearchBySignedComics(boolean exactMatch) {
        this.exactMatch = exactMatch;
    }

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

    @Override
    public List<Comic> databaseSearch(String searchTerm) {
        throw new UnsupportedOperationException("Unimplemented method 'databaseSearch'");
    }

    @Override
    public void setExactMatch(boolean exact) {
        this.exactMatch = exact;
    }
}