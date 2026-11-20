package datastructures;

import model.Booking;
import java.util.ArrayList;
import java.util.List;

public class WaitingListQueue {
    private static final class Node {
        private final Booking data;
        private Node next;
        private Node(Booking data) { this.data = data; }
    }
    private Node front;
    private Node rear;
    private int size;

    public void enqueue(Booking booking) {
        if (booking == null) throw new IllegalArgumentException("Booking cannot be null.");
        Node n = new Node(booking);
        if (rear == null) front = rear = n;
        else { rear.next = n; rear = n; }
        size++;
    }
    public void enqueueFirst(Booking booking) {
        if (booking == null) throw new IllegalArgumentException("Booking cannot be null.");
        Node n = new Node(booking);
        n.next = front;
        front = n;
        if (rear == null) rear = n;
        size++;
    }
    public Booking removeFirstOfType(String type) {
        Node prev = null, cur = front;
        while (cur != null) {
            if (cur.data.getRoomType().equalsIgnoreCase(type)) { unlink(prev, cur); return cur.data; }
            prev = cur; cur = cur.next;
        }
        return null;
    }
    public Booking removeByBookingId(int id) {
        Node prev = null, cur = front;
        while (cur != null) {
            if (cur.data.getBookingId() == id) { unlink(prev, cur); return cur.data; }
            prev = cur; cur = cur.next;
        }
        return null;
    }
    private void unlink(Node prev, Node cur) {
        if (prev == null) front = cur.next; else prev.next = cur.next;
        if (cur == rear) rear = prev;
        if (--size == 0) front = rear = null;
    }
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public List<Booking> snapshot() {
        List<Booking> result = new ArrayList<>();
        for (Node n = front; n != null; n = n.next) result.add(n.data);
        return result;
    }
    public void display() {
        if (isEmpty()) { System.out.println("Waiting list is empty."); return; }
        System.out.println("\nWaiting List (first in line at top):");
        int position = 1;
        for (Node n = front; n != null; n = n.next) {
            Booking b = n.data;
            System.out.println(position++ + ". Booking #" + b.getBookingId() + " - " + b.getGuest().getName() + " wants " + b.getRoomType());
        }
    }
}
