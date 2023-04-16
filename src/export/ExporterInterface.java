package src.export;

/**
 * This is the interface all other exporters will inherit behavior from
 * its only method is export() which is responsible for doing the conversion
 * between json to any other type
 */
public interface ExporterInterface {

    /*
     * Exports to the required format in that class
     */
    public void export();
}