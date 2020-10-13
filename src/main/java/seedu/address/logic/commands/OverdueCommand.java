package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;

import seedu.address.model.Model;
import seedu.address.model.student.admin.OverdueFeePredicate;

/**
 * Finds and lists all students who have not paid their fees in more than a month.
 */
public class OverdueCommand extends Command {

    public static final String COMMAND_WORD = "overdue";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(new OverdueFeePredicate());
        return new CommandResult(String.format(MESSAGE_PERSONS_LISTED_OVERVIEW,
                model.getFilteredPersonList().size()));
    }
}
