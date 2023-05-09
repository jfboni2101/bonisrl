package com.example.bonisrl;

import java.time.LocalDate;
import java.util.Objects;

public class Person {
    String lastName;
    String firstName;
    LocalDate birthday;

    public Person(String lastName, String firstName, LocalDate birthday) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
    }

    public Person(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = LocalDate.now();
    }

    public Person() {
        this.lastName = "NULL";
        this.firstName = "NULL";
        this.birthday = LocalDate.now();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Person person = (Person) o;
        return Objects.equals(lastName, person.lastName) && Objects.equals(firstName, person.firstName) && Objects.equals(birthday, person.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, birthday);
    }

    @Override
    public String toString() {
        return "Person{" + "lastName='" + lastName + '\'' + ", firstName='" + firstName + '\'' + ", birthday=" + birthday + '}';
    }
}
