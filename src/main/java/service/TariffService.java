package service;
import model.Booking;import java.math.BigDecimal;import java.math.RoundingMode;
/** Decimal-safe tariff calculation. */
public class TariffService{
 public static final BigDecimal GST_RATE=new BigDecimal("0.12");public static final int DISCOUNT_NIGHTS=5;public static final BigDecimal DISCOUNT_RATE=new BigDecimal("0.10");
 public BigDecimal baseAmount(BigDecimal rate,int nights){positive(nights);return money(rate.multiply(BigDecimal.valueOf(nights)));}
 public BigDecimal discount(BigDecimal base,int nights){positive(nights);return nights>=DISCOUNT_NIGHTS?money(base.multiply(DISCOUNT_RATE)):money(BigDecimal.ZERO);}
 public BigDecimal gst(BigDecimal taxable){return money(taxable.multiply(GST_RATE));}
 public BigDecimal generateBill(Booking b,int nights){if(b==null||b.getRoom()==null)throw new IllegalArgumentException("Booking has no assigned room.");positive(nights);BigDecimal base=baseAmount(b.getRoom().getRatePerNight(),nights);BigDecimal off=discount(base,nights);BigDecimal tax=gst(base.subtract(off));BigDecimal total=money(base.subtract(off).add(tax));System.out.println("\n============ BILL ============");System.out.println("Booking ID   : "+b.getBookingId());System.out.println("Guest        : "+b.getGuest().getName());System.out.println("Room         : "+b.getRoom().getNumber()+" ("+b.getRoom().getType()+")");System.out.printf("Nights       : %d x Rs.%s%n",nights,money(b.getRoom().getRatePerNight()));System.out.println("Base Amount  : Rs."+base);System.out.println("Discount     : Rs."+off);System.out.println("GST (12%)    : Rs."+tax);System.out.println("TOTAL        : Rs."+total);System.out.println("==============================");return total;}
 private static BigDecimal money(BigDecimal b){return b.setScale(2,RoundingMode.HALF_UP);}private static void positive(int n){if(n<=0)throw new IllegalArgumentException("Nights must be at least 1.");}
}
