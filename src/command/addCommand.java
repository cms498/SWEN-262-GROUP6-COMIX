package src.command;

import src.Comic;
import src.iPersonalCollection;

public class addCommand implements Command{
    private iPersonalCollection collection;
    private Comic comic;
    private String commandType;

    public addCommand(iPersonalCollection collection, Comic comic, String commandType){
        this.collection = collection;
        this.commandType = commandType;
        this.comic = comic;
    }

    @Override
    public void execute() {
        if(commandType.equals("add")){
            this.collection.addComicManually(comic.getPublisher().toString(), comic.getSeriesTitle(), comic.getStoryTitle(), comic.getVolumeNumber(), comic.getIssueNumber(), comic.getPublicationDate(), comic.getCreators().toString(), comic.getDescription(), comic.getValue() + "");
        }
        else if (commandType.equals("add from database")){
            this.collection.addComicByDataBase(comic.getSeriesTitle(), comic.getVolumeNumber(), comic.getIssueNumber());
            comic = collection.getComicInCollection2(comic.getSeriesTitle(), comic.getVolumeNumber(), comic.getIssueNumber());
        }
    }

    
    @Override
    public void undo() {
        collection.removeComic(comic.getStoryTitle());
    }
}
