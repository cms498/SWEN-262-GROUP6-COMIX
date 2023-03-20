import java.util.ArrayList;
import java.util.List;

public class SearchByCharacters implements CollectionSearcher{

    private boolean exactMatch;

    public SearchByCharacters(boolean exactMatch) {
        this.exactMatch = exactMatch;
    }

    @Override
    public List<Comic> search(List<Comic> comics, String searchTerm) {
        List<Comic> searchComics = new ArrayList<>();
        if(exactMatch) {

        } else {

        }

        //in place for now until further clarification
        return searchComics;
    }
    
}
