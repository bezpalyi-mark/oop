package ua.khpi.oop.bezpalyi15;

import ua.khpi.oop.bezpalyi07.AddressBook;
import ua.khpi.oop.bezpalyi09.ListContainer;
import ua.khpi.oop.bezpalyi10.AddressBookMenuIO;
import ua.khpi.oop.bezpalyi13.LoadData;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JavaCollectionConsoleHandler {
    private final AddressBookMenuIO io;

    private List<AddressBook> list;

    public JavaCollectionConsoleHandler(AddressBookMenuIO io, List<AddressBook> list) {
        this.io = io;
        this.list = list;
    }

    public void dialogMode() {
        System.out.println("List is ready!");
        while (true) {
            int choice = io.getMenuCollectionChoice();

            if (choice == 8) {
                System.out.println("Goodbye");
                break;
            }

            if (choice < 1 || choice > 7) {
                System.out.println("Invalid input data!");
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        AddressBook addressBook = io.getAddressBook();
                        if (addressBook != null) {
                            list.add(addressBook);
                        }
                    } catch (ParseException e) {
                        System.out.println("Failed to parse data!");
                    }
                    break;
                case 2:
                    list.remove(io.getIndexOfBook());
                    break;
                case 3:
                    io.printAddressBook(list.get(io.getIndexOfBook() - 1));
                    break;
                case 4:
                    io.print(list.toString());
                    break;
                case 5:
                    list.clear();
                    break;
                case 6:
                    io.print("Size: " + list.size());
                    break;
                case 7:
                    int sortChoice = io.getSortChoice();
                    if (sortChoice == 0) {
                        System.out.println("Invalid input data!");
                        break;
                    }
                    switch (sortChoice) {
                        case 1:
                            list = list.stream()
                                    .sorted(Comparator.comparing(AddressBook::getFirstName))
                                    .collect(Collectors.toList());
                            break;
                        case 2:
                            list = list.stream()
                                    .sorted(Comparator.comparing(AddressBook::getLastName))
                                    .collect(Collectors.toList());
                            break;
                        case 3:
                            list = list.stream()
                                    .sorted(Comparator.comparing(AddressBook::getDateOfBirth))
                                    .collect(Collectors.toList());
                            break;
                        case 4:
                            list = list.stream()
                                    .sorted(Comparator.comparing(AddressBook::getEditDateAndTime))
                                    .collect(Collectors.toList());
                            break;
                    }
                    break;
            }
        }
    }

    public void autoMode() {
        List<AddressBook> list = new ArrayList<>();
        ListContainer<AddressBook> bookListContainer = LoadData.loadList();
        for (int i = 0; i < 10; i++) {
            list.add(bookListContainer.get(i));
        }
        io.print(list.toString());
    }
}
