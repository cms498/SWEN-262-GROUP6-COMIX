package src;

import java.util.Random;

public class PTUIFormatting {
    private static final String RED_COLOR_CODE = "\033[31m";
    private static final String GREEN_COLOR_CODE = "\033[32m";
    private static final String BLUE_COLOR_CODE = "\033[34m";
    private static final String YELLOW_COLOR_CODE = "\033[33m";
    private static final String RESET_COLOR_CODE = "\033[0m";
    private static final String[] CODES = { RED_COLOR_CODE, GREEN_COLOR_CODE, BLUE_COLOR_CODE, YELLOW_COLOR_CODE };

    public void formatting() {

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
    }

    public void commands() {
        String quit = ">> To end the application -> quit";
        String login = ">> To login and access more features -> login";
        String command = ">> To list all commands -> lc";
        String viewComics = ">> To view your personal collection -> view | <category>";
        String viewType = "\t>> Viewing categories include [publisher, series title, volume number, issue number, whole collection]";
        String databaseSearch = ">> To search the Database for a comic -> search database | <search type> | <search term> | <exact or partial> ";
        String databaseSearchType = "\t>> Database search types include [series title, issue number, story title, publisher, release date, creators]";
        String collectionSearch = ">> To search your personal collection for a comic -> search collection | <search type> | <exact or partial> | <term> | <sort type>";
        String collectionSearchType = "\t>> Personal collection search types include [series title, creators, description, issue number, runs, gaps]";
        String collectionSearchNoExact = ">> To search your personal collection for comics that have been graded, slabbed, signed, or authenticated -> search collection | <search type> | <sort type>";
        String collectionSortType = "\t>> Personal collection sort types include [series title, volume number, date, issue number]";
        String addFromDB = ">> To add a comic from the database to your personal collection -> add from database | <series title> | <volume number> | <issue number>";
        String addManually = ">> To add a comic to your collection manually -> add | <series title> | <issue number> | <volume number> | <story title> | <description> |\n\t\t <publisher> | <release date> | <value> | <[creator1, creator2, ...]";
        String remove = ">> To remove a comic from your personal collection -> remove | <comic story title>";
        String edit = ">> To edit a comic in your personal collection -> edit | <comic story title> | <field to be edited> | <new value>";
        String editOptions = "\t>> Fields to be edited include [publisher, series title, story title, volume number, issue number, publication date, creator, description, value]";
        String gradeComic = ">> To grade a comic -> grade | <comic story title> | <value 1 to 10>";
        String slabComic = ">> To slab a graded comic -> slab | <comic story title>";
        String signComic = ">> To sign a comic -> sign | <comic story title> | <signature>";
        String authenticateComic = ">> To authenticate a signed comic -> authenticate | <comic story title>";
        String export = ">> To export your personal collection -> export | <export format>";
        String exportType = "\t>> Personal collections can be formatted into [csv, xml]";
        String undo = ">> To undo a command done on the personal collection -> undo";
        String undoType = "\t>> You can undo the following commands [adding, removing, editing, grading, slabbing, signing, authenticating]";
        String redo = ">> To redo any previously undone commands -> redo";

        String commands = String.format(
                "\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
                quit, login, command, viewComics, viewType, databaseSearch,
                databaseSearchType, collectionSearch, collectionSearchType,
                collectionSortType, collectionSearchNoExact, collectionSortType,
                addFromDB, addManually, remove, edit, editOptions, gradeComic,
                slabComic, signComic, authenticateComic, export, exportType, 
                undo, undoType, redo);

        System.out.println(commands);
    }
}