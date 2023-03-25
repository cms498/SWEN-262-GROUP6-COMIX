package test;

import java.util.ArrayList;
import java.util.List;

import src.Comic;
import src.Creator;
import src.Publisher;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class SearchByCreatorsTest {
    
    @BeforeEach
    public List<Comic> setUp() {
        List<Creator> creators = new ArrayList<Creator>();
        Publisher publisher = new Publisher("Marvel");
        Publisher publisher2 = new Publisher("DC");

        Creator creator = new Creator("Stan Lee");
        Creator creator2 = new Creator("Jack Kirby");
        creators.add(creator);
        creators.add(creator2);

        List<String> principleCharactersSpiderman = new ArrayList<String>();
        principleCharactersSpiderman.add("Spiderman");
        principleCharactersSpiderman.add("Green Goblin");
        principleCharactersSpiderman.add("Venom");

        List<String> principleCharactersBatman = new ArrayList<String>();
        principleCharactersBatman.add("Batman");
        principleCharactersBatman.add("Joker");

        Comic comics = new Comic(publisher, "Spider-Man", "SPIDER_MAN_TITLE", 0, "2", "1/2/2019", creators, "A good book", 26.08, principleCharactersSpiderman);
        Comic comics2 = new Comic(publisher2, "Batman", "BATMAN", 1, "1", "2/7/2020", creators, "MID", 0, principleCharactersBatman);
        Comic comics3 = new Comic(publisher, "ANT_MAN", "ANT_MAN", 2, "3", "2/8/2020", creators, "also very mid", 0, null);


        List<Comic> comicList = new ArrayList<Comic>();
        comicList.add(comics);
        comicList.add(comics2);
        comicList.add(comics3);
        return comicList;
    }

    @Test
    public void exactMatchSearch() {
        List<Comic> comics = new ArrayList<>();
    }
}