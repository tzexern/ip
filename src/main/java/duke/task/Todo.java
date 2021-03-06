package duke.task;

/**
 * Represents something to be done in the real world.
 * A Todo
 * object contains what a Task object has.
 */
public class Todo extends Task {
    private static final String ALPHABET_T = "T";

    public Todo(String d) {
        super(d);
    }

    @Override
    public String getTaskType() {
        return ALPHABET_T;
    }

    @Override
    public void printInputErrorMessage(String userInput) {
        super.printInputErrorMessage(userInput);
        System.out.println("  todo [task name]\n"
                + "    e.g. todo read book");
    }
}
