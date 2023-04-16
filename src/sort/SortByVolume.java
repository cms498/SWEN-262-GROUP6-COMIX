package src.sort;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import src.Comic;

/*
 * Implements the Collection Sorter interface, and sorts the list of comics by their volume number
 */
public class SortByVolume implements CollectionSorter{

    @Override
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
