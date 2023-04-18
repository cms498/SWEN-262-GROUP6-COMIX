package src.command;

import src.Comic;
import src.iPersonalCollection;

/*
 * Concrete Command, add comics to the personal collection.
 * This command can also be undone and redone.
 */
public class addCommand implements Command {
    private iPersonalCollection collection;
    private Comic comic;
    private String commandType;

    public addCommand(iPersonalCollection collection, Comic comic, String commandType) {
        this.collection = collection;
        this.commandType = commandType;
        this.comic = comic;
    }

    @Override
    /*
     * Executes the command to add comics to the personal collection.
     * This would also be used for when a user wants to redo this command once they
     * have undone it.
     */
    public void execute() {
        if (commandType.equals("add")) {
            this.collection.addComicManually(comic.getPublisher().toString(), comic.getSeriesTitle(),
                    comic.getStoryTitle(), comic.getVolumeNumber(), comic.getIssueNumber(), comic.getPublicationDate(),
                    comic.getCreators().toString(), comic.getDescription(), comic.getValue() + "");
        } else if (commandType.equals("add from database")) {
            this.collection.addComicByDataBase(comic.getSeriesTitle(), comic.getVolumeNumber(), comic.getIssueNumber());
            comic = collection.getComicInCollection2(comic.getSeriesTitle(), comic.getVolumeNumber(), comic.getIssueNumber());
        }
    }

    @Override
    /*
     * Executes the undo command for add comics to the user's personal collection by
     * removing the comic from the user's personal collection.
     */
    public void undo() {
        collection.removeComic(comic.getStoryTitle());
    }
}
