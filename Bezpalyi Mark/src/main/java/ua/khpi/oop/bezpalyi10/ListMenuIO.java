package ua.khpi.oop.bezpalyi10;

import ua.khpi.oop.bezpalyi05.StringContainer;
import ua.khpi.oop.bezpalyi07.AddressBook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class ListMenuIO implements AddressBookMenuIO {
    private final static String MAIN_MENU =
            "1. Add address book\n" +
                    "2. Remove address book\n" +
                    "3. Search address book\n" +
                    "4. Show all address books\n" +
                    "5. Remove all address book\n" +
                    "6. Show size\n" +
                    "7. Sort address books\n" +
                    "8. Exit";

    private final static String SORT_MENU =
            "1. Sort by first name\n" +
                    "2. Sort by last name\n" +
                    "3. Sort by birth date\n" +
                    "4. Sort by edit date";

    private final Scanner scanner;
    private final SimpleDateFormat simpleDateFormat;

    public ListMenuIO() {
        scanner = new Scanner(System.in);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public int getMenuChoice() {
        System.out.println(MAIN_MENU);
        return scanner.nextInt();
    }

    @Override
    public AddressBook getAddressBook() throws ParseException {
        AddressBook.Builder bookBuilder = new AddressBook.Builder();
        System.out.print("Enter first name: ");
        bookBuilder.setFirstName(scanner.next());

        System.out.print("Enter last name: ");
        bookBuilder.setLastName(scanner.next());

        System.out.print("Enter second name: ");
        bookBuilder.setSecondName(scanner.next());

        System.out.print("Enter date of birth in format yyyy-MM-dd: ");
        Date date = simpleDateFormat.parse(scanner.next());
        bookBuilder.setDateOfBirth(date);

        System.out.print("Enter address: ");
        bookBuilder.setAddress(scanner.next());

        bookBuilder.setEditTime(LocalDateTime.now().toString());
        bookBuilder.setPhoneNumbers(addPhones());
        return bookBuilder.build();
    }

    @Override
    public int getIndexOfBook() {
        System.out.print("Enter index of book: ");
        return scanner.nextInt();
    }

    @Override
    public void printAddressBook(AddressBook book) {
        System.out.println(book);
    }

    @Override
    public void print(String string) {
        System.out.println(string);
    }

    @Override
    public int getSortChoice() {
        System.out.println("Choose number of sort");
        System.out.println(SORT_MENU);
        int choice = scanner.nextInt();
        if(choice < 1 || choice > 4) {
            return 0;
        }
        return choice;
    }

    private StringContainer addPhones() {
        StringContainer container = new StringContainer();
        System.out.print("How many phone numbers book will contain?  ");
        int size = scanner.nextInt();

        for(int i = 0; i < size; i++) {
            System.out.print("Enter phone number:");
            container.add(scanner.next());
        }
        return container;
    }
}
