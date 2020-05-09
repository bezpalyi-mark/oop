package ua.khpi.oop.bezpalyi12;

import ua.khpi.oop.bezpalyi07.AddressBook;
import ua.khpi.oop.bezpalyi09.ListContainer;

public class OperatorHandler {

    public ListContainer<AddressBook> getLifeCellUsers(ListContainer<AddressBook> list) {
        ListContainer<AddressBook> listLifeCellUsers = new ListContainer<>();
        for (int i = 0; i < list.size(); i++) {
            AddressBook book = list.get(i);
            for (String number : book.getPhoneNumbers()) {
                if (OperatorChecker.checkLifeCellNumber(number)) {
                    listLifeCellUsers.add(book);
                    break;
                }
            }
        }
        return listLifeCellUsers;
    }

    public ListContainer<AddressBook> getKyivStarUsers(ListContainer<AddressBook> list) {
        ListContainer<AddressBook> listKyivStarUsers = new ListContainer<>();
        for (int i = 0; i < list.size(); i++) {
            AddressBook book = list.get(i);
            for (String number : book.getPhoneNumbers()) {
                if (OperatorChecker.checkKyivStarNumber(number)) {
                    listKyivStarUsers.add(book);
                    break;
                }
            }
        }
        return listKyivStarUsers;
    }
}
