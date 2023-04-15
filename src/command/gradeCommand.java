package src.command;

import src.Comic;
import src.iPersonalCollection;

public class gradeCommand implements Command {
    private iPersonalCollection collection;
    private Comic comic;
    private int gradeNumber;
    private double difference;

    public gradeCommand(iPersonalCollection collection, String storytitle, int gradeNumber){
        this.collection = collection;
        this.comic = this.collection.getComicInCollection(storytitle);
        this.gradeNumber = gradeNumber;
        this.difference = 0.0;
    }

    @Override
    public void execute() {
        this.difference = this.collection.editGrade(comic.getStoryTitle(), gradeNumber);
    }


    @Override
    public void undo() {
        this.collection.ungradeComic(comic, difference);
    }
}
