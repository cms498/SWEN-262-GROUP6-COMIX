package src;

import java.util.ArrayList;
import java.util.List;

import src.search.CollectionSearcher;
import src.sort.CollectionSorter;

/*
 * Interface for the personal collection that the proxy and real subject will implement
 */
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
    public double editGrade(String storyTitle, int grade);
    public void addComicByDataBase(String storyTitle, int volumeNumber, String issueNumber);
    public void addComicManually(String publisher, String seriesTitle, String storyTitle, int volumeNumber, String issueNumber, String publicationDate, String creators, String description, String value);
    public void addComicAllFields(Publisher publisher, String seriesTitle, String storyTitle, int volumeNumber, String issueNumber, String publicationDate, List<Creator> creators, String description, double value, boolean isGraded, boolean isSlabbed,  ArrayList<String> signatures, boolean authenticated, int gradeNumber);
    public void removeComic(String storyTitle);
    public void editComic(String storyTitle, String field, String newValue);
    public void PrettyPrintDatabase();
    public void prettyPrintHelper(String type);
    public void viewSeriesTitle();
    public void viewVolumeNumber();
    public void viewIssueNumber();
    public void viewPublisher();
    public void setGuestMode(boolean guestMode);
    public boolean isGuestMode();
    public void printDatabase(List<Comic> comics);
    public void addComic(Comic comic);
    public void authenticate(String storyTitle);
    public void sign(String storyTitle, String signature);
    public Comic getComicInCollection2(String seriesTitle, int volumeNumber, String issueNumber);
    public void ungradeComic(Comic comic, double difference);
    public void unslabComic(Comic comic);
    public void unauthenticateComic(Comic comic);
    public void unsignComic(Comic comic, String signature);
    public void dynamicPrettyPrint(List<String> comic_attributes, List<Comic> comics);
    public void setFlag(Boolean flag);
}