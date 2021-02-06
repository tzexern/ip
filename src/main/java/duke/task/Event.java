package duke.task;

import duke.exceptions.EmptyInputException;
import duke.exceptions.IncompleteInputException;
import duke.exceptions.InvalidDateInputException;

public class Event extends Task{
    private static final String ALPHABET_E = "E";
    private String at;
    private static final String JAN = "01";
    private static final String FEB = "02";
    private static final String MAR = "03";
    private static final String APR = "04";
    private static final String MAY = "05";
    private static final String JUN = "06";
    private static final String JUL = "07";
    private static final String AUG = "08";
    private static final String SEP = "09";
    private static final String OCT = "10";
    private static final String NOV = "11";
    private static final String DEC = "12";

    public Event(String d, String at){
        super(d);
        this.at = at;
    }

    @Override
    public String getTaskType(){
        return ALPHABET_E;
    }

    @Override
    public void printTaskInformation(){
        super.printTaskInformation();
        this.printTime();
    }

    public void printTime(){
        System.out.print(" (at:" + this.at + ")");
    }

    @Override
    public void printInputErrorMessage(String userInput) {
        super.printInputErrorMessage(userInput);
        System.out.println("  event [event name] /at [MM-DD-YYYY]\n"
                + "    e.g. event project meeting /at 03-28-2021");
    }

    @Override
    public void printInvalidDateInputMessage(String userInput) {
        super.printInvalidDateInputMessage(userInput);
        System.out.println("  event [event name] /at [MM-DD-YYYY]\n"
                + "    e.g. event project meeting /at 03-28-2021");
    }

    @Override
    public void addTask() throws EmptyInputException, IncompleteInputException, InvalidDateInputException {
        // Check if date field is valid
        if (isIncomplete(this)){
            throw new IncompleteInputException();
        }
        if (isInvalidDate(this)){
            throw new InvalidDateInputException();
        }
        super.addTask();
    }

    private boolean isIncomplete(Event e){
        return e.at.equals("");
    }

    private boolean isInvalidDate(Event e){
        String[] splitDate = e.at.trim().split("-");
        // Formatting is incorrect
        if (splitDate.length != 3){
            return true;
        }
        // Check if date is within valid range
        String month = splitDate[0];
        String day = splitDate[1];
        String year = splitDate[2];

        if (Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12){
            return true;
        }
        if (Integer.parseInt(year) < 2021){
            return true;
        }
        // Check day lower limit
        if (Integer.parseInt(day) < 1){
            return true;
        }

        // Check day upper limit according to month
        switch(month){
        case FEB:
            if (isLeapYear(Integer.parseInt(year))){
                if (Integer.parseInt(day) > 29){
                    return true;
                }
            } else {
                if (Integer.parseInt(day) > 28){
                    return true;
                }
            }
        case JAN:
        case MAR:
        case MAY:
        case JUL:
        case AUG:
        case OCT:
        case DEC:
            if (Integer.parseInt(day) > 31){
                return true;
            }
        case APR:
        case JUN:
        case SEP:
        case NOV:
            if (Integer.parseInt(day) > 30){
                return true;
            }
        }
        return false;
    }

    private boolean isLeapYear(int year){
        return (year % 4 == 0);
    }

}