package ua.khpi.oop.bezpalyi10;

import ua.khpi.oop.bezpalyi07.AddressBook;

import java.text.ParseException;

public interface AddressBookMenuIO {
    int getMenuChoice();

    AddressBook getAddressBook() throws ParseException;

    int getIndexOfBook();

    void printAddressBook(AddressBook book);

    void print(String string);

    int getSortChoice();
}
