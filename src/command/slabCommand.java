package src.command;

import src.Comic;
import src.iPersonalCollection;

/*
 * Concrete Command, slabs comics from the user's personal collection. 
 * This command can also be undone and redone.
 */
public class slabCommand implements Command {
    private iPersonalCollection collection;
    private Comic comic;

    public slabCommand(iPersonalCollection collection, String comicName) {
        this.collection = collection;
        this.comic = this.collection.getComicInCollection(comicName);
    }

    @Override
    /*
     * Executes the command to slab comics from the user's personal
     * collection. This would also be used for when a user wants to redo this
     * command once they have undone it.
     */
    public void execute() {
        this.collection.editSlab(comic.getStoryTitle());
    }

    @Override
    /*
     * Executes the undo command for slabbing comics from the user's
     * personal collection by removing the slab and subtracting
     * the overall value of the comic back to what is was before.
     */
    public void undo() {
        this.collection.unslabComic(comic);
    }
}