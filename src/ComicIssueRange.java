package src;

import java.util.ArrayList;
import java.util.List;

public class ComicIssueRange {
    private List<String> allValidIssues;

    public ComicIssueRange(String issueNumber){
        this.allValidIssues = new ArrayList<>();
        this.allValidIssues.add(issueNumber);
    }

    public void setAllValidIssues(List<String> allValidIssues) {
        this.allValidIssues = allValidIssues;
    }

    public void addToAllValidIssues(String issueNumber){
        this.allValidIssues.add(issueNumber);
    }

    public List<String> getAllValidIssues() {
        return allValidIssues;
    }


    public String getMax() {
        String currMax = "";
        int maxIndex = 0;
        while(currMax.isEmpty()){
            currMax = allValidIssues.get(maxIndex).replaceAll("[^\\d.]+", "");
            if(currMax.isEmpty()){
                maxIndex += 1;
            }
        }
        for(int i = maxIndex; i < allValidIssues.size(); i++){
            String tempString = allValidIssues.get(i).replaceAll("[^\\d.]+", "");
            if(Double.parseDouble(currMax) < Double.parseDouble(tempString) && !tempString.isEmpty()){
                currMax = tempString;
                maxIndex = i;
            }
        }
        return allValidIssues.get(maxIndex);
    }

    public String getMin() {
        String currMin = "";
        int minIndex = 0;
        while(currMin.isEmpty()){
            currMin = allValidIssues.get(minIndex).replaceAll("[^\\d.]+", "");
            if(currMin.isEmpty()){
                minIndex += 1;
            }
        }
        for(int i = minIndex; i < allValidIssues.size(); i++){
            String tempString = allValidIssues.get(i).replaceAll("[^\\d.]+", "");
            if(Double.parseDouble(currMin) > Double.parseDouble(tempString) && !tempString.isEmpty()){
                currMin = tempString;
                minIndex = i;
            }
        }
        return allValidIssues.get(minIndex);
    }

    public int getAmount() {
        return allValidIssues.size();
    }

    @Override
    public String toString() {
        return this.getMin() + " - " + this.getMax();
    }
}
