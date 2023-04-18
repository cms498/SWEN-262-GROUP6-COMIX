package src;

import java.io.IOException;

/**
 * This is the interface that all other importers will be based on
 * its single import method will be called by the PTUI
 */
public interface ImporterInterface {
    public void Import(String filename) throws IOException;
}
