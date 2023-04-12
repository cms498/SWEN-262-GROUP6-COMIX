/**
 * This class is used to sort a personal collection by title alphabetically,
 * where the lower letters will come before the higher letters
 */

package src.sort;

import java.util.ArrayList;
import java.util.List;

import src.Comic;

public class SortByTitle implements CollectionSorter {

    @Override
    public List<Comic> sort(List<Comic> comics) {
        List<Comic> sorted = new ArrayList<Comic>();

        for (int i = 0; i < comics.size(); i++) {
            Comic comic = comics.get(i);
            if (sorted.size() == 0) {
                sorted.add(comic);
            } else {
                for (int j = 0; j < sorted.size(); j++) {
                    Comic sortedComic = sorted.get(j);
                    if (comic.getSeriesTitle().compareTo(sortedComic.getSeriesTitle()) < 0) {
                        sorted.add(j, comic);
                        break;
                    } else if (j == sorted.size() - 1) {
                        sorted.add(comic);
                        break;
                    }
                }
            }
        }

        return sorted;
    }
}