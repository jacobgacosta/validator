package io.dojogeek.validator;

import java.util.List;

/**
 * Created by jgacosta on 12/01/17.
 */
public abstract class Validator {

    private String errorMessage;

    protected abstract List<DataValidator> getValidations();

    public boolean validate() {

        List<DataValidator> dataValidatorList = getValidations();

        for (DataValidator dataValidator : dataValidatorList) {
            boolean isValid = dataValidator.isValid();

            if (!isValid) {
                errorMessage = dataValidator.getErrorMessage();
                return false;
            }
        }

        return true;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
