public class Console {
    public static void clear()
    {
        println( "\f" );
    }
    public static void print(Object a)
    {
        System.out.print( a );
    }
    public static void println(Object a)
    {
        print( a );
        print( "\n" );
    }
    public static void println()
    {
        print( "\n" );
    }
}
