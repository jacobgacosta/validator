package io.dojogeek.validator;

import io.dojogeek.dtos.UserDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by norveo on 12/18/17.
 */
public class UserValidator extends Validator {

    private UserDto user;

    public UserValidator(UserDto user) {
        this.user = user;
    }

    @Override
    protected List<DataValidator> getValidations() {
        List<DataValidator> validatorList = new ArrayList<>();
        validatorList.add(new RangeValidator.RangeValidatorBuilder()
                .valueToValidate(user.getAge())
                .greaterThan(18)
                .errorMessage("The age must be greater that 18 years old.")
                .build());
        validatorList.add(new CompoundValidator()
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
