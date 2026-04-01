package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

// Solution below inspired by Claude AI
/**
 * Tests that a {@code Person}'s name, address and tag fields matches all filters specified (AND search).
 * Each filter is represented by its corresponding keywords list,
 * it is applied only when its corresponding keywords list is non-empty.
 * Each field matches its corresponding filter, if the field matches at least 1 keyword (OR search).
 */
public class PersonMatchesFilterPredicate implements Predicate<Person> {
    private static final Logger logger = LogsCenter.getLogger(PersonMatchesFilterPredicate.class);

    private final List<String> keywordsForName;
    private final List<String> keywordsForAddress;
    private final List<String> keywordsForTag;

    /**
     * Constructs a {@code PersonMatchesFilterPredicate} where each keywords list,
     * can be non-empty or empty, but not null.
     */
    public PersonMatchesFilterPredicate(List<String> keywordsForName,
                                        List<String> keywordsForAddress,
                                        List<String> keywordsForTag) {
        // Lists should never be null. If no keywords, list should be empty.
        requireNonNull(keywordsForName);
        requireNonNull(keywordsForAddress);
        requireNonNull(keywordsForTag);

        logger.fine("Initializing with keywordsForName: " + keywordsForName
                + ", keywordsForAddress: " + keywordsForAddress
                + ", keywordsForTag: " + keywordsForTag);

        this.keywordsForName = keywordsForName;
        this.keywordsForAddress = keywordsForAddress;
        this.keywordsForTag = keywordsForTag;
    }

    /**
     * Tests that {@code Person} name, address and tag fields, all match its corresponding keywords list.
     * Each field is checked only when its corresponding keyword list is non-empty.
     *
     * @param person The {@code Person} to test for, not null.
     * @return {@code true} if the {@code Person} matches the predicate, otherwise {@code false}.
     */
    @Override
    public boolean test(Person person) {
        assert person != null;
        logger.fine("Person: " + person);

        logger.fine("Lists of keywordsForName: " + keywordsForName
                + ", keywordsForAddress: " + keywordsForAddress
                + ", keywordsForTag: " + keywordsForTag);

        // Short-circuit evaluation. If list is empty, match without instantiating new predicate.
        // Match if: Either no keywords for "name" field or has at least 1 keyword in name.
        boolean isKeywordsForNameEmpty = keywordsForName.isEmpty();
        boolean hasMatchedName = isKeywordsForNameEmpty
                || new NameContainsKeywordsPredicate(keywordsForName).test(person);

        // Match if: Either no keywords for "address" field or has at least 1 keyword in address.
        boolean isKeywordsForAddressEmpty = keywordsForAddress.isEmpty();
        boolean hasMatchedAddress = isKeywordsForAddressEmpty
                || new AddressContainsKeywordsPredicate(keywordsForAddress).test(person);

        // Match if: Either no keywords for "tag" or has at least 1 keyword matching at least 1 tag.
        boolean isKeywordsForTagEmpty = keywordsForTag.isEmpty();
        boolean hasMatchedTag = isKeywordsForTagEmpty
                || new TagContainsKeywordsPredicate(keywordsForTag).test(person);

        logger.fine("Booleans isKeywordsForNameEmpty: " + isKeywordsForNameEmpty
                + ", isKeywordsForAddressEmpty: " + isKeywordsForAddressEmpty
                + ", isKeywordsForTagEmpty: " + isKeywordsForTagEmpty);

        logger.fine("Booleans hasMatchedName: " + hasMatchedName
                + ", hasMatchedAddress: " + hasMatchedAddress
                + ", hasMatchedTag: " + hasMatchedTag);

        return hasMatchedName && hasMatchedAddress && hasMatchedTag;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonMatchesFilterPredicate)) {
            return false;
        }

        PersonMatchesFilterPredicate otherPersonMatchesFilterPredicate = (PersonMatchesFilterPredicate) other;
        boolean isSameNameFilter = keywordsForName.equals(otherPersonMatchesFilterPredicate.keywordsForName);
        boolean isSameAddressFilter = keywordsForAddress.equals(otherPersonMatchesFilterPredicate.keywordsForAddress);
        boolean isSameTagFilter = keywordsForTag.equals(otherPersonMatchesFilterPredicate.keywordsForTag);

        return isSameNameFilter && isSameAddressFilter && isSameTagFilter;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("keywordsForName", keywordsForName)
                .add("keywordsForAddress", keywordsForAddress)
                .add("keywordsForTag", keywordsForTag)
                .toString();
    }
}
