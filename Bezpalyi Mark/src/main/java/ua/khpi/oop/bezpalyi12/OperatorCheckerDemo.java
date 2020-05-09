package ua.khpi.oop.bezpalyi12;

import ua.khpi.oop.bezpalyi07.AddressBook;
import ua.khpi.oop.bezpalyi09.ListContainer;
import ua.khpi.oop.bezpalyi10.ListHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OperatorCheckerDemo {
    public static void main(String[] args) throws ParseException {
        ListContainer<AddressBook> bookListContainer = new ListContainer<>();

        AddressBook addressBook1;
        AddressBook addressBook2;

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
        addressBook1.addPhoneNumber("+380950348577");
        addressBook1.addPhoneNumber("+380630394726");

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
        addressBook2.addPhoneNumber("+380997495847");
        addressBook2.addPhoneNumber("+380398437238");

        bookListContainer.add(addressBook1);
        bookListContainer.add(addressBook2);
        ListHandler listHandler = new ListHandler(bookListContainer);
        if (args.length > 0 && args[0].equals("-auto")) {
            System.out.println(new OperatorHandler().getKyivStarUsers(bookListContainer));
        } else {
            listHandler.dialogMode();
        }

    }
}
