package src.sort;
import java.util.List;

import src.Comic;

/*
 * Interface that all sorters of the personal collection will implement
 */
public interface CollectionSorter {
    public List<Comic> sort(List<Comic> comics);

}