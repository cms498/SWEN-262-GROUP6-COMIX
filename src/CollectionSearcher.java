import java.util.List;

public interface CollectionSearcher {
    public List<Comic> search(List<Comic> comics, String searchTerm);
}