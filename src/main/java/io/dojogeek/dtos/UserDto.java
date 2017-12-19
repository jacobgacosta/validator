package io.dojogeek.dtos;


import java.util.Date;
import java.util.Objects;

/**
 * Created by jgacosta on 6/01/17.
 */
public class UserDto {

    private String name;
    private int age;
    private Date birthDate;
    private String ocupation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getOcupation() {
        return ocupation;
    }

    public void setOcupation(String ocupation) {
        this.ocupation = ocupation;
    }

    public Object print(Objects o) {

        return o.getClass().getClass();

    }
}
