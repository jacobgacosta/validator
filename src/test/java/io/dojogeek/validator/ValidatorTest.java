package io.dojogeek.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

/**
 * Created by jgacosta on 12/01/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ValidatorTest {

    @Test
    public void testValidator_failValidation() {
        Validator validator = new Validator() {
            @Override
            protected List<DataValidator> getValidations() {
                List<DataValidator> validators = new ArrayList<>();

                validators.add(new RequiredValidator.RequiredValidatorBuilder()
                        .valueToValidate("")
                        .errorMessage("The value must be not empty.")
                        .build());

                return validators;
            }
        };

        assertFalse(validator.validate());
        assertEquals("The value must be not empty.", validator.getErrorMessages().get(0));
    }

    @Test
    public void testValidator_successValidation() {
        Validator validator = new Validator() {
            @Override
            protected List<DataValidator> getValidations() {
                List<DataValidator> validators = new ArrayList<>();

                validators.add(new RequiredValidator.RequiredValidatorBuilder()
                        .valueToValidate("Jacob Guzman Acosta")
                        .errorMessage("The value must be not empty.")
                        .build());

                return validators;
            }
        };

        assertTrue(validator.validate());
        assertEquals(0, validator.getErrorMessages().size());
    }

}
