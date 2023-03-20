import java.util.List;

public class SearchByCreators implements CollectionSearcher{

    private boolean exactMatch;
    
    public SearchByCreators(boolean exactMatch) {
        this.exactMatch = exactMatch;
    }
    
    @Override
    public List<Comic> search(List<Comic> comics, String searchTerm) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }
    
}
