package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_DETAIL_COMMAND;
import static seedu.address.logic.commands.HelpCommand.MESSAGE_USAGE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddAdditionalDetailCommand;
import seedu.address.logic.commands.AdditionalDetailCommand;
import seedu.address.logic.commands.DeleteAdditionalDetailCommand;
import seedu.address.logic.commands.EditAdditionalDetailCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AdditionalDetailCommandParser {


   public AdditionalDetailCommand parseAdditionalDetailCommand(String userInput, Pattern commandFormat)
           throws ParseException {

       final Matcher matcher = commandFormat.matcher(userInput.trim());
       if (!matcher.matches()) {
           throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                   MESSAGE_USAGE));
       }

       final String commandWord = matcher.group("commandWord");
       final String arguments = matcher.group("arguments");

       switch (commandWord) {

       case AddAdditionalDetailCommand.COMMAND_WORD:
            return new AddAdditionalDetailCommandParser().parse(arguments);
       case DeleteAdditionalDetailCommand.COMMAND_WORD:

       case EditAdditionalDetailCommand.COMMAND_WORD:

       default:
           throw new ParseException(MESSAGE_UNKNOWN_DETAIL_COMMAND);
       }
   }
}
