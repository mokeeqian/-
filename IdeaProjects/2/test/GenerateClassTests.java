import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Alexandros Evangelidis and Manfred Kerber
 * @version 2019-10-28
 */
public class GenerateClassTests {

    private GenerateClass personOneClass, personTwoClass, personThreeClass, personFourClass;

    @BeforeEach
    public void init() {
        String classname1 = "Person1";
        String[] variableNames1 = { "name", "dob" };
        String[] variableTypes1 = { "String", "Date" };
        personOneClass = new GenerateClass(classname1, variableNames1, variableTypes1);

        String classname2 = "Person2";
        String[] variableNames2 = { "name", "dob", "weight" };
        String[] variableTypes2 = { "String", "Date", "int" };
        personTwoClass = new GenerateClass(classname2, variableNames2, variableTypes2);

        String classname3 = "Person3";
        String[] variableNames3 = { "name" };
        String[] variableTypes3 = { "String" };

        personThreeClass = new GenerateClass(classname3, variableNames3, variableTypes3);

        String classname4 = "Person4";
        String[] variableNames4 = {};
        String[] variableTypes4 = {};

        personFourClass = new GenerateClass(classname4, variableNames4, variableTypes4);

    }

    // ----------------Testing personOneClass----------------------------------------------
    @Test
    public void test1() {
        String expectedFields = "  private String name;\n" + "  private Date dob;\n\n";
        String actualFields = personOneClass.makeFields();
        assertEquals(expectedFields, actualFields);
    }

    @Test
    public void test2() {
        String expectedConstructor = "  public Person1(String name, Date dob){\n" + "    this.name = name;\n"
                + "    this.dob = dob;\n" + "  }\n";
        String actualConstructor = personOneClass.makeConstructor();
        assertEquals(expectedConstructor, actualConstructor);
    }

    @Test
    public void test3() {
        String expectedGetters = "  public String getName(){\n" + "    return name;\n" + "  }\n"
                + "  public Date getDob(){\n" + "    return dob;\n" + "  }\n";
        String actualGetters = personOneClass.makeGetters();
        assertEquals(expectedGetters, actualGetters);
    }

    @Test
    public void test4() {
        String expectedSetters = "  public void setName(String name){\n" + "    this.name = name;\n" + "  }\n"
                + "  public void setDob(Date dob){\n" + "    this.dob = dob;\n" + "  }\n";
        String actualSetters = personOneClass.makeSetters();
        assertEquals(expectedSetters, actualSetters);
    }

    // ----------------Testing personTwoClass----------------------------------------------

    @Test
    public void test5() {
        String expectedFields = "  private String name;\n" + "  private Date dob;\n" + "  private int weight;\n\n";
        String actualFields = personTwoClass.makeFields();
        assertEquals(expectedFields, actualFields);
    }

    @Test
    public void test6() {
        String expectedConstructor = "  public Person2(String name, Date dob, int weight){\n"
                + "    this.name = name;\n" + "    this.dob = dob;\n" + "    this.weight = weight;\n" + "  }\n";
        String actualConstructor = personTwoClass.makeConstructor();
        assertEquals(expectedConstructor, actualConstructor);
    }

    @Test
    public void test7() {
        String expectedGetters = "  public String getName(){\n" + "    return name;\n" + "  }\n"
                + "  public Date getDob(){\n" + "    return dob;\n" + "  }\n" + "  public int getWeight(){\n"
                + "    return weight;\n" + "  }\n";
        String actualGetters = personTwoClass.makeGetters();
        assertEquals(expectedGetters, actualGetters);
    }

    @Test
    public void test8() {
        String expectedSetters = "  public void setName(String name){\n" + "    this.name = name;\n" + "  }\n"
                + "  public void setDob(Date dob){\n" + "    this.dob = dob;\n" + "  }\n"
                + "  public void setWeight(int weight){\n" + "    this.weight = weight;\n" + "  }\n";
        String actualSetters = personTwoClass.makeSetters();
        assertEquals(expectedSetters, actualSetters);
    }

    // ----------------Testing personThreeClass----------------------------------------------

    @Test
    public void test9() {
        String expectedFields = "  private String name;\n\n";
        String actualFields = personThreeClass.makeFields();
        assertEquals(expectedFields, actualFields);
    }

    @Test
    public void test10() {
        String expectedConstructor = "  public Person3(String name){\n" + "    this.name = name;\n" + "  }\n";
        String actualConstructor = personThreeClass.makeConstructor();
        assertEquals(expectedConstructor, actualConstructor);
    }

    @Test
    public void test11() {
        String expectedGetters = "  public String getName(){\n" + "    return name;\n" + "  }\n";
        String actualGetters = personThreeClass.makeGetters();
        assertEquals(expectedGetters, actualGetters);
    }

    @Test
    public void test12() {
        String expectedSetters = "  public void setName(String name){\n" + "    this.name = name;\n" + "  }\n";
        String actualSetters = personThreeClass.makeSetters();
        assertEquals(expectedSetters, actualSetters);
    }

    // ----------------Testing personFourClass----------------------------------------------

    @Test
    public void test13() {
        String expectedFields = "\n";
        String actualFields = personFourClass.makeFields();
        assertEquals(expectedFields, actualFields);
    }

    @Test
    public void test14() {
        String expectedConstructor = "  public Person4(){}\n";
        String actualConstructor = personFourClass.makeConstructor();
        assertEquals(expectedConstructor, actualConstructor);
    }

    @Test
    public void test15() {
        String expectedGetters = "";
        String actualGetters = personFourClass.makeGetters();
        assertEquals(expectedGetters, actualGetters);
    }

    @Test
    public void test16() {
        String expectedSetters = "";
        String actualSetters = personFourClass.makeSetters();
        assertEquals(expectedSetters, actualSetters);
    }

}
