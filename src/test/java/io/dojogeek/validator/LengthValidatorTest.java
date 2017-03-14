package io.dojogeek.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created by jgacosta on 6/01/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class LengthValidatorTest {

    @Test
    public void testLength_correctMaxLength() {

        int maxLength = 15;
        String value = "This a test";

        boolean isValid = new LengthValidator.LengthValidatorBuilder().
                valueToValidate(value).maxLength(maxLength).build().isValid();


        assertTrue(isValid);
    }

    @Test
    public void testLength_correctMinLength() {

        int minLength = 4;
        String value = "Hi!!";

        boolean isValid = new LengthValidator.LengthValidatorBuilder().
                minLength(minLength).valueToValidate(value).build().isValid();

        assertTrue(isValid);
    }

    @Test
    public void testLength_correctMinAndMaxLength() {

        int minLength = 4;
        int maxLength = 10;
        String value = "Fucking!!!";

        boolean isValid = new LengthValidator.LengthValidatorBuilder().minLength(minLength).
                maxLength(maxLength).valueToValidate(value).build().isValid();

        assertTrue(isValid);
    }

    @Test
    public void testLength_zeroMinAndMaxLength() {

        int minLength = 0;
        int maxLength = 0;
        String value = "Mi mama me mima";

        boolean isValid = new LengthValidator.LengthValidatorBuilder().
                minLength(minLength).maxLength(maxLength).valueToValidate(value).build().isValid();

        assertFalse(isValid);
    }

    @Test
    public void testLength_incorrectConditionWithErrorMessage() {

        int maxLength = 7;
        String value = "Hi I love my chaparrita";
        String message = "The value " + value + "is too long";

        LengthValidator actualLengthValidator = new LengthValidator.LengthValidatorBuilder().maxLength(maxLength).
                errorMessage(message).build();
        boolean isValid = actualLengthValidator.isValid();

        assertFalse(isValid);
        assertEquals(message, actualLengthValidator.getErrorMessage());
    }

    @Test
    public void testLength_nullValue() {

        String value = null;

        boolean isValid = new LengthValidator.LengthValidatorBuilder().valueToValidate(value).build().isValid();

        assertFalse(isValid);
    }

    @Test
    public void testLength_correctEqualLength() {

        int equal = 4;
        String value = "Hi!!";

        boolean isValid = new LengthValidator.LengthValidatorBuilder().
                equal(equal).valueToValidate(value).build().isValid();

        assertTrue(isValid);
    }

    @Test
    public void testLength_allCorrectLengths() {

        int equal = 4;
        int minLength = 2;
        int maxLength = 10;
        String value = "Hi!!";

        boolean isValid = new LengthValidator.LengthValidatorBuilder().minLength(minLength).
                maxLength(maxLength).equal(equal).valueToValidate(value).build().isValid();

        assertTrue(isValid);
    }

}
