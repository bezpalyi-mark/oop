package ua.khpi.oop.bezpalyi10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.khpi.oop.bezpalyi07.AddressBook;
import ua.khpi.oop.bezpalyi09.ListContainer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SortListContainerTest {

    AddressBook addressBook1;
    AddressBook addressBook2;
    AddressBook addressBook3;
    AddressBook addressBook4;

    Comparator<ListContainer.Node<AddressBook>> comparatorByFN =
            Comparator.comparing(addressBookNode -> addressBookNode.getValue().getFirstName());

    Comparator<ListContainer.Node<AddressBook>> comparatorByLN =
            Comparator.comparing(addressBookNode -> addressBookNode.getValue().getLastName());


    Comparator<ListContainer.Node<AddressBook>> comparatorByBD =
            Comparator.comparing(addressBookNode -> addressBookNode.getValue().getDateOfBirth());

    Comparator<ListContainer.Node<AddressBook>> comparatorByED =
            Comparator.comparing(addressBookNode -> addressBookNode.getValue().getEditDateAndTime());

    @BeforeEach
    void setUp() {
        addressBook1 = new AddressBook();
        addressBook2 = new AddressBook();
        addressBook3 = new AddressBook();
        addressBook4 = new AddressBook();

        Date date1;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date1 = simpleDateFormat.parse("2009-12-31");
            addressBook1.setDateOfBirth(date1);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        addressBook1.setLastName("First surname");
        addressBook1.setFirstName("Bob");
        addressBook1.setSecondName("First second name");
        addressBook1.addPhoneNumber("9825792");
        addressBook1.addPhoneNumber("3928729");
        addressBook1.setAddress("Address1");

        Date date2;
        try {
            date2 = simpleDateFormat.parse("1980-10-20");
            addressBook2.setDateOfBirth(date2);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        addressBook2.setLastName("Second surname");
        addressBook2.setFirstName("Alex");
        addressBook2.setSecondName("Second second name");
        addressBook2.addPhoneNumber("290302");
        addressBook2.addPhoneNumber("0978431");
        addressBook2.setAddress("Address2");
    }

    @Test
    void sort() {
        ListContainer<AddressBook> list = new ListContainer<>();
        list.insert(addressBook1);
        list.insert(addressBook2);

        SortListContainer<AddressBook> sortInst = new SortListContainer<>(comparatorByFN);
        list.setHead(sortInst.sort(list.getHead()));


    }
}