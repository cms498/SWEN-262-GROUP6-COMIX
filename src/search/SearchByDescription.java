package src.search;
import java.util.ArrayList;
import java.util.List;

import src.Comic;

public class SearchByDescription implements CollectionSearcher{

    private boolean exactMatch;

    public SearchByDescription(boolean exactMatch) {
         this.exactMatch = exactMatch;
    }

    @Override
    public List<Comic> search(List<Comic> comics, String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        List<Comic> searchComics = new ArrayList<>();
        if(this.exactMatch) {
            for(Comic comic : comics) {
                if(searchTerm.equals(comic.getDescription().toLowerCase())) {
                    searchComics.add(comic);
                }
            }
        } else {
            for(Comic comic : comics) {
                if(comic.getDescription().toLowerCase().contains(searchTerm)) {
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
