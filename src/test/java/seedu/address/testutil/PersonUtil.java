package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.description.Description;
import seedu.address.model.person.Person;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_SIZE + person.getSize().value + " ");
        sb.append(PREFIX_COLOUR + person.getColour().colour + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getDescriptions().stream().forEach(
            s -> sb.append(PREFIX_DESCRIPTION + s.descriptionName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getSize().ifPresent(phone -> sb.append(PREFIX_SIZE).append(phone.value).append(" "));
        descriptor.getColour().ifPresent(colour -> sb.append(PREFIX_COLOUR).append(colour.colour).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getDescriptions().isPresent()) {
            Set<Description> descriptions = descriptor.getDescriptions().get();
            if (descriptions.isEmpty()) {
                sb.append(PREFIX_DESCRIPTION);
            } else {
                descriptions.forEach(s -> sb.append(PREFIX_DESCRIPTION).append(s.descriptionName).append(" "));
            }
        }
        return sb.toString();
    }
}
