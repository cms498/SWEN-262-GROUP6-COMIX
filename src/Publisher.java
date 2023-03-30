package src;
public class Publisher {
    private String name;

    public Publisher(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Publisher) {
            Publisher other = (Publisher)o;
            return this.name.equals(other.name);
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}