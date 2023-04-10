package src;

import java.util.List;


import src.search.CollectionSearcher;
import src.search.SearchByGaps;
import src.search.SearchByRuns;

public class PersonalCollectionCoversion {
    public static void main(String[] args) {
        CollectionSearcher searcher = new SearchByGaps(false);
        PersonalCollection collection = new PersonalCollection();
        collection.initializeComics();
        collection.addComicByDataBase("The Incredible Hulk", 1, "202");
        collection.addComicByDataBase("The Incredible Hulk", 1, "203");
        collection.addComicByDataBase("The Incredible Hulk", 1, "204");
        collection.addComicByDataBase("The Incredible Hulk", 1, "207");
        collection.addComicByDataBase("The Incredible Hulk", 1, "241");
        collection.addComicByDataBase("The Incredible Hulk", 1, "271");
        collection.addComicByDataBase("The Incredible Hulk", 1, "264");
        collection.addComicByDataBase("The Incredible Hulk", 1, "272");
        collection.addComicByDataBase("The Incredible Hulk", 1, "273");
        collection.addComicByDataBase("The Incredible Hulk", 1, "223");
        collection.addComicByDataBase("The Incredible Hulk", 1, "236");
        collection.addComicByDataBase("The Incredible Hulk", 1, "255");
        collection.addComicByDataBase("The Incredible Hulk", 1, "256");
        collection.addComicByDataBase("The Incredible Hulk", 1, "278");
        collection.addComicByDataBase("The Incredible Hulk", 1, "270");
        collection.addComicByDataBase("The Incredible Hulk", 1, "279");
        List<Comic> comics = searcher.search(collection.getComics(), ""); 
        for(Comic comic: comics){
            System.out.println(comic.toStringRuns());
        }
        
    }
}
