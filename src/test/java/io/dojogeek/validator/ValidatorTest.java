package io.dojogeek.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jgacosta on 12/01/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ValidatorTest {

    @Test
    public void testValidator_validators_with_invalidData_isFalse() {

        int maxLength = 10;
        String value = "this data is incorrect";

        LengthValidator lengthValidator =
                new LengthValidator.LengthValidatorBuilder().
                        maxLength(maxLength).valueToValidate(value).build();

        RequiredValidator requiredValidator = new RequiredValidator.RequiredValidatorBuilder().
                valueToValidate("").build();

        Validator validator = createListDataValidator(lengthValidator, requiredValidator);

        boolean isValid = validator.validate();

        assertFalse(isValid);

    }

    @Test
    public void testValidator_validators_with_validData_isTrue() {

        int maxLength = 30;
        String value = "this data is correct";

        LengthValidator lengthValidator =
                new LengthValidator.LengthValidatorBuilder().
                        maxLength(maxLength).valueToValidate(value).build();

        RequiredValidator requiredValidator = new RequiredValidator.RequiredValidatorBuilder().
                valueToValidate(value).build();

        Validator validator = createListDataValidator(lengthValidator, requiredValidator);

        boolean isValid = validator.validate();

        assertTrue(isValid);

    }

    @Test
    public void testValidator_getErrorMessage_from_fail_validation() {

        String emptyValue = "";
        String errorMessage = "The value is required";

        RequiredValidator requiredValidator = new RequiredValidator.RequiredValidatorBuilder().
                valueToValidate(emptyValue).errorMessage(errorMessage).build();

        Validator validator = createListDataValidator(requiredValidator);

        assertFalse(validator.validate());
        assertEquals(errorMessage, validator.getErrorMessage());

    }

    public Validator createListDataValidator(final DataValidator... dataValidator) {

        Validator validator = new Validator() {

            @Override
            protected List<DataValidator> getValidations() {

                List<DataValidator> dataValidators = new ArrayList<>();

                for (DataValidator validator : dataValidator) {
                    dataValidators.add(validator);
                }

                return dataValidators;
            }
        };

        return validator;
    }

}
