

public class CircularPrime {
    /**
     * Main method to test
     * @param args
     */
    public static void main(String[] args) {
        int test = 1193;

        /* the length of the number */
        int numLen = (test + "").length();

        int i;
        int num = test;

        /* calculate the div */
        int division = Integer.parseInt((num + "").substring(0,1)) * (int)Math.pow(10, numLen-1);
        int shift ;


        /* Loop to calculate the num's shift bit number, check if is a prime */
        for ( i = 0 ; i < numLen-1; i++ ) {

            /* The current number by shift is NOT prime */
            if ( ! is_Prime(num) ) {
                System.out.println(" NOT Circular Prime");
                return;
            }

            /* update the var */
            shift = circulate(num, division);
            num = shift;
            division = Integer.parseInt((num + "").substring(0,1)) * (int)Math.pow(10, numLen-1);

        }

        System.out.println("Circular Prime");

    }

    // The method to check if a given number is prime ?
    public static boolean is_Prime( int number ){
        if (number <= 3) {
            return number > 1;
        }

        for(int i=2; i<=Math.sqrt(number); i++) {
            if ( number%i == 0 )
            return false;
        }
        return true;
    }

    // The method to find the next cyclic permutation of the input number
    public static int circulate( int n, int divisor_part ){
        if( n < 10 )
            return n;
        else
            return ( n % divisor_part ) * 10 + n / divisor_part;

    }






}
