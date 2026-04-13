package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HALAL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDeliveries.DELIVERY_ALICE;
import static seedu.address.testutil.TypicalDeliveries.DELIVERY_CARL;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.delivery.Delivery;
import seedu.address.testutil.DeliveryBuilder;
import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    private final Delivery delivery = new DeliveryBuilder()
            .withStartDate("2026-02-10")
            .withEndDate("2026-03-12")
            .withDeliveryTime("16:00")
            .build();
    private final Person person = new PersonBuilder()
            .withDelivery(delivery)
            .build();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HALAL)
                .withDelivery(DELIVERY_ALICE).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // delivery object differs, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withDelivery(DELIVERY_CARL).build();
        assertFalse(ALICE.equals(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void hasExpiredDelivery_personWithoutDelivery_returnsFalse() {
        // Equivalence partition for person without delivery
        Person person = new PersonBuilder()
                .withDelivery(null)
                .build();
        LocalDate beforeDate = LocalDate.of(2024, 4, 2);
        assertFalse(person.hasExpiredDelivery(beforeDate));
    }

    @Test
    public void hasExpiredDelivery_personWithNonExpiredDelivery_returnsFalse() {
        // Equivalence partition for person with non-expired delivery
        LocalDate beforeDate = LocalDate.of(2026, 2, 20);
        assertFalse(person.hasExpiredDelivery(beforeDate));
    }

    @Test
    public void hasExpiredDelivery_personWithExpiredDelivery_returnsTrue() {
        // Equivalence partition for person with expired delivery
        LocalDate beforeDate = LocalDate.of(2026, 6, 20);
        assertTrue(person.hasExpiredDelivery(beforeDate));
    }


    @Test
    public void getFormattedDeliverySchedule_personWithDelivery_returnsDeliverySchedule() {
        assertEquals("2026-02-10 to 2026-03-12  |  16:00", person.getFormattedDeliverySchedule());
    }

    @Test
    public void getFormattedDeliverySchedule_personWithoutDelivery_returnsEmptyString() {
        assertEquals("", BOB.getFormattedDeliverySchedule());
    }

    @Test
    public void getDeliveryDayNames_personWithDelivery_returnsDeliveryDayNames() {
        Person person = buildPersonWithDeliveryDays("Tuesday", "Friday");
        assertEquals(Set.of("TUESDAY", "FRIDAY"), person.getDeliveryDayNames());

        // no delivery days -> return empty set
        person = buildPersonWithDeliveryDays();
        assertEquals(Collections.emptySet(), person.getDeliveryDayNames());
    }

    @Test
    public void getDeliveryDayNames_personWithoutDelivery_returnsEmptySet() {
        assertEquals(Collections.emptySet(), BOB.getDeliveryDayNames());
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HALAL).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", address=" + ALICE.getAddress() + ", tags=" + ALICE.getTags()
                + ", delivery=" + ALICE.getDelivery() + "}";
        assertEquals(expected, ALICE.toString());
    }

    /**
     * Builds {@code Person} with delivery, given delivery day names.
     *
     * @param dayNames Delivery day names for the person's delivery.
     * @return Person with a delivery.
     */
    private Person buildPersonWithDeliveryDays(String... dayNames) {
        Delivery delivery = new DeliveryBuilder()
                .withDeliveryDays(dayNames)
                .build();
        return new PersonBuilder()
                .withDelivery(delivery)
                .build();
    }

}
