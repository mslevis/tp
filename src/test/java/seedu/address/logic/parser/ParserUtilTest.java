package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SPOT;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.studyspot.Address;
import seedu.address.model.studyspot.Name;
import seedu.address.model.studyspot.OperatingHours;
import seedu.address.model.studyspot.Rating;
import seedu.address.model.studyspot.StudiedHours;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_RATING = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_OPERATING_HOURS = "9-10, 9-6";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_STUDIED_HOURS = "hour";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_RATING = "3";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_OPERATING_HOURS = "0900-2200, 0900-1800";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_SPOT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_SPOT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseRating_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRating((String) null));
    }

    @Test
    public void parseRating_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRating(INVALID_RATING));
    }

    @Test
    public void parseRating_validValueWithoutWhitespace_returnsRating() throws Exception {
        Rating expectedRating = new Rating(VALID_RATING);
        assertEquals(expectedRating, ParserUtil.parseRating(VALID_RATING));
    }

    @Test
    public void parseRating_validValueWithWhitespace_returnsTrimmedRating() throws Exception {
        String ratingWithWhitespace = WHITESPACE + VALID_RATING + WHITESPACE;
        Rating expectedRating = new Rating(VALID_RATING);
        assertEquals(expectedRating, ParserUtil.parseRating(ratingWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseOperatingHours_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseOperatingHours((String) null));
    }

    @Test
    public void parseOperatingHours_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOperatingHours(INVALID_OPERATING_HOURS));
    }

    @Test
    public void parseOperatingHours_validValueWithoutWhitespace_returnsOperatingHours() throws Exception {
        OperatingHours expectedOperatingHours = new OperatingHours(VALID_OPERATING_HOURS);
        assertEquals(expectedOperatingHours, ParserUtil.parseOperatingHours(VALID_OPERATING_HOURS));
    }

    @Test
    public void parseOperatingHours_validValueWithWhitespace_returnsTrimmedOperatingHours() throws Exception {
        String operatingHoursWithWhitespace = WHITESPACE + VALID_OPERATING_HOURS + WHITESPACE;
        OperatingHours expectedOperatingHours = new OperatingHours(VALID_OPERATING_HOURS);
        assertEquals(expectedOperatingHours, ParserUtil.parseOperatingHours(operatingHoursWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTag_containsWhiteSpace_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(" "));
    }

    @Test
    public void parseTag_exceedsMaxLength_throwsParseException() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 49; i++) {
            sb.append("a");
        }
        assertDoesNotThrow(() -> ParserUtil.parseTag(sb.toString()));
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            sb1.append("a");
        }
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(sb1.toString()));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseStudiedHours_validInput_returnsStudiedHours() throws ParseException {
        StudiedHours actualHours = ParserUtil.parseStudiedHours("10");
        StudiedHours expectedHours = new StudiedHours("10");

        assertEquals(actualHours, expectedHours);
    }

    @Test
    public void parseStudiedHours_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStudiedHours(INVALID_STUDIED_HOURS));
    }

    @Test
    public void parseStudiedHours_negativeValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStudiedHours("-10"));
    }

    @Test
    public void parseStudiedHours_missingValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStudiedHours(""));
    }

    @Test
    public void parseStudiedHours_largerThanMaxInteger_throwsParseException() {
        String maxIntegerPlusOne = "2147483648";
        assertThrows(ParseException.class, () -> ParserUtil.parseStudiedHours(maxIntegerPlusOne));
    }

}
