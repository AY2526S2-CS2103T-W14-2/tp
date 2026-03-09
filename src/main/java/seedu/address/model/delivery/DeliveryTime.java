package seedu.address.model.delivery;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Represents a Delivery's time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeliveryTime(String)}
 */
public class DeliveryTime {
    public static final String MESSAGE_CONSTRAINTS =
            "Time should be of the valid delivery time format";

    /**
     * The time must follow the format HH:mm
     * where HH is the hour value in the 24-hour format
     * and mm is the minute value.
     */
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public final LocalTime time;

    /**
     * Constructs a {@code DeliveryTime}.
     *
     * @param time A valid time string in the valid format.
     */
    public DeliveryTime(String time) {
        requireNonNull(time);
        checkArgument(isValidDeliveryTime(time), MESSAGE_CONSTRAINTS);
        this.time = LocalTime.parse(time, FORMATTER);
    }

    /**
     * Returns true if a given string is a valid time
     * in the valid format.
     */
    public static boolean isValidDeliveryTime(String test) {
        try {
            LocalTime.parse(test, FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return time.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeliveryTime)) {
            return false;
        }

        DeliveryTime otherTime = (DeliveryTime) other;
        return time.equals(otherTime.time);
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }
}
