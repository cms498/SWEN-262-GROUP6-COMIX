import java.util.List;

public class Comic {
    private Publisher publisher;
    private String seriesTitle;
    private int volumeNumber;
    private String issueNumber;
    private String publicationDate;
    private List<Creator> creators; // optional
    private String description; // optional
    private double value; // optional
    private List<String> principleCharacters; // optional

    public Comic(Publisher publisher, String seriesTitle, int volumeNumber,
            String issueNumber, String publicationDate, List<Creator> creators,
            String description, double value, List<String> principleCharacters) {
        this.publisher = publisher;
        this.seriesTitle = seriesTitle;
        this.volumeNumber = volumeNumber;
        this.issueNumber = issueNumber;
        this.publicationDate = publicationDate;
        this.creators = creators;
        this.description = description;
        this.value = value;
        this.principleCharacters = principleCharacters;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public String getSeriesTitle() {
        return seriesTitle;
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

    public List<String> getPrincipleCharacters() {
        return principleCharacters;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Comic)){
            return false;
        }

        Comic otherComic = (Comic)(obj);
        return this.seriesTitle.equals(otherComic.seriesTitle) && this.volumeNumber == otherComic.volumeNumber && this.issueNumber.equals(otherComic.issueNumber);
    }
}