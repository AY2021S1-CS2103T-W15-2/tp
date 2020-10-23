package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.academic.exam.Exam;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class DeleteExamCommand extends ExamCommand{

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = ExamCommand.COMMAND_WORD + " " + COMMAND_WORD +
            ": Deletes an exam from a student.\n\n"
            + "Parameters: INDEX (must be a positive integer)"
            + PREFIX_EXAM_INDEX
            + "EXAM_INDEX (must be a positive integer)\n"
            + "Example: "
            + ExamCommand.COMMAND_WORD
            + COMMAND_WORD + " 2 "
            + PREFIX_EXAM_INDEX
            + "1";

    public static final String MESSAGE_EXAM_DELETED_SUCCESS = "Exam deleted from %1$s: %2$s";
    public static final String MESSAGE_MISSING_EXAM_INDEX = "There is no exam at this index";

    private final Index studentIndex;
    private final Index examIndex;

    public DeleteExamCommand(Index studentIndex, Index examIndex) {
        requireAllNonNull(studentIndex, examIndex);
        this.studentIndex = studentIndex;
        this.examIndex = examIndex;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert(studentIndex != null && examIndex != null);
        requireNonNull(model);

        List<Student> lastShownList = model.getFilteredPersonList();
        if (studentIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToDeleteExam = lastShownList.get(studentIndex.getZeroBased());

        if (examIndex.getZeroBased() >= studentToDeleteExam.getExams().size()) {
            throw new CommandException(MESSAGE_MISSING_EXAM_INDEX);
        }

        ArrayList<Exam> exams = new ArrayList<>(studentToDeleteExam.getExams());
        Exam removedExam = exams.remove(examIndex.getZeroBased());

        Student updatedStudent = super.updateStudentExam(studentToDeleteExam, exams);

        model.setPerson(studentToDeleteExam, updatedStudent);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EXAM_DELETED_SUCCESS, updatedStudent.getName(), removedExam));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DeleteExamCommand)) {
            return false;
        }

        DeleteExamCommand other = (DeleteExamCommand) obj;
        return studentIndex.equals(other.studentIndex) && examIndex.equals(other.examIndex);
    }
}
