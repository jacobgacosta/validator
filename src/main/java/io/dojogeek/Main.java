package io.dojogeek;

import io.dojogeek.dtos.DtoUser;
import io.dojogeek.utils.RegexValues;
import io.dojogeek.validator.RegexValidator;
import io.dojogeek.validator.RequiredValidator;
import io.dojogeek.validator.UserValidator;

import java.util.Date;

/**
 * Created by jgacosta on 6/01/17.
 */
public class Main {


    public static void main(String[] args) {

        User user = new User();

        System.out.println("************Incorrect User***********");
        user.createUser(getDtoUserWithIncorrectAge());
//        user.createUser(getDtoUserWithEmptyName());
//        user.createUser(getDtoUserWithTooLongOcupation());

        System.out.println("************Correct User***********");
        user.update(getCorrectDtoUser());
//        user.createUser(getCorrectDtoUser());

    }

    public static DtoUser getCorrectDtoUser() {

        DtoUser dtoUser = new DtoUser();
        dtoUser.setName("Jacob");
        dtoUser.setAge(28);
        dtoUser.setOcupation("Programmer");
        dtoUser.setBirthDate(new Date());

        return dtoUser;
    }

    public static DtoUser getDtoUserWithIncorrectAge() {

        return createDtoUser("Jacob", 18, null, new Date());
    }

    public static DtoUser getDtoUserWithEmptyName() {

        return createDtoUser("", 16, "Programmer", new Date());
    }

    public static DtoUser getDtoUserWithTooLongOcupation() {

        return createDtoUser("Jacob", 16, "Super Programmer", new Date());
    }

    private static DtoUser createDtoUser(String name, int age, String ocupation, Date birthDate) {

        DtoUser dtoUser = new DtoUser();
        dtoUser.setName(name);
        dtoUser.setAge(age);
        dtoUser.setOcupation(ocupation);
        dtoUser.setBirthDate(birthDate);

        return dtoUser;
    }
}

class User {

    public void createUser(DtoUser user) {

        UserValidator userValidator = new UserValidator(user);

        boolean isValidUser = userValidator.validate();

        System.out.println("The user is: " + isValidUser);
        System.out.println(userValidator.getErrorMessage());
    }

    public void update(DtoUser user) {

        UserValidator userValidator = new UserValidator(user);

        boolean isValidUser = userValidator.validate();

        System.out.println("The user is: " + isValidUser);
        System.out.println(userValidator.getErrorMessage());
    }

}

