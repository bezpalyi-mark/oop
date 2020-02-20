package ua.khpi.oop.bezpalyi09;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ua.khpi.oop.bezpalyi06.SerializeHandler;
import ua.khpi.oop.bezpalyi07.AddressBook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class Lab09Test {
    static AddressBook addressBook1;
    static AddressBook addressBook2;


    @BeforeAll
    static void setUp() {
        addressBook1 = new AddressBook();
        addressBook2 = new AddressBook();

        Date date1;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date1 = simpleDateFormat.parse("2009-12-31");
            addressBook1.setDateOfBirth(date1);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        addressBook1.setLastName("First surname");
        addressBook1.setFirstName("First first name");
        addressBook1.setSecondName("First second name");
        addressBook1.addPhoneNumber("9825792");
        addressBook1.addPhoneNumber("3928729");

        addressBook2.setAddress("Address2");

        Date date2;
        try {
            date2 = simpleDateFormat.parse("1980-10-20");
            addressBook2.setDateOfBirth(date2);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        addressBook2.setLastName("Second surname");
        addressBook2.setFirstName("Second first name");
        addressBook2.setSecondName("Second second name");
        addressBook2.addPhoneNumber("290302");
        addressBook2.addPhoneNumber("0978431");
    }

    @Test
    void testStandardSerializing() {
        System.out.println("Standard serializing test");
        ListContainer<AddressBook> expected = new ListContainer<>();
        expected.insert(addressBook1);
        expected.insert(addressBook2);

        SerializeHandler serializeHandler = new SerializeHandler();
        serializeHandler.serializeObject(expected, "./data/serData.xml");
        ListContainer<AddressBook> actual =
                (ListContainer<AddressBook>) serializeHandler.deserializeObject("./data/serData.xml");

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void testList() {
        System.out.println("Test of general functions");
        ListContainer<AddressBook> listContainer = new ListContainer<>();
        listContainer.insert(addressBook1);

        assertEquals(listContainer.get(0), addressBook1);
        assertEquals(listContainer.size(), 1);

        listContainer.insert(addressBook2);
        assertEquals(listContainer.get(0), addressBook1);
        assertEquals(listContainer.get(1), addressBook2);
        assertEquals(listContainer.size(), 2);

        listContainer.remove(0);
        assertEquals(listContainer.get(0), addressBook2);
        assertEquals(listContainer.size(), 1);

        listContainer.remove(0);
        assertEquals(listContainer.size(), 0);

        assertNull(listContainer.get(1));

        listContainer.insert(addressBook1);
        listContainer.insert(addressBook2);
        listContainer.clear();
        assertEquals(listContainer.size(), 0);
        assertNull(listContainer.get(1));
    }

    @Test
    void testXMLSerializing() {
        System.out.println("XML serializing test");
        ListContainer<AddressBook> expected = new ListContainer<>();
        expected.insert(addressBook1);
        expected.insert(addressBook2);
        XMLHandler<ListContainer<AddressBook>> xmlHandler = new XMLHandler<>();
        String date = xmlHandler.saveToXML("./data/dataInXml", expected);
        ListContainer<AddressBook> actual = xmlHandler.readFromXML("./data/dataInXml" + date + ".xml");
        for(int i = 0; i < actual.size(); i++) {
            actual.get(i).setEditDateAndTime(
                    expected.get(i).getEditDateAndTime()
            );
        }
        assertEquals(expected.toString(), actual.toString());
    }
}
