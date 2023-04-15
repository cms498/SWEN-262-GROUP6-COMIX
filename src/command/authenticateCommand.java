package src.command;

import src.Comic;
import src.iPersonalCollection;

public class authenticateCommand implements Command {
    private iPersonalCollection collection;
    private Comic comic;

    public authenticateCommand(iPersonalCollection collection, String comicName){
        this.collection = collection;
        this.comic = this.collection.getComicInCollection(comicName);
    }

    @Override
    public void execute() {
        this.collection.authenticate(comic.getStoryTitle());
    }

    @Override
    public void undo() {
        this.collection.unauthenticateComic(comic);
    } 
}