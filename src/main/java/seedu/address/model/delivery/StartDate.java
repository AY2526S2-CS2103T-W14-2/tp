package seedu.address.model.delivery;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Delivery's start date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStartDate(String)}
 */
public class StartDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Date should be of the valid start date format";

    /**
     * The date must follow the format yyyy-mm-dd
     * where yyyy is the 4-digit year, mm is the 2-digit month,
     * and dd is the 2-digit date number.
     */
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");

    public final LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date string in the valid format.
     */
    public StartDate(String date) {
        requireNonNull(date);
        checkArgument(isValidStartDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date, formatter);
    }

    /**
     * Returns true if a given string is a valid start
     * date in the valid format.
     */
    public static boolean isValidStartDate(String test) {
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
        if (!(other instanceof StartDate)) {
            return false;
        }

        StartDate otherDate = (StartDate) other;
        return date.equals(otherDate.date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
