package io.dojogeek.validator;

/**
 * Created by jgacosta on 6/01/17.
 */
public class RequiredValidator implements DataValidator {

    private String errorMessage;
    private static final String EMPTY_VALUE = "";
    private Object valueToValidate;

    private RequiredValidator(RequiredValidatorBuilder requiredValidatorBuilder) {
        this.errorMessage = requiredValidatorBuilder.errorMessage;
        this.valueToValidate = requiredValidatorBuilder.valueToValidate;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public boolean isValid() {

        if (valueToValidate == null || valueToValidate.equals(EMPTY_VALUE)) {
            return false;
        }

        return true;
    }


    public static class RequiredValidatorBuilder {

        private String errorMessage = "No error messages";
        private Object valueToValidate;

        public RequiredValidatorBuilder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public RequiredValidatorBuilder valueToValidate(Object valueToValidate) {
            this.valueToValidate = valueToValidate;
            return this;
        }

        public RequiredValidator build() {
            return new RequiredValidator(this);
        }

    }
}
