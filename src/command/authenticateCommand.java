package src.command;

import src.Comic;
import src.iPersonalCollection;

public class authenticateCommand implements Command {
    private iPersonalCollection collection;
    private Comic comic;
    private String commandType;

    public authenticateCommand(iPersonalCollection collection, String comicName, String commandType){
        this.collection = collection;
        this.commandType = commandType;
        this.comic = this.collection.getComicInCollection(comicName);
    }

    @Override
    public void execute() {
        if(this.commandType.equals("authenticate")){
            this.collection.authenticate(comic.getStoryTitle());
        }
    }

    @Override
    public void undo() {
        if(this.commandType.equals("authenticate")){
            this.collection.unauthenticateComic(comic);
        }
    } 
}