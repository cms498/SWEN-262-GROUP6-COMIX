package src.search;
import java.util.ArrayList;
import java.util.List;

import src.Comic;

public class SearchByTitle implements CollectionSearcher{

    private boolean exactMatch;

    public SearchByTitle(boolean exactMatch) {
        this.exactMatch = exactMatch;
    }
    
    @Override
    public List<Comic> search(List<Comic> comics, String searchTerm) {
        List<Comic> searchComics = new ArrayList<>();
        if(this.exactMatch) {
            for(Comic comic : comics) {
                if(searchTerm.equals(comic.getStoryTitle())) {
                    searchComics.add(comic);
                }
            }
        } else {
            for(Comic comic : comics) {
                if(comic.getStoryTitle().contains(searchTerm)) {
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