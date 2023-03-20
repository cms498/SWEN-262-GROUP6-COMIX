import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents the collection of comics the user has
 * Acts as the main facade to which the user interacts with, and it delegates all of the required logic to other classes
 */
public class PersonalCollection {

    private final String comicFile = "personalCollection.csv";

    private int value;
    private int numberOfIssues;
    private List<Comic> comics;
    private CollectionSorter sorter;
    private CollectionSearcher searcher;
    private List<PersonalCollectionObserver> observers;

    public PersonalCollection() {

    }

    private void initializeComics() {
        File file = new File(comicFile);
        try(Scanner s = new Scanner(file)){
            
        } catch(FileNotFoundException e) {
            System.out.println("File not found.");
        }

    }

    public void register(PersonalCollectionObserver observer) {
        this.observers.add(observer);
    }

    private void handle() {
        for(PersonalCollectionObserver observer: this.observers) {
            observer.handle();
        }
    }
}
