package io.dojogeek.validator;

/**
 * Created by jgacosta on 1/28/17.
 */
public class RangeValidator implements DataValidator {

    private Integer lessThat;
    private Integer greaterThan;
    private Integer equalTo;
    private Integer valueToValidate;
    private String errorMessage;

    public RangeValidator(Integer lessThat, Integer greaterThan, Integer equalTo, Integer valueToValidate,
                          String errorMessage) {
        this.lessThat = lessThat;
        this.greaterThan = greaterThan;
        this.equalTo = equalTo;
        this.valueToValidate = valueToValidate;
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean isValid() {
        if (valueToValidate == null) {
            return false;
        } else if (equalTo != null && !equalTo.equals(valueToValidate)) {
            return false;
        } else if (lessThat != null && valueToValidate > lessThat) {
            return false;
        } else if (greaterThan != null && valueToValidate < greaterThan) {
            return false;
        }

        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    public static class RangeValidatorBuilder {

        private Integer lessThat;
        private Integer greaterThan;
        private Integer equalTo;
        private Integer valueToValidate;
        private String errorMessage;

        public RangeValidatorBuilder lessThat(Integer lessThat) {
            this.lessThat = lessThat;
            return this;
        }

        public RangeValidatorBuilder greaterThan(Integer greaterThan) {
            this.greaterThan = greaterThan;
            return this;
        }

        public RangeValidatorBuilder equalTo(Integer equalTo) {
            this.equalTo = equalTo;
            return this;
        }

        public RangeValidatorBuilder valueToValidate(int valueToValidate) {
            this.valueToValidate = valueToValidate;
            return this;
        }

        public RangeValidatorBuilder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public RangeValidator build() {
            return new RangeValidator(lessThat, greaterThan, equalTo, valueToValidate, errorMessage);
        }

    }

}
