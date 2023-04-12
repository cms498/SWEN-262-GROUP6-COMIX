package src.command;

import src.Comic;
import src.iPersonalCollection;

public class addCommand implements Command{
    private iPersonalCollection collection;
    private Comic comic;
    private CommandType commandType;

    public addCommand(iPersonalCollection collection, Comic comic, CommandType commandType){
        this.collection = collection;
        this.commandType = commandType;
        this.comic = comic;
    }

    @Override
    public void execute() {
        if(commandType == CommandType.ADD_MANUALLY){
            collection.addComicManually(comic.getPublisher().toString(), comic.getSeriesTitle(), comic.getStoryTitle(), comic.getVolumeNumber(), comic.getIssueNumber(), comic.getPublicationDate(), comic.getCreators().toString(), comic.getDescription(), comic.getValue() + "");
        }
        else if (commandType == CommandType.ADD_DATABASE){
            collection.addComicByDataBase(comic.getSeriesTitle(), comic.getVolumeNumber(), comic.getIssueNumber());
        }
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }
    
    @Override
    public void undo() {
        collection.removeComic(comic.getStoryTitle());
    }
}
