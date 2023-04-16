package src;

/*
 * Represents a publisher for a comic
 */
public class Publisher {
    private String name;

    public Publisher(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the comic book publisher
     * 
     * @return name of publisher
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
    /**
     * This method is used to compared two publishers and see if they are equal
     * to each other, they are equal if they have the same name
     */
    public boolean equals(Object o) {
        if (o instanceof Publisher) {
            Publisher other = (Publisher) o;
            return this.name.equals(other.name);
        }
        return false;
    }
    
    /** 
     * Returns the name of the publisher as a String
     * @return String
     */
    @Override
    /**
     * Used to print out a publisher to the console
     */
    public String toString() {
        return name;
    }
}