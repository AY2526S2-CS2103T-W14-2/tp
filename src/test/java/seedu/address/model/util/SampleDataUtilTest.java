package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HALAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_VEGETARIAN;
import static seedu.address.model.delivery.DeliveryDay.toDeliveryDay;
import static seedu.address.model.util.SampleDataUtil.getDeliveryDaySet;
import static seedu.address.model.util.SampleDataUtil.getTagSet;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.delivery.DeliveryDay;
import seedu.address.model.tag.Tag;

/**
 * Contains tests for {@code SampleDataUtilTest}.
 */
public class SampleDataUtilTest {

    @Test
    void getTagSet_validTags_hasSameTags() {
        // non-empty set
        Tag vegetarianTag = new Tag(VALID_TAG_VEGETARIAN);
        Tag halalTag = new Tag(VALID_TAG_HALAL);
        assertEquals(Set.of(vegetarianTag, halalTag),
                getTagSet(VALID_TAG_VEGETARIAN, VALID_TAG_HALAL));

        // duplicate items are removed from set
        assertEquals(Set.of(vegetarianTag),
                getTagSet(VALID_TAG_VEGETARIAN, VALID_TAG_VEGETARIAN));

        // empty set
        assertEquals(Set.<Tag>of(), getTagSet());
    }

    @Test
    public void getTagSet_invalidTags_throwsIllegalArgumentException() {
        // empty tag
        String emptyTagName = "";
        assertThrows(IllegalArgumentException.class, () -> getTagSet(emptyTagName));

        // non-alphanumeric tag
        String nonAlphanumericTagName = "(foodie)";
        assertThrows(IllegalArgumentException.class, () -> getTagSet(nonAlphanumericTagName));

        // one invalid tag with many valid tags
        assertThrows(IllegalArgumentException.class, () -> getTagSet(
                VALID_TAG_VEGETARIAN, VALID_TAG_HALAL, nonAlphanumericTagName));
    }

    @Test
    void getDeliveryDaySet_validDeliveryDay_hasSameDeliveryDays() {
        // non-empty set
        String mondayString = "Monday";
        String tuesdayString = "TueSDAy";
        DeliveryDay monday = toDeliveryDay(mondayString);
        DeliveryDay tuesday = toDeliveryDay(tuesdayString);
        assertEquals(Set.of(monday, tuesday),
                getDeliveryDaySet(mondayString, tuesdayString));

        // duplicate items are removed from set
        assertEquals(Set.of(monday),
                getDeliveryDaySet(mondayString, mondayString));

        // empty set
        assertEquals(Set.<DeliveryDay>of(), getDeliveryDaySet());
    }

    @Test
    public void getDeliveryDaySet_invalidDeliveryDay_throwsIllegalArgumentException() {
        // empty delivery day
        String emptyDeliveryDayString = "";
        assertThrows(IllegalArgumentException.class, () -> getDeliveryDaySet(emptyDeliveryDayString));

        // wrong format for delivery day
        String invalidDeliveryDayString = "Mon";
        assertThrows(IllegalArgumentException.class, () -> getDeliveryDaySet(invalidDeliveryDayString));

        // one invalid delivery day with many valid delivery days
        String mondayString = "Monday";
        String tuesdayString = "TueSDAy";
        assertThrows(IllegalArgumentException.class, () -> getDeliveryDaySet(
                mondayString, tuesdayString, invalidDeliveryDayString));
    }
}
