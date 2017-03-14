package io.dojogeek.validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jgacosta on 6/01/17.
 */
public class CompoundDataValidator implements DataValidator {

    private List<DataValidator> dataValidatorList = new ArrayList<>();
    private String errorMessage;

    public void addDataValidator(DataValidator dataValidator) {
        dataValidatorList.add(dataValidator);
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

