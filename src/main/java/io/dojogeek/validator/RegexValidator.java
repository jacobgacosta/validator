package io.dojogeek.validator;

import java.util.regex.Pattern;

/**
 * Created by jgacosta on 6/01/17.
 */
public class RegexValidator implements DataValidator {

    private String regex;
    private String valueToValidate;
    private String errorMessage;

    private RegexValidator(String valueToValidate, String errorMessage, String regex) {
        this.valueToValidate = valueToValidate;
        this.errorMessage = errorMessage;
        this.regex = regex;
    }

    public boolean isValid() {
        if (valueToValidate == null || valueToValidate.isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(valueToValidate).matches();
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }


    public static class RegexValidatorBuilder {

        private String regex;
        private String valueToValidate;
        private String errorMessage;


        public RegexValidatorBuilder regex(String regex) {
            this.regex = regex;
            return this;
        }

        public RegexValidatorBuilder valueToValidate(String valueToValidate) {
            this.valueToValidate = valueToValidate;
            return this;
        }

        public RegexValidatorBuilder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public RegexValidator build() {
            return new RegexValidator(valueToValidate, errorMessage, regex);
        }

    }
}
