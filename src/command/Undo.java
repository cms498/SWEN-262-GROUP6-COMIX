package src.command;

import java.util.Stack;

public class Undo implements Command{

    private Stack<Command> undoStack;
    private Redo redo;

    public Undo(Stack<Command> undoStack) {
        this.undoStack = undoStack;
        this.redo = null;
    }

    @Override
    public void execute() {
        if(undoStack.size() > 0){
            Command command = undoStack.pop();
            command.undo();
            redo.addCommand(command);
        }
    }

    @Override
    public void undo() {
        System.out.println("Cannot undo an undo");
    }

    public void addCommand(Command command) {
        undoStack.push(command);
    }

    public void setRedo(Redo redo) {
        this.redo = redo;
    }
    
}
