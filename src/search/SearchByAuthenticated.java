package src.search;

import java.util.ArrayList;
import java.util.List;

import src.Comic;

public class SearchByAuthenticated implements CollectionSearcher{

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

    @Override
    public List<Comic> databaseSearch(String searchTerm) {
        throw new UnsupportedOperationException("Unimplemented method 'databaseSearch'");
    }

    @Override
    public void setExactMatch(boolean exact) {
    }
}
