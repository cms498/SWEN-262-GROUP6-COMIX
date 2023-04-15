package src.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import src.Comic;

/**
 * This class is used to sort a personal collection by volume number,
 * lower volume numbers will come before larger ones
 */
public class SortByVolume implements CollectionSorter {

    @Override
    /**
     * This method will perform the sorting, it will be called by the personal
     * collection, this sorting is not done in place
     */
    public List<Comic> sort(List<Comic> comics) {
        List<Comic> sorted = new ArrayList<Comic>(comics);
        Collections.sort(sorted, new Comparator<Comic>() {
            @Override
            public int compare(Comic c1, Comic c2) {
                return Integer.compare(c1.getVolumeNumber(), c2.getVolumeNumber());
            }
        });

        return sorted;
    }
}