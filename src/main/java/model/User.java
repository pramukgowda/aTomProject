package model;

public class User {
    protected final String username;
    public User(String username) {
        if (username == null || username.isBlank()) throw new IllegalArgumentException("Username is required.");
        this.username = username.trim();
    }
    public String getUsername() { return username; }
    public String getRole() { return "Guest"; }
}
