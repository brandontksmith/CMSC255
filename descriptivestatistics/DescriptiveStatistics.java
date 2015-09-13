//////////////////////////////////////////////////////////////////////////////////////////////
// CMSC-255 Fall 2014
//
// Name: Brandon Smith
// Lab Section number: 2
// (Project/Example): Project-5
//
// Description: This program accepts a list of numbers and generates and prints out certain
// descriptive statistics, including the sample mean, mean, first quartile, third quartile,
// mode, sample variance, sample standard deviation, and the range.
//
/////////////////////////////////////////////////////////////////////////////////////////////

package descriptivestatistics;

import java.util.HashMap;
import java.util.Scanner;

public class DescriptiveStatistics {
    
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int[] numbers;
        String numbersStr;
        int numbersQuantity = 0;
        int listInputMethod = 0;
        
        System.out.print("Enter the amount of numbers you want to give: ");        
        
        try {
            
            // get number of entries from console
            numbersQuantity = input.nextInt();
            input.nextLine();
            
        // runs if invalid valid is entered
        } catch (Exception e) {
            
            System.out.println("Invalid entry for number of entries:" + e);
            System.exit(0);
        }
        
        numbers = new int[numbersQuantity];
        
        System.out.println();
        System.out.println("How would you like to enter your list?");
        System.out.println("[1] = Individually (one at a time)");
        System.out.println("[2] = Once (all in one line)");
        System.out.println();
        System.out.print("Choose option 1 or 2: ");
        
        try {
            
            // get the option for entering the list
            listInputMethod = input.nextInt();
            input.nextLine();
            
            if (listInputMethod != 1 && listInputMethod != 2) {
                
                System.out.println("ERROR: INVALID OPTION ENTERED. EXITING.");
                System.exit(0);
            }
        
        // runs if invalid valid is entered
        } catch (Exception e) {
            
            System.out.println("Invalid value entered: " + e);
            System.exit(0);
        }
        
       System.out.println();
        
        // used to get numbers line by line (individually entered)
        if (listInputMethod == 1) {
            
            // loop to get the list of numbers
            for (int i = 0; i < numbersQuantity; i++) {

                System.out.print("Enter a number (or q to quit): ");
                String value = input.next();
                input.nextLine();

                // if the sentinel value 'q' has been entered 
                if (value.charAt(0) == 'q') {
                    
                    System.exit(0);
                }

                numbers[i] = Integer.parseInt(value);
            }
        }
        
        // used to get numbers all in one line (sequence of numbers that are space separated)
        if (listInputMethod == 2) {
            
            System.out.print("Enter the list of numbers (space separated): ");
            numbersStr = input.nextLine();
            
            Scanner inputReader = new Scanner(numbersStr);
            
            int index = 0;
            
            while (inputReader.hasNextInt()) {
                
                numbers[index] = inputReader.nextInt();
                index++;
            }
        }
        
        double mean = calculateMean(numbers);
        double median = calculateMedian(numbers);
        double firstQuartile = calculateQuartile(numbers, 1);
        double thirdQuartile = calculateQuartile(numbers, 3);
        double mode = calculateMode(numbers);
        double variance = calculateVariance(numbers);
        double standardDeviation = calculateStandardDeviation(numbers);
        double range = calculateRange(numbers);
        
        System.out.println();
        System.out.println("Descriptive Statistics of the Number of Tornadoes in the U.S.\n");
        System.out.printf("%8s \t %8s \t %8s \t %8s \n", "Mean", "Median", "Range", "Mode");
        System.out.printf("%8s \t %8s \t %8s \t %8s \n", "--------", "--------", "--------", "--------");
        System.out.printf("%8.2f \t %8.2f \t %8.2f \t %8.2f \n\n", mean, median, range, mode);
        
        System.out.printf("%15s \t %15s\n", "First Quartile", "Third Quartile");
        System.out.printf("%15s \t %15s\n", "---------------", "---------------");
        System.out.printf("%15.2f \t %15.2f\n\n", firstQuartile, thirdQuartile);
        
