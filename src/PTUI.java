package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import src.command.Redo;
import src.command.Undo;
import src.command.addCommand;
import src.command.authenticateCommand;
import src.command.editCommand;
import src.command.gradeCommand;
import src.command.removeCommand;
import src.command.signCommand;
import src.command.slabCommand;
import src.export.CSVAdapter;
import src.export.ExporterInterface;
import src.export.XMLAdapter;
import src.search.CollectionSearcher;
import src.search.SearchByAuthenticated;
import src.search.SearchByCreators;
import src.search.SearchByDescription;
import src.search.SearchByGaps;
import src.search.SearchByGrade;
import src.search.SearchByIssueNumber;
import src.search.SearchByPublisher;
import src.search.SearchByReleaseDate;
import src.search.SearchByRuns;
import src.search.SearchBySeriesTitle;
import src.search.SearchBySignedComics;
import src.search.SearchBySlab;
import src.search.SearchByStoryTitle;

/*
 * Manages the running of the entire programming, and all the interactions with the user
 */
public class PTUI {

    public static void main(String[] args) {
        PTUIFormatting formatter = new PTUIFormatting();
        formatter.formatting();

        Scanner scanner = new Scanner(System.in);
        System.out.print(">>");
        String result = scanner.nextLine();

        iPersonalCollection personalCollection = new PersonalCollectionProxy(true);

        HashMap<String, CollectionSearcher> searchOptions = new HashMap<>();
        searchOptions.put("series title", new SearchBySeriesTitle(false));
        searchOptions.put("issue number", new SearchByIssueNumber(false));
        searchOptions.put("story title", new SearchByStoryTitle(false));
        searchOptions.put("publisher", new SearchByPublisher(false));
        searchOptions.put("release date", new SearchByReleaseDate(false));
        searchOptions.put("description", new SearchByDescription(false));
        searchOptions.put("creators", new SearchByCreators(false));
        searchOptions.put("graded", new SearchByGrade(false));
        searchOptions.put("slabbed", new SearchBySlab(false));
        searchOptions.put("signed", new SearchBySignedComics());
        searchOptions.put("authenticated", new SearchByAuthenticated());
        searchOptions.put("runs", new SearchByRuns());
        searchOptions.put("gaps", new SearchByGaps());

        HashMap<String, ExporterInterface> exportOptions = new HashMap<>();
        exportOptions.put("csv", new CSVAdapter());
        exportOptions.put("xml", new XMLAdapter());

        Undo undoStack = new Undo();
        Redo redoStack = new Redo();
        undoStack.setRedo(redoStack);
        redoStack.setUndo(undoStack);

        while (!result.equals("quit")) {
            try {
                personalCollection.initializeComics();

                result = result.replace(" | ", "@");
                String[] multiResult = result.split("@");

                String command = multiResult[0];

                if (command.equals("search collection")) {
                    List<Comic> listy;
                    // code for searching for graded, slabbed, signed, authenticated
                    if (multiResult.length == 3) {
                        personalCollection.setSearch(searchOptions.get(multiResult[1]));
                        listy = personalCollection.doSearch("true", multiResult[2]);
                    } else { // code for every other search type
                        if (multiResult[2].equals("exact")) {
                            searchOptions.get(multiResult[1]).setExactMatch(true);
                        } else {
                            searchOptions.get(multiResult[1]).setExactMatch(false);
                        }
                        personalCollection.setSearch(searchOptions.get(multiResult[1]));
                        listy = (personalCollection.doSearch(multiResult[3], multiResult[4]));
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("\033[1m"); // Bold formatting
                    sb.append(String.format("%-20s | %-20s | %-20s | %-20s |%-20s | %-20s | %-20s | %-10s| %-10s",
                            "Publisher", "Series Title", "Story Title", "Description", "Volume Number", "Issue Number",
                            "Publication Date", "Value", "Graded"));
                    sb.append("\033[0m\n"); // Reset formatting to default and add new line
                    sb.append("_".repeat(175)); // Underscores
                    sb.append(System.lineSeparator());

                    if (listy != null) {
                        for (Comic comic : listy) {

                            if (comic.getSeriesTitle().length() > 20 && comic.getSeriesTitle() != null) {
                                comic.setSeriesTitle(comic.getSeriesTitle().substring(0, 17) + "...");
                            }

                            if (comic.getStoryTitle() != null) {
                                if (comic.getStoryTitle().length() > 20) {
                                    comic.setStoryTitle(comic.getStoryTitle().substring(0, 17) + "...");
                                }
                            }

                            if (comic.getDescription() != null) {
                                if (comic.getDescription().length() > 20) {
                                    comic.setDescription((comic.getDescription().substring(0, 17) + "..."));
                                }
                            }

                            if (comic.getPublisher() != null) {
                                if (comic.getPublisher().toString().length() > 20) {
                                    comic.setPublisher(comic.getPublisher().toString().substring(0, 17) + "...");
                                }
                            }

                            sb.append(
                                    String.format("%-20s | %-20s | %-20s | %-20s |%-20s | %-20s | %-20s | %-10s| %-10s",
                                            comic.getPublisher(), comic.getSeriesTitle(), comic.getStoryTitle(),
                                            comic.getDescription(), comic.getVolumeNumber(), comic.getIssueNumber(),
                                            comic.getPublicationDate(), comic.getValue(), comic.getIsGraded()));
                            sb.append(System.lineSeparator());
                            sb.append("_".repeat(175)); // Underscores
                            sb.append(System.lineSeparator());
                        }
                        System.out.println("\n\n" + sb.toString());
                    }

                }

                else if (command.equals("search database")) {

                    if (multiResult[3].equals("exact")) {
                        searchOptions.get(multiResult[1]).setExactMatch(true);
                    } else {
                        searchOptions.get(multiResult[1]).setExactMatch(false);
                    }
                    personalCollection.setSearch(searchOptions.get(multiResult[1]));
                    List<Comic> listy = (personalCollection.doDatabaseSearch(multiResult[2]));
                    personalCollection.printDatabase(listy);

                }

                else if (command.equals("add from database")) {
                    addCommand adder = new addCommand(personalCollection,
                            new Comic(multiResult[1], Integer.parseInt(multiResult[2]), multiResult[3]),
                            command);
                    adder.execute();
                    undoStack.addCommand(adder);
                }

                else if (command.equals("add")) {
                    List<Creator> creatorsList = new ArrayList<>();
                    String creators = multiResult[9];
                    creators = creators.substring(1, creators.length() - 1);
                    String[] creatorArr = creators.split(",");
                    for (String creatorName : creatorArr) {
                        creatorsList.add(new Creator(creatorName));
                    }
                    addCommand adder = new addCommand(personalCollection,
                            new Comic(new Publisher(multiResult[6]), multiResult[1], multiResult[4],
                                    Integer.parseInt(multiResult[3]), multiResult[2], multiResult[7], creatorsList,
                                    multiResult[5], Double.parseDouble(multiResult[8]), false, false,
                                    new ArrayList<String>(), false, 0),
                            command);
                    adder.execute();
                    undoStack.addCommand(adder);
                }

                else if (command.equals("edit")) {
                    editCommand editor = new editCommand(personalCollection, multiResult[1], multiResult[2],
                            multiResult[3]);
                    try {
                        editor.execute();
                    } catch (Exception e) {

                    }
                    undoStack.addCommand(editor);
                }

                else if (command.equals("grade")) {
                    gradeCommand grader = new gradeCommand(personalCollection, multiResult[1],
                            Integer.parseInt(multiResult[2]));
                    grader.execute();
                    undoStack.addCommand(grader);
                }

                else if (command.equals("slab")) {
                    slabCommand slabCommand = new slabCommand(personalCollection, multiResult[1]);
                    try {
                        slabCommand.execute();
                    } catch (Exception e) {

                    }
                    undoStack.addCommand(slabCommand);
                }

                else if (command.equals("remove")) {
                    removeCommand removeCommand = new removeCommand(personalCollection, multiResult[1]);
                    try {
                        removeCommand.execute();
                    } catch (Exception e) {
                    }
                    undoStack.addCommand(removeCommand);
                }

                else if (command.equals("authenticate")) {
                    authenticateCommand authenticateCommand = new authenticateCommand(personalCollection,
                            multiResult[1]);
                    try {
                        authenticateCommand.execute();
                    } catch (Exception e) {

                    }
                    undoStack.addCommand(authenticateCommand);
                }

                else if (command.equals("sign")) {
                    signCommand signer = new signCommand(personalCollection, multiResult[1], multiResult[2]);
                    signer.execute();
                    undoStack.addCommand(signer);
                }

                else if (command.equals("view")) {
                    String type = multiResult[1];
                    switch (type) {
                        case "publisher":
                            personalCollection.viewPublisher();
                            break;
                        case "series title":
                            personalCollection.viewSeriesTitle();
                            break;
                        case "volume number":
                            personalCollection.viewVolumeNumber();
                            break;
                        case "issue number":
                            personalCollection.viewIssueNumber();
                            break;
                        case "whole collection":
                            personalCollection.PrettyPrintDatabase();
                            break;
                        default:
                            System.out.println(
                                    "Command not recognized, your options are: publisher, series, volume, issue, collection");
                            break;
                    }
                }

                else if (command.equals("export")) {
                    if (personalCollection.isGuestMode()) {
                        System.out.println("Log in to access feature");
                    } else {
                        exportOptions.get(multiResult[1].toLowerCase()).export();
                    }
                }

                else if (command.equals("undo")) {
                    try {
                        undoStack.execute();
                    } catch (Exception e) {
                        
                    }
                }

                else if (command.equals("redo")) {
                    redoStack.execute();
                }

                else if (command.equals("lc")) {
                    formatter.commands();
                }

                else if (command.equals("login")) {
                    personalCollection.setGuestMode(false);
                }

                else {
                    System.out.println("Command not recognized");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out
                        .println("Incorrect format, commands should be comma seperated, type LC to view all commands");
            }

            System.out.print(">>");
            result = scanner.nextLine();
        }

        System.out.println("Thank you for using COMIX, log back in to continue growing your personal collection!\n");
        scanner.close();
    }
}