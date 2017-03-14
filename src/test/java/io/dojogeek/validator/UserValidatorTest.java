package io.dojogeek.validator;

import io.dojogeek.dtos.DtoUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by jgacosta on 1/28/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserValidatorTest {

    @Test
    public void testUserValidator_correctDtoUser() {

        DtoUser dtoUser = createDtoUser(28, new Date(), "Jacob", "Programmer");

        UserValidator userValidator = new UserValidator(dtoUser);
        boolean isValidUser = userValidator.validate();

        assertTrue(isValidUser);

    }

    @Test
    public void testUserValidator_withName_too_long() {

        DtoUser dtoUser = createDtoUser(28, new Date(), "INCENT LUC MICHEL JULES", "Programmer");

        UserValidator userValidator = new UserValidator(dtoUser);
        boolean isValidUser = userValidator.validate();

        assertFalse(isValidUser);
        assertEquals("The name is too long", userValidator.getErrorMessage());

    }

    private DtoUser createDtoUser(int age, Date birthDate, String name, String ocupation) {

        DtoUser user = new DtoUser();
        user.setAge(age);
        user.setBirthDate(birthDate);
        user.setName(name);
        user.setOcupation(ocupation);

        return user;
    }
}
