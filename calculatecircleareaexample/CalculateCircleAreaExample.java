//////////////////////////////////////////////////////////////////////////////////////////////
// CMSC-255 Fall 2014
//
// Name: Brandon Smith
// Lab Section number: 2
// (Project/Example): Project-1
//
// Description: This program will calculate the area of a circle with an inputed 
// radius. 
// This program will be entered compiled and run by the student. 
//
//
/////////////////////////////////////////////////////////////////////////////////////////////

package calculatecircleareaexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalculateCircleAreaExample {
    public static void main(String[] args) {
        
        int radius = 0;
        
        System.out.print("Please enter radius of a circle:");
        
        try
        {
            //get the radius from console
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            radius = Integer.parseInt(br.readLine());
        }
        
        //if invalid value was entered
        catch(NumberFormatException ne)
        {
            System.out.println("Invalid radius value" + ne);
            System.exit(0);
        }
        catch(IOException ioe)
        {
            System.out.println("IO Error :" + ioe);
            System.exit(0);
        }

        /*
        * Area of a circle is
        * pi * r * r
        * where r is a radius of a circle.
        */

        //NOTE : use Math.PI constant to get value of pi
        double area = Math.PI * radius * radius;

        System.out.println("Area of a circle is " + area);
    }
}