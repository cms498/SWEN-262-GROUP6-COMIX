package src;
import java.util.List;

public class Comic {
    private Publisher publisher;
    private String seriesTitle;
    private String storyTitle;
    private int volumeNumber;
    private String issueNumber;
    private String publicationDate;
    private List<Creator> creators; // optional
    private String description; // optional
    private double value; // optional
    private boolean isGraded;

    public Comic(Publisher publisher, String seriesTitle, String storyTitle, int volumeNumber,
            String issueNumber, String publicationDate, List<Creator> creators,
            String description, double value) {
        this.publisher = publisher;
        this.seriesTitle = seriesTitle;
        this.storyTitle = storyTitle;
        this.volumeNumber = volumeNumber;
        this.issueNumber = issueNumber;
        this.publicationDate = publicationDate;
        this.creators = creators;
        this.description = description;
        this.value = value;
        this.isGraded = false;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public int getVolumeNumber() {
        return volumeNumber;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public List<Creator> getCreators() {
        return creators;
    }

    public String getDescription() {
        return description;
    }

    public double getValue() {
        return value;
    }

    @Override 
    public boolean equals(Object o) {
        if(o instanceof Comic) {
            Comic other = (Comic)o;
            return this.publisher.equals(other.publisher) &&
                this.seriesTitle.equals(other.seriesTitle) &&
                this.storyTitle.equals(other.storyTitle) &&
                this.volumeNumber == other.volumeNumber &&
                this.issueNumber.equals(other.issueNumber) &&
                this.creators.equals(other.creators) &&
                this.description.equals(other.description) &&
                this.value == other.value;
        }
        return false;
    }
    public void setValue(double value) {
        this.value = value;
    }

    public boolean getIsGraded(){
        return isGraded;
    }

    public void setIsGraded(boolean isGraded){
        this.isGraded = isGraded; 
    }
}