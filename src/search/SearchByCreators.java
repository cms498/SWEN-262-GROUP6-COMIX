package src.search;
import java.util.ArrayList;
import java.util.List;

import src.Comic;
import src.Creator;

public class SearchByCreators implements CollectionSearcher{

    private boolean exactMatch;
    
    public SearchByCreators(boolean exactMatch) {
        this.exactMatch = exactMatch;
    }
    
    
    /** 
     * @param comics
     * @param searchTerm
     * @return List<Comic>
     */
    @Override
    public List<Comic> search(List<Comic> comics, String searchTerm) {
        List<Comic> searchComics = new ArrayList<>();
        if(this.exactMatch) {
            for(Comic comic : comics) {
                boolean add = false;
                for(Creator creator : comic.getCreators()) {
                    if(searchTerm.equals(creator.getName())) {
                        add = true;
                    }
                }
                if(add) {
                    searchComics.add(comic);
                }
            }
        } else {
            for(Comic comic : comics) {
                boolean add = false;
                for(Creator creator : comic.getCreators()) {
                    if(creator.getName().contains(searchTerm)) {
                        add = true;
                    }
                }
                if(add) {
                    searchComics.add(comic);
                }
            }
        }
        return searchComics;
    }


    
    /** 
     * @param filename
     * @param searchTerm
     * @return List<Comic>
     */
    @Override
    public List<Comic> databaseSearch(String filename, String searchTerm) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'databaseSearch'");
    }
    
}
