package seedu.address.model.delivery;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Delivery's end date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEndDate(String)}
 */
public class EndDate {
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be of the valid end date format";

    /**
     * The date must follow the format yyyy-mm-dd
     * where yyyy is the 4-digit year, mm is the 2-digit month,
     * and dd is the 2-digit date number.
     */
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");

    public final LocalDate date;

    /**
     * Constructs a {@code EndDate}.
     *
     * @param date A valid date string in the valid format.
     */
    public EndDate(String date) {
        requireNonNull(date);
        checkArgument(isValidEndDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date, formatter);
    }

    /**
     * Returns true if a given string is a valid end
     * date in the valid format.
     */
    public static boolean isValidEndDate(String test) {
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
        if (!(other instanceof EndDate)) {
            return false;
        }

        EndDate otherDate = (EndDate) other;
        return date.equals(otherDate.date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
