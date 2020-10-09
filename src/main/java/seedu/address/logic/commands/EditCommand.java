package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;
import seedu.address.model.student.Student;
import seedu.address.model.student.Year;
import seedu.address.model.student.admin.AdditionalDetail;
import seedu.address.model.student.admin.Admin;
import seedu.address.model.student.admin.ClassTime;
import seedu.address.model.student.admin.ClassVenue;
import seedu.address.model.student.admin.Fee;
import seedu.address.model.student.admin.PaymentDate;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_SCHOOL + "SCHOOL] "
            + "[" + PREFIX_YEAR + "YEAR]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 ";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in Reeve.";

    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;
    private final EditAdminDescriptor editAdminDescriptor;

    /**
     * @param index of the student in the filtered student list to edit
     * @param editStudentDescriptor details to edit the student with
     */
    public EditCommand(Index index, EditStudentDescriptor editStudentDescriptor,
                       EditAdminDescriptor editAdminDescriptor) {
        requireNonNull(index);
        requireNonNull(editStudentDescriptor);

        this.index = index;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
        this.editAdminDescriptor = new EditAdminDescriptor(editAdminDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor, editAdminDescriptor);

        if (!studentToEdit.isSameStudent(editedStudent) && model.hasPerson(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(studentToEdit, editedStudent);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedStudent));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor,
                                               EditAdminDescriptor editAdminDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        Phone updatedPhone = editStudentDescriptor.getPhone().orElse(studentToEdit.getPhone());
        School updatedSchool = editStudentDescriptor.getSchool().orElse(studentToEdit.getSchool());
        Year updatedYear = editStudentDescriptor.getYear().orElse(studentToEdit.getYear());

        ClassTime updatedClassTime = editAdminDescriptor.getClassTime()
                .orElse(studentToEdit.getAdmin().getClassTime());
        ClassVenue updatedClassVenue = editAdminDescriptor.getClassVenue()
                .orElse(studentToEdit.getAdmin().getClassVenue());
        Fee updatedFee = editAdminDescriptor.getFee()
                .orElse(studentToEdit.getAdmin().getFee());
        PaymentDate updatedPaymentDate = editAdminDescriptor.getPaymentDate()
                .orElse(studentToEdit.getAdmin().getPaymentDate());
        Set<AdditionalDetail> updatedAdditionalDetails = editAdminDescriptor.getAdditionalDetails()
                .orElse(studentToEdit.getAdmin().getDetails());
        Admin updatedAdmin = new Admin(updatedClassVenue, updatedClassTime, updatedFee, updatedPaymentDate,
                updatedAdditionalDetails);

        return new Student(updatedName, updatedPhone, updatedSchool, updatedYear, updatedAdmin);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditStudentDescriptor {
        private Name name;
        private Phone phone;
        private School school;
        private Year year;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setSchool(toCopy.school);
            setYear(toCopy.year);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, school, year);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setSchool(School school) {
            this.school = school;
        }

        public Optional<School> getSchool() {
            return Optional.ofNullable(school);
        }

        public void setYear(Year year) {
            this.year = year;
        }

        public Optional<Year> getYear() {
            return Optional.ofNullable(year);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditStudentDescriptor)) {
                return false;
            }

            // state check
            EditStudentDescriptor e = (EditStudentDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getSchool().equals(e.getSchool())
                    && getYear().equals(e.getYear());
        }
    }

    public static class EditAdminDescriptor {
        private ClassTime time;
        private ClassVenue venue;
        private Fee fee;
        private PaymentDate paymentDate;
        private Set<AdditionalDetail> details;

        public EditAdminDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code details} is used internally.
         */
        public EditAdminDescriptor(EditAdminDescriptor toCopy) {
            setTime(toCopy.time);
            setVenue(toCopy.venue);
            setFee(toCopy.fee);
            setPaymentDate(toCopy.paymentDate);
            setAdditionalDetails(toCopy.details);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(time, venue, fee, paymentDate, details);
        }

        public void setTime(ClassTime time) {
            this.time = time;
        }

        public Optional<ClassTime> getClassTime() {
            return Optional.ofNullable(time);
        }

        public void setVenue(ClassVenue venue) {
            this.venue = venue;
        }

        public Optional<ClassVenue> getClassVenue() {
            return Optional.ofNullable(venue);
        }

        public void setFee(Fee fee) {
            this.fee = fee;
        }

        public Optional<Fee> getFee() {
            return Optional.ofNullable(fee);
        }

        public void setPaymentDate(PaymentDate paymentDate) {
            this.paymentDate = paymentDate;
        }

        public Optional<PaymentDate> getPaymentDate() {
            return Optional.ofNullable(paymentDate);
        }

        /**
         * Sets {@code details} to this object's {@code details}.
         * A defensive copy of {@code details} is used internally.
         */
        public void setAdditionalDetails(Set<AdditionalDetail> details) {
            this.details = (details != null) ? new HashSet<>(details) : null;
        }

        /**
         * Returns an unmodifiable detail set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code details} is null.
         */
        public Optional<Set<AdditionalDetail>> getAdditionalDetails() {
            return (details != null) ? Optional.of(Collections.unmodifiableSet(details)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAdminDescriptor)) {
                return false;
            }

            // state check
            EditAdminDescriptor e = (EditAdminDescriptor) other;

            return getClassVenue().equals(e.getClassVenue())
                    && getClassTime().equals(e.getClassTime())
                    && getFee().equals(e.getFee())
                    && getPaymentDate().equals(e.getPaymentDate())
                    && getAdditionalDetails().equals(e.getAdditionalDetails());
        }
    }

}
