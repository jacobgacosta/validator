package io.dojogeek.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by jgacosta on 12/01/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ValidatorTest {

    @Test
    public void testValidator_failValidation() {
        Validator validator = new Validator() {
            @Override
            protected Map<String, DataValidator> getValidations() {
                Map<String, DataValidator> validators = new HashMap<>();
                validators.put("fieldName", new RequiredValidator.RequiredValidatorBuilder()
                        .valueToValidate("")
                        .errorMessage("The value must be not empty.")
                        .build());

                return validators;
            }
        };

        assertFalse(validator.isValid());
    }

    @Test
    public void testValidator_successValidation() {
        Validator validator = new Validator() {
            @Override
            protected Map<String, DataValidator> getValidations() {
                Map<String, DataValidator> validators = new HashMap<>();
                validators.put("fieldName", new RequiredValidator.RequiredValidatorBuilder()
                        .valueToValidate("Jacob Guzman Acosta")
                        .errorMessage("The value must be not empty.")
                        .build());

                return validators;
            }
        };

        assertTrue(validator.isValid());
    }

}
