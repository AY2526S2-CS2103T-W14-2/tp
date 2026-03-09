package seedu.address.model.delivery;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Delivery's skipped date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSkippedDate(String)}
 */
public class SkippedDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Date should be of the valid skipped date format";

    /**
     * The date must follow the format yyyy-MM-dd
     * where yyyy is the 4-digit year, MM is the 2-digit month number,
     * and dd is the 2-digit date number.
     */
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public final LocalDate date;

    /**
     * Constructs a {@code SkippedDate}.
     *
     * @param date A valid date string in the valid format.
     */
    public SkippedDate(String date) {
        requireNonNull(date);
        checkArgument(isValidSkippedDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date, FORMATTER);
    }

    /**
     * Returns true if a given string is a valid skipped
     * date in the valid format.
     */
    public static boolean isValidSkippedDate(String test) {
        try {
            LocalDate.parse(test, FORMATTER);
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
        if (!(other instanceof SkippedDate)) {
            return false;
        }

        SkippedDate otherDate = (SkippedDate) other;
        return date.equals(otherDate.date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
