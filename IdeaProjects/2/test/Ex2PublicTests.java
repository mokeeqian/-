import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class Ex2PublicTests {

    public static final double TOLERANCE = 0.0001;

    // testing getters
    @Test
    public void test1() {
        int[] tonysMarks = {50,50,50,50,50,50,50,50,50,50,50,50,-1,100};
        Student tony = new Student("1111110", tonysMarks);

        String expectedRegistrationNumber = "1111110";
        int[] expectedMarks = {50,50,50,50,50,50,50,50,50,50,50,50,-1,100};

        String actualRegistrationNumber = tony.getRegistrationNumber();
        int[] actualMarks = tony.getMarks();

        assertEquals(expectedRegistrationNumber, actualRegistrationNumber);
        assertTrue(Arrays.equals(expectedMarks, actualMarks));
    }

    // testing setters
    @Test
    public void test2() {
        int[] tonysMarks = {50,50,50,50,50,50,50,50,50,50,50,50,-1,100};
        Student tony = new Student("1111110", tonysMarks);

        tony.setRegistrationNumber("1251220");
        tony.setAssignmentMark(13, 51);

        String expectedRegistrationNumber = "1251220";
        int[] expectedMarks = {50,50,50,50,50,50,50,50,50,50,50,50,51,100};

        String actualRegistrationNumber = tony.getRegistrationNumber();
        int[] actualMarks = tony.getMarks();

        assertEquals(expectedRegistrationNumber, actualRegistrationNumber);
        assertTrue(Arrays.equals(expectedMarks, actualMarks));
    }

    // testing totalMark and passed
    @Test
    public void test3() {
        int[] tonysMarks = {50,50,50,50,50,50,50,50,50,50,50,50,-1,100};
        Student tony = new Student("1111110", tonysMarks);

        int[] samsMarks = {50,60,65,60,65,70,55,66,60,73,65,45,68,54};
        Student sam = new Student("1111111", samsMarks);

        int[] billysMarks = {50,60,-1,60,65,70,55,66,60,73,65,45,68,54};
        Student billy = new Student("1111112", billysMarks);

        int[] tobysMarks = {100,100,100,100,100,100,100,100,100,100,100,100,100,25};
        Student toby = new Student("1111113", tobysMarks);

        double expectedTotalMarkTony = 88.9;
        double expectedTotalMarkSam = 56.5;
        double expectedTotalMarkBilly = 56.3;
        double expectedTotalMarkToby = 47.5;

        double actualTotalMarkTony = tony.totalMark();
        double actualTotalMarkSam = sam.totalMark();
        double actualTotalMarkBilly = billy.totalMark();
        double actualTotalMarkToby = toby.totalMark();

        assertEquals(expectedTotalMarkTony, actualTotalMarkTony, TOLERANCE);
        assertEquals(expectedTotalMarkSam, actualTotalMarkSam, TOLERANCE);
        assertEquals(expectedTotalMarkBilly, actualTotalMarkBilly, TOLERANCE);
        assertEquals(expectedTotalMarkToby, actualTotalMarkToby, TOLERANCE);

        assertTrue(tony.passed());
        assertTrue(sam.passed());
        assertTrue(billy.passed());
        assertFalse(toby.passed());
    }

    // testing insufficient marks
    @Test
    public void test4() {

        int[] eddysMarks = {50,60,-1,60,65,70,55,66,60,73,65,45,68,-1};
        Student eddy = new Student("1111114", eddysMarks);

        double expectedTotalMark = -1;

        double actualTotalMark = eddy.totalMark();

        assertEquals(expectedTotalMark, actualTotalMark, TOLERANCE);

    }

    // testing insufficient marks
    @Test(expected = IllegalArgumentException.class)
    public void test5() {

        int[] eddysMarks = {50,60,-1,60,65,70,55,66,60,73,65,45,68,-1};
        Student eddy = new Student("1111114", eddysMarks);

        // exception should be thrown
        eddy.passed();

    }

}
