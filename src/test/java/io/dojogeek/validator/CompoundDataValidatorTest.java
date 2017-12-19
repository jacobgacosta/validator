package io.dojogeek.validator;

import org.junit.Before;
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

    private CompoundValidator compoundDataValidator;

    @Before
    public void setup() {
        compoundDataValidator = new CompoundValidator();
    }

    @Test
    public void testCompoundValidator_correctValidations() {
        compoundDataValidator.addDataValidator(new LengthValidator.LengthValidatorBuilder()
                .maxLength(30)
                .valueToValidate("fuck yeah!")
                .errorMessage("this a error message!").build())
                .addDataValidator(new RequiredValidator.RequiredValidatorBuilder()
                        .valueToValidate("fuck yeah!")
                        .build());

        assertTrue(compoundDataValidator.isValid());
    }

    @Test
    public void testCompoundValidator_withLengthValidatorFail() {
        compoundDataValidator.addDataValidator(new LengthValidator.LengthValidatorBuilder().maxLength(7)
                .valueToValidate("fuck yeah!")
                .errorMessage("this a error message!")
                .build())
                .addDataValidator(new RequiredValidator.RequiredValidatorBuilder()
                        .valueToValidate("This a value")
                        .build());

        assertFalse(compoundDataValidator.isValid());
        assertEquals("this a error message!", compoundDataValidator.getErrorMessage());
    }
}
