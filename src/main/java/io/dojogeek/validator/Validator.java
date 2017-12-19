package io.dojogeek.validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jgacosta on 12/01/17.
 */
public abstract class Validator {

    private List<String> errorMessages = new ArrayList<>();

    protected abstract List<DataValidator> getValidations();

    public boolean validate() {
        List<DataValidator> dataValidatorList = getValidations();

        boolean isValid = true;

        for (DataValidator dataValidator : dataValidatorList) {
            if (! dataValidator.isValid()) {
                errorMessages.add(dataValidator.getErrorMessage());
                isValid = false;
            }
        }

        return isValid;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

}
