package ua.khpi.oop.bezpalyi15;

import ua.khpi.oop.bezpalyi07.AddressBook;
import ua.khpi.oop.bezpalyi10.ListMenuIO;

import java.util.ArrayList;
import java.util.List;

public class JavaCollectionsDemo {

    public static void main(String[] args) {
        List<AddressBook> list = new ArrayList<>();
        JavaCollectionConsoleHandler handler = new JavaCollectionConsoleHandler(new ListMenuIO(), list);
        if (args.length > 0 && args[0].equals("-auto")) {
            handler.dialogMode();
        } else {
            handler.autoMode();
        }
    }
}
