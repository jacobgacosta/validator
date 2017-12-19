package io.dojogeek.validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jgacosta on 6/01/17.
 */
public class CompoundValidator implements DataValidator {

    private List<DataValidator> dataValidatorList = new ArrayList<>();
    private String errorMessage;

    public CompoundValidator addDataValidator(DataValidator dataValidator) {
        dataValidatorList.add(dataValidator);
        return this;
    }

    @Override
    public boolean isValid() {
        for (DataValidator dataValidator : dataValidatorList) {
            boolean isValid = dataValidator.isValid();

            if (!isValid) {
                errorMessage = dataValidator.getErrorMessage();
                return false;
            }
        }

        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

}

