package atom.parser;

import atom.command.Command;
import atom.command.AddCommand;
import atom.command.DeleteCommand;
import atom.command.ExitCommand;
import atom.command.InvalidCommand;
import atom.command.ListCommand;
import atom.command.MarkCommand;
import atom.command.FindCommand;
import atom.command.FilterCommand;

public class Parser {

    public static Command parse(String fullCommand) {
        String[] words = fullCommand.split(" ");
        String command = words[0].toLowerCase();

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
        case "unmark":
            return new MarkCommand(words, command);
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(words, fullCommand, command);
        case "delete":
            return new DeleteCommand(words);
        case "find":
            return new FindCommand(words, fullCommand);
        case "filter":
            return new FilterCommand(words, fullCommand);
        default:
            return new InvalidCommand();
        }
    }
}
