package com.example.newdatabase;

public class Module {
    private String username;
    private String email;
    private String password;

    public Module(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Module() {
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String setUsername(String username) {
        this.username = username;
        return username;
    }

    public String setEmail(String email) {
        this.email = email;
        return email;
    }

    public String setPassword(String password) {
        this.password = password;
        return password;
    }
}
