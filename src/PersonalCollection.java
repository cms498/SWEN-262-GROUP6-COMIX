package src;
import java.util.ArrayList;
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
            if(comicsInList.getStoryTitle().equals(comicName)){
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
        if(comic.getIsGraded() == true){
            comic.setValue(comic.getValue()*2);
        }
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

    public void addComicByDataBase(String storyTitle){
        
    }

    public void addComicManually(String publisher, String seriesTitle, String storyTitle, int volumeNumber,
    String issueNumber, String publicationDate, String creators,
    String description, String value){

        List<Creator> creatorsList = new ArrayList<>();
        Double valueNumber = 1.0;
        if(creators != null){
            String creatorName = "";
            for(int i = 1; i < creators.length()-1; i++){
                creatorName = creatorName + creators.charAt(i);
                if(creators.charAt(i+1) == ',' || creators.charAt(+1) == ']'){
                    creatorsList.add(new Creator(creatorName));
                    creatorName = "";
                }
            }
        }
        if(value != null){
            valueNumber = Double.valueOf(value);
        }

        comics.add(new Comic(new Publisher(publisher), seriesTitle, storyTitle, volumeNumber, issueNumber, publicationDate, creatorsList, description, valueNumber));
    }

    public void removeComic(Comic comic){
        comics.remove(comic);
    }
}
