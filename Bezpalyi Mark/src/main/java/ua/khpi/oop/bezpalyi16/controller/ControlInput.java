package ua.khpi.oop.bezpalyi16.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.khpi.oop.bezpalyi07.AddressBook;
import ua.khpi.oop.bezpalyi09.ListContainer;
import ua.khpi.oop.bezpalyi13.LoadData;

import javax.xml.stream.util.StreamReaderDelegate;
import java.util.ArrayList;
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
}
