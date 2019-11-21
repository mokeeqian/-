import java.math.BigDecimal;
import java.util.Arrays;

/**
 * A Student class
 */
public class Student {
    /* The registrstion number */
    private String registrationNumber;
    /*  The marks of the student */
    private int[] marks;

    /**
     * Constructor for the class
     * @param registrationNumber The student's registration number
     * @param marks The student's mark, should be an array
     */
    public Student(String registrationNumber, int[] marks) {
        this.registrationNumber = registrationNumber;
        this.marks = marks;
    }

    /**
     * Getter for RegistrationNumber
     * @return
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Setter for registractionNumber
     * @param registrationNumber
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * Getter for marks
      * @return Array to the marks
     */
    public int[] getMarks() {
        return marks;
    }

    /**
     * Setter for marks
     * @param marks An array of the student
     */
    public void setMarks(int[] marks) {
        this.marks = marks;
    }


    /**
     * Set assign from the parameters
     * @param assignmentNumber
     * @param mark
     */
    public void setAssignmentMark(int assignmentNumber, int mark) {
        if ( !( assignmentNumber >=1 && assignmentNumber <= 14 ) )
            return;
        this.marks[assignmentNumber-1] = mark;
    }

    /**
     * Get total marks
     * @return
     */
    public double totalMark() {

        /* Check if -1 exists */
        for ( int j = 0; j < marks.length; ++j ) {
            if ( marks[j] == -1 ) {
                /* Abort the issue */
                marks[j] = 0;
            }
        }

        //FIXME: got something WRONG, is the doc's answer right???

        double total = 0.0;
        int i;
        double weight = 0.02;
        /* The first eight element of the mask array */
        for ( i = 0; i < 8; ++i ) {
            if ( i >= 4 )
                weight = 0.01;
            total += this.marks[i] * weight;
        }

        total += (this.marks[8] + this.marks[9]) * 0.02;
        total += marks[10] * 0.01;
        total += marks[11] * 0.03;
        total += marks[12] * 0.10;
        total += marks[13] * 0.70;

        total = new BigDecimal(total).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();;
        return total;
    }

    /**
     * Check if a student passed
     * NOTE that the mothod throws a exception, the caller method should handle the exception!
     * @return
     * @throws IllegalArgumentException
     */
    boolean passed() throws IllegalArgumentException{
        double total = this.totalMark();
        if (total >= 50.0)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Student{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", marks=" + Arrays.toString(marks) +
                '}';
    }

    /**
     * Main method tests the class Student
     * @param args
     */
    public static void main(String[] args) {

        int[] fooMarks = {50, 60, 65, 60, 65, 70, 55, 66, 60, 73, 65, 45, 68, 54};
        int[] barMarks = {50, 60, -1, 60, 65, 70, 55, 66, 60, 73, 65, 45, 68, 54};
        Student foo = new Student("20191103", fooMarks);
        Student bar = new Student("20191104", barMarks);

        System.out.println(foo.totalMark());
        System.out.println(bar.totalMark());
        System.out.println(foo.passed());
        System.out.println(bar.passed());
    }
}
