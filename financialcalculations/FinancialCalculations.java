//////////////////////////////////////////////////////////////////////////////////////////////
// CMSC-255 Fall 2014
//
// Name: Brandon Smith
// Lab Section number: 2
// (Project/Example): Project-4
//
// Description: This program contains static methods that perform financial calculations with
// three different sections that test the differing methods. The program calculates future
// values, periods to reach a future value, and present values.
//
/////////////////////////////////////////////////////////////////////////////////////////////

package financialcalculations;

public class FinancialCalculations {
    
    public static void main(String[] args) {
        
        // Section A
        
        double[] payments = {500.00, 1000.00, 1500.00};
        int[] annualPercentageRates = {2, 3, 4, 5, 6, 7, 8};
        int[] years = {5, 7, 10};
        
        System.out.println("Name: Brandon Smith");
        System.out.println("V-Number: V00707171");
        System.out.println();
        
        System.out.println("Payment \t APR \t Years \t FV \t\t FV/Continuous \t FV/c - FV");
        System.out.println("-------- \t --- \t ----- \t ------------- \t ------------- \t ---------");
        
        for (int x = 0; x < payments.length; x++) {
            
            for (int y = 0; y < years.length; y++) {
                
                for (int z = 0; z < annualPercentageRates.length; z++) {
                    
                    String paymentStr = String.format("$%7.2f", payments[x]);
                    String aprStr = " " + annualPercentageRates[z] + " %";
                    String yearsStr = String.format("%4d", years[y]);
                    
                    double futureValue = calculateFV(payments[x], annualPercentageRates[z], years[y]);
                    double futureValueContinuous = calculateFVContinuous(payments[x], annualPercentageRates[z], years[y]);
                    double fvDifference = futureValueContinuous - futureValue;
                    
                    String futureValueStr = String.format(" $%.2f", futureValue);
                    String futureValueContinuousStr = String.format(" $%.2f", futureValueContinuous);
                    String fvDifferenceStr = String.format(" $%.2f", fvDifference);
                    
                    System.out.println(paymentStr + "\t" + aprStr + " \t" + yearsStr + "\t" + futureValueStr + "\t" + futureValueContinuousStr + "\t" + fvDifferenceStr);
                }
            }
        }
        
        // Section B
        
        System.out.println();
        
        double futureValues[] = {1000.00, 1200.00, 1400.00, 1600.00, 1800.00, 2000.00};
        
        System.out.println("FV \t\t Rate per Period \t Periodic Payment \t Number of Periods");
        System.out.println("-------- \t --------------- \t ---------------- \t -----------------");
        
        for (int x = 0; x < futureValues.length; x++) {
            
            int periods = calculateFVPeriods(futureValues[x], 5, 200);
            System.out.printf("$%6.2f \t %7d %% \t\t $%5.2f \t\t %9d \n", futureValues[x], 5, 200.00, periods);
            
        }
        
        // Section C
        
        System.out.println();
        
        int[] annualPercentageRates2 = {3, 4, 5, 6};
        int[] numberOfPeriods = {1, 2, 3};
        
        System.out.println("C1 \t\t Rate per Period \t Number of Periods \t Present Value");
        System.out.println("------- \t --------------- \t ----------------- \t -------------");
        
        for (int x = 0; x < numberOfPeriods.length; x++) {
            
            for (int y = 0; y < annualPercentageRates2.length; y++) {
                
                double presentValue = calculatePV(100.0, annualPercentageRates2[y], numberOfPeriods[x]);
                
                System.out.printf("$100.00 \t %7d %% \t %17d \t\t $%.2f \n", annualPercentageRates2[y], numberOfPeriods[x], presentValue);
            }
        }
    }
    
    /**
     * Calculates what the value at a future date would be for a series of
     * periodic payments.
     * 
     * @param payment payment amount
     * @param rate interest rate per period
     * @param time number of payment periods
     * @return future value
     */
    public static double calculateFV(double payment, int rate, int time) {
        
        double ratePercentage = rate / 100.0;
        
        double futureValue = payment * ((Math.pow(1 + ratePercentage, time) - 1) / ratePercentage);
        
        return futureValue;
    }
    
    /**
     * Calculates what the value at a future date would be for a series of
     * periodic payments that are compounded continuously.
     * 
     * @param payment payment amount
     * @param rate interest rate per period
     * @param time number of payment periods
     * @return future value
     */
    public static double calculateFVContinuous(double payment, int rate, int time) {
        
        double ratePercentage = rate / 100.0;
        
        double futureValue = payment * ((Math.pow(Math.E, ratePercentage * time) - 1) / (Math.pow(Math.E, ratePercentage) - 1));
        
        return futureValue;
    }
    
    /**
     * Calculate the number of periods based on the future value, rate, and
     * periodic cash flows.
     * 
     * @param futureValue future value (of annuity)
     * @param rate interest rate
     * @param payment payment amount
     * @return number of periods
     */
    public static int calculateFVPeriods(double futureValue, int rate, int payment) {
        
        double periods;
        
        double ratePercentage = rate / 100.0;
        
        periods = (Math.log(1 + (futureValue * ratePercentage) / payment) / Math.log(10)) / (Math.log(1 + ratePercentage) / Math.log(10));
        
        return (int) Math.ceil(periods);
    }
    
    /**
     * Calculates the present day value of an amount that is received at a
     * future date.
     * 
     * @param cashFlow future value (cash flow)
     * @param rate interest rate
     * @param time number of periods
     * @return present value
     */
    public static double calculatePV(double cashFlow, int rate, int time) {
        
        double ratePercentage = rate / 100.0;
        
        double presentValue = cashFlow / Math.pow(1 + ratePercentage, time);
        
        return presentValue;
    }
}