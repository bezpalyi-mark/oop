package ua.khpi.oop.bezpalyi10;

import ua.khpi.oop.bezpalyi07.AddressBook;

public interface AddressBookMenuIO {
    int getMenuChoice();

    AddressBook getAddressBook();

    int getIndexOfBook();

    void printAddressBook(AddressBook book);

    void print(String string);

    int getSortChoice();
}
