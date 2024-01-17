package org.example.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String passport;

    private String password;

    public Long getId() {
        return id;
    }

    public UserAccount setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserAccount setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassport() {
        return passport;
    }

    public UserAccount setPassport(String passport) {
        this.passport = passport;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserAccount setPassword(String password) {
        this.password = password;
        return this;
    }
}
