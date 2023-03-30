package src;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


import src.search.CollectionSearcher;
import src.search.SearchByCreators;
import src.search.SearchByDescription;
import src.search.SearchByTitle;

public class PTUI {

    private static final String RED_COLOR_CODE = "\033[31m";
    private static final String GREEN_COLOR_CODE = "\033[32m";
    private static final String BLUE_COLOR_CODE = "\033[34m";
    private static final String YELLOW_COLOR_CODE = "\033[33m";
    private static final String RESET_COLOR_CODE = "\033[0m";
    private static final String[] CODES = { RED_COLOR_CODE, GREEN_COLOR_CODE, BLUE_COLOR_CODE, YELLOW_COLOR_CODE };

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

        for (String line : comix) {
            System.out.println("\t\t\t" + CODES[new Random().nextInt(CODES.length)] + line);
        }
        System.out.println(RESET_COLOR_CODE);

        System.out.println("\033[0m" + "\n\n\033[41m"
                + "\t\t\t\t***********************************************************************"
                + "\033[0m");
        System.out.println(
                "\t\t\t\t\033[41m*\033[0m Welcome to COMIX! Maintain your own personal collection here!       \033[41m*\033[0m\n"
                        + "\t\t\t\t\033[41m*\033[0m To see a list and format of all commands type \033[4;37m<lc>\033[0m                  \033[41m*\033[0m"
                        + "\n\t\t\t\t\033[41m*\033[0m Type out all commands in the \033[4;37mproper format\033[0m to properly execute them \033[41m*\033[0m\n"
                        + "\033[41m" + "\t\t\t\t***********************************************************************"
                        + "\033[0m");

        String quitter = ">>To end the application -> \"quit\"";
        String PersonalCollectionSearchCommand = ">>To search your personal collection -> \"search collection\", <search type>, <term>, <exact or partial>. <sort type>";
        String DataBaseSearchCommand = ">>To search the database -> <search database>, \"search type\", <term>, <exact or partial>";
        String AddComicFROMDBtoPersonalCollection = ">>To add comic from the database to your personal collection -> \"add from database>\", <exact comic name>";
        String AddComicManuallytoPersonalCollection = ">>To add a comic manually to your personal collection-> \"add\", <series>, <issue>, <volume>, <title>, <description>, <publisher>, <release date>, <value> <[creator1, creator2, ...]>";
        String EditComicInPersonalCollection = ">>To edit a comic in your personal collection -> \"edit\", <exact comic name>, <field to be edited>, <new value>";
        String GradeComicPersonalCollection = ">>To grade a comic in your personal collection -> \"grade\", <exact comic name>, <value 1 to 10>";
        String ComicSlab = ">>To slab a graded comic -> \"slab\", <exact comic name>";
        String RemoveComic = ">>To remove a comic from the personal colection -> \"remove\", <exact comic name>";
        String viewBooks = ">>To view your personal collection -> \"view\"";

        String commands = String.format("\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
                quitter,
                PersonalCollectionSearchCommand,
                DataBaseSearchCommand,
                AddComicFROMDBtoPersonalCollection,
                AddComicManuallytoPersonalCollection,
                EditComicInPersonalCollection,
                GradeComicPersonalCollection,
                ComicSlab,
                RemoveComic,
                viewBooks);

        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();

        PersonalCollection personalCollection = new PersonalCollection();

        HashMap<String, CollectionSearcher> searchOptions = new HashMap<>();
        searchOptions.put("title", new SearchByTitle(false));
        searchOptions.put("description", new SearchByDescription(false));
        searchOptions.put("creators", new SearchByCreators(false));


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
                    List<Comic> listy = (personalCollection.doSearch(multiResult[2], multiResult[4]));
                    StringBuilder sb = new StringBuilder();
                    sb.append("\033[1m"); // Bold formatting
                    sb.append(String.format("%-20s | %-20s | %-20s | %-20s |%-20s | %-20s | %-20s | %-10s| %-10s", "Publisher", "Series Title", "Story Title", "Description" ,"Volume Number", "Issue Number", "Publication Date", "Value", "Graded"));
                    sb.append("\033[0m\n"); // Reset formatting to default and add new line
                    sb.append("_".repeat(175)); // Underscores
                    sb.append(System.lineSeparator());
                
