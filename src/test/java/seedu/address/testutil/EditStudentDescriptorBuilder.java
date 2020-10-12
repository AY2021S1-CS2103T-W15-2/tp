package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;
import seedu.address.model.student.Student;
import seedu.address.model.student.Year;
import seedu.address.model.student.admin.Admin;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditStudentDescriptorBuilder {

    private EditStudentDescriptor descriptor;

    public EditStudentDescriptorBuilder() {
        descriptor = new EditStudentDescriptor();
    }

    public EditStudentDescriptorBuilder(EditStudentDescriptor descriptor) {
        this.descriptor = new EditStudentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditStudentDescriptorBuilder(Student student) {
        descriptor = new EditStudentDescriptor();
        descriptor.setName(student.getName());
        descriptor.setPhone(student.getPhone());
        descriptor.setSchool(student.getSchool());
        descriptor.setYear(student.getYear());
        descriptor.setAdmin(student.getAdmin());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code School} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withSchool(String school) {
        descriptor.setSchool(new School(school));
        return this;
    }

    /**
     * Sets the {@code Year} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withYear(String year) {
        descriptor.setYear(new Year(year));
        return this;
    }

    /**
     * Sets the {@code Year} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withAdmin(Admin admin) {
        descriptor.setAdmin(admin);
        return this;
    }

    public EditStudentDescriptor build() {
        return descriptor;
    }
}
