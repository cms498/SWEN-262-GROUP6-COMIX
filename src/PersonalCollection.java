package src;

import src.search.CollectionSearcher;
import src.search.SearchByTitle;
import src.sort.CollectionSorter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

/**
 * This class represents the collection of comics the user has
 * Acts as the main facade to which the user interacts with, and it delegates all of the required logic to other classes
 */
public class PersonalCollection {

    private final String comicFile = "data/personalCollection.json";

    private double value;
    private int numberOfIssues;
    private List<Comic> comics;
    private CollectionSorter sorter;
    private CollectionSearcher searcher;
    private List<PersonalCollectionObserver> observers;

    public PersonalCollection() {
        value = 0.0;
        numberOfIssues = 0;
        comics = new ArrayList<>();
        observers = new ArrayList<>();
    }

    //converts from JSON to a list of comics
    public void initializeComics() {
        this.comics = new ArrayList<>();
        try{
                JSONParser parser = new JSONParser();
                try{
                    File file = new File(comicFile);
                    FileReader reader = new FileReader(file);
                    JSONArray jsonArray = (JSONArray) parser.parse(reader);
                    for(int i = 0; i < jsonArray.size(); i++){
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
                        for(String creator: creatorsArr){
                            creatorsList.add(new Creator(creator));
                        }
                        String description = (String) jsonObject.get("description");
                        double value = (Double) jsonObject.get("value");
                        Comic comic = new Comic(publisher, seriesTitle, storyTitle, (int) volumeNumber, issueNumber, publicationDate, creatorsList, description, value);
                        comics.add(comic);
                    }
                }
                catch (ParseException e) {
                    System.out.println("Invalid filename2");
                }
        }
        catch(IOException e){
            System.out.println("Invalid filename");
        }
    }

