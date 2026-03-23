package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.delivery.Delivery;
import seedu.address.testutil.DeliveryBuilder;
import seedu.address.testutil.PersonBuilder;

public class PersonHasExpiredDeliveryPredicateTest {

    @Test
    public void test_personWithExpiredDelivery_returnsTrue() {
        Delivery delivery = new DeliveryBuilder()
                .withStartDate("2024-03-01")
                .withEndDate("2024-04-01")
                .build();
        Person person = new PersonBuilder()
                .withDelivery(delivery)
                .build();

        // Boundary value: person with delivery expired one day ago
        LocalDate beforeDate = LocalDate.of(2024, 4, 2);
        PersonHasExpiredDeliveryPredicate predicate = new PersonHasExpiredDeliveryPredicate(beforeDate);
        assertTrue(predicate.test(person));

        // Equivalence partition for person with expired delivery
        beforeDate = LocalDate.of(2026, 3, 27);
        predicate = new PersonHasExpiredDeliveryPredicate(beforeDate);
        assertTrue(predicate.test(person));
    }

    @Test
    public void test_personWithoutDelivery_returnsFalse() {
        // Equivalence partition for person without deliveries
        Person person = new PersonBuilder()
                .withDelivery(null)
                .build();
        LocalDate beforeDate = LocalDate.of(2024, 4, 2);
        PersonHasExpiredDeliveryPredicate predicate = new PersonHasExpiredDeliveryPredicate(beforeDate);
        assertFalse(predicate.test(person));
    }

    @Test
    public void test_personWithNonExpiredDelivery_returnsFalse() {
        Delivery delivery = new DeliveryBuilder()
                .withStartDate("2024-03-01")
                .withEndDate("2024-04-01")
                .build();
        Person person = new PersonBuilder()
                .withDelivery(delivery)
                .build();

        // Boundary value: person with delivery that expires today
        LocalDate beforeDate = LocalDate.of(2024, 4, 1);
        PersonHasExpiredDeliveryPredicate predicate = new PersonHasExpiredDeliveryPredicate(beforeDate);
        assertFalse(predicate.test(person));

        // Near boundary value: person with delivery that expires in one day
        beforeDate = LocalDate.of(2024, 3, 31);
        predicate = new PersonHasExpiredDeliveryPredicate(beforeDate);
        assertFalse(predicate.test(person));

        // Equivalence partition where specified date is between start and end dates of person's delivery
        // Specified date is still before the end date of person's delivery
        beforeDate = LocalDate.of(2024, 3, 27);
        predicate = new PersonHasExpiredDeliveryPredicate(beforeDate);
        assertFalse(predicate.test(person));

        // Equivalence partition where specified date is not between delivery's start and end dates
        // Specified date is still before delivery's end date
        beforeDate = LocalDate.of(2024, 2, 27);
        predicate = new PersonHasExpiredDeliveryPredicate(beforeDate);
        assertFalse(predicate.test(person));
    }

    @Test
    public void equals() {
        LocalDate firstDate = LocalDate.of(2026, 4, 1);
        LocalDate secondDate = LocalDate.of(2026, 2, 28);

        PersonHasExpiredDeliveryPredicate firstPredicate = new PersonHasExpiredDeliveryPredicate(firstDate);
        PersonHasExpiredDeliveryPredicate secondPredicate = new PersonHasExpiredDeliveryPredicate(secondDate);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonHasExpiredDeliveryPredicate firstPredicateCopy = new PersonHasExpiredDeliveryPredicate(firstDate);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different date -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void toStringMethod() {
        LocalDate date = LocalDate.of(2026, 4, 1);
        PersonHasExpiredDeliveryPredicate predicate = new PersonHasExpiredDeliveryPredicate(date);

        String expected = PersonHasExpiredDeliveryPredicate.class.getCanonicalName()
                + "{beforeDate=" + date + '}';
        assertEquals(expected, predicate.toString());
    }
}
