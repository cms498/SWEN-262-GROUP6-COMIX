import java.util.List;

public class PersonalCollection {
    private int value;
    private int numberOfIssues;
    private List<Comic> Comics;
    private CollectionSorter sorter;
    private CollectionSearcher searcher;
    //private List<Observer> observers;

    public Comic getComicInCollection(String comicName){
        return null;
    }

    public void setSort(CollectionSorter sorter){

    }

    public void setSearch(CollectionSearcher searcher){
        
    }

    public void editSlab(Comic comic){

    }

    public void editGrade(Comic comic){

    }

    public void addComic(Comic comic){
        Comics.add(comic);
    }

    public void removeComic(Comic comic){
        for(Comic comicsInList: Comics){
            if(comicsInList.equals(comic)){
                Comics.remove(comicsInList);
            }
        }
    }

    public void notifyObserver(){

    }


}
