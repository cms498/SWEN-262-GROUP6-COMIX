package src.sort;

import java.util.List;

import src.Comic;

/**
 * This interface represents a basic sorter in order to sort and reorder a
 * personal collection
 * All other sorters will be based on this interface, represents the Strategy in
 * the GOF pattern
 */
public interface CollectionSorter {
    public List<Comic> sort(List<Comic> comics);
}