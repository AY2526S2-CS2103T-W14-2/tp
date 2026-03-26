package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.delivery.Delivery;

/**
 * A utility class containing a list of {@code Delivery} objects to be used in tests.
 */
public class TypicalDeliveries {
    public static final Delivery DELIVERY_ALICE = new DeliveryBuilder().withStartDate("2025-10-19")
            .withEndDate("2025-10-30").withDeliveryDays("Monday", "Tuesday")
            .withDeliveryTime("12:00").build();
    public static final Delivery DELIVERY_CARL = new DeliveryBuilder().withStartDate("2025-10-19")
            .withEndDate("2025-10-30").withDeliveryDays("Monday", "Tuesday", "Thursday")
            .withDeliveryTime("15:00").build();
    public static final Delivery DELIVERY_DANIEL = new DeliveryBuilder().withStartDate("2024-10-21")
            .withEndDate("2024-10-29").withDeliveryDays("Tuesday", "Wednesday", "Thursday")
            .withDeliveryTime("16:00").build();
    public static final Delivery DELIVERY_ELLE = new DeliveryBuilder().withStartDate("2019-03-19")
            .withEndDate("2019-03-27").withDeliveryDays("Wednesday", "Friday")
            .withDeliveryTime("12:00").build();
    public static final Delivery DELIVERY_FIONA = new DeliveryBuilder().withStartDate("2019-05-21")
            .withEndDate("2019-06-29").withDeliveryDays("Monday", "Thursday")
            .withDeliveryTime("11:30").build();

    private TypicalDeliveries() {} // prevents instantiation

    public static List<Delivery> getTypicalDeliveries() {
        return new ArrayList<>(Arrays.asList(
                DELIVERY_ALICE, DELIVERY_CARL, DELIVERY_DANIEL, DELIVERY_ELLE, DELIVERY_FIONA));
    }
}
