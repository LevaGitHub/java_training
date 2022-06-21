package ru.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mantis_user_table")

public class UserData {
    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="username")
    private String userName;

    @Column(name="email")
    private String email;

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void withId(Integer id) {
        this.id = id;
    }

    public void withUserName(String userName) {
        this.userName = userName;
    }

    public void withEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
