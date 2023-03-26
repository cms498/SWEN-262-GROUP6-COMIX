package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import src.Comic;
import src.Creator;
import src.Publisher;
import src.search.SearchByDescription;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class SearchByDescriptionTest {

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

        Comic comic = new Comic(publisher, "Spider-Man", "SPIDER_MAN_TITLE", 0, "2", "1/2/2019", creators, "A good book", 26.08);
        Comic comic2 = new Comic(publisher2, "Batman", "BATMAN", 1, "1", "2/7/2020", creators, "MID", 0);
        Comic comic3 = new Comic(publisher, "ANT_MAN", "ANT_MAN", 2, "3", "2/8/2020", creators, "also very mid", 0);
        Comic comic4 = new Comic(publisher2, "Action Comics", "Monster", 2, "26A", "12/4/2013", creatorSingle, "A good book", 0); 


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
        expected.add(comics.get(3));
        String searchTerm = "A good book";
        SearchByDescription testing = new SearchByDescription(true);

        List<Comic> actual = testing.search(comics, searchTerm);

        assertEquals(expected, actual);
    }

    @Test
    public void exactMatchSearchSingle() {
        List<Comic> comics = setUp();
        List<Comic> expected = new ArrayList<>();
        expected.add(comics.get(1));
        String searchTerm = "mid";
        SearchByDescription testing = new SearchByDescription(true);

        List<Comic> actual = testing.search(comics, searchTerm);

        assertEquals(expected, actual);
    }

    @Test
    public void exactMatchSearchNone() {
        List<Comic> comics = setUp();
        List<Comic> expected = new ArrayList<>();
        String searchTerm = "This is the best book ever!";
        SearchByDescription testing = new SearchByDescription(true);

        List<Comic> actual = testing.search(comics, searchTerm);

        assertEquals(expected, actual);
    }

    @Test
    public void partialMatchSearch() {
        List<Comic> comics = setUp();
        List<Comic> expected = new ArrayList<>();
        expected.add(comics.get(1));
        expected.add(comics.get(2));
        String searchTerm = "mi";
        SearchByDescription testing = new SearchByDescription(false);

        List<Comic> actual = testing.search(comics, searchTerm);

        assertEquals(expected, actual);
    }

    @Test
    public void partialMatchSearchSingle() {
        List<Comic> comics = setUp();
        List<Comic> expected = new ArrayList<>();
        expected.add(comics.get(2));
        String searchTerm = "Also";
        SearchByDescription testing = new SearchByDescription(false);

        List<Comic> actual = testing.search(comics, searchTerm);

        assertEquals(expected, actual);
    }

    @Test
    public void partialMatchSearchNone() {
        List<Comic> comics = setUp();
        List<Comic> expected = new ArrayList<>();
        String searchTerm = "fsdsl";
        SearchByDescription testing = new SearchByDescription(false);

        List<Comic> actual = testing.search(comics, searchTerm);

        assertEquals(expected, actual);
    }

    @Test
    public void exactMatchSearchDatabase() {
        List<Comic> expected = new ArrayList<>();
        String searchTerm = "Stephanie Hans Cover";
        SearchByDescription testing = new SearchByDescription(true);

        List<Comic> actual = testing.databaseSearch(searchTerm);

        assertEquals(expected, actual);
    }
    
}
