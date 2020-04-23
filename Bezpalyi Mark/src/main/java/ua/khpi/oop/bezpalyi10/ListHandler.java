package ua.khpi.oop.bezpalyi10;

import ua.khpi.oop.bezpalyi07.AddressBook;
import ua.khpi.oop.bezpalyi09.ListContainer;

import java.util.Comparator;

public class ListHandler {

    Comparator<ListContainer.Node<AddressBook>> comparatorByFN =
            Comparator.comparing(addressBookNode -> addressBookNode.getValue().getFirstName());

    Comparator<ListContainer.Node<AddressBook>> comparatorByLN =
            Comparator.comparing(addressBookNode -> addressBookNode.getValue().getLastName());


    Comparator<ListContainer.Node<AddressBook>> comparatorByBD =
            Comparator.comparing(addressBookNode -> addressBookNode.getValue().getDateOfBirth());

    Comparator<ListContainer.Node<AddressBook>> comparatorByED =
            Comparator.comparing(addressBookNode -> addressBookNode.getValue().getEditDateAndTime());

    private final AddressBookMenuIO io;

    private SortListContainer<AddressBook> sort;

    public ListHandler() {
        io = new ListMenuIO();
        sort = null;
    }

    public void dialogMod() {
        ListContainer<AddressBook> list = new ListContainer<>();
        System.out.println("List is ready!");
        while (true) {
            int choice = io.getMenuChoice();

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
                    list.add(io.getAddressBook());
                    break;
                case 2:
                    list.remove(io.getIndexOfBook());
                    break;
                case 3:
                    io.printAddressBook(list.get(io.getIndexOfBook()));
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
                            sort = new SortListContainer<>(comparatorByFN);
                            list.setHead(sort.sort(list.getHead()));
                            break;
                        case 2:
                            sort = new SortListContainer<>(comparatorByLN);
                            list.setHead(sort.sort(list.getHead()));
                            break;
                        case 3:
                            sort = new SortListContainer<>(comparatorByBD);
                            list.setHead(sort.sort(list.getHead()));
                            break;
                        case 4:
                            sort = new SortListContainer<>(comparatorByED);
                            list.setHead(sort.sort(list.getHead()));
                            break;
                    }
                    break;
            }
        }
    }
}
