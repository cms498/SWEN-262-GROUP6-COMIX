package src.command;

import src.Comic;
import src.iPersonalCollection;

/*
 * Concrete Command that takes care of removing a comic from the personal collection
 */
public class removeCommand implements Command{
    private iPersonalCollection collection;
    private String commandType;
    private Comic comic;

    public removeCommand(iPersonalCollection collection, String comicName, String commandType) {
        this.collection = collection;
        this.commandType = commandType;
        this.comic = this.collection.getComicInCollection(comicName);
    }

    @Override
    /**
     * If the CommandType is remove, will remove the given comic
     * from the personal collection
     */
    public void execute() {
        if(commandType.equals("remove")){
            collection.removeComic(comic.getStoryTitle());
        }
    }

    @Override
    /*
     * If the CommandType is remove, adds the given 
     * comic back into the personal collection
     */
    public void undo() {
        if(commandType.equals("remove")) {
            collection.addComic(comic);
        }
    }
}
