package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_BOB;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class StudentTest {

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSameStudent(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameStudent(null));

        // different phone and school and year -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).withSchool(VALID_SCHOOL_BOB)
                .withYear(VALID_YEAR_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // different name -> returns false
        editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // same name, same phone, different attributes -> returns false
        editedAlice = new StudentBuilder(ALICE).withSchool(VALID_SCHOOL_BOB).withYear(VALID_YEAR_BOB)
                .build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // same name, same school, different attributes -> returns true
        editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).withYear(VALID_YEAR_BOB)
                .build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // same name, same year, different attributes -> returns true
        editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).withSchool(VALID_SCHOOL_BOB)
                .build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // same name, same phone, same school, different year -> returns true
        editedAlice = new StudentBuilder(ALICE).withYear(VALID_YEAR_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new StudentBuilder(ALICE).build();
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
        Student editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different school -> returns false
        editedAlice = new StudentBuilder(ALICE).withSchool(VALID_SCHOOL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different year -> returns false
        editedAlice = new StudentBuilder(ALICE).withYear(VALID_YEAR_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

    }
}
