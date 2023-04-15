package src.command;

import src.Comic;
import src.iPersonalCollection;

/*
 * Concrete Command, authenticates the signatures of a comic from the user's personal collection.
 * This command can also be undone and redone
 */
public class authenticateCommand implements Command {
    private iPersonalCollection collection;
    private Comic comic;

    public authenticateCommand(iPersonalCollection collection, String comicName) {
        this.collection = collection;
        this.comic = this.collection.getComicInCollection(comicName);
    }

    @Override
    /*
     * Executes the command to authenticate the signatures of a comic from the
     * user's personal collection. This would also be used for when a user wants to
     * redo this command once they have undone it.
     */
    public void execute() {
        this.collection.authenticate(comic.getStoryTitle());
    }

    @Override
    /*
     * Executes the undo command for authenticate the signatures of a comic from the
     * user's personal collection by unauthenticating the signatures of a comic from
     * the user's personal collection and subtracting the overall value of the comic
     * back to what is was before.
     */
    public void undo() {
        this.collection.unauthenticateComic(comic);
    }
}