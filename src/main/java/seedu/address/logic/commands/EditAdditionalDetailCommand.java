package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAIL_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAIL_TEXT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.admin.AdditionalDetail;

public class EditAdditionalDetailCommand extends AdditionalDetailCommand {

    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = AdditionalDetailCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": edits an Additional Detail in the student identified "
            + "by the index number used in the displayed student list. \n"
            + "Parameters: STUDENT_INDEX (must be a positive integer) "
            + PREFIX_DETAIL_INDEX + "DETAIL_INDEX (must be a positive integer) "
            + PREFIX_DETAIL_TEXT + "DETAIL\n"
            + "Example: " + AdditionalDetailCommand.COMMAND_WORD + " " + COMMAND_WORD + " 2 "
            + PREFIX_DETAIL_INDEX + "1 "
            + PREFIX_DETAIL_TEXT + "Eats sweets in class";

    public static final String MESSAGE_SUCCESS = "Detail edited for %s: %s";
    public static final String MESSAGE_BAD_DETAIL_INDEX = "There is no detail at this index";

    private final Index studentIndex;
    private final Index detailIndex;
    private final AdditionalDetail detailToAdd;

    /**
     * Constructs a DeleteQuestionCommand to remove a specified {@code Question} from a {@code Student}.
     * @param studentIndex
     * @param detailIndex
     * @param detailToAdd
     */
    public EditAdditionalDetailCommand(Index studentIndex, Index detailIndex, AdditionalDetail detailToAdd) {
        requireAllNonNull(studentIndex, detailIndex, detailToAdd);
        this.studentIndex = studentIndex;
        this.detailIndex = detailIndex;
        this.detailToAdd = detailToAdd;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownList = model.getFilteredPersonList();
        if (studentIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToAddDetail = lastShownList.get(studentIndex.getZeroBased());

        if (detailIndex.getZeroBased() >= studentToAddDetail.getDetails().size()) {
            throw new CommandException(MESSAGE_BAD_DETAIL_INDEX);
        }

        List<AdditionalDetail> details = new ArrayList<>(studentToAddDetail.getDetails());
        details.set(detailIndex.getZeroBased(), detailToAdd);

        Student updatedStudent = super.updateStudentDetail(studentToAddDetail, details);

        model.setPerson(studentToAddDetail, updatedStudent);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, updatedStudent.getName(), detailToAdd));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof EditAdditionalDetailCommand)) {
            return false;
        }

        EditAdditionalDetailCommand other = (EditAdditionalDetailCommand) obj;
        return studentIndex.equals(other.studentIndex) && detailIndex.equals(other.detailIndex)
                && detailToAdd.equals(other.detailToAdd);
    }
}
