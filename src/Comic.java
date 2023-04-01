package src;
import java.util.List;


public class Comic {

    public final double SIGNED_VALUE_BOOST = .05;
    public final double AUTHENTICEATED_VALUE_BOOST = .2;

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
    private boolean isSlabbed;
    private List<String> signatures;
    private boolean authenticated;

    public Comic(Publisher publisher, String seriesTitle, String storyTitle, int volumeNumber,
            String issueNumber, String publicationDate, List<Creator> creators,
            String description, double value, boolean isGraded, boolean isSlabbed, 
            List<String> signatures, boolean authenticated) {
        this.publisher = publisher;
        this.seriesTitle = seriesTitle;
        this.storyTitle = storyTitle;
        this.volumeNumber = volumeNumber;
        this.issueNumber = issueNumber;
        this.publicationDate = publicationDate;
        this.creators = creators;
        this.description = description;
        this.value = value;
        this.isGraded = isGraded;
        this.isSlabbed = isSlabbed;
        this.signatures = signatures;
        this.authenticated = authenticated;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher){
        Publisher newPublisher = new Publisher(publisher);
        this.publisher = newPublisher;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public int getVolumeNumber() {
        return volumeNumber;
    }

    public void setVolumeNumber(int volumeNumber) {
        this.volumeNumber = volumeNumber;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<Creator> getCreators() {
        return creators;
    }

    public void setCreators(List<Creator> creators) {
        this.creators = creators;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
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

    public boolean getIsGraded(){
        return isGraded;
    }

    public void setIsGraded(boolean isGraded){
        this.isGraded = isGraded;
    }

    public boolean getIsSlabbed(){
        return isSlabbed;
    }

    public void setIsSlabbed(boolean isSlabbed){
        this.isSlabbed = isSlabbed;
    }

    @Override
    public String toString() {
        return "{publisher: " + publisher + ", seriestitle: " + seriesTitle + ", storytitle: " + storyTitle + ", volumenumber: " + volumeNumber + ", value: " + value + ", creators: " + creators + ", description: " + description + ", issuenumber: " + issueNumber + ", isGraded: " + isGraded + ", isSlabbed: "+ isSlabbed + "}";
    }

    public void sign(String signedBy) {
        this.signatures.add(signedBy);
    }

    public void authenticate(boolean authenticated) {
        if(this.signatures.size() > 0) {
            this.authenticated = authenticated;
        }
    }
}