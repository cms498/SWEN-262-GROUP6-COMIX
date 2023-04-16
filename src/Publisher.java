package src;

/*
 * Represents a publisher for a comic
 */
public class Publisher {
    private String name;

    public Publisher(String name){
        this.name = name;
    }
    
    /** 
     * Gets the Name of the publisher
     * @return String
     */
    public String getName() {
        return name;
    }
    
    /** 
     * Compares a given object with this publisher object
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if(o instanceof Publisher) {
            Publisher other = (Publisher)o;
            return this.name.equals(other.name);
        }
        return false;
    }
    
    /** 
     * Returns the name of the publisher as a String
     * @return String
     */
    @Override
    public String toString() {
        return name;
    }
}