package src.command;

import src.Comic;
import src.iPersonalCollection;

public class signCommand implements Command{
    private iPersonalCollection collection;
    private Comic comic;
    private String signature;

    public signCommand(iPersonalCollection collection, String comicName, String signature){
        this.collection = collection;
        this.comic = this.collection.getComicInCollection(comicName);
        this.signature = signature;
    }

    @Override
    public void execute() {
        this.collection.sign(comic.getStoryTitle(), this.signature);
    }

    @Override
    public void undo() {
        this.collection.unsignComic(comic, signature);
    }
}