package io.dojogeek.validator;

/**
 * Created by jgacosta on 6/01/17.
 */
public class LengthValidator implements DataValidator {

    private Integer maxLength;
    private Integer minLength;
    private Integer equalLength;
    private String errorMessage;
    private String valueToValidate;

    private LengthValidator(LengthValidatorBuilder lengthValidatorBuilder) {
        this.minLength = lengthValidatorBuilder.minLength;
        this.maxLength = lengthValidatorBuilder.maxLength;
        this.errorMessage = lengthValidatorBuilder.errorMessage;
        this.valueToValidate = lengthValidatorBuilder.valueToValidate;
        this.equalLength = lengthValidatorBuilder.equalLength;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public boolean isValid() {
        if (valueToValidate == null) {
            return false;
        } else if (equalLength != null && !equalLength.equals(valueToValidate.length())) {
            return false;
        } else if (minLength != null && valueToValidate.length() < minLength) {
            return false;
        } else if (maxLength != null && valueToValidate.length() > maxLength) {
            return false;
        }
        return true;
    }

    public static class LengthValidatorBuilder {

        private Integer maxLength;
        private Integer minLength;
        private Integer equalLength;
        private String valueToValidate;
        private String errorMessage = "No error messages";

        public LengthValidatorBuilder maxLength(Integer maxLength) {
            this.maxLength = maxLength;
            return this;
        }

        public LengthValidatorBuilder minLength(Integer minLength) {
            this.minLength = minLength;
            return this;
        }

        public LengthValidatorBuilder equal(Integer equalLength) {
            this.equalLength = equalLength;
            return this;
        }

        public LengthValidatorBuilder valueToValidate(String valueToValidate) {
            this.valueToValidate = valueToValidate;
            return this;
        }

        public LengthValidatorBuilder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public LengthValidator build() {
            return new LengthValidator(this);
        }

    }

}
