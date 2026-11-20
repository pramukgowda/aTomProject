package model;

import java.math.BigDecimal;
import java.util.Objects;

public final class Booking {
    public enum Status { WAITING, ALLOCATED, CHECKED_IN, CHECKED_OUT, CANCELLED }
    private final int bookingId;
    private final Guest guest;
    private final String roomType;
    private final int plannedNights;
    private Room room;
    private Status status;
    private BigDecimal totalBill = BigDecimal.ZERO.setScale(2);

    public Booking(int bookingId, Guest guest, String roomType, int plannedNights) {
        if (bookingId <= 0) throw new IllegalArgumentException("Booking ID must be positive.");
        this.guest = Objects.requireNonNull(guest, "guest");
        if (roomType == null || roomType.isBlank()) throw new IllegalArgumentException("Room type is required.");
        if (plannedNights <= 0) throw new IllegalArgumentException("Planned nights must be at least 1.");
        this.bookingId = bookingId;
        this.roomType = roomType.trim();
        this.plannedNights = plannedNights;
        this.status = Status.WAITING;
    }
    public void assignRoom(Room room) {
        Objects.requireNonNull(room, "room");
        if (status != Status.WAITING) throw new IllegalStateException("Only waiting bookings can receive a room.");
        if (!roomType.equalsIgnoreCase(room.getType())) throw new IllegalArgumentException("Room type mismatch.");
        if (!room.isOccupied()) throw new IllegalStateException("Room must be occupied before assignment.");
        this.room = room;
        this.status = Status.ALLOCATED;
    }
    public void checkIn() {
        if (status != Status.ALLOCATED) throw new IllegalStateException("Only allocated bookings can check in.");
        status = Status.CHECKED_IN;
    }
    public void checkOut(BigDecimal bill) {
        if (status != Status.CHECKED_IN) throw new IllegalStateException("Only checked-in bookings can check out.");
        Objects.requireNonNull(bill, "bill");
        if (bill.signum() < 0) throw new IllegalArgumentException("Bill cannot be negative.");
        totalBill = bill.setScale(2, java.math.RoundingMode.HALF_UP);
        status = Status.CHECKED_OUT;
    }
    public void cancel() {
        if (status != Status.WAITING && status != Status.ALLOCATED) throw new IllegalStateException("Only waiting or allocated bookings can be cancelled.");
        status = Status.CANCELLED;
    }
    public int getBookingId() { return bookingId; }
    public Guest getGuest() { return guest; }
    public String getRoomType() { return roomType; }
    public int getPlannedNights() { return plannedNights; }
    public Room getRoom() { return room; }
    public Status getStatus() { return status; }
    public BigDecimal getTotalBill() { return totalBill; }
}
