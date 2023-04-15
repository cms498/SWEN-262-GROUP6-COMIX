package src.command;

import src.Comic;
import src.iPersonalCollection;

public class slabCommand implements Command {
    private iPersonalCollection collection;
    private String commandType;
    private Comic comic;

    public slabCommand(iPersonalCollection collection, String comicName, String commandType){
        this.collection = collection;
        this.commandType = commandType;
        this.comic = this.collection.getComicInCollection(comicName);
    }

    @Override
    public void execute() {
        if(commandType.equals("slab")){
            this.collection.editSlab(comic.getStoryTitle());
        }
    }

    @Override
    public void undo() {
        if(this.commandType.equals("slab")){
            this.collection.unslabComic(comic);
        }
    }
}