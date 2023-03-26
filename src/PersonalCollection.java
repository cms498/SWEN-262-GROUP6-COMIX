package src;

import src.search.CollectionSearcher;

import java.util.ArrayList;
import java.util.Arrays;
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

    //In progress: waiting on Search by Title class to be finished 
    public void addComicByDataBase(String storyTitle){
        
    }

    //adds comics manually by user input (optional attributes can be passed in as null into this method if the user didn't input anything for them)
    public void addComicManually(String publisher, String seriesTitle, String storyTitle, int volumeNumber,
    String issueNumber, String publicationDate, String creators,
    String description, String value){

        //Default parameters for optional attributes that aren't specified by user input
        List<Creator> creatorsList = new ArrayList<>();
        Double valueNumber = 1.0;
        //turns string 'creators' into a list of creators if the attribute doesn't equal to null
        // format for creators should look something like this '[Harry,Sam,Tim]' as a string
        if(creators != null){
            creators = creators.substring(1, creators.length()-1);
            String[] creatorArr = creators.split(",");
            for(String creatorName: creatorArr){
                creatorsList.add(new Creator(creatorName));
            }
        }
        //turns string 'value' into a double type if the attribute doesn't equal to null
        if(value != null){
            valueNumber = Double.valueOf(value);
        }

        comics.add(new Comic(new Publisher(publisher), seriesTitle, storyTitle, volumeNumber, issueNumber, publicationDate, creatorsList, description, valueNumber));
    }

    //removes comics from user's personal collection based on what story title they inputted
    public void removeComic(String storyTitle){
        Comic comic = getComicInCollection(storyTitle);
        if(comic != null){
            comics.remove(comic);
        }
    }
}
