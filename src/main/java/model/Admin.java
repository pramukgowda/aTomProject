package model;

/** Represents an administrator using the console application. */
public final class Admin extends User {
    public Admin(String username) {
        super(username);
    }

    @Override
    public String getRole() {
        return "Admin";
    }
}
