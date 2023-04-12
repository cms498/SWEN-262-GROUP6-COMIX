package src.command;

import java.util.Stack;

public class Redo implements Command{

    private Stack<Command> redoStack;
    private Undo undo;

    public Redo(Stack<Command> redoStack) {
        this.redoStack = redoStack;
        this.undo = null;
    }

    @Override
    public void execute() {
        if(redoStack.size() > 0){
            Command command = redoStack.pop();
            command.execute();
            undo.addCommand(command);
        }
    }

    @Override
    public void undo() {
        System.out.println("Cannot undo a redo");
    }

    public void addCommand(Command command) {
        redoStack.push(command);
    }
    
}
