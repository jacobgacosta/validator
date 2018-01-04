package io.dojogeek.validator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jgacosta on 12/01/17.
 */
public abstract class Validator {

    private Map<String, String> errorMessages = new HashMap<>();

    protected abstract Map<String, DataValidator> getValidations();

    private boolean validate() {
        Map<String, DataValidator> dataValidatorList = getValidations();

        this.clearStackErrors();

        for (Map.Entry<String, DataValidator> entry : dataValidatorList.entrySet()) {
            if (! entry.getValue().isValid()) {
                errorMessages.put(entry.getKey(), entry.getValue().getErrorMessage());
            }
        }

        return errorMessages.isEmpty();
    }

    public String getErrorFor(String name) {
        return errorMessages.get(name);
    }

    public boolean isValid(String name) {
        return ! errorMessages.containsKey(name);
    }

    public boolean isValid() {
        return this.validate();
    }

    private void clearStackErrors() {
        errorMessages.clear();
    }

}
