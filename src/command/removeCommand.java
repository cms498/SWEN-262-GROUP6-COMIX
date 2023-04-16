package src.command;

import src.Comic;
import src.iPersonalCollection;

/*
 * Concrete Command that takes care of removing a comic from the personal collection
 */
public class removeCommand implements Command{
    private iPersonalCollection collection;
    private Comic comic;

    public removeCommand(iPersonalCollection collection, String comicName) {
        this.collection = collection;
        this.comic = this.collection.getComicInCollection(comicName);
    }

    @Override
    /**
     * If the CommandType is remove, will remove the given comic
     * from the personal collection
     */
    public void execute() {
        collection.removeComic(comic.getStoryTitle());
    }

    @Override
    /*
     * If the CommandType is remove, adds the given 
     * comic back into the personal collection
     */
    public void undo() {
        collection.addComic(comic);
    }
}
