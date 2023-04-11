package src;

import java.util.List;

import src.search.CollectionSearcher;
import src.sort.CollectionSorter;

public class PersonalCollectionProxy implements iPersonalCollection {

    private boolean guestMode;
    private PersonalCollection collection = new PersonalCollection();

    public PersonalCollectionProxy(boolean guestMode) {
        this.guestMode = guestMode;
    }

    public void setGuestMode(boolean guestMode) {
        if (this.guestMode == false) {
            collection.setGuestMode(guestMode);
        } else {
            this.guestMode = guestMode;
            System.out.println("You are logged in");
        }
    }

    public boolean isGuestMode() {
        return guestMode;
    }

    @Override
    public void initializeComics() {
        collection.initializeComics();
    }

    @Override
    public void clearJson() {
        if (guestMode == false) {
            collection.clearJson();
        } else {
            System.out.println("Log in to have access to this feature");
        }
    }

    @Override
    public void convertBackToJson() {
        if (guestMode == false) {
            collection.convertBackToJson();
        } else {
            System.out.println("Log in to have access to this feature");
        }
    }

    @Override
    public Comic getComicInCollection(String name) {
        if (guestMode == false) {
            return collection.getComicInCollection(name);
        } else {
            System.out.println("Log in to have access to this feature");
            return null;
        }
    }

    @Override
    public void setSort(CollectionSorter sorter) {
        if (guestMode == false) {
            collection.setSort(sorter);
        } else {
            System.out.println("Log in to have access to this feature");
        }
    }

    @Override
    public void setSearch(CollectionSearcher searcher) {
        collection.setSearch(searcher);
    }

    @Override
    public void updateCollectionValue() {
        if (guestMode == false) {
            collection.updateCollectionValue();
        } else {
            System.out.println("Log in to have access to this feature");
        }
    }

    @Override
    public void updateCollectionIssues() {
        if (guestMode == false) {
            collection.updateCollectionIssues();
        } else {
            System.out.println("Log in to have access to this feature");
        }
    }

    @Override
    public double getValue() {
        if (guestMode == false) {
            return collection.getValue();
        } else {
            System.out.println("Log in to have access to this feature");
            return 0;
        }
    }

    @Override
    public int getNumberOfIssues() {
        if (guestMode == false) {
            return collection.getNumberOfIssues();
        } else {
            System.out.println("Log in to have access to this feature");
            return 0;
        }
    }

    @Override
    public List<Comic> doSearch(String searchTerm, String sortType) {
        return collection.doSearch(searchTerm, sortType);
    }

    @Override
    public List<Comic> doDatabaseSearch(String searchTerm) {
        return collection.doDatabaseSearch(searchTerm);
    }

    @Override
    public List<Comic> getComics() {
        if (guestMode == false) {
            return collection.getComics();
        } else {
            System.out.println("Log in to have access to this feature");
            return null;
        }
    }

    @Override
    public void editSlab(String storyTitle) {
        if (guestMode == false) {
            collection.editSlab(storyTitle);
        } else {
            System.out.println("Log in to have access to this feature");
        }
    }

    @Override
    public void editGrade(String storyTitle, int grade) {
        if (guestMode == false) {
            collection.editGrade(storyTitle, grade);
        } else {
            System.out.println("Log in to have access to this feature");
        }
    }

    @Override
    public void addComicByDataBase(String seriesTitle, int volumeNumber, String issueNumber) {
        if (guestMode == false) {
            collection.addComicByDataBase(seriesTitle, volumeNumber, issueNumber);
        } else {
            System.out.println("Log in to have access to this feature");
        }
    }

    @Override
    public void addComicManually(String publisher, String seriesTitle, String storyTitle, int volumeNumber,
            String issueNumber, String publicationDate, String creators, String description, String value) {
        if (guestMode == false) {
            collection.addComicManually(publisher, seriesTitle, storyTitle, volumeNumber, issueNumber, publicationDate, creators, description, value);
        } else {
            System.out.println("Log in to have access to this feature");
        }
    }

    @Override
    public void removeComic(String storyTitle) {
        if(guestMode == false){
            collection.removeComic(storyTitle);
        } else {
            System.out.println("Log in to have access to this feature");
        }
    }

    @Override
    public void editComic(String storyTitle, String field, String newValue) {
        if(guestMode == false){
            collection.editComic(storyTitle, field, newValue);
        } else {
            System.out.println("Log in to have access to this feature");
        }
    }

    @Override
    public void PrettyPrintDatabase() {
        collection.PrettyPrintDatabase();
    }

    @Override
    public void prettyPrintHelper(String type) {
        collection.prettyPrintHelper(type);
    }

    @Override
    public void viewSeriesTitle() {
        collection.viewSeriesTitle();
    }

    @Override
    public void viewVolumeNumber() {
        collection.viewVolumeNumber();
    }

    @Override
    public void viewIssueNumber() {
        collection.viewIssueNumber();
    }

    @Override
    public void viewPublisher() {
        collection.viewPublisher();
    }

    @Override
    public void printDatabase(List<Comic> comics) {
        if (guestMode == false) {
            collection.printDatabase(comics);
        } else {
            System.out.println("Log in to have access to this feature");
        }
    }

}
