package com.example.demo.DTO;

public class AdminDTO {
    private Long id;
    private String username;
    private String password;

    public AdminDTO() {}

    public AdminDTO(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    //getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //to string

    @Override
    public String toString() {
        return "AdminDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
