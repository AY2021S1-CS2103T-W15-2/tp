package seedu.address.testutil;

import seedu.address.model.Reeve;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventRecurrence;
import seedu.address.model.student.SchoolType;
import seedu.address.model.student.Student;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypicalEvents {

    public static final LocalDateTime dateTimeBeforeAliceEndTime = LocalDateTime.parse("2020-12-03T10:16:30");

    public static final Event ALICE_CLASS_EVENT = new EventBuilder().withEventName("Lesson event")
            .withEventStartDateTime(LocalDateTime.parse("2020-12-03T10:15:30"))
            .withEventEndDateTime(LocalDateTime.parse("2020-12-03T10:17:30"))
            .withDescription("Alice Pauline Sec 3")
            .withUniqueIdentifier("uidAliceLesson")
            .withRecurrence(EventRecurrence.WEEKLY)
            .build();

    public static final Event TODO_EVENT = new EventBuilder().withEventName("Plan lessons")
            .withEventStartDateTime(LocalDateTime.parse("2020-11-03T10:14:30"))
            .withEventEndDateTime(LocalDateTime.parse("2020-11-03T10:16:35"))
            .withDescription("For Benson and Carl")
            .withUniqueIdentifier("uidPlanLesson")
            .withRecurrence(EventRecurrence.NONE)
            .build();

    public static final Event RELAX_EVENT = new EventBuilder().withEventName("Watch a movie")
            .withEventStartDateTime(LocalDateTime.parse("2020-11-25T10:10:00"))
            .withEventEndDateTime(LocalDateTime.parse("2020-11-26T10:12:01"))
            .withDescription("The fun movie")
            .withUniqueIdentifier("uidWatchMovie")
            .withRecurrence(EventRecurrence.NONE)
            .build();

    private TypicalEvents() {} // prevents instantiation

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(ALICE_CLASS_EVENT, TODO_EVENT, RELAX_EVENT));
    }
}
