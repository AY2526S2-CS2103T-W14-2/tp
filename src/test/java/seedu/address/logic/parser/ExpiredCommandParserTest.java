package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_DUPLICATE_FIELDS;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BEFORE_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExpiredCommand;
import seedu.address.model.person.PersonHasExpiredDeliveryPredicate;

public class ExpiredCommandParserTest {

    private ExpiredCommandParser parser = new ExpiredCommandParser();

    @Test
    public void parse_validDate_success() {
        LocalDate beforeDate = LocalDate.of(2026, 4, 1);
        PersonHasExpiredDeliveryPredicate predicate = new PersonHasExpiredDeliveryPredicate(beforeDate);
        ExpiredCommand expectedCommand = new ExpiredCommand(predicate);
        assertParseSuccess(parser, " " + PREFIX_BEFORE_DATE + "2026-04-01", expectedCommand);
        assertParseSuccess(parser, " " + PREFIX_BEFORE_DATE + "2026-04-01   ", expectedCommand);

        // Valid date - 29 February exists on leap years
        beforeDate = LocalDate.of(2024, 2, 29);
        predicate = new PersonHasExpiredDeliveryPredicate(beforeDate);
        expectedCommand = new ExpiredCommand(predicate);
        assertParseSuccess(parser, " " + PREFIX_BEFORE_DATE + "2024-02-29", expectedCommand);
    }

    @Test
    public void parse_emptyArg_failure() {
        // No attribute filters
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExpiredCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_preambleExistsBeforePrefix_failure() {
        assertParseFailure(parser, " garbage " + PREFIX_BEFORE_DATE + "2026-04-01",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExpiredCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingPrefix_failure() {
        assertParseFailure(parser, " 2026-04-01",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExpiredCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_duplicatePrefix_failure() {
        assertParseFailure(parser, " "
                        + PREFIX_BEFORE_DATE + "2026-04-01 "
                        + PREFIX_BEFORE_DATE + "2026-07-11",
                MESSAGE_DUPLICATE_FIELDS + PREFIX_BEFORE_DATE);
    }

    @Test
    public void parse_invalidDate_failure() {
        // No date provided
        assertParseFailure(parser, " " + PREFIX_BEFORE_DATE, ParserUtil.MESSAGE_INVALID_DATE);

        // Not a date
        assertParseFailure(parser, " " + PREFIX_BEFORE_DATE + "invalid-date", ParserUtil.MESSAGE_INVALID_DATE);

        // Invalid date format - wrong separator
        assertParseFailure(parser, " " + PREFIX_BEFORE_DATE + "01/04/2026", ParserUtil.MESSAGE_INVALID_DATE);

        // Invalid date - 31 April does not exist
        assertParseFailure(parser, " " + PREFIX_BEFORE_DATE + "2026-04-31", ParserUtil.MESSAGE_INVALID_DATE);

        // Invalid date - a year does not have 13 months
        assertParseFailure(parser, " " + PREFIX_BEFORE_DATE + "2026-13-01", ParserUtil.MESSAGE_INVALID_DATE);

        // Invalid date - 29 February does not exist on non-leap years
        assertParseFailure(parser, " " + PREFIX_BEFORE_DATE + "2026-02-29", ParserUtil.MESSAGE_INVALID_DATE);
    }

    @Test
    public void parse_extraDate_failure() {
        assertParseFailure(parser, " " + PREFIX_BEFORE_DATE + "2026-02-29 2026-02-29",
                ParserUtil.MESSAGE_INVALID_DATE);
    }

}
