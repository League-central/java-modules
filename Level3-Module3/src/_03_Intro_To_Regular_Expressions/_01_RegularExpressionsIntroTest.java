package _03_Intro_To_Regular_Expressions;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

/*
 * Write your code in the other file
 */

public class _01_RegularExpressionsIntroTest {
    final String originalMessage =
            "To: support@fakemail.com\n" +
            "From: johnDoe@aol.com\n" +
            "Hello mail support,\n" +
            "Please forward all new messages to my new e-mail address, johnDoe@league.com";
    
    /*
     * What the message should be after the regex method is called
     */
    final String expectedMessage =
            "To: support@mail.com\n" +
            "From: johnDoe@mail.com\n" +
            "Hello mail support,\n" +
            "Please forward all new messages to my new e-mail address, johnDoe@mail.com";
    
    @Test
    void testEmailAddressRemoved(){
        assertEquals(expectedMessage, new _01_RegularExpressionsIntro().emailRemover(originalMessage));
    }
    
}
