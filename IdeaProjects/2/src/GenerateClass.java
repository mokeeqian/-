
import java.io.*;

/**
 * Generate a class by the given info
 * FIXME: I may ignore the Date type's generation...
 */
public class GenerateClass {
    private String classname;
    String[] variableTypes;
    String[] variableNames;

    public GenerateClass(String classname,  String[] variableNames, String[] variableTypes) {
        this.classname = classname;
        this.variableNames = variableNames;
        this.variableTypes = variableTypes;
    }

    /**
     * Make fields, declear the member variables
     * @return A string
     */
    public String makeFields() {
        String declearation = "";

        int size = variableNames.length;
        for ( int i = 0; i < size; ++i ) {
            declearation +=  "private " +  variableTypes[i] + " " + variableNames[i] + ";\n";
        }
        return declearation;
    }

    /**
     * Make a construtor
     * @return A string of the constructor's definition
     */
    public String makeConstructor() {

        String declearation = "";
        StringBuilder buf = new StringBuilder();
        StringBuilder buf1 = new StringBuilder();

        /* Store the function declearation */
        declearation += "public " + classname + "( ";

        /* loop to handle the variables */
        for ( int i = 0; i < variableNames.length; ++i ) {
            /* The method parameter */
            buf.append(variableTypes[i]).append(" ").append(variableNames[i]).append(", ");
        }

        /* Reomve the last comma(dulplicated) */
        buf.deleteCharAt(buf.length()-2);

        /* Link the buf to the dec string variable */
        declearation += buf;
        declearation += " )\n";
        declearation += "{\n";

        for ( int i = 0; i < variableNames.length; ++i ) {
            buf1.append( "    this." + variableNames[i]).append(" = ").append(variableNames[i]).append(";\n");
        }

        /* Finish the method body */
        declearation += buf1;
        declearation += "}\n";

        /* Return the String to the method */
        return declearation;
    }

    /**
     * Generate all the getters
     * @return
     */
    public String makeGetters() {
        /* The function description string */
        String dec = "";
        /* The tmp string container to store the definations */
        StringBuilder buf = new StringBuilder();

        for ( int i = 0; i < variableNames.length; ++i ) {
            buf.append("public ").append(variableTypes[i]).append(" ").append("get").append((variableNames[i]).toUpperCase()).append("()");
            buf.append(" {\n    return this." ).append(variableNames[i]).append(";\n}\n");
        }

        dec += buf;
        return dec;
    }

    /**
     * Generate all the setters
     * @return
     */
    public String makeSetters() {
        String dec = "";
        StringBuilder buf = new StringBuilder();

        for ( int i = 0; i < variableNames.length; ++i ) {
            buf.append("public void set").append((variableNames[i]).toUpperCase()).append("( ").append(variableTypes[i]).append(" ");
            buf.append(variableNames[i]).append(" ) {\n    this.").append(variableNames[i]).append(" = ").append(variableNames[i]).append(";\n}\n");
        }

        dec += buf;
        return dec;
    }

    /**
     * Write the class string to file
     */
    public void writeFile() {
        String buf = "";
        String res = "";
        /* The class header */
        res += "public class " + this.classname + "{\n";

        buf = "public class " + this.classname + "{\n" + makeFields() + makeConstructor() + makeGetters() + makeSetters() + "}\n";
        String[] lines = buf.split("\n");
        for ( int i = 0; i < lines.length; ++i ) {

            /* skip the first and the last line */
            if ( i == 0 || i == lines.length-1 )
                continue;

            /* append two bankspaces as indent */
            lines[i] = "    " + lines[i] + "\n";

            /* Link the lines altogether */
            res += lines[i];
        }

        /* append the last line */
        res += "}\n";

        /* Try write */
        try (FileWriter fileWriter = new FileWriter(this.classname + ".java")) {
            fileWriter.write(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Main method to the this class to test the GenerateClass
     * @param args
     */
    public static void main(String[] args) {
        String[] type = {"int", "String"};
        String[] name = {"a", "b"};
        GenerateClass generateClass = new GenerateClass("Person", name, type );


        generateClass.writeFile();
        System.out.println(generateClass.makeConstructor());
        System.out.println(generateClass.makeFields());
        System.out.println(generateClass.makeSetters());
        System.out.println(generateClass.makeFields());
    }
}
