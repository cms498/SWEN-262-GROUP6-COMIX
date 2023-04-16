package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Represents a range of all the issues of a comic with the same title
 */
public class ComicIssueRange {
    private List<String> allValidIssues;
    private int rangeAmount;

    public ComicIssueRange(String issueNumber){
        this.allValidIssues = new ArrayList<>();
        this.allValidIssues.add(issueNumber);
        this.rangeAmount = 0;
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
    
    /*
     * Gets the highest issue number within the range
     */
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

    /*
     * Gets the lowest issue number within the range
     */
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

    public void sortIssueNumbers(){
        List<Integer> issueNumbers = new ArrayList<>();
        for(int i = 0; i < allValidIssues.size(); i++){
            String issueNumber = allValidIssues.get(i);
            String number = "";
            int j = 0;
            while(j < issueNumber.length()){
                if(!Character.isDigit(issueNumber.charAt(j))){
                    break;
                }
               number = number + issueNumber.charAt(j);
               j++;
            }
            issueNumbers.add(Integer.parseInt(number));
        }
        Collections.sort(issueNumbers);

        for(int i = 0; i < issueNumbers.size()-1; i++){
            if(issueNumbers.get(i) + 1 == issueNumbers.get(i+1)){
                rangeAmount += 1;
            }
            else{
                rangeAmount = 0;
            }
        }
    }

    /*
     * Gets the amount of issues within the range
     */
    public int getAmount() {
        return rangeAmount;
    }

    @Override
    public String toString() {
        return this.getMin() + " - " + this.getMax();
    }
}