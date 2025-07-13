package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin  {

    @Id
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String userName;

    @Column(length = 48, nullable = false)
    private String password;

    public Admin(Long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public Admin() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
