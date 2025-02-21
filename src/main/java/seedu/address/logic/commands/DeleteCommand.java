package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_SPOT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.studyspot.Name;
import seedu.address.model.studyspot.StudySpot;

/**
 * Deletes a study spot identified using it's displayed index from the study tracker.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the specified study spot\n"
            + "Parameters: n/NAME* \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_DELETE_SPOT + "starbucks";

    public static final String MESSAGE_DELETE_STUDYSPOT_SUCCESS = "Deleted study spot: %1$s";

    private Name name;

    public DeleteCommand(Name name) {
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        StudySpot studySpotToDelete = model.findStudySpot(name);
        if (studySpotToDelete == null) {
            throw new CommandException(MESSAGE_INVALID_NAME);
        }

        if (studySpotToDelete.isFavourite()) {
            StudySpot unfavStudySpot = model.removeStudySpotFromFavourites(studySpotToDelete);
            model.deleteStudySpot(unfavStudySpot);
        } else {
            model.deleteStudySpot(studySpotToDelete);
        }
        return new CommandResult(String.format(MESSAGE_DELETE_STUDYSPOT_SUCCESS, studySpotToDelete.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && name.equals(((DeleteCommand) other).name)); // state check
    }

}
