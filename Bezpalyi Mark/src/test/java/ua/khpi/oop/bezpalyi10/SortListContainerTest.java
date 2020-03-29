package ua.khpi.oop.bezpalyi10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.khpi.oop.bezpalyi07.AddressBook;
import ua.khpi.oop.bezpalyi09.ListContainer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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
    void setUp() throws ParseException {

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
    }

    @Test
    void sort() {
        ListContainer<AddressBook> list = new ListContainer<>();
        list.insert(addressBook1);
        list.insert(addressBook2);
        list.insert(addressBook3);
        list.insert(addressBook4);

        SortListContainer<AddressBook> sortInst = new SortListContainer<>(comparatorByFN);
        list.setHead(sortInst.sort(list.getHead()));
        List<AddressBook> expected = Arrays.asList(
                addressBook2, addressBook1, addressBook3, addressBook4
        );
        assertList(expected, list);

        sortInst.setComparator(comparatorByLN);
        list.setHead(sortInst.sort(list.getHead()));
        expected = Arrays.asList(
                addressBook4, addressBook3, addressBook1, addressBook2
        );
        assertList(expected, list);

        sortInst.setComparator(comparatorByBD);
        list.setHead(sortInst.sort(list.getHead()));
        expected = Arrays.asList(
                addressBook2, addressBook3, addressBook4, addressBook1
        );
        assertList(expected, list);

        sortInst.setComparator(comparatorByED);
        list.setHead(sortInst.sort(list.getHead()));
        expected = Arrays.asList(
                addressBook3, addressBook1, addressBook2, addressBook4
        );
        assertList(expected, list);
    }

    private void assertList(List<AddressBook> expected, ListContainer<AddressBook> list) {
        for(int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), list.get(i));
        }
    }
}