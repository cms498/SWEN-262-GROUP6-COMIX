package src.search;

import java.util.ArrayList;
import java.util.List;

import src.Comic;

public class SearchBySignedComics implements CollectionSearcher{

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
        return new ArrayList<Comic>();
    }

    @Override
    public void setExactMatch(boolean exact) {
    }
}