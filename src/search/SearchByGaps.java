package src.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.Comic;
import src.ComicIssueRange;

/*
 * Implementation of Collection Searcher that searches for any gaps within a series of comics
 */
public class SearchByGaps implements CollectionSearcher{

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
            List<Integer> gapCount = issueRange.sortIssueNumbersWithGaps();
            if(issueRange.getAmount() >= 12 && gapCount.size() < 3){
                searchComics.add(new Comic(ComicTitleAndVolume[0], Integer.parseInt(ComicTitleAndVolume[1]), issueRange.toString() + ", Missing: " + gapCount.toString()));    
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


    /*
     * N/A
     */
    @Override
    public List<Comic> databaseSearch(String searchTerm) {
        throw new UnsupportedOperationException("Unimplemented method 'databaseSearch'");
    }

    @Override
    public void setExactMatch(boolean exact) {
    }
}


