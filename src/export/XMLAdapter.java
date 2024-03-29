package src.export;

import java.io.FileWriter;
import java.io.IOException;

import src.Comic;
import src.PersonalCollection;

/*
 * Class that implements the Exporter Interface to converts the
 * personal collection to a csv file
 */
public class XMLAdapter implements ExporterInterface{

    private PersonalCollection collection;

    /*
     * Constructor, gets all the comics from the current personal collection
     */
    public XMLAdapter() {
        this.collection = new PersonalCollection();
        this.collection.initializeComics();
    }

    /*
     * Gets the personal collection XML file, and writes all the
     * current personal collection data to the file
     */
    @Override
    public void export() {
        try {
            FileWriter write = new FileWriter("data/personalCollection.xml");
            write.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            write.write("<comics>\n");
            for (Comic comic : collection.getComics()) {
                write.write("\t<comic>\n");
                write.write("\t\t<publisher>" + comic.getPublisher() + "</publisher>\n");
                write.write("\t\t<seriesTitle>" + comic.getSeriesTitle() + "</seriesTitle>\n");
                write.write("\t\t<storyTitle>" + comic.getStoryTitle() + "</storyTitle>\n");
                write.write("\t\t<volumeNumber>" + comic.getVolumeNumber() + "</volumeNumber>\n");
                write.write("\t\t<value>" + comic.getValue() + "</value>\n");
                write.write("\t\t<creators>" + comic.getCreators() + "</creators>\n");
                write.write("\t\t<description>" + comic.getDescription() + "</description>\n");
                write.write("\t\t<issueNumber>" + comic.getIssueNumber() + "</issueNumber>\n");
                write.write("\t\t<isGraded>" + comic.getIsGraded() + "</isGraded>\n");
                write.write("\t\t<isSlabbed>" + comic.getIsSlabbed() + "</isSlabbed>\n");
                write.write("\t\t<signatures>" + comic.getSignatures() + "</signatures>\n");
                write.write("\t\t<isAuthenticated>" + comic.getIsAuthenticated() + "</isAuthenticated>\n");
                write.write("\t\t<gradeNumber>" + comic.getGradeNumber() + "</gradeNumber>\n");
                write.write("\t\t<publicationDate>" + comic.getPublicationDate()+ "</publicationDate>\n");
                write.write("\t</comic>\n");
            }
            write.write("</comics>");
            write.close();
            System.out.println("Export successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}