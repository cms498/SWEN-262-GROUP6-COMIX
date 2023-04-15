package src.command;

import src.Comic;
import src.iPersonalCollection;

/*
 * Concrete Command, grades comics from the user's personal collection. 
 * This command can also be undone and redone.
 */
public class gradeCommand implements Command {
    private iPersonalCollection collection;
    private Comic comic;
    private int gradeNumber;
    private double difference;

    public gradeCommand(iPersonalCollection collection, String storytitle, int gradeNumber) {
        this.collection = collection;
        this.comic = this.collection.getComicInCollection(storytitle);
        this.gradeNumber = gradeNumber;
        this.difference = 0.0;
    }

    @Override
    /*
     * Executes the command to grade comics from the user's personal
     * collection. This would also be used for when a user wants to redo this
     * command once they have undone it.
     */
    public void execute() {
        try {
            this.difference = this.collection.editGrade(comic.getStoryTitle(), gradeNumber);
        } catch (Exception e) {
            
        }
    }

    @Override
    /*
     * Executes the undo command for grading comics from the user's
     * personal collection by removing the grade that the user made and subtracting
     * the overall value of the comic back to what is was before.
     */
    public void undo() {
        this.collection.ungradeComic(comic, difference);
    }
}
