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
        // Equivalence partition for valid date
        LocalDate beforeDate = LocalDate.of(2026, 4, 1);
        PersonHasExpiredDeliveryPredicate predicate = new PersonHasExpiredDeliveryPredicate(beforeDate);
        ExpiredCommand expectedCommand = new ExpiredCommand(predicate);
        assertParseSuccess(parser, " " + PREFIX_BEFORE_DATE + "2026-04-01", expectedCommand);
        assertParseSuccess(parser, " " + PREFIX_BEFORE_DATE + "2026-04-01   ", expectedCommand);

        // Boundary value: 29 February exists on leap years
        beforeDate = LocalDate.of(2024, 2, 29);
        predicate = new PersonHasExpiredDeliveryPredicate(beforeDate);
        expectedCommand = new ExpiredCommand(predicate);
        assertParseSuccess(parser, " " + PREFIX_BEFORE_DATE + "2024-02-29", expectedCommand);
    }

    @Test
    public void parse_emptyArg_failure() {
        // Equivalence partition for empty arguments
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExpiredCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyDateField_failure() {
        // Equivalence partition for empty date field
        assertParseFailure(parser, " " + PREFIX_BEFORE_DATE, ParserUtil.MESSAGE_INVALID_DATE);
    }

    @Test
    public void parse_preambleExistsBeforePrefix_failure() {
        // Equivalence partition for including non-empty preamble
        assertParseFailure(parser, " garbage " + PREFIX_BEFORE_DATE + "2026-04-01",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExpiredCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingPrefix_failure() {
        // Equivalence partition for missing prefix
        assertParseFailure(parser, " 2026-04-01",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExpiredCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_duplicateField_failure() {
        // Equivalence partition for duplicate fields
        assertParseFailure(parser, " "
                        + PREFIX_BEFORE_DATE + "2026-04-01 "
                        + PREFIX_BEFORE_DATE + "2026-07-11",
                MESSAGE_DUPLICATE_FIELDS + PREFIX_BEFORE_DATE);
    }

    @Test
    public void parse_invalidDate_failure() {
        // Equivalence partition for non-date strings
        assertParseFailure(parser, " " + PREFIX_BEFORE_DATE + "invalid-date", ParserUtil.MESSAGE_INVALID_DATE);

        // Equivalence partition for wrong date format
        assertParseFailure(parser, " " + PREFIX_BEFORE_DATE + "01/04/2026", ParserUtil.MESSAGE_INVALID_DATE);

        // Equivalence partition for non-existent dates
        assertParseFailure(parser, " " + PREFIX_BEFORE_DATE + "2026-04-31", ParserUtil.MESSAGE_INVALID_DATE);
        assertParseFailure(parser, " " + PREFIX_BEFORE_DATE + "2026-13-01", ParserUtil.MESSAGE_INVALID_DATE);
        assertParseFailure(parser, " " + PREFIX_BEFORE_DATE + "2026-02-29", ParserUtil.MESSAGE_INVALID_DATE);
    }

    @Test
    public void parse_extraDate_failure() {
        // Equivalence partition for having multiple keyword for same field
        assertParseFailure(parser, " " + PREFIX_BEFORE_DATE + "2026-02-29 2026-02-29",
                ParserUtil.MESSAGE_INVALID_DATE);
    }

}
