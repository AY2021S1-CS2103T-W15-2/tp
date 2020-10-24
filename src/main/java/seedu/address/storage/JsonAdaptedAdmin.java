package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.admin.AdditionalDetail;
import seedu.address.model.student.admin.Admin;
import seedu.address.model.student.admin.ClassTime;
import seedu.address.model.student.admin.ClassVenue;
import seedu.address.model.student.admin.Fee;
import seedu.address.model.student.admin.PaymentDate;


/**
 * Jackson-friendly version of {@link Admin}.
 */
public class JsonAdaptedAdmin {

    public static final String MISSING_ADMIN_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String classVenue;
    private final String classTime;
    private final String fee;
    private final String paymentDate;
    private final List<JsonAdaptedAdditionalDetail> additionalDetails = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAdmin} with admin details
     */
    @JsonCreator
    public JsonAdaptedAdmin(@JsonProperty("classVenue") String classVenue,
                            @JsonProperty("classTime") String classTime,
                            @JsonProperty("fee") String fee,
                            @JsonProperty("paymentDate") String paymentDate,
                            @JsonProperty("additionalDetails") List<JsonAdaptedAdditionalDetail> additionalDetails) {
        this.classVenue = classVenue;
        this.classTime = classTime;
        this.fee = fee;
        this.paymentDate = paymentDate;
        if (additionalDetails != null) {
            this.additionalDetails.addAll(additionalDetails);
        }
    }

    /**
     * Converts a given {@code Admin} into this class for Jackson use.
     */
    public JsonAdaptedAdmin(Admin source) {
        this.classVenue = source.getClassVenue().toString();
        this.classTime = source.getClassTime().convertClassTimeToUserInputString();
        this.fee = source.getFee().convertFeeToUserInputString();
        this.paymentDate = source.getPaymentDate().convertPaymentDateToUserInputString();
        additionalDetails.addAll(source.getDetails().stream()
                .map(JsonAdaptedAdditionalDetail::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted admin object into the model's {@code Admin} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted admin.
     */
    public Admin toModelType() throws IllegalValueException {

        final List<AdditionalDetail> details = new ArrayList<>();
        for (JsonAdaptedAdditionalDetail additionalDetail : additionalDetails) {
            details.add(additionalDetail.toModelType());
        }

        if (classVenue == null) {
            throw new IllegalValueException(String.format(MISSING_ADMIN_FIELD_MESSAGE_FORMAT,
                    ClassVenue.class.getSimpleName()));
        }
        if (!ClassVenue.isValidClassVenue(classVenue)) {
            throw new IllegalValueException(ClassVenue.MESSAGE_CONSTRAINTS);
        }
        final ClassVenue modelClassVenue = new ClassVenue(classVenue);

        if (classTime == null) {
            throw new IllegalValueException(String.format(MISSING_ADMIN_FIELD_MESSAGE_FORMAT,
                    ClassTime.class.getSimpleName()));
        }
        if (!ClassTime.isValidClassTime(classTime)) {
            throw new IllegalValueException(ClassTime.MESSAGE_CONSTRAINTS);
        }
        final ClassTime modelClassTime = new ClassTime(classTime);

        if (fee == null) {
            throw new IllegalValueException(String.format(MISSING_ADMIN_FIELD_MESSAGE_FORMAT,
                    Fee.class.getSimpleName()));
        }
        if (!Fee.isValidFee(fee)) {
            throw new IllegalValueException(Fee.MESSAGE_CONSTRAINTS);
        }
        final Fee modelFee = new Fee(fee);

        if (paymentDate == null) {
            throw new IllegalValueException(String.format(MISSING_ADMIN_FIELD_MESSAGE_FORMAT,
                    PaymentDate.class.getSimpleName()));
        }
        if (!PaymentDate.isValidDate(paymentDate)) {
            throw new IllegalValueException(PaymentDate.MESSAGE_CONSTRAINTS);
        }
        final PaymentDate modelPaymentDate = new PaymentDate(paymentDate);

        final List<AdditionalDetail> modelAdditionalDetails = new ArrayList<>(details);
        return new Admin(modelClassVenue, modelClassTime , modelFee, modelPaymentDate, modelAdditionalDetails);
    }
}
