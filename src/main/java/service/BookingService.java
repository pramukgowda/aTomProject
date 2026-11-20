package service;
import exceptions.BookingNotFoundException;import model.Booking;import model.Guest;import java.math.BigDecimal;import java.util.ArrayList;import java.util.Collections;import java.util.List;
/** Booking lifecycle, lookup and revenue management. */
public class BookingService{
 private final List<Booking> bookings=new ArrayList<>();private final TariffService tariffService;private int nextId=1;
 public BookingService(TariffService t){if(t==null)throw new IllegalArgumentException("Tariff service is required.");tariffService=t;}
 public Booking createBooking(Guest g,String type,int nights){if(g==null)throw new IllegalArgumentException("Guest details are required.");if(type==null||type.isBlank())throw new IllegalArgumentException("Room type is required.");if(nights<=0)throw new IllegalArgumentException("Planned nights must be at least 1.");Booking b=new Booking(nextId++,g,type,nights);bookings.add(b);return b;}
 public Booking findById(int id)throws BookingNotFoundException{for(Booking b:bookings)if(b.getBookingId()==id)return b;throw new BookingNotFoundException("Booking #"+id+" not found.");}
 public void checkIn(int id)throws BookingNotFoundException{Booking b=findById(id);try{b.checkIn();System.out.println("Check-in done. "+b.getGuest().getName()+" is now in Room "+b.getRoom().getNumber()+".");}catch(IllegalStateException e){System.out.println("Cannot check in: "+e.getMessage());}}
 public Booking checkOut(int id,int nights)throws BookingNotFoundException{if(nights<=0)throw new IllegalArgumentException("Nights must be at least 1.");Booking b=findById(id);if(b.getStatus()!=Booking.Status.CHECKED_IN){System.out.println("Only checked-in guests can check out. Status is "+b.getStatus()+".");return null;}BigDecimal total=tariffService.generateBill(b,nights);b.checkOut(total);b.getRoom().setOccupied(false);return b;}
 public int countByStatus(Booking.Status s){int n=0;for(Booking b:bookings)if(b.getStatus()==s)n++;return n;}
 public BigDecimal getTotalRevenue(){BigDecimal sum=BigDecimal.ZERO.setScale(2);for(Booking b:bookings)if(b.getStatus()==Booking.Status.CHECKED_OUT)sum=sum.add(b.getTotalBill());return sum;}
 public List<Booking> getBookings(){return Collections.unmodifiableList(bookings);}
 public void viewBookings(){if(bookings.isEmpty()){System.out.println("No bookings yet.");return;}System.out.printf("\n%-5s %-18s %-13s %-7s %-14s%n","ID","Guest","Type","Room","Status");System.out.println("---------------------------------------------------------------");for(Booking b:bookings){String room=b.getRoom()==null?"-":String.valueOf(b.getRoom().getNumber());System.out.printf("%-5d %-18.18s %-13s %-7s %-14s%n",b.getBookingId(),b.getGuest().getName(),b.getRoomType(),room,b.getStatus());}}
}
