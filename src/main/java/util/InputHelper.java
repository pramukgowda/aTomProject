package util;
import java.util.Scanner;
/** Validated console input helpers. */
public final class InputHelper{
 private InputHelper(){}
 public static int readInt(Scanner sc,String prompt){while(true){System.out.print(prompt);try{return Integer.parseInt(sc.nextLine().trim());}catch(NumberFormatException e){System.out.println("Please enter a valid whole number.");}}}
 public static int readPositiveInt(Scanner sc,String prompt){while(true){int n=readInt(sc,prompt);if(n>0)return n;System.out.println("Value must be at least 1.");}}
 public static String readString(Scanner sc,String prompt){while(true){System.out.print(prompt);String s=sc.nextLine().trim();if(!s.isEmpty())return s;System.out.println("This field cannot be empty.");}}
}
