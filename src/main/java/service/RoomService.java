package service;

import exceptions.InvalidRoomException;
import exceptions.RoomNotAvailableException;
import model.Room;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomService {
    public static final String[] ROOM_TYPES = {"Single", "Double", "Suite"};
    private final List<Room> rooms = new ArrayList<>();
    public RoomService() {
        for (int i = 101; i <= 104; i++) rooms.add(new Room(i, "Single", new BigDecimal("1500.00")));
        for (int i = 201; i <= 204; i++) rooms.add(new Room(i, "Double", new BigDecimal("2500.00")));
        for (int i = 301; i <= 302; i++) rooms.add(new Room(i, "Suite", new BigDecimal("5000.00")));
    }
    public List<Room> getRooms() { return Collections.unmodifiableList(rooms); }
    public String validateType(String type) throws InvalidRoomException {
        if (type != null) for (String known : ROOM_TYPES) if (known.equalsIgnoreCase(type.trim())) return known;
        throw new InvalidRoomException("Invalid room type '" + type + "'. Choose Single, Double or Suite.");
    }
    public Room allocateRoom(String type) throws RoomNotAvailableException {
        for (Room room : rooms) if (!room.isOccupied() && room.getType().equalsIgnoreCase(type)) {
            room.setOccupied(true); return room;
        }
        throw new RoomNotAvailableException("No vacant " + type + " room available.");
    }
    public Room findByNumber(int number) { for (Room r : rooms) if (r.getNumber() == number) return r; return null; }
    public void releaseRoom(Room room) {
        if (room == null || !rooms.contains(room)) throw new IllegalArgumentException("Room does not belong to this hotel.");
        if (!room.isOccupied()) throw new IllegalStateException("Room is already vacant.");
        room.setOccupied(false);
    }
    public void viewRooms() {
        System.out.println("\nRoom  Type     Rate/Night  Status");
        System.out.println("-----------------------------------");
        for (Room r : rooms) System.out.printf("%-5d %-8s Rs.%-8s %s%n", r.getNumber(), r.getType(),
                r.getRatePerNight().setScale(2), r.isOccupied() ? "Occupied" : "Vacant");
    }
}
