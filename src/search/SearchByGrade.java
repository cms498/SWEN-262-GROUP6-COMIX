package src.search;

import java.util.ArrayList;
import java.util.List;

import src.Comic;

public class SearchByGrade implements CollectionSearcher {

    private boolean exactMatch;

    public SearchByGrade(boolean exactMatch) {
        this.exactMatch = exactMatch;
    }

    @Override
    public List<Comic> search(List<Comic> comics, String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        List<Comic> searchComics = new ArrayList<>();
        if (this.exactMatch) {
            for (Comic comic : comics) {
                if (searchTerm.equals(Boolean.toString(comic.getIsGraded()))) {
                    searchComics.add(comic);
                }
            }
        } else {
            for (Comic comic : comics) {
                if (Boolean.toString(comic.getIsGraded()).toLowerCase().contains(searchTerm)) {
                    searchComics.add(comic);
                }
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