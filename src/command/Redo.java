package src.command;

import java.util.Stack;

/*
 * Concrete Command, This command only redo's any command based on a specific order (FILO)
 */
public class Redo implements Command {

    private Stack<Command> redoStack;
    private Undo undo;

    public Redo() {
        this.redoStack = new Stack<>();
        this.undo = null;
    }

    @Override
    /*
     * Executes the redo command for specific command object.
     * This also adds the command objects back into the undo stack
     */
    public void execute() {
        if (redoStack.size() > 0) {
            Command command = redoStack.pop();
            command.execute();
            undo.addCommand(command);
        } else {
            System.out.println("No commands to redo");
        }
    }

    @Override
    /*
     * prints out error message as the redo object doesn't need this method
     */
    public void undo() {
        System.out.println("Cannot undo a redo");
    }

    /*
     * adds command into the stack of commands that can be redone
     */
    public void addCommand(Command command) {
        redoStack.push(command);
    }

    /*
     * sets the undo attribute
     */
    public void setUndo(Undo undo) {
        this.undo = undo;
    }

}
