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

/**
 * Class - controller input data.
 */
public class ControlInput {

    /**
     * Apache logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ControlInput.class);

    /**
     * Stored books, container.
     */
    private final List<AddressBook> books = new ArrayList<>();

    public ControlInput() {
    }

    /**
     * Method for get integer input from text fields.
     *
     * @param text input from text fields.
     * @return parsed integer.
     */
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

    /**
     * Getter for container.
     *
     * @return address book container.
     */
    public List<AddressBook> getBooks() {
        return books;
    }

    /**
     * Method for load sample data.
     */
    public void loadData() {
        if (books.size() == 0) {
            ListContainer<AddressBook> bookListContainer = LoadData.loadList();
            for (int i = 0; i < bookListContainer.size(); i++) {
                books.add(bookListContainer.get(i));
            }
        }
    }

    /**
     * Method check input data and create new address book.
     * Add it to container.
     *
     * @param firstName   first name from text input filed.
     * @param lastName    last name from text input filed.
     * @param secondName  second name from text input filed.
     * @param dateOfBirth birthday from text input filed.
     * @param address     address from text input filed.
     * @param phoneNumber phone number from text input filed.
     * @return null if new address book was added to container, or name of field where occurred mistake.
     */
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
