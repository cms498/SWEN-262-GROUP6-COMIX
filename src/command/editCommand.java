package src.command;

import src.Comic;
import src.iPersonalCollection;

public class editCommand implements Command{
    private iPersonalCollection collection;
    private Comic comic;
    private Comic newComic;
    private CommandType commandType;

    public editCommand(iPersonalCollection collection){
        this.collection = collection;
        this.comic = null;
        this.newComic = null;
        this.commandType = null;
    }

    @Override
    public void execute() {
        if(commandType == CommandType.EDIT_SERIES_TITLE){
            collection.editComic(comic.getStoryTitle(), "seriestitle", newComic.getSeriesTitle());
        }
        if(commandType == CommandType.EDIT_STORY_TITLE){
            collection.editComic(comic.getStoryTitle(), "storytitle", newComic.getStoryTitle());
        }
        if(commandType == CommandType.EDIT_PUBLISHER){
            collection.editComic(comic.getStoryTitle(), "publisher",newComic.getPublisher().toString());
        }
        if(commandType == CommandType.EDIT_DESCRIPTION){
            collection.editComic(comic.getStoryTitle(), "description", newComic.getDescription());
        }
        if(commandType == CommandType.EDIT_VOLUMENUMBER){
            collection.editComic(comic.getStoryTitle(), "volumenumber", newComic.getVolumeNumber() + "");
        }
        if(commandType == CommandType.EDIT_ISSUENUMBER){
            collection.editComic(comic.getStoryTitle(), "issuenumber", newComic.getIssueNumber());
        }
        if(commandType == CommandType.EDIT_PUBLICATION_DATE){
            collection.editComic(comic.getStoryTitle(), "publicationdate", newComic.getPublicationDate());
        }
        if(commandType == CommandType.EDIT_CREATOR){
            collection.editComic(comic.getStoryTitle(), "creator", null);
        }

        
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }

    public void setNewComic(Comic newComic) {
        this.newComic = newComic;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    @Override
    public void undo() {
        
    }

}
