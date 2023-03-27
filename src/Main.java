package src;

public class Main {
    public static void main(String[] args) {
        PersonalCollection collection = new PersonalCollection();
        collection.initializeComics();
        System.out.println(collection.getComics().toString());
        collection.clearJson();
        collection.convertBackToJson();
    }
}
