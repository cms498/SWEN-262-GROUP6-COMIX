import java.util.List;

public class PersonalCollection {
    private int value;
    private int numberOfIssues;
    private List<Comic> Comics;
    private CollectionSorter sorter;
    private CollectionSearcher searcher;
    //private List<Observer> observers;

    public Comic getComicInCollection(String comicName){
        for(Comic comicsInList: Comics){
            if(comicsInList.getSeriesTitle().equals(comicName)){
                return comicsInList;
            }
        }
        return null;
    }

    public void setSort(CollectionSorter sorter){
        this.sorter = sorter;
    }

    public void setSearch(CollectionSearcher searcher){
        this.searcher = searcher;
    }

    public void editSlab(Comic comic){

    }

    public void editGrade(Comic comic){

    }

    public void addComic(Comic comic){
        Comics.add(comic);
    }

    public void removeComic(Comic comic){
        Comics.remove(comic);
    }

    public void notifyObserver(){

    }


}
