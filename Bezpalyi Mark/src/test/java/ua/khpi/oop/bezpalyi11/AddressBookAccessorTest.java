package ua.khpi.oop.bezpalyi11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookAccessorTest {

    @Test
    void assertNames() {
        AddressBookAccessor accessor = new AddressBookAccessor();
        assertTrue(accessor.assertNames("Linus"));
        assertTrue(accessor.assertNames("Dima"));

        assertFalse(accessor.assertNames("dima"));
        assertFalse(accessor.assertNames("D1ma"));
        assertFalse(accessor.assertNames("Di-ma"));
        assertFalse(accessor.assertNames(""));
        assertFalse(accessor.assertNames(" "));
    }

    @Test
    void assertPhoneNumber() {
        AddressBookAccessor accessor = new AddressBookAccessor();
        assertTrue(accessor.assertPhoneNumber("123114132"));
        assertTrue(accessor.assertPhoneNumber("123-456-789"));

        assertFalse(accessor.assertPhoneNumber("123"));
        assertFalse(accessor.assertPhoneNumber("123124A"));
        assertFalse(accessor.assertPhoneNumber("123-1323-32-"));
        assertFalse(accessor.assertPhoneNumber(""));
        assertFalse(accessor.assertPhoneNumber(" "));
    }
}