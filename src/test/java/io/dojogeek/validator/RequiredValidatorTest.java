package io.dojogeek.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created by jgacosta on 12/01/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class RequiredValidatorTest {

    @Test
    public void testRequiredValue_correctValue() {
        String value = "Shit";

        boolean isValid = new RequiredValidator.RequiredValidatorBuilder().
                valueToValidate(value).build().isValid();

        assertTrue(isValid);
    }

    @Test
    public void testRequiredValue_emptyValue() {
        String value = "";

        boolean isValid = new RequiredValidator.RequiredValidatorBuilder().
                valueToValidate(value).build().isValid();

        assertFalse(isValid);
    }

    @Test
    public void testRequiredValue_nullValue() {
        String value = null;

        boolean isValid = new RequiredValidator.RequiredValidatorBuilder().
                valueToValidate(value).build().isValid();

        assertFalse(isValid);
    }

    @Test
    public void testRequiredValue_incorrectValueWithErrorMessage() {
        String[] incorrectValues = {null, ""};
        String errorMessage = "The value is required";

        for (String value : incorrectValues) {
            RequiredValidator requiredValidator = new RequiredValidator.RequiredValidatorBuilder().
                    errorMessage(errorMessage).valueToValidate(value).build();

            boolean isValid = requiredValidator.isValid();

            assertFalse(isValid);
            assertEquals(errorMessage, requiredValidator.getErrorMessage());
        }
    }
}
