package src.command;

import src.Comic;
import src.iPersonalCollection;

/*
 * Concrete Command that takes care of removing a comic from the personal collection
 */
public class removeCommand implements Command{
    private iPersonalCollection collection;
    private String comicName;
    private CommandType commandType;
    private Comic comic;

    public removeCommand(iPersonalCollection collection) {
        this.collection = collection;
        this.comicName = null;
        this.commandType = null;
        this.comic = null;
    }

    
    /** 
     * @param comic
     * Sets the current comic that the command modifies
     */
    public void setComic(String comicName) {
        this.comicName = comicName;
        this.comic = this.collection.getComicInCollection(comicName);
    }

    
    /** 
     * @param commandType
     * Sets the command type to the desired type
     */
    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    @Override
    /**
     * If the CommandType is remove, will remove the given comic
     * from the personal collection
     */
    public void execute() {
        if(commandType == CommandType.REMOVE)
        collection.removeComic(comicName);
    }

    @Override
    /*
     * If the CommandType is remove, adds the given 
     * comic back into the personal collection
     */
    public void undo() {
        if(commandType == CommandType.REMOVE) {
            collection.addComic(comic);
        }
    }
}
