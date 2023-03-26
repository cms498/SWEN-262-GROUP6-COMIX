package src;
public class Creator {
    private String name;

    public Creator(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public boolean equals(Object o) {
        if(o instanceof Creator) {
            Creator other = (Creator)o;
            return this.name.equals(other.name);
        }
        return false;
    }
}