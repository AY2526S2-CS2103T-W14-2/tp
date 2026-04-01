package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Person> {
    private static final Logger logger = LogsCenter.getLogger(NameContainsKeywordsPredicate.class);

    private final List<String> keywords;

    /**
     * Constructs an {@code NameContainsKeywordsPredicate}.
     *
     * @param keywords A list to check if the name matches any keywords, not null.
     */
    public NameContainsKeywordsPredicate(List<String> keywords) {
        // List should never be null
        requireNonNull(keywords);

        logger.finer("Initializing with keywords: " + keywords);
        this.keywords = keywords;
    }

    /**
     * Tests whether the {@code Person} name matches at least 1 keyword in the keywords list.
     *
     * @param person The {@code Person} to test for, not null.
     * @return {@code true} if the {@code Person} name matches a keyword. Otherwise, {@code false}.
     */
    @Override
    public boolean test(Person person) {
        assert person != null;
        logger.finer("Person: " + person);

        logger.finer("List of keywords: " + keywords);
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsKeywordsPredicate)) {
            return false;
        }

        NameContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (NameContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
