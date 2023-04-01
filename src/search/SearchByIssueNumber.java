package src.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import src.Comic;

public class SearchByIssueNumber implements CollectionSearcher{

    private boolean exactMatch;

    public SearchByIssueNumber(boolean exactMatch) {
        this.exactMatch = exactMatch;
    }
    @Override
    public List<Comic> search(List<Comic> comics, String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        List<Comic> searchComics = new ArrayList<>();
        for(Comic comic: comics) {
            if(exactMatch) {
                if(comic.getIssueNumber().toLowerCase().equals(searchTerm)) {
                    searchComics.add(comic);
                }
            } else {
                if(comic.getIssueNumber().toLowerCase().contains(searchTerm)) {
                    searchComics.add(comic);
                }
            }
        }
        return searchComics;
    }

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
                    if(exactMatch) {
                        if(searchTerm.equals(split[1])) {
                            searchComics.add(generateComic(split));
                        }
                    } else {
                        if(split[1].contains(searchTerm)) {
                           searchComics.add(generateComic(split));
                        }
                    }
                }
                br.close();
        } catch (IOException e){
            System.out.println("Invalid filename.");
        }
        return searchComics;
    }

    @Override
    public void setExactMatch(boolean exact) {
        this.exactMatch = exact;
    }
    
}
