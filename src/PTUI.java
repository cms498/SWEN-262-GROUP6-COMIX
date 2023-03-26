package src;

import java.util.Scanner;

public class PTUI{

    public static void main(String[] args) {
        System.out.println("\n\n\033[1;31m" + "***********************************************************************" + "\033[0m");
        System.out.println("Welcome to COMIX! Maintain your own personal collection here!\033[1;31m\033[0m\n" + "To see a list and format of all commands type \033[4;37m<lc>\033[0m" + "\nType out all commands in the \033[4;37mproper format\033[0m to properly execute them\n" + "\033[1;31m" + "***********************************************************************" + "\033[0m");

        String quitter = "\033[1;90m>>To end the application -> <quit>\u001B[0m";
        String PersonalCollectionSearchCommand = "\033[1;90m\u001B[31m>>To search your personal collection -> <search collection> <search type> <exact or partial>";
        String DataBaseSearchCommand = "\u001B[32m>>To search the database -> <search database> <search type> <exact or partial>";
        String PersonalCollectionSortCommand = "\u001B[33m>>To sort your personal collection -> <sort> <sort type>";
        String AddComicFROMDBtoPersonalCollection = "\u001B[34m>>To add comic from the database to your personal collection -> <add from database> <exact comic name>";
        String AddComicManuallytoPersonalCollection = "\u001B[35m>>To add a comic manually to your personal collection-> <add> <series> <issue> <volume> <title> <description> <publisher> <release date> <value>";
        String EditComicInPersonalCollection = "\u001B[36m>>To edit a comic in your personal collection -> <edit> <exact comic name> <field to be edited> <new value>";
        String GradeComicPersonalCollection = "\u001B[37m>>To grade a comic in your personal collection -> <grade> <exact comic name> <value 1 to 10>";
        String ComicSlab = "\u001B[33m>>To slab a graded comic -> <slab> <exact comic name>\u001B[0m";
        

        String commands = String.format("\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s", 
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

        while(!result.equals("quit")){
            if(result.equals("lc")){
                System.out.println(commands);
                result = scanner.nextLine();
                continue;
            }
            System.out.println(result);
            result = scanner.nextLine();
        }

        scanner.close();
    }
}