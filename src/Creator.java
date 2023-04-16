package src;

/*
 * Represents a creator for a comic
 */
public class Creator {
    private String name;

    public Creator(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    /*
     * Compares a given object with this creator
     * by comparing the names of each
     * If object isn't a creator returns false
     * 
     * @return true if the objects are the same, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if(o instanceof Creator) {
            Creator other = (Creator)o;
            return this.name.equals(other.name);
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}