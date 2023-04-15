package src;

/**
 * This class represents a comic book creators, a comic can have one or multiple
 * creators
 */
public class Creator {
    private String name;

    public Creator(String name) {
        this.name = name;
    }

    /**
     * Used to get the name of the specific creator
     * 
     * @return name of the creator
     */
    public String getName() {
        return name;
    }

    @Override
    /**
     * This method compares two creators and determines if they are equal,
     * they are equal if they have the same name
     */
    public boolean equals(Object o) {
        if (o instanceof Creator) {
            Creator other = (Creator) o;
            return this.name.equals(other.name);
        }
        return false;
    }

    @Override
    /**
     * Used to print out a creator to the console
     */
    public String toString() {
        return name;
    }
}