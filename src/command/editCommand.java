package src.command;

import src.Comic;
import src.iPersonalCollection;

/*
 * Concrete Command, edits existing comics from the user's personal collection.
 * This command can also be undone and redone.
 */
public class editCommand implements Command {
    private iPersonalCollection collection;
    private Comic comic;
    private String field;
    private String oldValue;
    private String newValue;

    public editCommand(iPersonalCollection collection, String comicName, String field, String newValue) {
        this.collection = collection;
        this.comic = collection.getComicInCollection(comicName);
        this.field = field;
        this.newValue = newValue;

        // The oldValue variable is used here to stored the existing comic attritube
        // that the user wants to edit.
        if (field.equals("publisher")) {
            oldValue = comic.getPublisher().toString();
        }
        if (field.equals("seriestitle")) {
            oldValue = comic.getSeriesTitle();
        }
        if (field.equals("storytitle")) {
            oldValue = comic.getStoryTitle();
        }
        if (field.equals("volumenumber")) {
            oldValue = comic.getVolumeNumber() + "";
        }
        if (field.equals("issuenumber")) {
            oldValue = comic.getIssueNumber();
        }
        if (field.equals("publicationdate")) {
            oldValue = comic.getPublicationDate();
        }
        if (field.equals("creator")) {
            oldValue = comic.getCreators().toString();
        }
        if (field.equals("description")) {
            oldValue = comic.getDescription();
        }
        if (field.equals("value")) {
            oldValue = comic.getValue() + "";
        }
    }

    @Override
    /*
     * Executes the command to edit existing comics from the user's personal
     * collection. This would also be used for when a user wants to redo this
     * command once they have undone it.
     */
    public void execute() {
        if (field.equals("storytitle")) {
            this.collection.editComic(oldValue, field, newValue);
        } else {
            this.collection.editComic(comic.getStoryTitle(), field, newValue);
        }
    }

    @Override
    /*
     * Executes the undo command for editing comics from the user's
     * personal collection by removing the edit that the user made and replacing
     * with the oldValue comic attribute.
     */
    public void undo() {
        if (field.equals("storytitle")) {
            this.collection.editComic(newValue, field, oldValue);
        } else {
            this.collection.editComic(comic.getStoryTitle(), field, oldValue);
        }
    }

}
