package model;

public final class Guest {
    private final String name;
    private final String phone;

    public Guest(String name, String phone) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Guest name is required.");
        if (phone == null || phone.isBlank()) throw new IllegalArgumentException("Phone number is required.");
        String digits = phone.replaceAll("\\D", "");
        if (digits.length() < 7 || digits.length() > 15) throw new IllegalArgumentException("Phone number must contain 7 to 15 digits.");
        this.name = name.trim();
        this.phone = phone.trim();
    }
    public String getName() { return name; }
    public String getPhone() { return phone; }
}
