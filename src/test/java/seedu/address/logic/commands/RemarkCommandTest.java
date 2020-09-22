package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalIndexes;
import seedu.address.testutil.TypicalPersons;

class RemarkCommandTest {

    private static final String REMARK_STUB = "Pi ka chu!";

    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addRemarkUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        // Create expected person directly from stub
        Person expectedPerson = new PersonBuilder(firstPerson).withRemark(REMARK_STUB).build();
        // Create expected message
        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, expectedPerson);
        // Create expected model
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, expectedPerson);

        // Create remark command for testing
        RemarkCommand remarkCommand = new RemarkCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                new Remark(expectedPerson.getRemark().toString()));

        CommandTestUtil.assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }
}
