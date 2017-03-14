package io.dojogeek.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.spy;

/**
 * Created by jgacosta on 12/01/17.
 */
@RunWith(PowerMockRunner.class)
public class CompoundDataValidatorTest {

    private CompoundDataValidator compoundDataValidator = new CompoundDataValidator();

    @Test
    public void testCompoundDataValidator_correctValidations() {

        LengthValidator lengthValidator = spy(new LengthValidator.LengthValidatorBuilder().maxLength(30).
                valueToValidate("fuck yhea!").errorMessage("this a errror message!").build());

        RequiredValidator requiredValidator = spy(new RequiredValidator.RequiredValidatorBuilder().
                valueToValidate("fuck yhea!").build());

        compoundDataValidator.addDataValidator(lengthValidator);
        compoundDataValidator.addDataValidator(requiredValidator);

        boolean isValid = compoundDataValidator.isValid();

        assertTrue(isValid);

        verify(lengthValidator).isValid();
        verify(requiredValidator).isValid();
    }


    @Test
    public void testCompoundDataValidator_withLengthValidatorFail() {

        LengthValidator lengthValidator = spy(new LengthValidator.LengthValidatorBuilder().maxLength(7).
                valueToValidate("fuck yhea!").errorMessage("this a errror message!").build());

        RequiredValidator requiredValidator = spy(new RequiredValidator.RequiredValidatorBuilder().
                valueToValidate("This a value").build());

        compoundDataValidator.addDataValidator(lengthValidator);
        compoundDataValidator.addDataValidator(requiredValidator);

        boolean isValid = compoundDataValidator.isValid();

        assertFalse(isValid);

        verify(lengthValidator).isValid();
        verify(requiredValidator, never()).isValid();

    }

    @Test
    public void testCompoundDataValidator_withLengthValidatorFailAndErrorMessage() {

        LengthValidator lengthValidator = spy(new LengthValidator.LengthValidatorBuilder().maxLength(7).
                valueToValidate("fuck yhea!").errorMessage("this a errror message!").build());

        RequiredValidator requiredValidator = spy(new RequiredValidator.RequiredValidatorBuilder().
                valueToValidate("This a value").build());

        compoundDataValidator.addDataValidator(lengthValidator);
        compoundDataValidator.addDataValidator(requiredValidator);

        boolean isValid = compoundDataValidator.isValid();

        assertFalse(isValid);
        assertEquals("this a errror message!", compoundDataValidator.getErrorMessage());

        verify(lengthValidator).isValid();
        verify(requiredValidator, never()).isValid();
    }

}
