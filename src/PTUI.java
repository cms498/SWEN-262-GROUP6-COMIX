package src;

import java.util.HashMap;
import java.util.Scanner;

import src.search.CollectionSearcher;
import src.search.SearchByCreators;
import src.search.SearchByDescription;
import src.search.SearchByTitle;
import src.sort.CollectionSorter;
import src.sort.SortByDate;
import src.sort.SortByIssueNumber;
import src.sort.SortByTitle;
import src.sort.SortByVolume;

public class PTUI {

    public static void main(String[] args) {

        String[] comix = {
            " .----------------.  .----------------.  .----------------.  .----------------.  .----------------. ",
            "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |",
            "| |     ______   | || |     ____     | || | ____    ____ | || |     _____    | || |  ____  ____  | |",
            "| |   .' ___  |  | || |   .'    `.   | || ||_   \\  /   _|| || |    |_   _|   | || | |_  _||_  _| | |",
            "| |  / .'   \\_|  | || |  /  .--.  \\  | || |  |   \\/   |  | || |      | |     | || |   \\ \\  / /   | |",
            "| |  | |         | || |  | |    | |  | || |  | |\\  /| |  | || |      | |     | || |    > `' <    | |",
            "| |  \\ `.___.'\\  | || |  \\  `--'  /  | || | _| |_\\/_| |_ | || |     _| |_    | || |  _/ /'`\\ \\_  | |",
            "| |   `._____.'  | || |   `.____.'   | || ||_____||_____|| || |    |_____|   | || | |____||____| | |",
            "| |              | || |              | || |              | || |              | || |              | |",
            "'-|--------------' || '--------------' || '--------------' || '--------------' || '--------------|-",
            "  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'"
        };
        System.out.println("\n\n");
        for (String line : comix) {
            System.out.println("\033[1;93m"+line);
        }

           


        System.out.println("\033[0m"+ "\n\n\033[41m" + "***********************************************************************"
                + "\033[0m");
        System.out.println("Welcome to COMIX! Maintain your own personal collection here!\033[1;31m\033[0m\n"
                + "To see a list and format of all commands type \033[4;37m<lc>\033[0m"
                + "\nType out all commands in the \033[4;37mproper format\033[0m to properly execute them\n"
                + "\033[41m" + "***********************************************************************" + "\033[0m");

        String quitter = "\033[1;90m>>To end the application -> \"quit\"\u001B[0m";
        String PersonalCollectionSearchCommand = "\033[1;90m\u001B[31m>>To search your personal collection -> \"search collection\", <search type>, <term>, <exact or partial>";
        String DataBaseSearchCommand = "\u001B[32m>>To search the database -> <search database>, \"search type\", <term>, <exact or partial>";
        String PersonalCollectionSortCommand = "\u001B[33m>>To sort your personal collection -> \"sort\", <sort type>";
        String AddComicFROMDBtoPersonalCollection = "\u001B[34m>>To add comic from the database to your personal collection -> \"add from database>\", <exact comic name>";
        String AddComicManuallytoPersonalCollection = "\u001B[35m>>To add a comic manually to your personal collection-> \"add\", <series>, <issue>, <volume>, <title>, <description>, <publisher>, <release date>, <value> <creator1, creator2, ...>";
        String EditComicInPersonalCollection = "\u001B[36m>>To edit a comic in your personal collection -> \"edit\", <exact comic name>, <field to be edited>, <new value>";
        String GradeComicPersonalCollection = "\u001B[37m>>To grade a comic in your personal collection -> \"grade\", <exact comic name>, <value 1 to 10>";
        String ComicSlab = "\u001B[33m>>To slab a graded comic -> \"slab\", <exact comic name>\u001B[0m";

        String commands = String.format("\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
                quitter,
                PersonalCollectionSearchCommand,
                DataBaseSearchCommand,
                PersonalCollectionSortCommand,
                AddComicFROMDBtoPersonalCollection,
                AddComicManuallytoPersonalCollection,
                EditComicInPersonalCollection,
                GradeComicPersonalCollection,
                ComicSlab);

        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();

        PersonalCollection personalCollection = new PersonalCollection();

        HashMap<String, CollectionSearcher> searchOptions = new HashMap<>();
        searchOptions.put("title", new SearchByTitle(false));
        searchOptions.put("description", new SearchByDescription(false));
        searchOptions.put("creators", new SearchByCreators(false));

        HashMap<String, CollectionSorter> sortOptions = new HashMap<>();
        sortOptions.put("volume", new SortByVolume());
        sortOptions.put("date", new SortByDate());
        sortOptions.put("issue number", new SortByIssueNumber());
        sortOptions.put("title", new SortByTitle());

        while (!result.equals("quit")) {
            try {
                personalCollection.initializeComics();

                String[] multiResult = result.split(", ");

                String command = multiResult[0];

                if (command.equals("search collection")) {
                    if (multiResult[3].equals("exact")) {
                        searchOptions.get(multiResult[1]).setExactMatch(true);
                    } else {
                        searchOptions.get(multiResult[1]).setExactMatch(false);
                    }
                    personalCollection.setSearch(searchOptions.get(multiResult[1]));
                    System.out.println(personalCollection.doSearch(multiResult[2]));
                }

                if (command.equals("search database")) {
                    if (multiResult[3].equals("exact")) {
                        searchOptions.get(multiResult[1]).setExactMatch(true);
                    } else {
                        searchOptions.get(multiResult[1]).setExactMatch(false);
                    }
                    personalCollection.setSearch(searchOptions.get(multiResult[1]));
                    System.out.println(personalCollection.doDatabaseSearch(multiResult[2]));
                }

                if (command.equals("sort collection")) {
                    personalCollection.setSort(sortOptions.get(multiResult[1]));
                    System.out.println(personalCollection.doSort());
                }

                if (command.equals("add from database")) {
                    personalCollection.addComicByDataBase(multiResult[1]);
                    personalCollection.convertBackToJson();
                }

                if (command.equals("add")) {
                    personalCollection.addComicManually(multiResult[6], multiResult[1], multiResult[4],
                            Integer.parseInt(multiResult[3]), multiResult[2], multiResult[7], multiResult[9],
                            multiResult[5], multiResult[8]);
                    personalCollection.convertBackToJson();
                }

                if(command.equals("edit")){

                }

                if(command.equals("grade")){

                }

                if(command.equals("slab")){
                    
                }

                if (command.equals("lc")) {
                    System.out.println(commands);
                    result = scanner.nextLine();
                    continue;
                }
            } catch (Exception e) {
                System.out
                        .println("Incorrect format, commands should be comma seperated, type LC to view all commands");
            }

            result = scanner.nextLine();
        }

        System.out.println("Thank you for using COMIX, log back in to continue growing your personal collection!\n");
        scanner.close();
    }
}