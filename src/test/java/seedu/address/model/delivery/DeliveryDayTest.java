package seedu.address.model.delivery;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class DeliveryDayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeliveryDay(null));
    }

    @Test
    public void constructor_invalidDay_throwsIllegalArgumentException() {
        String invalidDay = "";
        assertThrows(IllegalArgumentException.class, () -> new DeliveryDay(invalidDay));
    }

    @Test
    public void isValidDeliveryDay() {
        // null day
        assertThrows(NullPointerException.class, () -> DeliveryDay.isValidDeliveryDay(null));

        // invalid days
        assertFalse(DeliveryDay.isValidDeliveryDay("")); // empty string
        assertFalse(DeliveryDay.isValidDeliveryDay(" ")); // spaces only
        assertFalse(DeliveryDay.isValidDeliveryDay("Mon")); // only 3-character day

        // valid days
        assertTrue(DeliveryDay.isValidDeliveryDay("Monday"));
        assertTrue(DeliveryDay.isValidDeliveryDay("thursday"));
        assertTrue(DeliveryDay.isValidDeliveryDay("FRIDAY"));
        assertTrue(DeliveryDay.isValidDeliveryDay("WEDnesDay"));
    }

    @Test
    // TODO: Refactor or remove after refactoring DeliveryDay class.
    public void isValidDeliveryDayNumber() {
        // null day numbers
        assertThrows(NullPointerException.class, () -> DeliveryDay.isValidDeliveryDayNumber(null));

        // invalid day numbers
        assertFalse(DeliveryDay.isValidDeliveryDayNumber("")); // empty string
        assertFalse(DeliveryDay.isValidDeliveryDayNumber(" ")); // spaces only
        assertFalse(DeliveryDay.isValidDeliveryDayNumber("Mon")); // 3-character day
        // full word representing the day
        assertFalse(DeliveryDay.isValidDeliveryDayNumber("Monday"));
        assertFalse(DeliveryDay.isValidDeliveryDayNumber("thursday"));
        assertFalse(DeliveryDay.isValidDeliveryDayNumber("FRIDAY"));
        assertFalse(DeliveryDay.isValidDeliveryDayNumber("WEDnesDay"));
        assertFalse(DeliveryDay.isValidDeliveryDayNumber("-3")); // negative number
        assertFalse(DeliveryDay.isValidDeliveryDayNumber("0")); // zero value
        assertFalse(DeliveryDay.isValidDeliveryDayNumber("8")); // number exceeding 7

        // valid day numbers
        assertTrue(DeliveryDay.isValidDeliveryDayNumber("1"));
        assertTrue(DeliveryDay.isValidDeliveryDayNumber("3"));
        assertTrue(DeliveryDay.isValidDeliveryDayNumber("4"));
        assertTrue(DeliveryDay.isValidDeliveryDayNumber("7"));
    }

    @Test
    // TODO: Refactor or remove after refactoring DeliveryDay class.
    public void convertDayNumberToDayWord_nullDayNumber_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> DeliveryDay.convertDayNumberToDayWord(null));
    }

    @Test
    // TODO: Refactor or remove after refactoring DeliveryDay class.
    public void convertDayNumberToDayWord_invalidDayNumber_throwsIllegalArgumentException() {
        String invalidDayNumber = "";
        assertThrows(IllegalArgumentException.class, () -> DeliveryDay.convertDayNumberToDayWord(invalidDayNumber));
    }

    @Test
    public void equals() {
        DeliveryDay day = new DeliveryDay("Tuesday");

        // same values -> returns true
        assertTrue(day.equals(new DeliveryDay("Tuesday")));

        // same object -> returns true
        assertTrue(day.equals(day));

        // null -> returns false
        assertFalse(day.equals(null));

        // different types -> returns false
        assertFalse(day.equals(5.0f));

        // different values -> returns false
        assertFalse(day.equals(new DeliveryDay("Thursday")));
    }
}
