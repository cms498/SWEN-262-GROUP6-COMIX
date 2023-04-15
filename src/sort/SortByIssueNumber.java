package src.sort;

import java.util.ArrayList;
import java.util.List;

import src.Comic;

/**
 * This class is used to sort a personal collection of comics by issues numbers,
 * where lower issues numbers will come first
 */
public class SortByIssueNumber implements CollectionSorter {

    @Override
    /**
     * This is the method the personal collection will call when the user wants to sort a list
     * this is not done in place
     */
    public List<Comic> sort(List<Comic> comics) {
        List<Comic> sorted = new ArrayList<Comic>();

        for (int i = 0; i < comics.size(); i++) {
            Comic comic = comics.get(i);
            if (sorted.size() == 0) {
                sorted.add(comic);
            } else {
                for (int j = 0; j < sorted.size(); j++) {
                    Comic sortedComic = sorted.get(j);
                    if (Integer.parseInt(comic.getIssueNumber()) < Integer.parseInt(sortedComic.getIssueNumber())) {
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