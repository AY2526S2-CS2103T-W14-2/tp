package seedu.address.model.delivery;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Delivery's start date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS =
            "Date should be of the valid date format";

    /**
     * The date must follow the format MMM dd yyyy
     * where MMM is the 3-character month, dd is the 2-character date number
     * and yyyy is the 4-character year.
     */
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public final LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date string in the valid format.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date, formatter);
    }

    /**
     * Returns true if a given string is a valid date
     * in the valid format.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return date.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Date)) {
            return false;
        }

        Date otherDate = (Date) other;
        return date.equals(otherDate.date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
