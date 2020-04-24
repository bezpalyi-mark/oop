package ua.khpi.oop.bezpalyi10;

import java.text.ParseException;

public class DemoListApp {
    public static void main(String[] args) throws ParseException {
        ListHandler listHandler = new ListHandler();
        if(args[0].equals("-auto")) {
            listHandler.autoMode();
        } else {
            listHandler.dialogMod();
        }
    }
}
