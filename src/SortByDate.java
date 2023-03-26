package src;
import java.util.List;

public class SortByDate implements CollectionSorter{

    @Override
    public void sort(List<Comic> comics) {
        comics.sort((Comic c1, Comic c2) -> c1.getPublicationDate().compareTo(c2.getPublicationDate()));
        }
    
}
