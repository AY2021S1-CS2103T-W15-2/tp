package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_SCHOOL = new Prefix("s/");
    public static final Prefix PREFIX_YEAR = new Prefix("y/");
    public static final Prefix PREFIX_VENUE = new Prefix("v/");
    public static final Prefix PREFIX_TIME = new Prefix("t/");
    public static final Prefix PREFIX_FEE = new Prefix("f/");
    public static final Prefix PREFIX_PAYMENT = new Prefix("d/");
    public static final Prefix PREFIX_DETAILS = new Prefix("a/");

    /* Prefix for question commands */
    public static final Prefix PREFIX_ADD_QUESTION = new Prefix("a/");
    public static final Prefix PREFIX_SOLVE_QUESTION = new Prefix("s/");
    public static final Prefix PREFIX_DELETE_QUESTION = new Prefix("d/");

    /* Prefix for exam commands */
    public static final Prefix PREFIX_EXAM_NAME = new Prefix("n/");
    public static final Prefix PREFIX_EXAM_DATE = new Prefix("d/");
    public static final Prefix PREFIX_EXPECTED_SCORE = new Prefix("e/");
    public static final Prefix PREFIX_SCORE = new Prefix("s/");

    public static final Prefix[] COMPULSORY_PREFIXES = new Prefix[] {PREFIX_NAME, PREFIX_PHONE,
        PREFIX_SCHOOL, PREFIX_YEAR, PREFIX_VENUE, PREFIX_TIME, PREFIX_FEE, PREFIX_PAYMENT};

    public static final Prefix[] ALL_PREFIXES = new Prefix[] {PREFIX_NAME, PREFIX_PHONE, PREFIX_SCHOOL,
        PREFIX_YEAR, PREFIX_VENUE, PREFIX_TIME, PREFIX_FEE, PREFIX_PAYMENT, PREFIX_DETAILS};

    public static final Prefix[] FIND_SUPPORTED_PREFIXES =
            new Prefix[] {PREFIX_NAME, PREFIX_SCHOOL, PREFIX_YEAR};

    public static final Prefix[] QUESTION_COMMAND_PREFIXES =
            new Prefix[] {PREFIX_ADD_QUESTION, PREFIX_SOLVE_QUESTION, PREFIX_DELETE_QUESTION};

    public static final Prefix[] EXAM_COMMAND_PREFIXES =
            new Prefix[] {PREFIX_EXAM_NAME, PREFIX_EXAM_DATE, PREFIX_EXPECTED_SCORE};
}
