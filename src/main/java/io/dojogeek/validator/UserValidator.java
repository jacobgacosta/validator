package io.dojogeek.validator;

import io.dojogeek.dtos.UserDto;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by norveo on 12/18/17.
 */
public class UserValidator extends Validator {

    public static String NAME = "name";
    public static String AGE = "age";

    private UserDto user;

    public UserValidator(UserDto user) {
        this.user = user;
    }

    @Override
    protected Map<String, DataValidator> getValidations() {
        Map<String, DataValidator> validatorList = new HashMap<>();
        validatorList.put(AGE, new RangeValidator.RangeValidatorBuilder()
                .valueToValidate(user.getAge())
                .greaterThan(18)
                .errorMessage("The age must be greater that 18 years old.")
                .build());
        validatorList.put(NAME, new CompoundValidator()
                .addDataValidator(new RequiredValidator.RequiredValidatorBuilder()
                        .valueToValidate(user.getName())
                        .errorMessage("The name is required.")
                        .build())
                .addDataValidator(new LengthValidator.LengthValidatorBuilder()
                        .valueToValidate(user.getName()).maxLength(15)
                        .errorMessage("The name is too long, the max value is 15.")
                        .build()));

        return validatorList;
    }

}
