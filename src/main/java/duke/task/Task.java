package duke.task;

import duke.exceptions.EmptyInputException;
import duke.exceptions.IncompleteInputException;
import duke.exceptions.InvalidDateInputException;
import java.util.ArrayList;

public class Task {
    private static final int MAX_NUMBER_OF_TASKS = 100;
    protected String description;
    private boolean isDone;
//    private static Task[] taskList = new Task[MAX_NUMBER_OF_TASKS];
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int taskCount = 0;
    private static String time;

    // duke.task.Task Constructor
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return(this.isDone ? "x" : " ");
    }

    public static void markAsDone(String i){
        int index = Integer.parseInt(i);
        Task t = taskList.get(index-1);
        t.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("  ");
        t.printTaskInformation();
        System.out.println("");
    }

    // Add duke.task.Task to taskList and increment taskCount
    public void addTask() throws
            EmptyInputException, IncompleteInputException, InvalidDateInputException {
        // Throw Exception if no valid task is detected
        if (isEmpty(this)){
            throw new EmptyInputException();
        }

        System.out.println("Got it, I've added this task:");
        System.out.print("  ");
        this.printTaskInformation();
        System.out.println("");
        taskList.add(this);
        taskCount++;
        printTaskCount();
    }

    public static void deleteTask(String i){
        int index = Integer.parseInt(i);
        Task t = taskList.get(index-1);
        System.out.println("Noted. I've removed this task:");
        System.out.print("  ");
        t.printTaskInformation();
        System.out.println("");
        taskCount--;
        printTaskCount();
    }

    private static void printTaskCount() {
        if(taskCount == 1) {
            System.out.println("Now you have " + taskCount + " task in the list.");
        }
        else{
            System.out.println("Now you have " + taskCount + " tasks in the list.");
        }
    }

    public static void listTasks(){
        int index = 1;
        System.out.println("Here are the tasks in your list:");
        for(Task t : taskList){
            printIndex(index);
            t.printTaskInformation();
            System.out.println("");
            index++;
            if(!(taskCount > index - 1)){
                break;
            }
        }
    }

    private static void printIndex(int index) {
        System.out.print(Integer.toString(index) + ".");
    }

    public void printTaskInformation(){
        this.printTaskType();
        this.printStatusIcon();
        this.printTaskName();
    }

    public String getTaskType(){
        return "N/A";
    }

    private void printTaskType(){
        System.out.print("[" + this.getTaskType() + "]");
    }

    private void printStatusIcon(){
        System.out.print("[" + this.getStatusIcon() + "] ");
    }

    private void printTaskName(){
        System.out.print(this.description);
    }

    private boolean isEmpty(Task t){
        return t.description.equals("");
    }

    public void printInputErrorMessage(String userInput) {
        System.out.println("This command entered is incomplete: "
                + userInput + "\n");
        System.out.println("Please enter the command as follows:");
    }

    public void printInvalidDateInputMessage(String userInput) {
        System.out.println("The date entered is invalid: "
                + userInput + "\n");
        System.out.println("Please enter the command as follows:");
    }

}
