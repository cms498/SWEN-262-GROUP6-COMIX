package src;

/**
 * This class represents a comic book publisher, each comic has one
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

    @Override
    /**
     * Used to print out a publisher to the console
     */
    public String toString() {
        return name;
    }
}