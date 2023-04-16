package src;

import src.search.CollectionSearcher;
import src.search.SearchBySeriesTitle;
import src.sort.CollectionSorter;
import src.sort.SortByDate;
import src.sort.SortByIssueNumber;
import src.sort.SortByTitle;
import src.sort.SortByVolume;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class represents the collection of comics the user has
 * Acts as the main facade to which the user interacts with, and it delegates
 * all of the required logic to other classes
 */
public class PersonalCollection implements iPersonalCollection {

    private final String comicFile = "data/personalCollection.json";

    private double value;
    private int numberOfIssues;
    private List<Comic> comics;
    private CollectionSorter sorter;
    private CollectionSearcher searcher;
    private HashMap<String, CollectionSorter> sortOptions = new HashMap<>();
    private boolean flag;

    public PersonalCollection() {
        value = 0.0;
        numberOfIssues = 0;
        comics = new ArrayList<>();
        sortOptions.put("volume number", new SortByVolume());
        sortOptions.put("date", new SortByDate());
        sortOptions.put("issue number", new SortByIssueNumber());
        sortOptions.put("series title", new SortByTitle());
        flag = false;
    }

    /*
     * From the personal collection JSON file, it converts all of the comics into a list
     * so that it can be used while the program is running
     */
    public void initializeComics() {
        this.comics = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            try {
                File file = new File(comicFile);
                FileReader reader = new FileReader(file);
                JSONArray jsonArray = (JSONArray) parser.parse(reader);
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    Publisher publisher = new Publisher((String) jsonObject.get("publisher"));
                    String seriesTitle = (String) jsonObject.get("seriestitle");
                    String storyTitle = (String) jsonObject.get("storytitle");
                    long volumeNumber = (Long) jsonObject.get("volumenumber");
                    String issueNumber = (String) jsonObject.get("issuenumber");
                    String publicationDate = (String) jsonObject.get("publicationdate");
                    String creators = (String) jsonObject.get("creators");
                    String[] creatorsArr = creators.strip().split(",");
                    List<Creator> creatorsList = new ArrayList<>();
                    for (String creator : creatorsArr) {
                        creatorsList.add(new Creator(creator));
                    }
                    String description = (String) jsonObject.get("description");
                    double value = (Double) jsonObject.get("value");
                    Boolean isGraded = (Boolean) jsonObject.get("isGraded");
                    Boolean isSlabbed = (Boolean) jsonObject.get("isSlabbed");
                    String signatures = (String) jsonObject.get("signatures");
                    // work in progress
                    String[] signaturesArr = signatures.strip().split(",");
                    List<String> signatureList = Arrays.asList(signaturesArr);
                    ArrayList<String> arrAigList = new ArrayList<>(signatureList);
                    Boolean authenticated = (Boolean) jsonObject.get("authenticated");
                    long gradeNumber = (Long) jsonObject.get("gradenumber");
                    Comic comic = new Comic(publisher, seriesTitle, storyTitle, (int) volumeNumber, issueNumber,
                            publicationDate, creatorsList, description, value, isGraded, isSlabbed, arrAigList,
                            authenticated, (int) gradeNumber);
                    comics.add(comic);
                }
            } catch (ParseException e) {
                System.out.println("Invalid filename2");
            }
        } catch (IOException e) {
            System.out.println("Invalid filename");
        }
    }

    // clears Json file for when the user starts updating their information
    public void clearJson() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.clear();

            FileWriter fileWriter = new FileWriter(comicFile);
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Invalid filename");
        }
    }

    // converts from list to json
    public void convertBackToJson() {
        StringBuilder json = new StringBuilder();
        json.append("[\n");
        for (int i = 0; i < comics.size(); i++) {
            json.append("   {\n");
            json.append("       \"").append("seriestitle").append("\": ");
            json.append("\"").append(comics.get(i).getSeriesTitle()).append("\",\n");
            json.append("       \"").append("issuenumber").append("\": ");
            json.append("\"").append(comics.get(i).getIssueNumber()).append("\",\n");
            json.append("       \"").append("storytitle").append("\": ");
            json.append("\"").append(comics.get(i).getStoryTitle()).append("\",\n");
            List<Creator> creatorList = comics.get(i).getCreators();
            String creators = "";
            for (int j = 0; j < creatorList.size(); j++) {
                creators = creators + creatorList.get(j);
                if (j < creatorList.size() - 1) {
                    creators = creators + ",";
                }
            }
            List<String> signaturesList = comics.get(i).getSignatures();
            signaturesList.removeAll(Arrays.asList("", null));
            String signatures = "";
            for (int k = 0; k < signaturesList.size(); k++) {
                signatures = signatures + signaturesList.get(k);
                if (k < signaturesList.size() - 1) {
                    signatures = signatures + ",";
                }
            }
            json.append("       \"").append("creators").append("\": ");
            json.append("\"").append(creators).append("\",\n");
            json.append("       \"").append("publisher").append("\": ");
            json.append("\"").append(comics.get(i).getPublisher()).append("\",\n");
            json.append("       \"").append("description").append("\": ");
            json.append("\"").append(comics.get(i).getDescription()).append("\",\n");
            json.append("       \"").append("value").append("\": ");
            String valueString = String.format("%.2f", comics.get(i).getValue());
            json.append(Double.valueOf(valueString)).append(",\n");

            json.append("       \"").append("volumenumber").append("\": ");
            json.append(comics.get(i).getVolumeNumber()).append(",\n");

            json.append("       \"").append("publicationdate").append("\": ");
            json.append("\"").append(comics.get(i).getPublicationDate()).append("\",\n");

            json.append("       \"").append("isGraded").append("\": ");
            json.append(comics.get(i).getIsGraded()).append(",\n");

            json.append("       \"").append("isSlabbed").append("\": ");
            json.append(comics.get(i).getIsSlabbed()).append(",\n");

            json.append("       \"").append("signatures").append("\": ");
            json.append("\"").append(signatures).append("\",\n");

            json.append("       \"").append("authenticated").append("\": ");
            json.append(comics.get(i).getIsAuthenticated()).append(",\n");

            json.append("       \"").append("gradenumber").append("\": ");
            json.append(comics.get(i).getGradeNumber()).append("\n");

            if (i < comics.size() - 1) {
                json.append("   },\n");
            } else {
                json.append("   }\n");
            }
        }
        json.append("]");
        String jsonString = json.toString();
        try (FileWriter fileWriter = new FileWriter(comicFile)) {
            fileWriter.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
     * Retrives a comic from the list given a story title
     */
    public Comic getComicInCollection(String comicName) {
        comicName = comicName.toLowerCase();
        for (Comic comicsInList : comics) {
            if (comicName.equals(comicsInList.getStoryTitle().toLowerCase())) {
                return comicsInList;
            }
        }
        return null;
    }

    /*
     * Retrives a comic from the list given a story title, volume number, and issue number
     */
    public Comic getComicInCollection2(String seriesTitle, int volumeNumber, String issueNumber){
        seriesTitle = seriesTitle.toLowerCase();
        issueNumber = issueNumber.toLowerCase();
        for (Comic comicsInList : comics) {
            if (seriesTitle.equals(comicsInList.getSeriesTitle().toLowerCase()) && volumeNumber == comicsInList.getVolumeNumber() && issueNumber.equals(comicsInList.getIssueNumber())) {
                return comicsInList;
            }
        }
        return null;
    }

    /*
     * Sets the collection sorter for the personal collection
     */
    public void setSort(CollectionSorter sorter) {
        this.sorter = sorter;
    }

    /*
     * Sets the collection searcher for the personal collection
     */
    public void setSearch(CollectionSearcher searcher) {
        this.searcher = searcher;
    }

    /*
     * Updates the value of the entire collection
     */
    public void updateCollectionValue() {
        double tempValue = 0.0;
        for (Comic comic : comics) {
            tempValue += comic.getValue();
        }
        value = tempValue;
    }

    /*
     * Updates the number of issues within the collection
     */
    public void updateCollectionIssues() {
        numberOfIssues = comics.size();
    }

    /*
     * Searches the personal collection with a given search term and search type
     */
    public List<Comic> doSearch(String searchTerm, String sortType) {
        List<Comic> searchComics = this.searcher.search(comics, searchTerm);
        setSort(sortOptions.get(sortType));
        return this.sorter.sort(searchComics);
    }

    /*
     * Searches the database with a given search term and type
     */
    public List<Comic> doDatabaseSearch(String searchTerm) {
        return this.searcher.databaseSearch(searchTerm);
    }

    public void editSlab(String storyTitle) {
        Comic comic = getComicInCollection(storyTitle);
        if (comic.getIsGraded() == true && comic.getIsSlabbed() == false) {
            comic.setIsSlabbed(true);
            comic.setValue((comic.getValue() * 2));
        } else if (comic.getIsSlabbed() == true) {
            System.out.println("You can't slab a comic again");
        } else {
            System.out.println("You cant slab a non graded comic");
        }
        this.convertBackToJson();
    }

    public double editGrade(String storyTitle, int grade) {
        Comic comic = getComicInCollection(storyTitle);
        double oldValue = comic.getValue();
        double newValue = grade;

        if (grade == 1) {
            newValue = comic.getValue() * (0.10);
        } else {
            newValue = Math.log10(grade) * comic.getValue();
        }
        comic.setValue(newValue + oldValue);
        comic.setIsGraded(true);
        comic.setGradeNumber(grade);
        this.convertBackToJson();
        return newValue;
    }

    public void undoSlab(String storyTitle) {
        Comic comic = getComicInCollection(storyTitle);
        if(comic.getIsGraded() == true) {
            
        }

    }
    
    /*
     * Adds comics from the database to the personal collection through the main criteria:
     * Comic's series title, Comic's volume number, and Comic's issue number
     */
    public void addComicByDataBase(String seriesTitle, int volumeNumber, String issueNumber) {
        // initializes the 'searcher' variable to the SearchByTitle class. Also sets the
        // variable to only look for exact matches only
        searcher = new SearchBySeriesTitle(true);
        List<Comic> initialSearch = searcher.databaseSearch(seriesTitle.toLowerCase());
        // if there's a comic that exists within the initialSearch list, then add it to
        // their personal collection list
        if (initialSearch.size() != 0) {
            for(Comic comic: initialSearch){
                if(comic != null){
                    if(comic.getIssueNumber().equals(issueNumber) && comic.getVolumeNumber() == volumeNumber){
                        comics.add(comic);
                        System.out.println(seriesTitle + " has been successfully added to your personal collection");
                    }
                }
            }
        }

        // otherwise print out error message
        else {
            System.out.println(seriesTitle + " doesn't exist in comic database");
        }
        this.convertBackToJson();
    }

    /*
     * Adds a comic manually by providing all of its necessary fields
     */
    public void addComicManually(String publisher, String seriesTitle, String storyTitle, int volumeNumber,
            String issueNumber, String publicationDate, String creators,
            String description, String value) {


        // Default parameters for optional attributes that aren't specified by user
        // input
        List<Creator> creatorsList = new ArrayList<>();
        Double valueNumber = 1.0;
        // turns string 'creators' into a list of creators if the attribute doesn't
        // equal to null
        // format for creators should look something like this '[Harry,Sam,Tim]' as a
        // string
        if (creators != null) {
            if(flag==false){
                creators = creators.substring(1, creators.length() - 1);
            }
            
            String[] creatorArr = creators.split(",");
            for (String creatorName : creatorArr) {
                creatorsList.add(new Creator(creatorName));
            }
        }
        // turns string 'value' into a double type if the attribute doesn't equal to
        // null
        if (value != null) {
            valueNumber = Double.valueOf(value);
        }

        // adds new comic object to their personal collection
        comics.add(new Comic(new Publisher(publisher), seriesTitle, storyTitle, volumeNumber, issueNumber,
                publicationDate, creatorsList, description, valueNumber, false, false, new ArrayList<>(), false, 0));
        System.out.println(storyTitle + " has been successfully added to your personal collection");
        this.convertBackToJson();
    }

    /*
     * Removes a comic from the collection given its story title
     */
    public void removeComic(String storyTitle) {
        Comic comic = getComicInCollection(storyTitle);
        // if there's a comic story title that exists within the users database, then
        // the object of that comic will be removed here
        if (comic != null) {
            comics.remove(comic);
            System.out.println(storyTitle + " has been successfully removed from your personal collection");
        }
        // otherwise print out error message
        else {
            System.out.println(storyTitle + " doesn't exist within your personal collection");
        }
        this.convertBackToJson();
    }

    /*
     * Authenticates a given comic's signatures, has to be signed first
     */
    public void authenticate(String storyTitle){
        Comic comic = getComicInCollection(storyTitle);
        if (!comic.getSignatures().isEmpty()) {
            comic.authenticate(true);
            comic.setValue(comic.getValue() * 1.2);
        }
        else {
            System.out.println(storyTitle + " doesn't exist within your personal collection");
        }
        this.convertBackToJson();
    }

    /*
     * Adds a signature to the list of signatures on a given comic
     */
    public void sign(String storyTitle, String signature){
        Comic comic = getComicInCollection(storyTitle);
        if (comic != null) {
            comic.sign(signature);
            comic.setValue(comic.getValue() * 1.05);
        }
        else {
            System.out.println(storyTitle + " doesn't exist within your personal collection");
        }
        this.convertBackToJson();
    }

    /*
     * Given a comic, and its field, changes that field to the provided new value
     */
    public void editComic(String storyTitle, String field, String newValue) {
        Comic comic = getComicInCollection(storyTitle);
        String command = field.replaceAll("\\s", "").toLowerCase();
        switch (command) {
            case "publisher":
                comic.setPublisher(newValue);
                break;
            case "seriestitle":
                comic.setSeriesTitle(newValue);
                break;
            case "storytitle":
                comic.setStoryTitle(newValue);
                break;
            case "volumenumber":
                int newValue2 = Integer.parseInt(newValue);
                comic.setVolumeNumber(newValue2);
                break;
            case "issuenumber":
                comic.setIssueNumber(newValue);
                break;
            case "publicationdate":
                comic.setPublicationDate(newValue);
                break;
            case "creator":
                List<Creator> newCreators = new ArrayList<>();
                String[] creators = newValue.split(",");
                for (String string : creators) {
                    newCreators.add(new Creator(string));
                }
                comic.setCreators(newCreators);
                break;
            case "description":
                comic.setDescription(newValue);
                break;
            case "value":
                double newDouble = Double.parseDouble(newValue);
                DecimalFormat dfZero = new DecimalFormat("0.00");
                double finalValue = Double.parseDouble(dfZero.format(newDouble));
                comic.setValue(finalValue);
                break;
            case "grade":
                if (comic.getIsGraded() == false) {
                    editGrade(storyTitle, Integer.parseInt(newValue));
                } else {
                    ungradeComic(comic, Integer.parseInt(newValue));
                }
                break;
            case "slab":
                if(comic.getIsSlabbed() == false){
                    editSlab(storyTitle);
                }else{
                    unslabComic(comic);
                }
                break;
            default:
                System.out.println("please input a valid field");
                break;
        }
        this.convertBackToJson();
    }

    /*
    * Add an underscore after the header of each column
    */
    public void PrettyPrintDatabase() {
        StringBuilder sb = new StringBuilder();
        sb.append("\033[1m"); // Bold formatting
        sb.append(String.format("%-20s | %-20s | %-20s | %-20s |%-10s | %-10s  | %-20s | %-10s| %-10s| %-10s",
                "Publisher", "Series Title", "Story Title", "Description", "Volume Number", "Issue Number",
                "Publication Date", "Value", "Graded", "Slabbed"));
        sb.append("\033[0m\n"); // Reset formatting to default and add new line
        sb.append("_".repeat(175)); // Underscores
        sb.append(System.lineSeparator());

        for (Comic comic : comics) {
            if (comic.getSeriesTitle().length() > 20) {
                comic.setSeriesTitle(comic.getSeriesTitle().substring(0, 17) + "...");
            }

            if (comic.getStoryTitle().length() > 20) {
                comic.setStoryTitle(comic.getStoryTitle().substring(0, 17) + "...");
            }

            if (comic.getDescription().length() > 20) {
                comic.setDescription(comic.getDescription().substring(0, 17) + "...");
            }

            sb.append(String.format("%-20s | %-20s | %-20s | %-20s |%-10s    | %-10s    | %-20s | %-10s| %-10s| %-10s",
                    comic.getPublisher(), comic.getSeriesTitle(), comic.getStoryTitle(), comic.getDescription(),
                    comic.getVolumeNumber(), comic.getIssueNumber(), comic.getPublicationDate(), comic.getValue(),
                    comic.getIsGraded(), comic.getIsSlabbed()));
            sb.append(System.lineSeparator());
            sb.append("_".repeat(175)); // Underscores
            sb.append(System.lineSeparator());
        }
        System.out.println("\n\n" + sb.toString());
    }

    public void prettyPrintHelper(String type) {
        StringBuilder sb = new StringBuilder();
        sb.append("\033[1m"); // Bold formatting
        sb.append(String.format("%-20s", type));
        sb.append("\033[0m\n"); // Reset formatting to default and add new line
        sb.append("_".repeat(20)); // Underscores
        sb.append(System.lineSeparator());

        for (Comic comic : comics) {
            if (type.equals("Series Title")) {
                sb.append(String.format("%-20s", comic.getSeriesTitle()));
            } else if (type.equals("Volume Number")) {
                sb.append(String.format("%-20s", comic.getVolumeNumber()));
            } else if (type.equals("Issue Number")) {
                sb.append(String.format("%-20s", comic.getIssueNumber()));
            } else if (type.equals("Publisher")) {
                sb.append(String.format("%-20s", comic.getPublisher()));
            }

            sb.append(System.lineSeparator());
            sb.append("_".repeat(20)); // Underscores
            sb.append(System.lineSeparator());
        }

        System.out.println("\n\n" + sb.toString());
    }

    public void viewSeriesTitle() {
        prettyPrintHelper("Series Title");
    }

    public void viewVolumeNumber() {
        prettyPrintHelper("Volume Number");
    }

    public void viewIssueNumber() {
        prettyPrintHelper("Issue Number");
    }

    public void viewPublisher() {
        prettyPrintHelper("Publisher");
    }

    public void setGuestMode(boolean guestMode) {
        System.out.println("You are already logged in, end the program to logout");
    }

    public boolean isGuestMode(){
        return false;
    }
    
    /*
     * Prints the database in a nice manner
     */
    @Override
    public void printDatabase(List<Comic> comics) {
        StringBuilder sb = new StringBuilder();
        sb.append("\033[1m"); // Bold formatting
        sb.append(String.format("%-20s | %-20s | %-20s | %-20s |%-10s | %-10s  | %-20s | %-10s| %-10s| %-10s",
                "Publisher", "Series Title", "Story Title", "Description", "Volume Number", "Issue Number",
                "Publication Date", "Value", "Graded", "Slabbed"));
        sb.append("\033[0m\n"); // Reset formatting to default and add new line
        sb.append("_".repeat(175)); // Underscores
        sb.append(System.lineSeparator());

        for (Comic comic : comics) {
            if(comic != null){
                if (comic.getSeriesTitle().length() > 20) {
                    comic.setSeriesTitle(comic.getSeriesTitle().substring(0, 17) + "...");
                }

                if (comic.getStoryTitle().length() > 20) {
                    comic.setStoryTitle(comic.getStoryTitle().substring(0, 17) + "...");
                }

                if (comic.getDescription().length() > 20) {
                    comic.setDescription(comic.getDescription().substring(0, 17) + "...");
                }

                if (comic.getPublisher().toString().length() > 20) {
                    comic.setPublisher(comic.getPublisher().toString().substring(0, 17) + "...");
                }

                sb.append(String.format("%-20s | %-20s | %-20s | %-20s |%-10s    | %-10s    | %-20s | %-10s| %-10s| %-10s",
                        comic.getPublisher(), comic.getSeriesTitle(), comic.getStoryTitle(), comic.getDescription(),
                        comic.getVolumeNumber(), comic.getIssueNumber(), comic.getPublicationDate(), comic.getValue(),
                        comic.getIsGraded(), comic.getIsSlabbed()));
                sb.append(System.lineSeparator());
                sb.append("_".repeat(175)); // Underscores
                sb.append(System.lineSeparator());
            }
        }
        System.out.println("\n\n" + sb.toString());
    }

    /*
     * Adds a comic to the list of comcis then updates the JSON
     */
    @Override
    public void addComic(Comic comic) {
        comics.add(comic);
        System.out.println(comic.getStoryTitle() + " has been successfully added to your personal collection");
        this.convertBackToJson();
    }

    /*
     * Ungrades a comic
     */
    public void ungradeComic(Comic comic, double difference){
        if(comic.getIsGraded()){
            Comic comicNew = getComicInCollection(comic.getStoryTitle());
            double oldValue = comicNew.getValue();
            
            unslabComic(comicNew);
            comicNew.setValue(oldValue - difference);
            comicNew.setIsGraded(false);
            comicNew.setGradeNumber(0);
            this.convertBackToJson();
        }
    }

    /*
     * Unslabs a comic
     */
    public void unslabComic(Comic comic){
        if(comic.getIsSlabbed() && comic.getIsGraded()){
            Comic comicInCollection = this.getComicInCollection(comic.getStoryTitle());
            comicInCollection.setIsSlabbed(false);
            comicInCollection.setValue((comicInCollection.getValue()/2.0));
            System.out.println(comicInCollection.getStoryTitle() + " has been successfully unslabbed");
            this.convertBackToJson();
        }
    }

    /*
     * Unauthenticates a comic
     */
    public void unauthenticateComic(Comic comic){
        if(!comic.getSignatures().isEmpty()){
            Comic comicInCollection = this.getComicInCollection(comic.getStoryTitle());
            comicInCollection.setIsAuthenticated(false);
            comicInCollection.setValue(comicInCollection.getValue() / 1.2);
            System.out.println(comicInCollection.getStoryTitle() + " has been successfully unauthenticated");
            this.convertBackToJson();
        }
    }

    /*
     * Removes a signature from the comic
     */
    public void unsignComic(Comic comic, String signature){
        if(!comic.getSignatures().isEmpty()){
            Comic comicInCollection = this.getComicInCollection(comic.getStoryTitle());
            comicInCollection.getSignatures().remove(comicInCollection.getSignatures().size() - 1);
            comicInCollection.setValue(comicInCollection.getValue() / 1.05);
            System.out.println(comicInCollection.getStoryTitle() + "has been successfully unsigned");
            this.convertBackToJson();
        }
    }

    public void setFlag(Boolean flag){
        this.flag = flag;
    }

    public double getValue() {
        return value;
    }

    public int getNumberOfIssues() {
        return numberOfIssues;
    }

    public List<Comic> getComics() {
        return comics;
    }
}