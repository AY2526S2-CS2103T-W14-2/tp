package seedu.address.model.tag;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        // empty tag
        String emptyTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(emptyTagName));

        // non alphanumeric tag
        String nonAlphanumericTagName = "(vegan)";
        assertThrows(IllegalArgumentException.class, () -> new Tag(nonAlphanumericTagName));

        // tag longer than 50 characters
        String longTagName = "a".repeat(51);
        assertThrows(IllegalArgumentException.class, () -> new Tag(longTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

}
