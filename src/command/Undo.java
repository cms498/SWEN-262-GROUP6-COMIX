package src.command;

import java.util.Stack;

/*
 * Concrete Command, This command only undo's any command based on a specific order (FILO)
 */
public class Undo implements Command{

    private Stack<Command> undoStack;
    private Redo redo;

    public Undo() {
        this.undoStack = new Stack<>();
        this.redo = null;
    }

    @Override
     /*
     * Executes the undo command for specific command object.
     * This also adds the command objects back into the redo stack
     */
    public void execute() {
        if(undoStack.size() > 0){
            Command command = undoStack.pop();
            command.undo();
            redo.addCommand(command);
        } else {
            System.out.println("No commands to undo");
        }
    }

    @Override
    /*
     * prints out error message as the undo object doesn't need this method
     */
    public void undo() {
        System.out.println("Cannot undo an undo");
    }

    /*
     * adds command into the stack of commands that can be undone
     */
    public void addCommand(Command command) {
        undoStack.push(command);
    }

    /*
     * sets the redo attribute
     */
    public void setRedo(Redo redo) {
        this.redo = redo;
    }
    
}
