package ua.khpi.oop.bezpalyi10;

import ua.khpi.oop.bezpalyi05.StringContainer;
import ua.khpi.oop.bezpalyi07.AddressBook;
import ua.khpi.oop.bezpalyi09.ListContainer;
import ua.khpi.oop.bezpalyi11.AddressBookAccessor;
import ua.khpi.oop.bezpalyi11.BookAccessor;

import java.io.*;
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
    private final BookAccessor accessor;

    public ListMenuIO() {
        scanner = new Scanner(System.in);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        accessor = new AddressBookAccessor();
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
        String data = scanner.next();
        if (accessor.assertNames(data)) {
            bookBuilder.setFirstName(data);
        } else {
            System.out.println("Invalid input first name!");
            return null;
        }

        System.out.print("Enter last name: ");
        data = scanner.next();
        if (accessor.assertNames(data)) {
            bookBuilder.setLastName(data);
        } else {
            System.out.println("Invalid input last name!");
            return null;
        }

        System.out.print("Enter second name: ");
        data = scanner.next();
        if (accessor.assertNames(data)) {
            bookBuilder.setSecondName(data);
        } else {
            System.out.println("Invalid input second name!");
            return null;
        }

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
        if (choice < 1 || choice > 4) {
            return 0;
        }
        return choice;
    }

    private StringContainer addPhones() {
        StringContainer container = new StringContainer();
        System.out.print("How many phone numbers book will contain? (max: 5)  ");
        int size = scanner.nextInt();

        if (size < 0) {
            return container;
        } else if (size > AddressBookAccessor.MAX_PHONE_NUMBERS_SIZE) {
            size = AddressBookAccessor.MAX_PHONE_NUMBERS_SIZE;
        }

        int maxTriesSize = 5;
        String data;
        for (int i = 0; i < size && maxTriesSize > 0; i++) {
            System.out.print("Enter phone number:");
            data = scanner.next();
            if (accessor.assertPhoneNumber(data)) {
                container.add(data);
            } else {
                i--;
                System.out.println("Invalid input phone number!");
                maxTriesSize--;
            }
        }
        return container;
    }

    public ListContainer<AddressBook> readAddressBooksFromFile(String path) {
        ListContainer<AddressBook> list = new ListContainer<>();
        try(FileReader reader = new FileReader(path)) {
            BufferedReader br = new BufferedReader(reader);
            AddressBook.Builder bookBuilder = new AddressBook.Builder();

            String data = br.readLine();
            while(data != null) {
                if (accessor.assertNames(data)) {
                    bookBuilder.setFirstName(data);
                } else {
                    System.out.println("Invalid input first name!");
                    return list;
                }

                data = br.readLine();
                if (accessor.assertNames(data)) {
                    bookBuilder.setLastName(data);
                } else {
                    System.out.println("Invalid input last name!");
                    return list;
                }

                data = br.readLine();
                if (accessor.assertNames(data)) {
                    bookBuilder.setSecondName(data);
                } else {
                    System.out.println("Invalid input second name!");
                    return list;
                }

                data = br.readLine();
                Date date = simpleDateFormat.parse(data);
                bookBuilder.setDateOfBirth(date);

                data = br.readLine();
                bookBuilder.setAddress(data);

                bookBuilder.setEditTime(LocalDateTime.now().toString());

                data = br.readLine();
                String[] phoneNumbers = data.split(", ");
                StringContainer sc = new StringContainer();
                for(String s : phoneNumbers) {
                    sc.add(s);
                }
                bookBuilder.setPhoneNumbers(sc);
                list.add(bookBuilder.build());

                br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No such file " + path);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (ParseException e) {
            System.out.println("Invalid birth date format!");
        }
        return list;
    }
}