                    for (Comic comic : listy) {
                        if(comic.getSeriesTitle().length() > 20){
                            comic.setSeriesTitle(comic.getSeriesTitle().substring(0, 17) + "...");
                        }
                
                        if(comic.getStoryTitle().length() > 20){
                            comic.setStoryTitle(comic.getStoryTitle().substring(0, 17) + "...");
                        }
            
                        if(comic.getDescription().length() > 20){
                            comic.setDescription(comic.getDescription().substring(0, 17) + "...");
                        }

                        if(comic.getPublisher().toString().length() > 20){
                            comic.setPublisher(comic.getPublisher().toString().substring(0, 17) + "...");
                        }
            
                        sb.append(String.format("%-20s | %-20s | %-20s | %-20s |%-20s | %-20s | %-20s | %-10s| %-10s", comic.getPublisher(), comic.getSeriesTitle(), comic.getStoryTitle(), comic.getDescription() ,comic.getVolumeNumber(), comic.getIssueNumber(), comic.getPublicationDate(), comic.getValue(), comic.getIsGraded()));
                        sb.append(System.lineSeparator());
                        sb.append("_".repeat(175)); // Underscores            
                        sb.append(System.lineSeparator());
                    }
                    System.out.println("\n\n"+sb.toString());
                }

                else if (command.equals("search database")) {
                    if (multiResult[3].equals("exact")) {
                        searchOptions.get(multiResult[1]).setExactMatch(true);
                    } else {
                        searchOptions.get(multiResult[1]).setExactMatch(false);
                    }
                    personalCollection.setSearch(searchOptions.get(multiResult[1]));
                    System.out.println(personalCollection.doDatabaseSearch(multiResult[2]));
                }

                else if (command.equals("add from database")) {
                    personalCollection.addComicByDataBase(multiResult[1]);
                    personalCollection.convertBackToJson();
                }

                else if (command.equals("add")) {
                    personalCollection.addComicManually(multiResult[6], multiResult[1], multiResult[4],
                            Integer.parseInt(multiResult[3]), multiResult[2], multiResult[7], multiResult[9],
                            multiResult[5], multiResult[8]);
                    personalCollection.convertBackToJson();
                }

                else if (command.equals("edit")) {
                    personalCollection.editComic(multiResult[1], multiResult[2], multiResult[3]);
                    personalCollection.convertBackToJson();
                }

                else if (command.equals("grade")) {
                    personalCollection.editGrade(multiResult[1], Integer.parseInt(multiResult[2]));
                    personalCollection.convertBackToJson();
                }

                else if (command.equals("slab")) {
                    personalCollection.editSlab(multiResult[1]);
                    personalCollection.convertBackToJson();
                }

                else if (command.equals("remove")) {
                    personalCollection.removeComic(multiResult[1]);
                    personalCollection.convertBackToJson();
                }

                else if(command.equals("view")){
                    String type = multiResult[1];
                    switch (type) {
                        case "publisher":
                            personalCollection.viewPublisher();
                            break;
                        case "series":
                            personalCollection.viewSeriesTitle();
                            break;
                        case "volume":
                            personalCollection.viewVolumeNumber();
                            break;
                        case "issue":
                            personalCollection.viewIssueNumber();
                            break;
                        case "collection":
                        personalCollection.PrettyPrintDatabase();
                            break;
                        default:
                            System.out.println("Command not recognized, your options are: publisher, series, volume, issue, collection");
                            break;
                    }
                }

                else if (command.equals("lc")) {
                    System.out.println(commands);
                }

                else {
                    System.out.println("Command not recognized");
                }
            } catch (Exception e) {
                // e.printStackTrace();
                System.out.println("Incorrect format, commands should be comma seperated, type LC to view all commands");
            }

            result = scanner.nextLine();
        }

        System.out.println("Thank you for using COMIX, log back in to continue growing your personal collection!\n");
        scanner.close();
    }
}