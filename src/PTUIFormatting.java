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
        String quitter = ">>To end the application -> \"quit\"";
        String PersonalCollectionSearchCommand = ">>To search your personal collection -> \"search collection\", <search type>, <exact or partial>, <term>, <sort type>";
        String PersonalCollectionSearchNoExact = ">>To search for comics that has been graded, slabbed, signed, or authenticated -> \"search collection \', <search type>, <sort type>";
        String DataBaseSearchCommand = ">>To search the database -> <search database>, \"search type\", <term>, <exact or partial>";
        String AddComicFROMDBtoPersonalCollection = ">>To add comic from the database to your personal collection -> \"add from database\", <series>, <volume>, <issue>";
        String AddComicManuallytoPersonalCollection = ">>To add a comic manually to your personal collection-> \"add\", <series>, <issue>, <volume>, <title>, <description>, <publisher>, <release date>, <value>, <[creator1, creator2, ...]>";
        String EditComicInPersonalCollection = ">>To edit a comic in your personal collection -> \"edit\", <exact comic name>, <field to be edited>, <new value>";
        String GradeComicPersonalCollection = ">>To grade a comic in your personal collection -> \"grade\", <exact comic name>, <value 1 to 10>";
        String ComicSlab = ">>To slab a graded comic -> \"slab\", <exact comic name>";
        String RemoveComic = ">>To remove a comic from the personal colection -> \"remove\", <exact comic name>";
        String AuthenticateComic = ">>To authenticate a comic -> \"authenticate\", <exact comic name>";
        String sign = ">>To sign a comic -> \"sign\", <exact comic name>, <signature>";
        String viewBooks = ">>To view your personal collection -> \"view\", <category>";
        String logIn = ">>To log in and have more features -> login";
        String export = ">>To export your personal collection into various types -> \"export\", <export format>";
        String undo = ">>To Undo a command done on the personal collection -> \"undo \"";
        String redo = ">>To redo a previously undone command -> \"redo\"";

        String commands = String.format("\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
                quitter, PersonalCollectionSearchCommand, PersonalCollectionSearchNoExact,
                DataBaseSearchCommand, AddComicFROMDBtoPersonalCollection,
                AddComicManuallytoPersonalCollection, EditComicInPersonalCollection,
                GradeComicPersonalCollection, ComicSlab, RemoveComic,
                sign, AuthenticateComic, viewBooks, export,
                undo, redo, logIn);

        System.out.println(commands);
    }
}
