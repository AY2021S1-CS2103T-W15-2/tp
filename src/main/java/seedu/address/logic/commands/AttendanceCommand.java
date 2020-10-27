package seedu.address.logic.commands;

import java.util.List;

import seedu.address.model.student.Student;
import seedu.address.model.student.academic.Academic;
import seedu.address.model.student.academic.Attendance;

public abstract class AttendanceCommand extends Command {

    public static final String COMMAND_WORD = "attendance";
    public static final String MESSAGE_USAGE = "";

    /**
     * Creates a new Student, with the provided attendance.
     * @param studentToAddAttendance student to add attendance to.
     * @param attendanceList new list of additional attendanceList.
     * @return updated Student.
     */
    public Student updateStudentAttendance(Student studentToAddAttendance, List<Attendance> attendanceList) {
        Academic academicToAddAttendance = studentToAddAttendance.getAcademic();
        Academic updatedAcademic = new Academic(attendanceList);
        Student updatedStudent = new Student(studentToAddAttendance.getName(), studentToAddAttendance.getPhone(),
                studentToAddAttendance.getSchool(), studentToAddAttendance.getYear(), studentToAddAttendance.getAdmin(),
                studentToAddAttendance.getQuestions(), studentToAddAttendance.getExams(),
                updatedAcademic);

        return updatedStudent;
    }
}