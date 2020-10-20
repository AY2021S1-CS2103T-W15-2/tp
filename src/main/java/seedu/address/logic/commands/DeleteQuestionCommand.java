package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.question.Question;

/**
 * Deletes a specified question of a student at the displayed index in Reeve.
 */
public class DeleteQuestionCommand extends QuestionCommand {

    public static final String MESSAGE_SUCCESS = "Question removed from %1$s: %2$s";
    public static final String MESSAGE_BAD_QUESTION_INDEX = "There is no question at this index";

    private static Logger logger = Logger.getLogger("Delete Question Log");

    private final Index studentIndex;
    private final Index questionIndex;

    /**
     * Constructs a DeleteQuestionCommand to remove a specified {@code Question} from a {@code Student}.
     */
    public DeleteQuestionCommand(Index studentIndex, Index questionIndex) {
        requireAllNonNull(studentIndex, questionIndex);
        this.studentIndex = studentIndex;
        this.questionIndex = questionIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert (studentIndex != null) && (questionIndex != null);
        requireNonNull(model);
        logger.log(Level.INFO, "Beginning command execution");

        List<Student> lastShownList = model.getFilteredPersonList();
        if (studentIndex.getZeroBased() >= lastShownList.size()) {
            logger.log(Level.WARNING, "Handling non-existent student error");
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student asker = lastShownList.get(studentIndex.getZeroBased());
        if (questionIndex.getZeroBased() >= asker.getQuestions().size()) {
            logger.log(Level.WARNING, "Handling non-existent question error");
            throw new CommandException(MESSAGE_BAD_QUESTION_INDEX);
        }

        List<Question> questionList = new ArrayList<>(asker.getQuestions());
        Question deleted = questionList.remove(questionIndex.getZeroBased());
        Student replacement = new Student(asker.getName(), asker.getPhone(), asker.getSchool(),
                asker.getYear(), asker.getAdmin(), questionList);

        model.setPerson(asker, replacement);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        logger.log(Level.INFO, "Execution complete");
        return new CommandResult(String.format(MESSAGE_SUCCESS, replacement.getName(), deleted));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DeleteQuestionCommand)) {
            return false;
        }

        DeleteQuestionCommand other = (DeleteQuestionCommand) obj;
        return studentIndex.equals(other.studentIndex)
                && questionIndex.equals(other.questionIndex);
    }

}
