package src;

import java.util.List;

import src.search.CollectionSearcher;
import src.sort.CollectionSorter;

public interface iPersonalCollection {
    public void initializeComics();
    public void clearJson();
    public void convertBackToJson();
    public Comic getComicInCollection(String name);
    public void setSort(CollectionSorter sorter);
    public void setSearch(CollectionSearcher searcher);
    public void updateCollectionValue();
    public void updateCollectionIssues();
    public double getValue();
    public int getNumberOfIssues();
    public List<Comic> doSearch(String searchTerm, String sortType);
    public List<Comic> doDatabaseSearch(String searchTerm);
    public List<Comic> getComics();
    public void editSlab(String storyTitle);
    public void editGrade(String storyTitle, int grade);
    public void addComicByDataBase(String storyTitle, int volumeNumber, String issueNumber);
    public void addComicManually(String publisher, String seriesTitle, String storyTitle, int volumeNumber, String issueNumber, String publicationDate, String creators, String description, String value);
    public void removeComic(String storyTitle);
    public void editComic(String storyTitle, String field, String newValue);
    public void PrettyPrintDatabase();
    public void prettyPrintHelper(String type);
    public void viewSeriesTitle();
    public void viewVolumeNumber();
    public void viewIssueNumber();
    public void viewPublisher();
    public void setGuestMode(boolean guestMode);
    public void printDatabase(List<Comic> comics);
}