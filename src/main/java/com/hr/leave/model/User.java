package com.hr.leave.model;

public class User {
    private final String username, password, name, role; // role: EMPLOYEE or HR

    public User(String username, String password, String name, String role) {
        this.username = username; this.password = password; this.name = name; this.role = role;
    }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getName()     { return name; }
    public String getRole()     { return role; }
    public boolean isManager()  { return "HR".equals(role); }
}
