package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Address} matches any of the keywords given.
 */
public class AddressContainsKeywordsPredicate implements Predicate<Person> {
    private static final Logger logger = LogsCenter.getLogger(AddressContainsKeywordsPredicate.class);

    private final List<String> keywords;

    /**
     * Constructs an {@code AddressContainsKeywordsPredicate}.
     *
     * @param keywords A list to check if the address matches any keywords, not null.
     */
    public AddressContainsKeywordsPredicate(List<String> keywords) {
        // List should never be null
        requireNonNull(keywords);

        logger.finer("Initializing with keywords: " + keywords);
        this.keywords = keywords;
    }

    /**
     * Tests whether the {@code Person} address matches at least 1 keyword in the keywords list.
     *
     * @param person The {@code Person} to test for, not null.
     * @return {@code true} if the {@code Person} address matches a keyword. Otherwise, {@code false}.
     */
    @Override
    public boolean test(Person person) {
        assert person != null;
        logger.finer("Person: " + person);

        logger.finer("List of keywords: " + keywords);
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getAddress().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressContainsKeywordsPredicate)) {
            return false;
        }

        AddressContainsKeywordsPredicate otherAddressContainsKeywordsPredicate =
                (AddressContainsKeywordsPredicate) other;
        return keywords.equals(otherAddressContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
