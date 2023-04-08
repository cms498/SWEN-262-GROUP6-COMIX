package src;

import java.util.ArrayList;
import java.util.List;

public class ComicIssueRange {
    private String min;
    private String max;
    private int amount;
    private List<String> allValidIssues;

    public ComicIssueRange(String issueNumber){
        this.min = issueNumber;
        this.max = issueNumber;
        this.amount = 1;
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

    public void setMin(String min) {
        this.min = min;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public void increaseAmount(){
        this.amount += 1;
    }

    public String getMax() {
        return max;
    }

    public String getMin() {
        return min;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return min + "-" + max;
    }
}
