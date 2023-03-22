import java.util.ArrayList;
import java.util.List;

public class SearchByCreators implements CollectionSearcher{

    private boolean exactMatch;
    
    public SearchByCreators(boolean exactMatch) {
        this.exactMatch = exactMatch;
    }
    
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
                    add = false;
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
    
}
