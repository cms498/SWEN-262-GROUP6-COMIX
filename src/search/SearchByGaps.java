package src.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import src.Comic;
import src.ComicIssueRange;

public class SearchByGaps implements CollectionSearcher{
    
    private boolean exactMatch;

    public SearchByGaps(boolean exactMatch){
        this.exactMatch = exactMatch;
    }

    /* This searches the user's personal collection for any series with runs of 12 or more issues and any gaps in between the runs
     * Parameters:
     * comics: list containing the user's personal collection
     * (searchTerm is not needed here)
    */
    @Override
    public List<Comic> search(List<Comic> comics, String searchTerm) {
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
                List<String> missingIssues = findMissingIssues(ComicTitleAndVolume[0], ComicTitleAndVolume[1], issueRange);
                if(missingIssues.size() == 0){
                    searchComics.add(new Comic(ComicTitleAndVolume[0], Integer.parseInt(ComicTitleAndVolume[1]), issueRange.toString()));
                }
                else{
                    searchComics.add(new Comic(ComicTitleAndVolume[0], Integer.parseInt(ComicTitleAndVolume[1]), issueRange.toString() + ", Missing: " + missingIssues.toString()));
                }
            }
        }

        return searchComics;
    }

    /*  method that helps compute the runs of a specific comic
     *  Parameters:
     *  comicsSeriesIssueRanges: map that catorgorises a specific comic series and volume number to how many issues it has
     *  comic: the current comic that is being added to the map
    */
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
                    issueRange.addToAllValidIssues(comic.getIssueNumber());
                    comicSeriesIssueRanges.put(key, issueRange);
                }
            }
        }
    }

    /*  finds the missing issues of a comic series with runs (if it exists). Uses the databse to help find the missing issues
     *  Parameters:
     *  seiresTitle: series of the comic that is being passed in
     *  volumeNumber: volume of the comic that is being passed in
     *  issueRange: the "run" of the comic that is being passed in
    */
    private List<String> findMissingIssues(String seriesTitle, String volumeNumber, ComicIssueRange issueRange){
        File file = new File(COMIC_DATABASE);
        List<String> missingIssues = new ArrayList<>();
        Set<String> validIssues = new HashSet<>();
        double minIssueNumber = Double.parseDouble(issueRange.getMin().replaceAll("[^\\d.]+", ""));
        double maxIssueNumber = Double.parseDouble(issueRange.getMax().replaceAll("[^\\d.]+", ""));
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while((line = br.readLine()) != null) {
                String[] split = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                Comic comic = generateComic(split);
                if(comic != null){
                    if(comic.getSeriesTitle().equals(seriesTitle) && comic.getVolumeNumber() == Integer.parseInt(volumeNumber)){
                        validIssues.add(comic.getIssueNumber());
                    }
                }
            }
            br.close();

            for(String issueNumber: validIssues){
                double tempIssueNumber = Double.parseDouble(issueNumber.replaceAll("[^\\d.]+", ""));
                if(issueRange.getAllValidIssues().contains(issueNumber) == false && minIssueNumber <= tempIssueNumber && maxIssueNumber >= tempIssueNumber){
                    missingIssues.add(issueNumber);
                }
            }
            
        }catch (IOException e){
        System.out.println("Invalid filename.");
        }
        return missingIssues;
    }

    @Override
    public List<Comic> databaseSearch(String searchTerm) {
        throw new UnsupportedOperationException("Unimplemented method 'databaseSearch'");
    }

    @Override
    public void setExactMatch(boolean exact) {
        this.exactMatch = exact;
    }
}


