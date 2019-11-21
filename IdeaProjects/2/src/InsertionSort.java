/**
 * A class InsertionSort to implement the insert sort algorithm.
 * Especially, I offered a JUnit Test for the class!
 * @author
 */
public class InsertionSort {
    /**
     * A static method to the algorithm
     * @param numbers The original array given to be sorted
     * @return A array sorted by the original array's elements' size
     */
    public static int[] insertionSort(int[] numbers) {

        /* Loop until the array ends */
        for(int index = 1; index < numbers.length; index++) {
            /* A temporary variable to store the current element of the array */
            int temp = numbers[index];

            /* The left part of the array */
            int leftindex = index-1;

            while( leftindex >= 0 && numbers[leftindex] > temp ) {
                numbers[leftindex+1] = numbers[leftindex];
                leftindex --;
            }

            /* Set the temp in the empty position */
            numbers[leftindex+1] = temp;
        }

        /* Return the sorted array */
        return numbers;
    }

    /**
     * Show and print all the elements of the array
     * @param numbers A given array
     */
    public static void showArray(int[] numbers) {
        int i;
        for ( i = 0; i < numbers.length; ++i ) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println("\n");
    }

    /**
     * Main method for test
     * @param args
     */
    public static void main(String[] args) {
        int[] array = {100,12,23,43,21,22,11,54,65,76,84,99};
        /* Show the original array */
        InsertionSort.showArray(array);

        /* Execute the insert sort */
        InsertionSort.insertionSort(array);

        /* Show the sorted array */
        InsertionSort.showArray(array);
    }

}
