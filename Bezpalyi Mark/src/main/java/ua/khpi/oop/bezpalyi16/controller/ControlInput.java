package ua.khpi.oop.bezpalyi16.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.khpi.oop.bezpalyi05.StringContainer;
import ua.khpi.oop.bezpalyi07.AddressBook;
import ua.khpi.oop.bezpalyi09.ListContainer;
import ua.khpi.oop.bezpalyi11.AddressBookAccessor;
import ua.khpi.oop.bezpalyi13.LoadData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControlInput {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControlInput.class);

    private final List<AddressBook> books = new ArrayList<>();

    public ControlInput() {
    }

    public int getIndex(String text) {
        int index;
        try {
            index = Integer.parseInt(text);
            if (index <= 0 || index > books.size()) {
                LOGGER.error("Invalid input!");
                return -1;
            }
        } catch (NumberFormatException e) {
            LOGGER.error("Invalid input!");
            return -1;
        }
        return index;
    }

    public List<AddressBook> getBooks() {
        return books;
    }

    public void loadData() {
        if (books.size() == 0) {
            ListContainer<AddressBook> bookListContainer = LoadData.loadList();
            for (int i = 0; i < bookListContainer.size(); i++) {
                books.add(bookListContainer.get(i));
            }
        }
    }

    public String getAllAddressBooks() {
        StringBuilder stringBuilder = new StringBuilder();
        for (AddressBook addressBook : books) {
            stringBuilder.append(addressBook.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    public String addNewAddressBook(String firstName, String lastName, String secondName,
                                     String dateOfBirth, String address, String phoneNumber) {
        AddressBookAccessor accessor = new AddressBookAccessor();
        AddressBook.Builder bookBuilder = new AddressBook.Builder();

        if (accessor.assertNames(firstName)) {
            bookBuilder.setFirstName(firstName);
        } else {
            LOGGER.error("Invalid input first name!");
            return "first name";
        }

        if (accessor.assertNames(lastName)) {
            bookBuilder.setLastName(lastName);
        } else {
            LOGGER.error("Invalid input last name!");
            return "last name";
        }

        if (accessor.assertNames(secondName)) {
            bookBuilder.setSecondName(secondName);
        } else {
            LOGGER.error("Invalid input second name!");
            return "second name";
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = format.parse(dateOfBirth);
            bookBuilder.setDateOfBirth(parse);
        } catch (ParseException e) {
            LOGGER.error("Error while parsing date");
            return "date";
        }

        if (accessor.assertPhoneNumber(phoneNumber)) {
            StringContainer strings = new StringContainer();
            strings.add(phoneNumber);
            bookBuilder.setPhoneNumbers(strings);
        } else {
            LOGGER.error("Invalid phone number!");
            return "phone number";
        }

        bookBuilder.setAddress(address);
        bookBuilder.setEditTime(LocalDateTime.now().toString());
        books.add(bookBuilder.build());
        return null;
    }

}
