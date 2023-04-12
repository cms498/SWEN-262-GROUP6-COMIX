package src.command;

/*
 * Command interface, all future commands will implement this interface.
 * Must be able to execute and undo.
 */
public interface Command {
    void execute();
    void undo();
}
