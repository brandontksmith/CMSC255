//////////////////////////////////////////////////////////////////////////////////////////////
// CMSC-255 Fall 2014
//
// Name: Brandon Smith
// Lab Section number: 2
// (Project/Example): Project-3
//
// Description: Calculates the maximum height, total distance traveled, flight time, and the
// trajectory of a projectile fired with an initial angle of elevation, initial speed, and
// gravity constant.
//
/////////////////////////////////////////////////////////////////////////////////////////////

package calculateprojectilemotion;

import java.util.Scanner;

public class CalculateProjectileMotion {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        double initialAngleOfElevation = 0;
        double angleOfElevationRadians = 0;
        double initialSpeed = 0;
        double gravityConstant = 0;
        
        System.out.print("Enter an initial angle of elevation: ");
        
        try
        {            
            // get the initial angle of elevation from the console
            initialAngleOfElevation = scanner.nextDouble();
        }
        
        // if invalid value was entered
        catch (Exception e)
        {
            System.out.println("Invalid angle of elevation entered: " + e);
            System.exit(0);
        }
        
        System.out.print("Enter the initial speed: ");
        
        try
        {            
            // get the initial speed from the console
            initialSpeed = scanner.nextDouble();
        }
        
        // if invalid value was entered
        catch (Exception e)
        {
            System.out.println("Invalid initial speed entered: " + e);
            System.exit(0);
        }
        
        System.out.print("Enter the gravity constant: ");
        
        try
        {            
            // get the unit from the console
            gravityConstant = scanner.nextDouble();
        }
        
        // if invalid value was entered
        catch (Exception e)
        {
            System.out.println("Invalid gravity constant entered: " + e);
            System.exit(0);
        }
        
        // converts initialAngleOfElevation from degrees to radians
        angleOfElevationRadians = Math.toRadians(initialAngleOfElevation);
        
        System.out.println();
        System.out.println("Summary Information");
        System.out.println();
        
        // gets the maximum height of the projectile
        double maxHeight = getMaxHeight(initialSpeed, angleOfElevationRadians, gravityConstant);
        System.out.printf("Maximum height: %.2f \n", maxHeight);
        
        // gets the total flight time of the projectile
        double flightTime = getTotalFlightTime(initialSpeed, angleOfElevationRadians, gravityConstant);
        System.out.printf("Flight time: %.2f \n", flightTime);
        
        // gets the total distance traveled by the projectile
        int range = getRange(initialSpeed, angleOfElevationRadians, gravityConstant);
        System.out.println("Range: " + range + "\n");
        
        System.out.println();
        System.out.println("Trajectory Values");
        System.out.println();
        System.out.println("Time in Seconds \t x-coordinate \t\t y-coordinate");
        
        int seconds = 0;
        double y = 0;
        
        // loops until y is less then or equal 0, which is
        // the point at which the projectile hits the ground
        // expression is evaluated at the bottom of the loop
        do
        {
            double x = 0;
            
            if (seconds > 0)
            {
                // gets the x coordinate of the projectile at time=seconds
                x = getXCoordinate(initialSpeed, angleOfElevationRadians, seconds);
                
                // gets the y coordinate of the projectile at time=seconds
                y = getYCoordinate(initialSpeed, angleOfElevationRadians, seconds, gravityConstant);
                
                System.out.printf("%d \t\t\t %.2f \t\t %.2f \n", seconds, x, y);
            }
            else
            {
                System.out.println("0 \t\t\t 0.00 \t\t\t 0.00");
            }
            
            seconds += 10;
        } while (y >= 0);
    }
    
    /**
     * Calculates the x-coordinate for position of the projectile based on the initial
     * velocity, initial angle of elevation, and the time in seconds.
     * 
     * @param initialSpeed The speed of the projectile in m/s or ft/s
     * @param angleOfElevationRadians The initial angle of elevation in radians
     * @param seconds The time in seconds of the projectile
     * 
     * @return The x-coordinate of the position of the projectile at time=seconds
     */
    public static double getXCoordinate(double initialSpeed, double angleOfElevationRadians, int seconds) {
        
        double x = (initialSpeed * Math.cos(angleOfElevationRadians)) * seconds;
        
        return x;
    }
    
    /**
     * Calculates the y-coordinate for position of the projectile based on the initial velocity,
     * initial angle of elevation, time in seconds, and the gravity constant, g.

     * @param initialSpeed The speed of the projectile in m/s or ft/s
     * @param angleOfElevationRadians The initial angle of elevation in radians
     * @param seconds The time in seconds of the projectile
     * @param gravityConstant The gravity constant for acceleration.
     * 
     * @return The y-coordinate of the position of the projectile at time=seconds
     */
    public static double getYCoordinate(double initialSpeed, double angleOfElevationRadians, int seconds, double gravityConstant) {
        
        double y = (initialSpeed * Math.sin(angleOfElevationRadians) * seconds) - 0.5 * gravityConstant * Math.pow(seconds, 2);
        
        return y;
    }
    
    /**
     * Calculates the maximum height of the projectile.
     * 
     * @param initialSpeed The speed of the projectile in m/s or ft/s
     * @param angleOfElevationRadians The initial angle of elevation in radians
     * @param gravityConstant The gravity constant for acceleration.
     * 
     * @return The maximum height of the projectile
     */
    public static double getMaxHeight(double initialSpeed, double angleOfElevationRadians, double gravityConstant) {
                
        double maxHeight = Math.pow(initialSpeed * Math.sin(angleOfElevationRadians), 2.0) / (2.0 * gravityConstant);
        
        return maxHeight;
    }
    
    /**
     * Calculates the total flight time of the projectile.
     * 
     * @param initialSpeed The speed of the projectile in m/s or ft/s.
     * @param angleOfElevationRadians The initial angle of elevation in radians.
     * @param gravityConstant The gravity constant for acceleration.
     * 
     * @return The total flight time of the projectile.
     */
    public static double getTotalFlightTime(double initialSpeed, double angleOfElevationRadians, double gravityConstant) {
                
        double totalFlightTime = (2.0 * initialSpeed * Math.sin(angleOfElevationRadians)) / gravityConstant;
        
        return totalFlightTime;
    }
    
    /**
     * Calculates the distance traveled by the projectile.
     * 
     * @param initialSpeed The speed of the projectile in m/s or ft/s.
     * @param angleOfElevationRadians The initial angle of elevation in radians.
     * @param gravityConstant The gravity constant for acceleration.
     * 
     * @return The range of the projectile.
     */
    public static int getRange(double initialSpeed, double angleOfElevationRadians, double gravityConstant) {
                
        double range = Math.pow(initialSpeed, 2.0) / gravityConstant * Math.sin(2.0 * angleOfElevationRadians);
        
        return (int) range;
    }
}