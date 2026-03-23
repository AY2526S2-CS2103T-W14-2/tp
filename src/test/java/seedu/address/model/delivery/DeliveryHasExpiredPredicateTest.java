package seedu.address.model.delivery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DeliveryBuilder;

public class DeliveryHasExpiredPredicateTest {

    @Test
    public void test_deliveryExpired_returnsTrue() {
        Delivery delivery = new DeliveryBuilder()
                .withStartDate("2024-03-01")
                .withEndDate("2024-04-01")
                .build();

        // Boundary value: delivery expired one day ago
        LocalDate beforeDate = LocalDate.of(2024, 4, 2);
        DeliveryHasExpiredPredicate predicate = new DeliveryHasExpiredPredicate(beforeDate);
        assertTrue(predicate.test(delivery));

        // Equivalence partition for expired delivery
        beforeDate = LocalDate.of(2026, 3, 27);
        predicate = new DeliveryHasExpiredPredicate(beforeDate);
        assertTrue(predicate.test(delivery));
    }

    @Test
    public void test_deliveryNotExpired_returnsFalse() {
        Delivery delivery = new DeliveryBuilder()
                .withStartDate("2024-03-01")
                .withEndDate("2024-04-01")
                .build();

        // Boundary value: delivery expires today
        LocalDate beforeDate = LocalDate.of(2024, 4, 1);
        DeliveryHasExpiredPredicate predicate = new DeliveryHasExpiredPredicate(beforeDate);
        assertFalse(predicate.test(delivery));

        // Near boundary value: delivery expires in one day
        beforeDate = LocalDate.of(2024, 3, 31);
        predicate = new DeliveryHasExpiredPredicate(beforeDate);
        assertFalse(predicate.test(delivery));

        // Equivalence partition where specified date is between delivery's start and end dates
        // Specified date is still before delivery's end date
        beforeDate = LocalDate.of(2024, 3, 27);
        predicate = new DeliveryHasExpiredPredicate(beforeDate);
        assertFalse(predicate.test(delivery));

        // Equivalence partition where specified date is not between delivery's start and end dates
        // Specified date is still before delivery's end date
        beforeDate = LocalDate.of(2024, 2, 27);
        predicate = new DeliveryHasExpiredPredicate(beforeDate);
        assertFalse(predicate.test(delivery));
    }

    @Test
    public void equals() {
        LocalDate firstDate = LocalDate.of(2026, 4, 1);
        LocalDate secondDate = LocalDate.of(2026, 2, 28);

        DeliveryHasExpiredPredicate firstPredicate = new DeliveryHasExpiredPredicate(firstDate);
        DeliveryHasExpiredPredicate secondPredicate = new DeliveryHasExpiredPredicate(secondDate);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DeliveryHasExpiredPredicate firstPredicateCopy = new DeliveryHasExpiredPredicate(firstDate);
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
        DeliveryHasExpiredPredicate predicate = new DeliveryHasExpiredPredicate(date);

        String expected = DeliveryHasExpiredPredicate.class.getCanonicalName()
                + "{beforeDate=" + date + '}';
        assertEquals(expected, predicate.toString());
    }

}
