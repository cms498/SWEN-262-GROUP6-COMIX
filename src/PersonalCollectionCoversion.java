package src;

import java.util.List;

import src.search.CollectionSearcher;
import src.search.SearchByRuns;

public class PersonalCollectionCoversion {
    public static void main(String[] args) {
        CollectionSearcher searcher = new SearchByRuns(false);
        List<Comic> comics = searcher.databaseSearch(""); 
        System.out.println(comics.toString());
    }
}
