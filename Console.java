/**
 * This class simplifies console I/O
 */
public class Console {
    /**
     * Clears the console and moves to the next line
     */
    public static void clear()
    {
        println( "\f" );
    }

    /**
     * Prints the given object's String representation to the console
     * @param a The object to be printed
     */
    public static void print(Object a)
    {
        System.out.print( a );
    }

    /**
     * Prints the given object's String representation to the console and moves to the next line
     * @param a The object to be printed
     */
    public static void println(Object a)
    {
        print( a );
        print( "\n" );
    }

    /**
     * Moves the cursor to the next line
     */
    public static void println()
    {
        print( "\n" );
    }

    /**
     * Reads a line from the console
     * @return The line read from the console
     */
    public static String readLine()
    {
        return System.console().readLine();
    }

    /**
     * Reads a line from the console and converts it to an integer
     * @return The integer read from the console
     */
    public static int readInt()
    {
        return Integer.parseInt( readLine() );
    }

    /**
     * Reads a line from the console and converts it to a double
     * @return The double read from the console
     */
    public static double readDouble()
    {
        return Double.parseDouble( readLine() );
    }

    /**
     * Reads a line from the console and converts it to a boolean
     * @return The boolean read from the console
     */
    public static boolean readBoolean()
    {
        return Boolean.parseBoolean( readLine() );
    }

    /**
     * Writes a formatted string to this console's output stream using the specified format string and arguments.
     * @param format  A format string as described in Format string syntax
     * @param args Arguments referenced by the format specifiers in the format string. If there are more arguments than format specifiers,
     *             the extra arguments are ignored. The number of arguments is variable and may be zero.
     *             The maximum number of arguments is limited by the maximum dimension of a Java array as defined by The JVM Specification.
     *             The behaviour on a null argument depends on the conversion.
     */
    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }
}
