package src;

import java.util.Scanner;

public class PTUI{

    public static void main(String[] args) {
        System.out.println("Welcome to COMIX! Maintain your own personal collection here!");
        System.out.println("To see a list and format of all commands type <lc>");
        String commands = String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n", 
                                "Type out all commands in the proper format to properly execute them",
                        "To end the application -> <quit>",
                        "To search your personal collection -> <search collection> <search type> <exact or partial>",
                        "To search the database -> <search database> <search type> <exact or partial>",
                        "To sort your personal collection -> <sort> <sort type>",
                        "To add comic from the database to your personal collection -> <add from database> <exact comic name>",
                        "To add a comic manually to your personal collection-> <add> <series> <issue> <volume> <title> <description> <publisher> <release date> <value>",
                        "To remove a comic in your personal collection-> <remove> <exact comic name>",
                        "To edit a comic in your personal collection -> <edit> <exact comic name> <field to be edited> <new value>",
                        "To grade a comic in your personal collection -> <grade> <exact comic name> <value 1 to 10>",
                        "To slab a graded comic -> <slab> <exact comic name>");

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