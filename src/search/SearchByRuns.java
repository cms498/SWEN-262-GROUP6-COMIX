package src.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.Comic;
import src.ComicIssueRange;

public class SearchByRuns implements CollectionSearcher{
    
    private boolean exactMatch;

    public SearchByRuns(boolean exactMatch){
        this.exactMatch = exactMatch;
    }

    /* This searches the user's personal collection for any series with runs of 12 or more issues
     * Parameters:
     * comics: list containing the user's personal collection
     * (searchTerm is not needed here)
    */
    @Override
    public List<Comic> search(List<Comic> comics, String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        Map<String, ComicIssueRange> comicSeriesIssueRanges = new HashMap<>();
        List<Comic> searchComics = new ArrayList<>();

        //finds the runs of all of the comics series within the users database
        for(Comic comic: comics){
            addToInitialComics(comicSeriesIssueRanges, comic);
        }

        //goes through the map to find all/any of the valid series that has a run of 12 or more issues
        for(Map.Entry<String,ComicIssueRange> mapElement : comicSeriesIssueRanges.entrySet()) {
            String key = mapElement.getKey();
            String[] ComicTitleAndVolume = key.split(", Vol. ");
            ComicIssueRange issueRange = mapElement.getValue();
            if(issueRange.getAmount() >= 12){
                searchComics.add(new Comic(ComicTitleAndVolume[0], Integer.parseInt(ComicTitleAndVolume[1]), issueRange.toString()));
            }
        }

        return searchComics;
    }

    //method that helps compute the runs of a specific comic
    private void addToInitialComics(Map<String, ComicIssueRange> comicSeriesIssueRanges, Comic comic){
        String fullSeriesTitle = comic.getSeriesTitle() + ", Vol. " + comic.getVolumeNumber();
        if(comicSeriesIssueRanges.containsKey(fullSeriesTitle) == false){
            comicSeriesIssueRanges.put(fullSeriesTitle, new ComicIssueRange(comic.getIssueNumber()));
        }
        else{
            for (Map.Entry<String,ComicIssueRange> mapElement : comicSeriesIssueRanges.entrySet()) {
                String key = mapElement.getKey();
                ComicIssueRange issueRange = mapElement.getValue();
                if(key.equals(fullSeriesTitle)){
                    if(issueRange.getMin().compareTo(comic.getIssueNumber()) == 1){
                        issueRange.setMin(comic.getIssueNumber());
                    }
                    if(issueRange.getMax().compareTo(comic.getIssueNumber()) == -1){
                        issueRange.setMax(comic.getIssueNumber());
                    }
                    issueRange.increaseAmount();
                    issueRange.addToAllValidIssues(comic.getIssueNumber());
                    comicSeriesIssueRanges.put(key, issueRange);
                }
            }
        }
    }

    /*
     * This searches the database for any series with runs of 12 or more issues (parameter is not needed here)
     */
    @Override
    public List<Comic> databaseSearch(String searchTerm) {
        File file = new File(COMIC_DATABASE);
        Map<String, ComicIssueRange> comicSeriesIssueRanges = new HashMap<>();
        List<Comic> searchComics = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            //reads the database file to find the comic series with a run of 12 or more issues
            while((line = br.readLine()) != null) {
                String[] split = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                Comic comic = generateComic(split);
                addToInitialComics(comicSeriesIssueRanges, comic);
            }
            //goes through the map to find all/any of the valid series that has a run of 12 or more issues
            for(Map.Entry<String,ComicIssueRange> mapElement : comicSeriesIssueRanges.entrySet()) {
                String key = mapElement.getKey();
                String[] ComicTitleAndVolume = key.split(", Vol. ");
                ComicIssueRange issueRange = mapElement.getValue();
                if(issueRange.getAmount() >= 12){
                    searchComics.add(new Comic(ComicTitleAndVolume[0], Integer.parseInt(ComicTitleAndVolume[1]), issueRange.toString()));
                }
            }
            
            br.close();
        }catch (IOException e){
            System.out.println("Invalid filename.");
        }

        return searchComics;

    }

    @Override
    public void setExactMatch(boolean exact) {
        this.exactMatch = exact;
    }
}
