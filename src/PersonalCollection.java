package src;

import src.search.CollectionSearcher;
import src.search.SearchByTitle;
import src.sort.CollectionSorter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        comics = new ArrayList<>();
    }

    //converts from JSON to a list of comics
    public void initializeComics() {
        try{
            BufferedReader br = new BufferedReader(new FileReader(comicFile));
            String line = "";
            while((line = br.readLine()) != null) {
                JSONParser parser = new JSONParser();
                try{
                    JSONArray jsonArray = (JSONArray) parser.parse(line);
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
                        long value = (Long) jsonObject.get("value");
                        Comic comic = new Comic(publisher, seriesTitle, storyTitle, (int) volumeNumber, issueNumber, publicationDate, creatorsList, description, (double) value);
                        comics.add(comic);
                    }
                }
                catch (ParseException e) {
                    System.out.println("Invalid filename2");
                }
            }
            br.close();
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
        for(Comic comic: comics){
            JSONObject jsonObject = new JSONObject();
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
            jsonObject.put("value", (int) comic.getValue());
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

    public void editSlab(Comic comic){
        if(comic.getIsGraded() == true){
            comic.setValue(comic.getValue()*2);
        }
    }

    public void editGrade(Comic comic, int grade){
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
        List<Comic> initialSearch = searcher.databaseSearch(storyTitle);

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
}
