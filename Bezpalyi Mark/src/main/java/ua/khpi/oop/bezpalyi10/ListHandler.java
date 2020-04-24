package ua.khpi.oop.bezpalyi10;

import ua.khpi.oop.bezpalyi07.AddressBook;
import ua.khpi.oop.bezpalyi09.ListContainer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;

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
                    try {
                        list.add(io.getAddressBook());
                    } catch (ParseException e) {
                        System.out.println("Failed to parse data!");
                    }
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

    public void autoMode() throws ParseException {
        ListContainer<AddressBook> list = new ListContainer<>();
        AddressBook addressBook1;
        AddressBook addressBook2;
        AddressBook addressBook3;
        AddressBook addressBook4;
        LocalDateTime now = LocalDateTime.now();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = simpleDateFormat.parse("2009-12-31");

        addressBook1 = new AddressBook.Builder()
                .setFirstName("Bob")
                .setLastName("First surname")
                .setSecondName("First second name")
                .setAddress("Address1")
                .setDateOfBirth(date1)
                .setEditTime(now.toString())
                .build();

        addressBook1.addPhoneNumber("9825792");
        addressBook1.addPhoneNumber("3928729");


        Date date2 = simpleDateFormat.parse("1980-10-20");
        addressBook2 = new AddressBook.Builder()
                .setFirstName("Alex")
                .setLastName("Second surname")
                .setSecondName("Second second name")
                .setAddress("Address2")
                .setDateOfBirth(date2)
                .setEditTime(now.plusDays(3).toString())
                .build();

        addressBook2.addPhoneNumber("290302");
        addressBook2.addPhoneNumber("0978431");

        Date date3 = simpleDateFormat.parse("2000-09-23");
        addressBook3 = new AddressBook.Builder()
                .setFirstName("John")
                .setLastName("Biter")
                .setSecondName("Alfred")
                .setAddress("Address3")
                .setDateOfBirth(date3)
                .setEditTime(now.minusHours(72).toString())
                .build();
        addressBook3.addPhoneNumber("74744798");

        Date date4 = simpleDateFormat.parse("2000-10-20");
        addressBook4 = new AddressBook.Builder()
                .setFirstName("Mark")
                .setLastName("Bezpalyi")
                .setSecondName("Leonidovich")
                .setAddress("Address4")
                .setDateOfBirth(date4)
                .setEditTime(now.plusMonths(2).toString())
                .build();
        addressBook4.addPhoneNumber("0371630238743");

        list.add(addressBook1);
        list.add(addressBook2);
        list.add(addressBook3);
        list.add(addressBook4);

        io.print(list.toString());
    }
}
