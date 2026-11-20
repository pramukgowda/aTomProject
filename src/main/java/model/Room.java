package model;

import java.math.BigDecimal;
import java.util.Objects;

public final class Room {
    private final int number;
    private final String type;
    private final BigDecimal ratePerNight;
    private boolean occupied;
    public Room(int number, String type, BigDecimal ratePerNight) {
        if (number <= 0) throw new IllegalArgumentException("Room number must be positive.");
        if (type == null || type.isBlank()) throw new IllegalArgumentException("Room type is required.");
        this.number = number;
        this.type = type.trim();
        this.ratePerNight = Objects.requireNonNull(ratePerNight, "ratePerNight");
        if (ratePerNight.signum() < 0) throw new IllegalArgumentException("Room rate cannot be negative.");
    }
    public Room(int number, String type, double ratePerNight) {
        this(number, type, BigDecimal.valueOf(ratePerNight));
    }
    public int getNumber() { return number; }
    public String getType() { return type; }
    public BigDecimal getRatePerNight() { return ratePerNight; }
    public boolean isOccupied() { return occupied; }
    public void setOccupied(boolean occupied) { this.occupied = occupied; }
}
