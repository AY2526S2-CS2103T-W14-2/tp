package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.FindDeliveryCommandParser.MESSAGE_START_AFTER_END;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DATE;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindDeliveryCommand;
import seedu.address.model.delivery.DeliveryDatePredicate;

public class FindDeliveryCommandParserTest {

    private FindDeliveryCommandParser parser = new FindDeliveryCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDeliveryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noPrefixes_throwsParseException() {
        // raw date with no prefix
        assertParseFailure(parser, "2026-04-01",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDeliveryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validSingleDate_returnsFindDeliveryCommand() {
        LocalDate date = LocalDate.parse("2026-04-01");
        FindDeliveryCommand expectedCommand =
                new FindDeliveryCommand(new DeliveryDatePredicate(date));

        // exact input
        assertParseSuccess(parser, " dt/2026-04-01", expectedCommand);

        // extra whitespace around date value
        assertParseSuccess(parser, " dt/ 2026-04-01 ", expectedCommand);
    }

    @Test
    public void parse_validDateRange_returnsFindDeliveryCommand() {
        LocalDate startDate = LocalDate.parse("2026-04-01");
        LocalDate endDate = LocalDate.parse("2026-04-30");
        FindDeliveryCommand expectedCommand =
                new FindDeliveryCommand(new DeliveryDatePredicate(startDate, endDate));

        assertParseSuccess(parser, " st/2026-04-01 ed/2026-04-30", expectedCommand);
    }

    @Test
    public void parse_sameDateRange_returnsFindDeliveryCommand() {
        // st/ and ed/ on the same date should equal the single-date predicate
        LocalDate date = LocalDate.parse("2026-04-01");
        FindDeliveryCommand expectedCommand =
                new FindDeliveryCommand(new DeliveryDatePredicate(date));

        assertParseSuccess(parser, " st/2026-04-01 ed/2026-04-01", expectedCommand);
    }

    @Test
    public void parse_invalidSingleDate_throwsParseException() {
        assertParseFailure(parser, " dt/invalid-date", MESSAGE_INVALID_DATE);
    }

    @Test
    public void parse_invalidStartDate_throwsParseException() {
        assertParseFailure(parser, " st/invalid-date ed/2026-04-30", MESSAGE_INVALID_DATE);
    }

    @Test
    public void parse_invalidEndDate_throwsParseException() {
        assertParseFailure(parser, " st/2026-04-01 ed/invalid-date", MESSAGE_INVALID_DATE);
    }

    @Test
    public void parse_startAfterEnd_throwsParseException() {
        assertParseFailure(parser, " st/2026-04-30 ed/2026-04-01", MESSAGE_START_AFTER_END);
    }

    @Test
    public void parse_missingEndDate_throwsParseException() {
        // st/ without ed/
        assertParseFailure(parser, " st/2026-04-01",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDeliveryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingStartDate_throwsParseException() {
        // ed/ without st/
        assertParseFailure(parser, " ed/2026-04-30",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDeliveryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_mixedPrefixes_throwsParseException() {
        // dt/ mixed with st/
        assertParseFailure(parser, " dt/2026-04-01 st/2026-04-01",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDeliveryCommand.MESSAGE_USAGE));

        // dt/ mixed with ed/
        assertParseFailure(parser, " dt/2026-04-01 ed/2026-04-30",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDeliveryCommand.MESSAGE_USAGE));
    }
}