    //clears Json file for when the user starts updating their information
    public void clearJson(){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.clear();

            FileWriter fileWriter = new FileWriter(comicFile);
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } 
        catch (IOException e) {
            System.out.println("Invalid filename");
        }
    }
    
    //converts from list to json
    public void convertBackToJson(){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for(Comic comic: comics){
            jsonObject.put("publisher", (String) comic.getPublisher().getName());
            jsonObject.put("seriestitle", (String) comic.getSeriesTitle());
            jsonObject.put("storytitle", (String) comic.getStoryTitle());
            jsonObject.put("volumenumber", (int) comic.getVolumeNumber());
            jsonObject.put("issuenumber", (String) comic.getIssueNumber());
            jsonObject.put("publicationdate", (String) comic.getPublicationDate());
            List<Creator> creatorList = comic.getCreators();
            String creators = "";
            for(int i = 0; i < creatorList.size(); i++){
                creators = creators + creatorList.get(i);
                if(i < creatorList.size()-1){
                    creators = creators + ",";
                }
            }
            jsonObject.put("creators", (String) creators);
            jsonObject.put("description", (String) comic.getDescription());
            jsonObject.put("value", (Double) comic.getValue());
            jsonArray.add(jsonObject);
        }
        try (FileWriter fileWriter = new FileWriter(comicFile)) {
            JSONValue.writeJSONString(jsonArray, fileWriter);
          } catch (IOException e) {
            e.printStackTrace();
          }
    }

    public void register(PersonalCollectionObserver observer) {
        this.observers.add(observer);
    }

    private void handle() {
        for(PersonalCollectionObserver observer: this.observers) {
            observer.handle();
        }
    }

    public Comic getComicInCollection(String comicName){
        comicName = comicName.toLowerCase();
        for(Comic comicsInList: comics){
            if(comicName.equals(comicsInList.getStoryTitle().toLowerCase())){
                return comicsInList;
            }
        }
        return null;
    }

    public void setSort(CollectionSorter sorter){
        this.sorter = sorter;
    }

    public void setSearch(CollectionSearcher searcher){
        this.searcher = searcher;
    }

    //updates the total collection value
    public void updateCollectionValue(){
        double tempValue = 0.0;
        for(Comic comic: comics){
            tempValue += comic.getValue();
        }
        value = tempValue;
    }

    //updates the total number of comics within the collection list
    public void updateCollectionIssues(){
        numberOfIssues = comics.size();
    }

    public double getValue() {
        return value;
    }

    public int getNumberOfIssues() {
        return numberOfIssues;
    }

    public List<Comic> doSearch(String searchTerm){
        return this.searcher.search(comics, searchTerm);
    }

    public List<Comic> doDatabaseSearch(String searchTerm){
        return this.searcher.databaseSearch(searchTerm);
    }

    public List<Comic> doSort(){
        return this.sorter.sort(comics);
    }

    public List<Comic> getComics(){
        return comics;
    }

    public void editSlab(String storyTitle){
        Comic comic = getComicInCollection(storyTitle);
        if(comic.getIsGraded() == true){
            comic.setValue(comic.getValue()*2);
        }
    }

    public void editGrade(String storyTitle, int grade){
        Comic comic = getComicInCollection(storyTitle);
        double newValue = grade;
        if(grade == 1){
            newValue = comic.getValue()*(grade/100);
        }
        else{
            newValue = Math.log10(grade)*comic.getValue();
        }
        comic.setValue(newValue);
    }

    //adds comics from the database by user input (user inputs only the story title here) 
    public void addComicByDataBase(String storyTitle){
        //initializes the 'searcher' variable to the SearchByTitle class. Also sets the variable to only look for exact matches only 
        searcher = new SearchByTitle(true);
        List<Comic> initialSearch = searcher.databaseSearch(storyTitle.toLowerCase());

        //if there's a comic that exists within the initialSearch list, then add it to their personal collection list
        if(initialSearch.size() != 0){
            comics.add(initialSearch.get(0));
            System.out.println(storyTitle + " has been successfully added to your personal collection");
        }

        //otherwise print out error message
        else{
            System.out.println(storyTitle + " doesn't exist in comic database");
        }
    }

    //adds comics manually by user input (optional attributes can be passed in as null into this method if the user didn't input anything for them)
    public void addComicManually(String publisher, String seriesTitle, String storyTitle, int volumeNumber,
    String issueNumber, String publicationDate, String creators,
    String description, String value){

        //Default parameters for optional attributes that aren't specified by user input
        List<Creator> creatorsList = new ArrayList<>();
        Double valueNumber = 1.0;
        //turns string 'creators' into a list of creators if the attribute doesn't equal to null
        // format for creators should look something like this '[Harry,Sam,Tim]' as a string
        if(creators != null){
            creators = creators.substring(1, creators.length()-1);
            String[] creatorArr = creators.split(",");
            for(String creatorName: creatorArr){
                creatorsList.add(new Creator(creatorName));
            }
        }
        //turns string 'value' into a double type if the attribute doesn't equal to null
        if(value != null){
            valueNumber = Double.valueOf(value);
        }

        //adds new comic object to their personal collection
        comics.add(new Comic(new Publisher(publisher), seriesTitle, storyTitle, volumeNumber, issueNumber, publicationDate, creatorsList, description, valueNumber));
        System.out.println(storyTitle + " has been successfully added to your personal collection");
    }

    //removes comics from user's personal collection based on what story title they inputted
    public void removeComic(String storyTitle){
        Comic comic = getComicInCollection(storyTitle);
        //if there's a comic story title that exists within the users database, then the object of that comic will be removed here
        if(comic != null){
            comics.remove(comic);
            System.out.println(storyTitle + " has been successfully removed from your personal collection");
        }
        //otherwise print out error message
        else{
            System.out.println(storyTitle + " doesn't exist within your personal collection");
        }
    }

    public void editComic(String storyTitle, String field, String newValue){
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
                String[] creators =  newValue.split(",");
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
            default:
                System.out.println("please input a valid field");
                break;
        }

    }

}
