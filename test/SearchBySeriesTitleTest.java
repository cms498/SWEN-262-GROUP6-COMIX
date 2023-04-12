package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import src.Comic;
import src.Creator;
import src.Publisher;
import src.search.SearchBySeriesTitle;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class SearchBySeriesTitleTest {
    
    /** 
     * setup method that creates a list of comics for each test to use
     * @return List<Comic>
     */
    public List<Comic> setUp() {
        List<Creator> creators = new ArrayList<Creator>();
        List<Creator> creatorSingle = new ArrayList<Creator>();
        Publisher publisher = new Publisher("Marvel");
        Publisher publisher2 = new Publisher("DC");

        Creator creator = new Creator("Stan Lee");
        Creator creator2 = new Creator("Jack Kirby");
        creators.add(creator);
        creators.add(creator2);
        Creator creator3 = new Creator("Aaron Kuder");
        creatorSingle.add(creator3);

        ArrayList<String> signatures = new ArrayList<>();

        Comic comic = new Comic(publisher, "Spider-Man", "SPIDER_MAN_TITLE", 0, "2", "1/2/2019", creators, "A good book", 26.08, false, false, signatures, false);
        Comic comic2 = new Comic(publisher2, "Batman", "BATMAN", 1, "1", "2/7/2020", creators, "MID", 0, false, false, signatures, false);
        Comic comic3 = new Comic(publisher, "ANT_MAN", "ANT_MAN", 2, "3", "2/8/2020", creators, "also very mid", 0, false, false, signatures, false);
        Comic comic4 = new Comic(publisher2, "Action Comics", "Monster", 2, "26A", "12/4/2013", creatorSingle, "A classic.", 0, false, false, signatures, false); 


        List<Comic> comicList = new ArrayList<Comic>();
        comicList.add(comic);
        comicList.add(comic2);
        comicList.add(comic3);
        comicList.add(comic4);
        return comicList;
    }

    @Test
    public void exactMatchSearch() {
        List<Comic> comics = setUp();
        List<Comic> expected = new ArrayList<>();
        expected.add(comics.get(0));
        String searchTerm = "Spider_Man_Title";
        SearchBySeriesTitle testing = new SearchBySeriesTitle(true);

        List<Comic> actual = testing.search(comics, searchTerm);

        assertEquals(expected, actual);
    }

    @Test
    public void exactMatchSearchNone() {
        List<Comic> comics = setUp();
        List<Comic> expected = new ArrayList<>();
        String searchTerm = "The Amazing Hulk";
        SearchBySeriesTitle testing = new SearchBySeriesTitle(true);

        List<Comic> actual = testing.search(comics, searchTerm);

        assertEquals(expected, actual);
    }

    @Test
    public void partialMatchSearch() {
        List<Comic> comics = setUp();
        List<Comic> expected = new ArrayList<>();
        expected.add(comics.get(0));
        expected.add(comics.get(1));
        expected.add(comics.get(2));
        String searchTerm = "man";
        SearchBySeriesTitle testing = new SearchBySeriesTitle(false);

        List<Comic> actual = testing.search(comics, searchTerm);

        assertEquals(expected, actual);
    }

    @Test
    public void partialMatchSearchSingle() {
        List<Comic> comics = setUp();
        List<Comic> expected = new ArrayList<>();
        expected.add(comics.get(3));
        String searchTerm = "mon";
        SearchBySeriesTitle testing = new SearchBySeriesTitle(false);

        List<Comic> actual = testing.search(comics, searchTerm);

        assertEquals(expected, actual);
    }

    @Test
    public void partialMatchSearchNone() {
        List<Comic> comics = setUp();
        List<Comic> expected = new ArrayList<>();
        String searchTerm = "fsdsl";
        SearchBySeriesTitle testing = new SearchBySeriesTitle(false);

        List<Comic> actual = testing.search(comics, searchTerm);

        assertEquals(expected, actual);
    }
}
