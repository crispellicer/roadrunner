package com.svalero.roadrunner.domain;

public class User {

    private long id;
    private String name;
    private String surname;
    private String telephone;
    private String birthDate;

    public User(long id, String name, String surname, String telephone, String birthDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.birthDate = birthDate;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
