package src.sort;
import java.util.ArrayList;
import java.util.List;

import src.Comic;

/*
 * Implements the Collection Sorter interface, and sorts the list of comics by issue number
 */
public class SortByIssueNumber implements CollectionSorter{

    /** 
     * Sorts the list of comics by volume number
     * @param comics
     * @return List<Comic>
     */
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
                    if (comic.getIssueNumber().charAt(0) < sortedComic.getIssueNumber().charAt(0)) {
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
