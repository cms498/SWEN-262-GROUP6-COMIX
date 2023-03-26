package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import src.CollectionSorter;
import src.Comic;
import src.Creator;
import src.Publisher;
import src.SortByDate;
import src.SortByIssueNumber;
import src.SortByTitle;
import src.SortByVolume;


public class SortTest {
    
    @BeforeEach
    public List<Comic> setUp() {
        List<Creator> creators = new ArrayList<Creator>();
        Publisher publisher = new Publisher("Marvel");
        Publisher publisher2 = new Publisher("DC");

        Creator creator = new Creator("Stan Lee");
        Creator creator2 = new Creator("Jack Kirby");
        creators.add(creator);
        creators.add(creator2);

        Comic comics = new Comic(publisher, "Spider-Man", "SPIDER_MAN_TITLE", 0, "2", "1/2/2019", creators, "A good book", 26.08);
        Comic comics2 = new Comic(publisher2, "Batman", "BATMAN", 1, "1", "2/7/2020", creators, "MID", 0);
        Comic comics3 = new Comic(publisher, "ANT_MAN", "ANT_MAN", 2, "3", "2/8/2020", creators, "also very mid", 0);


        List<Comic> comicList = new ArrayList<Comic>();
        comicList.add(comics);
        comicList.add(comics2);
        comicList.add(comics3);
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

        String actualBack = sorted.get(2).getSeriesTitle();
        assertEquals(expected, actual);
        assertEquals("ANT_MAN", actualBack);
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
