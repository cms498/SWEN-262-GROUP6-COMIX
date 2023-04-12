package src.command;

import src.Comic;
import src.PersonalCollection;

public class addCommand implements Command{
    private PersonalCollection collection;
    private Comic comic;
    private CommandType commandType;

    public addCommand(PersonalCollection collection, Comic comic, CommandType commandType){
        this.collection = collection;
        this.commandType = commandType;
        this.comic = comic;
    }

    @Override
    public void execute() {
        if(commandType == CommandType.ADD_MANUALLY){
            collection.addComicManually(comic.getPublisher().toString(), comic.getSeriesTitle(), comic.getStoryTitle(), comic.getVolumeNumber(), comic.getIssueNumber(), comic.getPublicationDate(), comic.getCreators().toString(), comic.getDescription(), comic.getValue() + "");
        }
        else{
            collection.addComicByDataBase(comic.getSeriesTitle(), comic.getVolumeNumber(), comic.getIssueNumber());
        }
    }
}
