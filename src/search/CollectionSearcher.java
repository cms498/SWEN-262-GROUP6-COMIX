package src.search;
import java.util.List;

import src.Comic;

/*
 * CollectionSearcher interface that acts as the Strategy interface
 * for all future implementations of search
 */
public interface CollectionSearcher {

    public List<Comic> search(List<Comic> comics, String searchTerm);
    public List<Comic> databaseSearch(String filename, String searchTerm);
}