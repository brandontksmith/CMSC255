//////////////////////////////////////////////////////////////////////////////////////////////
// CMSC-255 Fall 2014
//
// Name: Brandon Smith
// Lab Section number: 2
// (Project/Example): Project-2
//
// Description: This program will determine the date of Easter for the inputted year using an 
// algorithm invented by the mathematician Carl Friedrich Gauss in 1800.
//
/////////////////////////////////////////////////////////////////////////////////////////////

package computedateofeaster;

import java.util.Scanner;
import java.text.DateFormatSymbols;

public class ComputeDateOfEaster {
    
    private static Scanner scanner;
    private static int year, month, day;
    
    public static void main(String[] args) {
        
        scanner = new Scanner(System.in);
        year = 0; month = 0; day = 0;
        
        System.out.print("Please enter the year: ");
        
        try
        {
            // get the year from the console
            year = scanner.nextInt();
        }
        
        // if invalid value was entered
        catch (Exception e)
        {
            System.out.println("Invalid year entered: " + e);
            System.exit(0);
        }
        
        // divide Y by 19 and call the remainder a. Ignore the quotient
        int a = year % 19;
        
        // divide Y by 100 to get the quotient b and a remainder c
        int b = year / 100;        
        int c = year % 100;
        
        // divide b by 4 to get a quotient d and a remainder e
        int d = b / 4;
        int e = b % 4;
        
        // divide 8 * b + 13 by 25 to get a quotient g. Ignore the remainder
        int g = (8 * b + 13) / 25;
        
        // divide 19 * a + b – d – g + 15 by 30 to get a remainder h. Ignore the quotient
        int h = (19 * a + b - d - g + 15) % 30;
        
        // divide c by 4 to get a quotient j and a remainder k
        int j = c / 4;
        int k = c % 4;
        
        // divide a + 11 * h by 319 to get a quotient m. Ignore the remainder
        int m = (a + 11 * h) / 319;
        
        // divide 2 * e + 2 * j – k – h + m + 32 by 7 to get a remainder r. Ignore the quotient
        int r = (2 * e + 2 * j - k - h + m + 32) % 7;
        
        // this is the integer of the number of the month easter sunday falls on
        // divide h – m + r + 90 by 25 to get a quotient n. Ignore the remainder
        int month = (h - m + r + 90) / 25;
        
        // this is the integer of the number of the day easter sunday falls on
        // divide h – m + r + n + 19 by 32 to get a remainder p. Ignore the quotient
        int day = (h - m + r + month + 19) % 32;
        
        System.out.println("Sunday " + getMonthName(month) + " " + day + " is the date of Easter.");
    }
    
    /**
     * Returns the corresponding name of the month
     * 
     * @param month the integer of the month (1-12)
     * @return a String of the month name
     */
    public static String getMonthName(int month) {
        
        return new DateFormatSymbols().getMonths()[month - 1];
    }
}