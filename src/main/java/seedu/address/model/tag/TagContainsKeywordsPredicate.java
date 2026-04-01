package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Tag}(s) matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private static final Logger logger = LogsCenter.getLogger(TagContainsKeywordsPredicate.class);

    private final List<String> keywords;

    /**
     * Constructs an {@code TagContainsKeywordsPredicate}.
     *
     * @param keywords A list to check if the tag(s) matches any keywords, not null.
     */
    public TagContainsKeywordsPredicate(List<String> keywords) {
        // List should never be null
        requireNonNull(keywords);

        logger.finer("Initializing with keywords: " + keywords);
        this.keywords = keywords;
    }

    /**
     * Tests whether any of the {@code Person} tags matches at least 1 keyword in the keywords list.
     *
     * @param person The {@code Person} to test for, not null.
     * @return {@code true} if any of the {@code Person} tags matches a keyword. Otherwise, {@code false}.
     */
    @Override
    public boolean test(Person person) {
        assert person != null;
        logger.finer("Person: " + person);

        logger.finer("List of keywords: " + keywords);
        return keywords.stream()
                .anyMatch(keyword -> person.getTags().stream()
                        .anyMatch(tag -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagContainsKeywordsPredicate)) {
            return false;
        }

        TagContainsKeywordsPredicate otherTagContainsKeywordsPredicate = (TagContainsKeywordsPredicate) other;
        return keywords.equals(otherTagContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
