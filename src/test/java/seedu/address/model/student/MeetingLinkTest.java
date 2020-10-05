package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MeetingLinkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MeetingLink(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new MeetingLink(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> MeetingLink.isValidEmail(null));

        // blank email
        assertFalse(MeetingLink.isValidEmail("")); // empty string
        assertFalse(MeetingLink.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(MeetingLink.isValidEmail("@example.com")); // missing local part
        assertFalse(MeetingLink.isValidEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(MeetingLink.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(MeetingLink.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(MeetingLink.isValidEmail("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(MeetingLink.isValidEmail("peter jack@example.com")); // spaces in local part
        assertFalse(MeetingLink.isValidEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(MeetingLink.isValidEmail(" peterjack@example.com")); // leading space
        assertFalse(MeetingLink.isValidEmail("peterjack@example.com ")); // trailing space
        assertFalse(MeetingLink.isValidEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(MeetingLink.isValidEmail("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(MeetingLink.isValidEmail("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(MeetingLink.isValidEmail("peterjack@.example.com")); // domain name starts with a period
        assertFalse(MeetingLink.isValidEmail("peterjack@example.com.")); // domain name ends with a period
        assertFalse(MeetingLink.isValidEmail("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(MeetingLink.isValidEmail("peterjack@example.com-")); // domain name ends with a hyphen

        // valid email
        assertTrue(MeetingLink.isValidEmail("PeterJack_1190@example.com"));
        assertTrue(MeetingLink.isValidEmail("a@bc")); // minimal
        assertTrue(MeetingLink.isValidEmail("test@localhost")); // alphabets only
        assertTrue(MeetingLink.isValidEmail("!#$%&'*+/=?`{|}~^.-@example.org")); // special characters local part
        assertTrue(MeetingLink.isValidEmail("123@145")); // numeric local part and domain name
        assertTrue(MeetingLink.isValidEmail("a1+be!@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(MeetingLink.isValidEmail("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(MeetingLink.isValidEmail("if.you.dream.it_you.can.do.it@example.com")); // long local part
    }
}
