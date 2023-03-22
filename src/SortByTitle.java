package src;
import java.util.List;

public class SortByTitle implements CollectionSorter{

    @Override
    public void sort(List<Comic> comics) {
        comics.sort((Comic c1, Comic c2) -> c1.getSeriesTitle().compareTo(c2.getSeriesTitle()));
    }
}
