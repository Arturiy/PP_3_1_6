package org.kata.yreya;

public class User {

    private Long id;
    private String name;

    private String lastName;
    private Byte age;

    public User() {

    }

    public User(int id, String name, String surname, byte age) {
        this.id = (long) id;
        this.name = name;
        this.lastName = surname;
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Byte getAge() {
        return age;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }
}