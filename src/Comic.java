package src;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to represent a basic comic, comics have a publisher,
 * series and story title, volume and issue number, they can be graded,
 * slabbed, signed, and authenticated to increase their overall value
 */
public class Comic {

    /*
     * A Comic's value is boosted by 1.05 times for each signature
     */
    public final double SIGNED_VALUE_BOOST = .05;

    /*
     * A Comic's value is boosted by 1.2 times if it is authenticated
     */
    public final double AUTHENTICEATED_VALUE_BOOST = .2;

    private Publisher publisher;
    private String seriesTitle;
    private String storyTitle;
    private int volumeNumber;
    private String issueNumber;
    private int gradeNumber;
    private String publicationDate;
    private List<Creator> creators; // optional
    private String description; // optional
    private double value; // optional
    private boolean isGraded;
    private boolean isSlabbed;
    private ArrayList<String> signatures;
    private boolean authenticated;

    public Comic(Publisher publisher, String seriesTitle, String storyTitle, int volumeNumber,
            String issueNumber, String publicationDate, List<Creator> creators,
            String description, double value, boolean isGraded, boolean isSlabbed, 
            ArrayList<String> signatures, boolean authenticated, int gradeNumber) {
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
        this.gradeNumber = gradeNumber;
    }

    /**
     * Second constructor for a comic with limited fields, sets default values of
     * null
     * and false for all other fields
     * 
     * @param seriesTitle
     * @param volumeNumber
     * @param issueRange
     */
    public Comic(String seriesTitle, int volumeNumber, String issueRange) {
        this.publisher = null;
        this.seriesTitle = seriesTitle;
        this.storyTitle = null;
        this.volumeNumber = volumeNumber;
        this.issueNumber = issueRange;
        this.publicationDate = null;
        this.creators = null;
        this.description = null;
        this.value = 1.0;
        this.isGraded = false;
        this.isSlabbed = false;
        this.signatures = null;
        this.authenticated = false;
        this.gradeNumber = 0;
    }
    
    public Publisher getPublisher() {
        return publisher;
    }

    /**
     * sets the publisher to be a specfic value
     * 
     * @param publisher creates a publisher based on the given name
     */
    public void setPublisher(String publisher) {
        Publisher newPublisher = new Publisher(publisher);
        this.publisher = newPublisher;
    }

    /**
     * getter method for the comics series title
     * 
     * @return series title
     */
    public String getSeriesTitle() {
        return seriesTitle;
    }

    /**
     * sets the comics series title
     * 
     * @param seriesTitle
     */
    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    /**
     * getter method for the comics story title
     * 
     * @return the comics name
     */
    public String getStoryTitle() {
        return storyTitle;
    }

    /**
     * sets the comics story title
     * 
     * @param storyTitle
     */
    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    /**
     * getter method for the comics volume number
     * 
     * @return comics volume number
     */
    public int getVolumeNumber() {
        return volumeNumber;
    }

    /**
     * sets the comics volume number
     * 
     * @param volumeNumber
     */
    public void setVolumeNumber(int volumeNumber) {
        this.volumeNumber = volumeNumber;
    }

    /**
     * getter method for the comics issue number
     * 
     * @return the comics issue number
     */
    public String getIssueNumber() {
        return issueNumber;
    }

    /**
     * sets the comic issue number
     * 
     * @param issueNumber
     */
    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    /**
     * getter method for the comics publication date
     * 
     * @return date the comic was created
     */
    public String getPublicationDate() {
        return publicationDate;
    }

    /**
     * sets the comics publication date
     * 
     * @param publicationDate
     */
    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * getter method for the comics creators
     * 
     * @return list of all the comics creators
     */
    public List<Creator> getCreators() {
        return creators;
    }

    /**
     * sets the comics creators to be a new list of them
     * 
     * @param creators
     */
    public void setCreators(List<Creator> creators) {
        this.creators = creators;
    }

    /**
     * getter method for the comics description
     * 
     * @return description of what the comic is about
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the comics description
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * getter method for the comics value or price
     * 
     * @return comics value
     */
    public double getValue() {
        return value;
    }

    /**
     * sets the comics grade number
     * 
     * @param grade
     */
    public void setGradeNumber(int grade) {
        this.gradeNumber = grade;
    }

    /**
     * getter method for the comics grade number
     * 
     * @return comics grade number, 1-10
     */
    public int getGradeNumber() {
        return gradeNumber;
    }

    /**
     * sets the comics overall value
     * 
     * @param value
     */
    public void setValue(double value) {
        this.value = value;
    }

    /*
     * Compares a given object with this comic by 
     * seeing if each of its fields matches
     * If the object is not a Comic returns false
     * 
     * @return true if the objects are the same, false otherwise
     */
    @Override
    /**
     * Determines if two comics are equal to each other
     * they are if all of their fields are the same
     */
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

    /**
     * getter method for if the comic has been graded or not
     * 
     * @return true if the comic has been graded
     */
    public boolean getIsGraded() {
        return isGraded;
    }

    /**
     * sets the comics graded status
     * 
     * @param isGraded
     */
    public void setIsGraded(boolean isGraded) {
        this.isGraded = isGraded;
    }

    /**
     * getter method for if the comic has been slabbed or not
     * 
     * @return true if the comic has been slabbed
     */
    public boolean getIsSlabbed() {
        return isSlabbed;
    }

    /**
     * sets the comics slabbed status
     * 
     * @param isSlabbed
     */
    public void setIsSlabbed(boolean isSlabbed) {
        this.isSlabbed = isSlabbed;
    }

    @Override
    /**
     * Used to print out a comic and all its fields
     */
    public String toString() {
        return "\"" + publisher + "\"," + seriesTitle + "\"," + storyTitle + "\"," + volumeNumber + "\"," + value
                + "\"," + creators + "\","
                + description + "\"," + issueNumber + "\"," + isGraded + "\"," + isSlabbed + "\"" + signatures + "\""
                + authenticated;
    }

    /**
     * used to print out a comic when seeing if it is within a run or not,
     * most fields aren't included due to repetition
     * @return
     */
    public String toStringRuns() {
        return "\"" + seriesTitle + "\", " + volumeNumber + ", " + issueNumber;
    }

    /**
     * adds a signature to the signature list
     * @param signedBy
     */
    public void sign(String signedBy) {
        this.signatures.add(signedBy);
    }

    /**
     * if a comic has signatures in its list, it can be authenticate
     * changes the status of that
     * @param authenticated
     */
    public void authenticate(boolean authenticated) {
        if(this.signatures.size() > 0) {
            this.authenticated = authenticated;
        }
    }

    /**
     * getter method for the comics signatures
     * 
     * @return list of comics signatures
     */
    public List<String> getSignatures() {
        return signatures;
    }

    /**
     * getter method for if the comic has been authenticated
     * 
     * @return true if the comic has been authenticated
     */
    public boolean getIsAuthenticated() {
        return authenticated;
    }

    /**
     * sets the authenticated status
     * @param authenticated
     */
    public void setIsAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}