        System.out.printf("%12s \t %18s\n", "Variance", "Standard Deviation");
        System.out.printf("%12s \t %18s\n", "------------", "------------------");
        System.out.printf("%12.2f \t %18.2f\n\n", variance, standardDeviation);
    }
    
    /**
     * Sorts an array of numbers in ascending order using bubble sort.
     * 
     * @param numbers array of numbers
     * @return array of numbers in ascending order
     */
    public static int[] bubbleSort(int[] numbers) {
        
        int[] sortedNumbers = numbers.clone();
        
        int i;
        boolean flag;
        int temp;
        
        do {
            
            // set flag to false to see if there are any possible swaps
            flag = false;
            
            for (i = 0; i < sortedNumbers.length - 1; i++) {
                
                // sorts in ascending order
                if (sortedNumbers[i] > sortedNumbers[i + 1]) {
                    
                    // swap the elements, i and i + 1
                    temp = sortedNumbers[i];
                    sortedNumbers[i] = sortedNumbers[i + 1];
                    sortedNumbers[i + 1] = temp;
                    
                    // swap occured
                    flag = true;
                }
            }
        } while (flag);
        
        return sortedNumbers;
    }
    
    /**
     * Calculates the mean, or the average, of the number set.
     * 
     * @param numbers number set
     * @return mean of the numbers
     */
    public static double calculateMean(int[] numbers) {
        
        double sum = 0;
        double mean;
        
        // if numbers is empty
        if (numbers.length == 0) {
            
            return 0;
        }
        
        // if numbers contains only 1 number
        if (numbers.length == 1) {
            
            return numbers[0];
        }
        
        // comutes the sum of the numbers
        for (int i = 0; i < numbers.length; i++) {
            
            sum += numbers[i];
        }
        
        // the average of the numbers
        // equals sum of numbers divided by amount of numbers
        mean = sum / numbers.length;
        
        return mean;
    }
    
    /**
     * Calculates the median, or middle value, of the number set.
     * 
     * @param numbers number set
     * @return the median of the numbers
     */
    public static double calculateMedian(int[] numbers) {
        
        int[] sortedNumbers = bubbleSort(numbers);
        
        // if numbers is empty
        if (numbers.length == 0) {
            
            return 0;
        }
        
        // if numbers contains only 1 number
        if (numbers.length == 1) {
            
            return numbers[0];
        }
        
        int middleIndex = sortedNumbers.length / 2;
        double middleValue;
        
        // odd amount of numbers
        if (sortedNumbers.length % 2 == 1) {
            
            middleValue = sortedNumbers[middleIndex];
        }
        
        // even amount of numbers
        else {
            
            middleValue = (sortedNumbers[middleIndex] + sortedNumbers[middleIndex - 1]) / 2.0;
        }
        
        return middleValue;
    }
    
    /**
     * Calculates the first and third quartile of a number set.
     * 
     * @param numbers number set
     * @return first or third quartile
     */
    public static double calculateQuartile(int[] numbers, int quartile) {
        
        int size = 0;
        int position = 0;
        double quartileValue = 0;
        
        // if numbers is empty
        if (numbers.length == 0) {
            
            return 0;
        }
        
        // if numbers contains only 1 number
        if (numbers.length == 1) {
            
            return numbers[0];
        }
                
        int[] sortedNumbers = bubbleSort(numbers);
        
        // position for the first quartile
        if (quartile == 1) {
            
            position = (int) Math.round(sortedNumbers.length * 0.25) - 1;
            
        // position for the third quartile
        } else if (quartile == 3) {
            
            position = (int) Math.ceil(sortedNumbers.length * 0.75) - 1;
        }
        
        // odd amount of numbers
        if (sortedNumbers.length % 2 == 1) {
            
            size = (sortedNumbers.length - 1) / 2;
            
        // even amount of numbers
        } else {
            
            size = sortedNumbers.length / 2;
        }
        
        // even number for size
        if (size % 2 == 0) {
            
            int value1 = sortedNumbers[position + 1];
            int value2 = sortedNumbers[position];
            
            quartileValue = (value1 + value2) / 2.0;
            
        // odd number for size
        } else {
            
            quartileValue = sortedNumbers[position];
        }
        
        return quartileValue;
    }
        
    /**
     * Calculates the mode, or the number that occurs the most frequently
     * in the number set.
     * 
     * @param numbers number set
     * @return the mode
     */
    public static int calculateMode(int[] numbers) {
        
        HashMap<Integer, Integer> occurrences = new HashMap<>();
        HashMap.Entry<Integer, Integer> mode = null;
        
        // if numbers is empty
        if (numbers.length == 0) {
            
            return 0;
        }
        
        // if numbers contains only 1 number
        if (numbers.length == 1) {
            
            return numbers[0];
        }
        
        // calculates the occurrences of each number
        for (int i = 0; i < numbers.length; i++) {
            
            int key = numbers[i];
            
            if (!occurrences.containsKey(key)) {
                
                occurrences.put(key, 1);
            } else {
                
                int value = occurrences.get(key) + 1;
                occurrences.replace(key, value);
            }
        }
        
        // finds the entry with the maximum value
        for (HashMap.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            
            if (mode == null || entry.getValue().compareTo(mode.getValue()) > 0) {
                
                mode = entry;
            }
        }
        
        // returns the number of the mode
        return mode.getKey();
    }
    
    /**
     * Calculates the average of the squared differences from the mean of the
     * number set.
     * 
     * @param numbers number set
     * @return variance
     */
    public static double calculateVariance(int[] numbers) {
        
        double mean = calculateMean(numbers);
        double temp = 0;
        double variance;
        
        // if the number set contains 1 or fewer numbers
        if (numbers.length == 0 || numbers.length == 1) {
            
            return 0;
        }
        
        for (double i : numbers) {
            
            temp += (mean - i) * (mean - i);
        }
        
        variance = temp / numbers.length;
        
        return variance;
    }
    
    /**
     * Calculates the measure of how spread out the numbers are.
     * 
     * @param numbers number set
     * @return standard deviation
     */
    public static double calculateStandardDeviation(int[] numbers) {
        
        double standardDeviation;
        double variance = calculateVariance(numbers);
        
        // if the number set contains 1 or fewer numbers
        if (numbers.length == 0 || numbers.length == 1) {
            
            return 0;
        }
        
        standardDeviation = Math.sqrt(variance);
        
        return standardDeviation;
    }
    
    /**
     * Calculates the difference between the highest and the lowest
     * value in the number set.
     * 
     * @param numbers number set
     * @return range
     */
    public static int calculateRange(int[] numbers) {
        
        // if numbers is empty
        if (numbers.length == 0) {
            
            return 0;
        }
        
        // if numbers contains only 1 number
        if (numbers.length == 1) {
            
            return numbers[0];
        }
        
        int maxValue = maxValue(numbers);
        int minValue = minValue(numbers);
        
        // difference of max and min values
        int range = maxValue - minValue;
        
        return range;
    }
    
    /**
     * Finds the maximum value in the number set.
     * 
     * @param numbers number set
     * @return maximum value
     */
    public static int maxValue(int[] numbers) {
        
        int maxValue = 0;
        
        for (int i = 0; i < numbers.length; i++) {
            
            if (numbers[i] > maxValue) {
                
                maxValue = numbers[i];
            }
        }
        
        return maxValue;
    }
    
    /**
     * Finds the minimum value in the number set.
     * 
     * @param numbers number set
     * @return minimum value
     */
    public static int minValue(int[] numbers) {
        
        int minValue = numbers[0];
        
        for (int i = 0; i < numbers.length; i++) {
            
            if (numbers[i] < minValue) {
                
                minValue = numbers[i];
            }
        }
        
        return minValue;
    }
}