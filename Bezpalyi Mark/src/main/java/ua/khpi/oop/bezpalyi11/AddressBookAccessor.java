package ua.khpi.oop.bezpalyi11;

public class AddressBookAccessor implements BookAccessor {

    private final static String REGEX_NAME = "^\\b[A-Z][a-z]+\\b";

    private final static String REGEX_PHONE_NUMBER = "^\\b([0-9\\s|-]{5,})\\b";

    public final static Integer MAX_PHONE_NUMBERS_SIZE = 5;

    @Override
    public boolean assertNames(String name) {
        return name.matches(REGEX_NAME);
    }

    @Override
    public boolean assertPhoneNumber(String phoneNumber) {
        return phoneNumber.matches(REGEX_PHONE_NUMBER);
    }
}
