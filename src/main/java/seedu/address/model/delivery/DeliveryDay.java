package seedu.address.model.delivery;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.DateTimeUtil.isValidDeliveryDayWord;
import static seedu.address.commons.util.DateTimeUtil.parseDeliveryDayWord;

import java.time.DayOfWeek;

/**
 * Represents a Delivery's day in the address book.
 * Guarantees: immutable;
 * is valid as declared in {@link #isValidDeliveryDay(String)}
 */
public class DeliveryDay {
    public static final String MESSAGE_CONSTRAINTS =
            "day should be of the valid delivery day format";

    public final DayOfWeek day;

    /**
     * Constructs a {@code DeliveryDay}
     *
     * @param day A valid day string in the valid format.
     */
    public DeliveryDay(String day) {
        requireNonNull(day);
        checkArgument(isValidDeliveryDay(day), MESSAGE_CONSTRAINTS);
        this.day = parseDeliveryDayWord(day);
    }

    /**
     * Returns true if a given string is a valid
     * day of the week in the valid format.
     */
    public static boolean isValidDeliveryDay(String test) {
        return isValidDeliveryDayWord(test);
    }

    @Override
    public String toString() {
        return day.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeliveryDay)) {
            return false;
        }

        DeliveryDay otherDay = (DeliveryDay) other;
        return day.equals(otherDay.day);
    }

    @Override
    public int hashCode() {
        return day.hashCode();
    }
}
