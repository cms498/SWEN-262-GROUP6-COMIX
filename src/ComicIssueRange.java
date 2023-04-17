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
    private int min;
    private int max;

    public ComicIssueRange(String issueNumber){
        this.allValidIssues = new ArrayList<>();
        this.allValidIssues.add(issueNumber);
        this.rangeAmount = 0;
        this.min = 0;
        this.max = 0;
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
    public int getMax() {
       return max;
    }

    /*
     * Gets the lowest issue number within the range
     */
    public int getMin() {
        return min;
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

        min = issueNumbers.get(0);
        max = issueNumbers.get(issueNumbers.size()-1);

        for(int i = 0; i < issueNumbers.size()-1; i++){
            if(issueNumbers.get(i) + 1 == issueNumbers.get(i+1)){
                rangeAmount += 1;
            }
            else{
                if(rangeAmount >= 12){
                    max = issueNumbers.get(i);
                    break;
                }
                rangeAmount = 0;
                min = issueNumbers.get(i+1);
            }
        }
    }

    public List<Integer> sortIssueNumbersWithGaps(){
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
        List<Integer> gapCount = new ArrayList<>();

        min = issueNumbers.get(0);
        max = issueNumbers.get(issueNumbers.size()-1);
        
        for(int i = 0; i < issueNumbers.size()-1; i++){
            if(issueNumbers.get(i) + 1 == issueNumbers.get(i+1)){
                rangeAmount += 1;
            }
            else{
                if(issueNumbers.get(i) + 2 == issueNumbers.get(i+1)){
                   gapCount.add(issueNumbers.get(i)+1);
                }
                else{
                    rangeAmount = 0;
                }
            }
        }
        return gapCount;
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