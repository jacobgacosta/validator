package io.dojogeek.validator;

import io.dojogeek.dtos.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created by jgacosta on 1/28/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserValidatorTest {

    private UserDto user;

    @Before
    public void setup() {
        user = new UserDto();
        user.setAge(19);
        user.setName("Jacob");
    }

    @Test
    public void testUserValidator_errorForInvalidAge() {
        user.setAge(0);

        UserValidator validator = new UserValidator(user);
        assertFalse(validator.isValid());
    }

    @Test
    public void testUserValidator_validAge() {
        UserValidator validator = new UserValidator(user);
        assertTrue(validator.isValid());
    }

    @Test
    public void testUserValidator_emptyName() {
        user.setName("");

        UserValidator validator = new UserValidator(user);
        assertFalse(validator.isValid());
    }

    @Test
    public void testUserValidator_tooLongName() {
        user.setName("Lorem ipsum dolor sit amet, consectetur adipiscing elit");

        UserValidator validator = new UserValidator(user);
        assertFalse(validator.isValid());
    }

    @Test
    public void testUserValidator_invalidFieldValues() {
        user.setName("Lorem ipsum dolor sit amet");
        user.setAge(0);

        UserValidator validator = new UserValidator(user);
        assertFalse(validator.isValid());
        assertFalse(validator.isValid(UserValidator.NAME));
        assertEquals("The name is too long, the max value is 15.", validator.getErrorFor(UserValidator.NAME));
        assertFalse(validator.isValid(UserValidator.AGE));
        assertEquals("The age must be greater that 18 years old.", validator.getErrorFor(UserValidator.AGE));
    }

}
