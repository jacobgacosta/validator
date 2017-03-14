package io.dojogeek.validator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jgacosta on 1/28/17.
 */
public class RangeValidatorTest {

    @Test
    public void testRangeValidator_allCorrectRanges() {

        int value = 28;

        RangeValidator rangeValidator = new RangeValidator.RangeValidatorBuilder().
                valueToValidate(value).equalTo(28).greaterThan(20).lessThat(100).build();

        assertTrue(rangeValidator.isValid());

    }

    @Test
    public void testRangeValidator_allRangesWithFailEqualTo() {

        int value = 28;

        RangeValidator rangeValidator = new RangeValidator.RangeValidatorBuilder().
                valueToValidate(value).equalTo(20).greaterThan(20).lessThat(100).build();

        assertFalse(rangeValidator.isValid());

    }

    @Test
    public void testRangeValidator_allRangesWithFailGreaterThan() {

        int value = 28;

        RangeValidator rangeValidator = new RangeValidator.RangeValidatorBuilder().
                valueToValidate(value).equalTo(28).greaterThan(29).lessThat(100).build();

        assertFalse(rangeValidator.isValid());

    }

    @Test
    public void testRangeValidator_allRangesWithFailLessThan() {

        int value = 28;

        RangeValidator rangeValidator = new RangeValidator.RangeValidatorBuilder().
                valueToValidate(value).equalTo(28).greaterThan(20).lessThat(18).build();

        assertFalse(rangeValidator.isValid());

    }

    @Test
    public void testRangeValidator_correctLessThan() {

        int value = 4;

        RangeValidator rangeValidator = new RangeValidator.RangeValidatorBuilder().
                valueToValidate(value).lessThat(18).build();

        assertTrue(rangeValidator.isValid());

    }

    @Test
    public void testRangeValidator_correctGreaterThan() {

        int value = 20;

        RangeValidator rangeValidator = new RangeValidator.RangeValidatorBuilder().
                valueToValidate(value).greaterThan(0).build();

        assertTrue(rangeValidator.isValid());

    }

    @Test
    public void testRangeValidator_correctEqualTo() {

        int value = 20;

        RangeValidator rangeValidator = new RangeValidator.RangeValidatorBuilder().
                greaterThan(value).valueToValidate(value).build();

        assertTrue(rangeValidator.isValid());

    }

    @Test
    public void testRangeValidator_withoutRanges() {

        int value = 20;

        RangeValidator rangeValidator = new RangeValidator.RangeValidatorBuilder().valueToValidate(value).build();

        assertTrue(rangeValidator.isValid());

    }

    @Test
    public void testRangeValidator_withErrorMessage() {

        int value = 28;

        RangeValidator rangeValidator = new RangeValidator.RangeValidatorBuilder().
                valueToValidate(value).equalTo(28).greaterThan(-4).lessThat(-100).
                errorMessage("the range is incorrect").build();

        assertFalse(rangeValidator.isValid());
        assertEquals("the range is incorrect", rangeValidator.getErrorMessage());

    }

}
