package ua.khpi.oop.bezpalyi10;

import ua.khpi.oop.bezpalyi07.AddressBook;

public class ListMenuIO implements AddressBookMenuIO {
    private final String mainMenu =
            "1. Add address book\n" +
                    "2. Remove address book\n" +
                    "3. Search address book\n" +
                    "4. Show all address books\n" +
                    "5. Remove all address book\n" +
                    "6. Show size\n" +
                    "7. Sort address books\n" +
                    "8. Exit";

    private final String sortMenu =
            "1. Sort by first name\n" +
                    "2. Sort by last name\n" +
                    "3. Sort by birth date\n" +
                    "4. Sort by edit date";

    @Override
    public int getMenuChoice() {
        return 0;
    }

    @Override
    public AddressBook getAddressBook() {
        return null;
    }

    @Override
    public int getIndexOfBook() {
        return 0;
    }

    @Override
    public void printAddressBook(AddressBook book) {

    }

    @Override
    public void print(String string) {

    }

    @Override
    public int getSortChoice() {
        return 0;
    }
}
