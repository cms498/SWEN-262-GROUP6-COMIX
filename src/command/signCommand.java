package src.command;

import src.Comic;
import src.iPersonalCollection;

/*
 * Concrete Command, adds signatures to comics from the user's personal collection. 
 * This command can also be undone and redone.
 */
public class signCommand implements Command {
    private iPersonalCollection collection;
    private Comic comic;
    private String signature;

    public signCommand(iPersonalCollection collection, String comicName, String signature) {
        this.collection = collection;
        this.comic = this.collection.getComicInCollection(comicName);
        this.signature = signature;
    }

    @Override
    /*
     * Executes the command to adds signatures to comics from the user's personal
     * collection. This would also be used for when a user wants to redo this
     * command once they have undone it.
     */
    public void execute() {
        this.collection.sign(comic.getStoryTitle(), this.signature);
    }

    @Override
    /*
     * Executes the undo command for signing comics from the user's
     * personal collection by removing the signtuare that the user inputted and
     * subtracting the overall value of the comic back to what is was before.
     */
    public void undo() {
        this.collection.unsignComic(comic, signature);
    }
}