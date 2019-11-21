import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class Ex1PublicTests {

    // testing unsorted array
    @Test
    public void test1() {
        int[] numbers = {5, 3, 9, 1, 6};
        int[] expected = {1, 3, 5, 6, 9};
        int[] actual = InsertionSort.insertionSort(numbers);
        assertTrue(Arrays.equals(expected, actual));
    }

    // testing larger array
    @Test
    public void test2() {
        int[] numbers = {5, 3, 9, 1, 6, 10, 56, 4, 8, 2, 14};
        int[] expected = {1, 2, 3, 4, 5, 6, 8, 9, 10, 14, 56};
        int[] actual = InsertionSort.insertionSort(numbers);
        assertTrue(Arrays.equals(expected, actual));
    }

    // testing on already sorted array
    @Test
    public void test3() {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] actual = InsertionSort.insertionSort(numbers);
        assertTrue(Arrays.equals(expected, actual));
    }


    // testing on array with duplicates
    @Test
    public void test4() {
        int[] numbers = {1, 3, 3, 1, 2, 4, 1};
        int[] expected = {1, 1, 1, 2, 3, 3, 4};
        int[] actual = InsertionSort.insertionSort(numbers);
        assertTrue(Arrays.equals(expected, actual));
    }

    // testing on empty array
    @Test
    public void test5() {
        int[] numbers = {};
        int[] expected = {};
        int[] actual = InsertionSort.insertionSort(numbers);
        assertTrue(Arrays.equals(expected, actual));
    }


    // testing on array with negative numbers
    @Test
    public void test6() {
        int[] numbers = {1, -2, -3, 4, 5, -6};
        int[] expected = {-6, -3, -2, 1, 4, 5};
        int[] actual = InsertionSort.insertionSort(numbers);
        assertTrue(Arrays.equals(expected, actual));
    }

    // testing on array with negative zero
    @Test
    public void test7() {
        int[] numbers = {0, -0};
        int[] expected = {0, 0};
        int[] actual = InsertionSort.insertionSort(numbers);
        assertTrue(Arrays.equals(expected, actual));
    }

}
