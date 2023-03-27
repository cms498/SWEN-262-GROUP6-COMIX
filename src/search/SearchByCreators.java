package src.search;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import src.Comic;
import src.Creator;

/*
 * A implementation of Collection Searcher
 * Searches comics by comparing creators and the search term
 */
public class SearchByCreators implements CollectionSearcher{

    private boolean exactMatch;
    
    public SearchByCreators(boolean exactMatch) {
        this.exactMatch = exactMatch;
    }
    
    /** 
     * Searches through the list of given comics for any matches with a comic's
     * creators and the given search term
     * @param comics
     * @param searchTerm
     * @return List<Comic>
     */
    @Override
    public List<Comic> search(List<Comic> comics, String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        List<Comic> searchComics = new ArrayList<>();
        if(this.exactMatch) {
            for(Comic comic : comics) {
                for(Creator creator : comic.getCreators()) {
                    if(searchTerm.equals(creator.getName().toLowerCase())) {
                        searchComics.add(comic);
                    }
                }
            }
        } else {
            for(Comic comic : comics) {
                for(Creator creator : comic.getCreators()) {
                    if(creator.getName().toLowerCase().contains(searchTerm)) {
                        searchComics.add(comic);
                    }
                }
            }
        }
        return searchComics;
    }


    
    /** 
     * Searches the database for any comics having a creator with
     * the matching search term
     * @param filename
     * @param searchTerm
     * @return List<Comic>
     */
    @Override
    public List<Comic> databaseSearch(String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        List<Comic> searchComics = new ArrayList<>();
        File file = new File(COMIC_DATABASE);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine(); //skipping a line
            while((line = br.readLine()) != null) {
                String[] split = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if(split.length == 9) {
                    String[] creatorSplit = split[8].split(" \\| ");
                for(int i = 0; i < creatorSplit.length; i++) {
                    String creator = creatorSplit[i].replace("\"", "");
                    if(exactMatch) {
                        if(searchTerm.equals(creator.toLowerCase())) {
                            System.out.println("Verified match");
                            searchComics.add(generateComic(split));
                        }
                    } else {
                        if(creatorSplit[i].toLowerCase().contains(searchTerm)) {
                           searchComics.add(generateComic(split));
                        }
                    }
                }
                }
            }
            br.close();
            return searchComics;
        } catch (IOException e){
            System.out.println("Invalid filename.");
        }
        return searchComics;
    }

    public void setExactMatch(boolean exactMatch) {
        this.exactMatch = exactMatch;
    }

    public static void main(String[] args) {
        SearchByCreators sbc = new SearchByCreators(true);
        List<Comic> comics = sbc.databaseSearch("Karl Kesel");
        for(Comic comic: comics) {
            System.out.println(comic.getStoryTitle());
        }
    }
}
