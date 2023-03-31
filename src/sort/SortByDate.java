package src.sort;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import src.Comic;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class SortByDate implements CollectionSorter{

    public int compareDates(Object obj1, Object obj2) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        String dateStr1 = getDateFromObject(obj1); // helper function to get date string from object
        String dateStr2 = getDateFromObject(obj2);
    
        Date date1 = parseDate(dateStr1); // helper function to parse date string into java.util.Date
        Date date2 = parseDate(dateStr2);
    
        return date1.compareTo(date2); // compare dates using compareTo method
    }

    private String getDateFromObject(Object obj) {
        if (obj instanceof Date) {
            // If the object is already a date, simply format it to "yyyy-MM-dd" format
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format((Date)obj);
        } else if (obj instanceof String) {
            // If the object is a string, parse the date based on its format
            String dateString = (String)obj;
            DateFormat dateFormat = null;
            if (dateString.matches("\\w{3} \\d{1,2}, \\d{4}")) {
                dateFormat = new SimpleDateFormat("MMM dd, yyyy");
            } else if (dateString.matches("\\w{3} \\d{4}")) {
                dateFormat = new SimpleDateFormat("MMM yyyy");
            } else if (dateString.matches("\\d{4}")) {
                dateFormat = new SimpleDateFormat("yyyy");
            }
            try {
                Date date = dateFormat.parse(dateString);
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return dateFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        // Return null if the object is not a date or a string with a valid date format
        return null;
    }
    
    
    private Date parseDate(String dateStr) throws ParseException {
        // helper function to parse date string into java.util.Date
        Date date = null;
        if (dateStr.matches("\\w{3} \\d{1,2}, \\d{4}")) { // check if date format is "MMM dd, yyyy"
            DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
            date = dateFormat.parse(dateStr);
        } else if (dateStr.matches("\\w{3} \\d{4}")) { // check if date format is "MMM yyyy"
            DateFormat dateFormat = new SimpleDateFormat("MMM yyyy");
            date = dateFormat.parse(dateStr);
        } else if (dateStr.matches("\\d{4}")) { // check if date format is "yyyy"
            DateFormat dateFormat = new SimpleDateFormat("yyyy");
            date = dateFormat.parse(dateStr);
        }
        return date;
    }
    
    @Override
public List<Comic> sort(List<Comic> comics) {
    List<Comic> sorted = new ArrayList<>(comics);
    
    // sort the list using a custom Comparator
    Collections.sort(sorted, new Comparator<Comic>() {
        @Override
        public int compare(Comic c1, Comic c2) {
            // get the date strings from each comic's date attribute
            String date1 = getDateFromObject(c1.getPublicationDate());
            String date2 = getDateFromObject(c2.getPublicationDate());
            
            // parse the date strings into LocalDate objects
            LocalDate localDate1 = LocalDate.parse(date1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate localDate2 = LocalDate.parse(date2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            
            // compare the local dates and return the result
            return localDate1.compareTo(localDate2);
        }
    });
    
    return sorted;
}

}
