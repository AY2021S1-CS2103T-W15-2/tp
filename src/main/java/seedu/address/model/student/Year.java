package seedu.address.model.student;

import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

public class Year {

    public static final String MESSAGE_CONSTRAINTS =
            "Year should contain only the type of school and a number representing the level. The type"
            + "of school can only be one of \"secondary\", \"Secondary\", \"sec\", \"Sec\", \"s\", \"S\""
            + "\"primary\", \"Primary\", \"pri\", \"Pri\", \"p\", \"P\" and the level must be valid";

    public static final String VALIDATION_REGEX = "((secondary|sec|s|Secondary|Sec|S)\\s*([1-5])|"
        + "(primary|pri|p|Primary|Pri|P)\\s*([1-6])|(jc|Jc|j|J|JC)\\s*([1-2]))\\s*";

    /**
     * Used for separation of year into school type and level.
     */
    public static final Pattern YEAR_FORMAT = Pattern.compile("(?<school>[^\\d\\s]+)\\s*(?<level>\\d)");

    private final SchoolType schoolType;
    private final Integer level;

    public Year(SchoolType schoolType, Integer level) {
        requireNonNull(schoolType);
        requireNonNull(level);
        this.schoolType = schoolType;
        this.level = level;
    }

    /**
     * Returns true if a given string is a valid year.
     */
    public static boolean isValidYear(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.schoolType, this.level);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Year // instanceof handles nulls
                && schoolType.equals(((Year) other).schoolType)) // state check
                && level.equals(((Year) other).level);
    }
}