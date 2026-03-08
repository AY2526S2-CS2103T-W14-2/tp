package seedu.address.model.delivery;

import seedu.address.commons.util.ToStringBuilder;

import java.util.Objects;

/**
 * Represents a Delivery in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Delivery {

    // Data fields
    private final Date date;
    private final Time time;

    /**
     * Every field must be present and not null.
     */
    public Delivery(Date date, Time time) {
        this.date = date;
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    /**
     * Returns true if both deliveries have the same dates.
     * This defines the notion of equality between two deliveries consistent
     * with the application logic.
     */
    public boolean isSameDelivery(Delivery otherDelivery) {
        if (otherDelivery == this) {
            return true;
        }

        return otherDelivery != null
                && date.date.isEqual(otherDelivery.date.date);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Delivery)) {
            return false;
        }

        Delivery otherDelivery = (Delivery) other;
        return date.equals(otherDelivery.date)
                && time.equals(otherDelivery.time);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(date, time);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("date", date)
                .add("time", time)
                .toString();
    }
}
