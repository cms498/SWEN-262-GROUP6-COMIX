package src;
import java.util.List;

/**
 * This class represents the collection of comics the user has
 * Acts as the main facade to which the user interacts with, and it delegates all of the required logic to other classes
 */
public class PersonalCollection {

    private final String comicFile = "personalCollection.csv";

    private double value;
    private int numberOfIssues;
    private List<Comic> comics;
    private CollectionSorter sorter;
    private CollectionSearcher searcher;
    private List<PersonalCollectionObserver> observers;

    public PersonalCollection() {
        
    }

    private void initializeComics() {

    }

    public void register(PersonalCollectionObserver observer) {
        this.observers.add(observer);
    }

    private void handle() {
        for(PersonalCollectionObserver observer: this.observers) {
            observer.handle();
        }
    }

    public Comic getComicInCollection(String comicName){
        for(Comic comicsInList: comics){
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

    public void editGrade(Comic comic, int grade){
        double newValue = grade;
        if(grade == 1){
            newValue = comic.getValue()*(grade/100);
        }
        else{
            newValue = Math.log10(grade)*comic.getValue();
        }
        comic.setValue(newValue);
    }

    public void addComic(Comic comic){
        comics.add(comic);
    }

    public void removeComic(Comic comic){
        comics.remove(comic);
    }
}
