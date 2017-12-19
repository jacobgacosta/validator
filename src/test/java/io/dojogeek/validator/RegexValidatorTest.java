package io.dojogeek.validator;

import io.dojogeek.utils.RegexValues;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jgacosta on 1/28/17.
 */
public class RegexValidatorTest {

    @Test
    public void testRegexValidator_correctEmail() {
        String email = "myemail@gmail.com";

        RegexValidator regexValidator = new RegexValidator.RegexValidatorBuilder().
                regex(RegexValues.EMAIL_REGEXP).valueToValidate(email).build();

        boolean isValidEmail = regexValidator.isValid();

        assertTrue(isValidEmail);
    }

    @Test
    public void testRegexValidator_incorrectEmail() {
        final String[] incorrectEmails = {"jgacosta@", "jgacosta.io", "jgacosta@gmail.",
                "@gmail.io", "jgacosta", "jgacosta@gmail.@", "jgacosta@gmail._"};

        for (String email : incorrectEmails) {
            RegexValidator regexValidator = new RegexValidator.RegexValidatorBuilder().
                    valueToValidate(email).regex(RegexValues.EMAIL_REGEXP).errorMessage("The email is invalid").build();

            boolean isValidEmail = regexValidator.isValid();

            assertFalse(isValidEmail);
            assertEquals("The email is invalid", regexValidator.getErrorMessage());
        }
    }

}
