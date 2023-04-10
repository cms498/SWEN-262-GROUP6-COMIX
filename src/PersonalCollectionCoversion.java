package src;

import java.util.List;


import src.search.CollectionSearcher;
import src.search.SearchByRuns;

public class PersonalCollectionCoversion {
    public static void main(String[] args) {
        CollectionSearcher searcher = new SearchByRuns(false);
        List<Comic> comics = searcher.databaseSearch(""); 
        for(Comic comic: comics){
            System.out.println(comic.toStringRuns());
        }
        
    }
}
