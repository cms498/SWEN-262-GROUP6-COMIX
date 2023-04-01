package src.search;

import java.util.List;

import src.Comic;

public class SearchByPublisher implements CollectionSearcher {

    private boolean exactMatch;

    public SearchByPublisher(boolean exactMatch) {
        this.exactMatch = exactMatch;
    }

    @Override
    public List<Comic> search(List<Comic> comics, String searchTerm) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public List<Comic> databaseSearch(String searchTerm) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'databaseSearch'");
    }

    @Override
    public void setExactMatch(boolean exact) {
        this.exactMatch = exact;
    }

}