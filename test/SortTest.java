package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import src.Comic;
import src.Creator;
import src.Publisher;
import src.sort.CollectionSorter;
import src.sort.SortByDate;
import src.sort.SortByIssueNumber;
import src.sort.SortByTitle;
import src.sort.SortByVolume;


public class SortTest {
    
    @BeforeEach
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

        Comic comic = new Comic(publisher, "Spider-Man", "SPIDER_MAN_TITLE", 0, "2", "Jun 1986", creators, "A good book", 26.08, false, false, signatures, false);
        Comic comic2 = new Comic(publisher2, "Batman", "BATMAN", 1, "1", "Jun 10, 2015", creators, "MID", 0, false, false, signatures, false);
        Comic comic3 = new Comic(publisher, "ANT_MAN", "ANT_MAN", 2, "3", "Jul 29, 2015", creators, "also very mid", 0, false, false, signatures, false);
        // Comic comic4 = new Comic(publisher2, "Action Comics", "Monster", 2, "26A", "12/4/2013", creatorSingle, "A classic.", 0, false, false, signatures, false); 


        List<Comic> comicList = new ArrayList<Comic>();
        comicList.add(comic);
        comicList.add(comic2);
        comicList.add(comic3);
        // comicList.add(comic4);
        return comicList;
    }

    @Test
    public void testSortByIssueNumber() {
        List<Comic> comics = setUp();
        System.out.println(comics);

        CollectionSorter sorter = new SortByIssueNumber();

        List<Comic> sorted = sorter.sort(comics);
        String expected = "Batman";
        String actual = sorted.get(0).getSeriesTitle();

        String actualBack = sorted.get(2).getSeriesTitle();
        assertEquals(expected, actual);
        assertEquals("ANT_MAN", actualBack);

    }

    @Test
    public void testSortByDate(){
        List<Comic> comics = setUp();
        System.out.println(comics);

        CollectionSorter sorter = new SortByDate();

        List<Comic> sorted = sorter.sort(comics);
        String expected = "Spider-Man";
        String actual = sorted.get(0).getSeriesTitle();
        assertEquals(expected, actual);
    }

    @Test
    public void testSortByTitle(){
        List<Comic> comics = setUp();
        System.out.println(comics);

        CollectionSorter sorter = new SortByTitle();

        List<Comic> sorted = sorter.sort(comics);
        
        List<String> expected = new ArrayList<String>();
        expected.add("ANT_MAN");
        expected.add("Batman");
        expected.add("Spider-Man");

        List<String> actual = new ArrayList<String>();
        for (int i = 0; i < sorted.size(); i++) {
            actual.add(sorted.get(i).getSeriesTitle());
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testSortByVolume(){
        List<Comic> comics = setUp();
        System.out.println(comics);

        CollectionSorter sorter = new SortByVolume();

        List<Comic> sorted = sorter.sort(comics);
        
        List<String> expected = new ArrayList<String>();
        expected.add("Spider-Man");
        expected.add("Batman");
        expected.add("ANT_MAN");

        List<String> actual = new ArrayList<String>();
        for (int i = 0; i < sorted.size(); i++) {
            actual.add(sorted.get(i).getSeriesTitle());
        }
        assertEquals(expected, actual);
    }
}
