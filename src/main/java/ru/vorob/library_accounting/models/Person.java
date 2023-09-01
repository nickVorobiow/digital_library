package ru.vorob.library_accounting.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


public class Person {
    private int id;

    @NotEmpty(message = "Заполните поле, пожалуйста")
    private String fullName;

    @Min(value = 1900, message = "Введите корректное значение")
    private int yearOfBirth;


    public Person() {
    }

    public Person(String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
