package seedu.address.testutil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditAdminDescriptor;
import seedu.address.model.student.Student;
import seedu.address.model.student.admin.AdditionalDetail;
import seedu.address.model.student.admin.ClassTime;
import seedu.address.model.student.admin.ClassVenue;
import seedu.address.model.student.admin.Fee;
import seedu.address.model.student.admin.PaymentDate;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditAdminDescriptorBuilder {

    private EditAdminDescriptor descriptor;

    public EditAdminDescriptorBuilder() {
        descriptor = new EditAdminDescriptor();
    }

    public EditAdminDescriptorBuilder(EditAdminDescriptor descriptor) {
        this.descriptor = new EditAdminDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditAdminDescriptorBuilder(Student student) {
        descriptor = new EditAdminDescriptor();
        descriptor.setTime(student.getAdmin().getClassTime());
        descriptor.setVenue(student.getAdmin().getClassVenue());
        descriptor.setFee(student.getAdmin().getFee());
        descriptor.setPaymentDate(student.getAdmin().getPaymentDate());
        descriptor.setAdditionalDetails(student.getAdmin().getDetails());
    }

    /**
     * Sets the {@code Venue} of the {@code EditAdminDescriptor} that we are building.
     */
    public EditAdminDescriptorBuilder withVenue(String venue) {
        descriptor.setVenue(new ClassVenue(venue));
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code EditAdminDescriptor} that we are building.
     */
    public EditAdminDescriptorBuilder withTime(String time) {
        descriptor.setTime(new ClassTime(time));
        return this;
    }

    /**
     * Sets the {@code Fee} of the {@code EditAdminDescriptor} that we are building.
     */
    public EditAdminDescriptorBuilder withFee(String fee) {
        descriptor.setFee(new Fee(fee));
        return this;
    }

    /**
     * Sets the {@code PaymentDate} of the {@code EditAdminDescriptor} that we are building.
     */
    public EditAdminDescriptorBuilder withPaymentDate(String paymentDate) {
        descriptor.setPaymentDate(new PaymentDate(paymentDate));
        return this;
    }

    /**
     * Parses the {@code details} into a {@code Set<AdditionalDetail>} and set it to the {@code EditAdminDescriptor}
     * that we are building.
     */
    public EditAdminDescriptorBuilder withAdditionalDetails(String... details) {
        List<AdditionalDetail> detailList = Stream.of(details).map(AdditionalDetail::new).collect(Collectors.toList());
        descriptor.setAdditionalDetails(detailList);
        return this;
    }

    public EditAdminDescriptor build() {
        return descriptor;
    }
}
