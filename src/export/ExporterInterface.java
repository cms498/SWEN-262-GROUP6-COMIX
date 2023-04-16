package src.export;

/*
 * Interface that all adapters of the personal collection and database will implement
 */
public interface ExporterInterface {

    /*
     * Exports to the required format in that class
     */
    public void export();
}
