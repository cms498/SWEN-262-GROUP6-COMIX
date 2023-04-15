/**
 * This interface represents a basic sorter in order to sort and reorder a personal collection
 * All other sorters will be based on this interface, represents the Stragey in the GOF pattern
 */

package src.sort;

import java.util.List;

import src.Comic;

public interface CollectionSorter {
    public List<Comic> sort(List<Comic> comics);
}