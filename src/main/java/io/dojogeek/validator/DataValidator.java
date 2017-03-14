package io.dojogeek.validator;

/**
 * Created by jgacosta on 6/01/17.
 */
public interface DataValidator {

    boolean isValid();

    String getErrorMessage();

}
