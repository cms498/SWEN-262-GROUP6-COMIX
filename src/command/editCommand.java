package src.command;

import src.Comic;
import src.iPersonalCollection;

public class editCommand implements Command{
    private iPersonalCollection collection;
    private Comic comic;
    private String field;
    private String oldValue;
    private String newValue;
    private String commandType;

    public editCommand(iPersonalCollection collection, String comicName, String field, String newValue, String commandType){
        this.collection = collection;
        this.comic = collection.getComicInCollection(comicName);
        this.field = field;
        this.newValue = newValue;
        this.commandType = commandType;

        if(field.equals("publisher")){
            oldValue = comic.getPublisher().toString();
        }
        if(field.equals("seriestitle")){
            oldValue = comic.getSeriesTitle();
        }
        if(field.equals("storytitle")){
            oldValue = comic.getStoryTitle();
        }
        if(field.equals("volumenumber")){
            oldValue = comic.getVolumeNumber()+"";
        }
        if(field.equals("issuenumber")){
            oldValue = comic.getIssueNumber();
        }
        if(field.equals("publicationdate")){
            oldValue = comic.getPublicationDate();
        }
        if(field.equals("creator")){
            oldValue = comic.getCreators().toString();
        }
        if(field.equals("description")){
            oldValue = comic.getDescription();
        }
        if(field.equals("value")){
            oldValue = comic.getValue()+"";
        }
    }

    @Override
    public void execute() { 
        if(field.equals("storytitle")){
            this.collection.editComic(oldValue, field, newValue);
        }
        else{
            this.collection.editComic(comic.getStoryTitle(), field, newValue);
        }
    }

    @Override
    public void undo() {
        if(field.equals("storytitle")){
            this.collection.editComic(newValue, field, oldValue);
        }
        else{
            this.collection.editComic(comic.getStoryTitle(), field, oldValue);
        }
    }

}
