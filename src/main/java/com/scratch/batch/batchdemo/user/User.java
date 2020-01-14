package com.scratch.batch.batchdemo.user;

public class User {
    private String firstName;
    private String lastName;
    private int age;
    private String birthMonth;

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public User setBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", birthMonth='" + birthMonth + '\'' +
                '}';
    }
}
