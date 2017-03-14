package io.dojogeek.validator;

import io.dojogeek.dtos.DtoUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jgacosta on 1/28/17.
 */
public class UserValidator extends Validator {

    private DtoUser user;

    public UserValidator(DtoUser user) {
        this.user = user;
    }

    @Override
    protected List<DataValidator> getValidations() {

        List<DataValidator> dataValidators = new ArrayList<>();
        dataValidators.add(getValidatorsForName());
        dataValidators.add(getValidatorsForOcupation());
        dataValidators.add(createRequiredValidatorFor(user.getBirthDate(), "The birth date is required"));
        dataValidators.add(new RangeValidator.RangeValidatorBuilder().valueToValidate(user.getAge()).
                greaterThan(18).errorMessage("The age is less than 18").build());

        return dataValidators;
}

    private CompoundDataValidator getValidatorsForName() {

        CompoundDataValidator compoundDataValidator = new CompoundDataValidator();

        LengthValidator lengthValidator = new LengthValidator.LengthValidatorBuilder().
                valueToValidate(user.getName()).maxLength(7).errorMessage("The name is too long").build();

        RequiredValidator requiredValidator = createRequiredValidatorFor(user.getName(), "The name is required");

        compoundDataValidator.addDataValidator(lengthValidator);
        compoundDataValidator.addDataValidator(requiredValidator);

        return compoundDataValidator;

    }

    private CompoundDataValidator getValidatorsForOcupation() {

        CompoundDataValidator compoundDataValidator = new CompoundDataValidator();

        RequiredValidator requiredValidator = createRequiredValidatorFor(user.getOcupation(), "The ocupation is required");

        LengthValidator lengthValidator = new LengthValidator.LengthValidatorBuilder().
                valueToValidate(user.getOcupation()).maxLength(10).errorMessage("The ocupation is too long").build();


        compoundDataValidator.addDataValidator(lengthValidator);
        compoundDataValidator.addDataValidator(requiredValidator);

        return compoundDataValidator;

    }

    private RequiredValidator createRequiredValidatorFor(Object valueToValidate, String errorMessage) {
        return new RequiredValidator.RequiredValidatorBuilder().valueToValidate(valueToValidate).
                errorMessage(errorMessage).build();
    }

}
