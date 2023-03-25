import java.util.List;

public class SortByIssueNumber implements CollectionSorter{

    @Override
    public void sort(List<Comic> comics) {
        comics.sort((Comic c1, Comic c2) -> c1.getIssueNumber().compareTo(c2.getIssueNumber()));
    }
}
