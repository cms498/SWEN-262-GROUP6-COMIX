package src.command;

import src.Comic;
import src.iPersonalCollection;

public class slabCommand implements Command {
    private iPersonalCollection collection;
    private Comic comic;

    public slabCommand(iPersonalCollection collection, String comicName){
        this.collection = collection;
        this.comic = this.collection.getComicInCollection(comicName);
    }

    @Override
    public void execute() {
        this.collection.editSlab(comic.getStoryTitle());
    }

    @Override
    public void undo() {
        this.collection.unslabComic(comic);
    }
}