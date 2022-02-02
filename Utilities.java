
public class Utilities {
    public static double radians(double degrees)
    {
        return degrees*Math.PI/180;
    }
    public static double degrees(double radians)
    {
        return radians*180/Math.PI;
    }

    /**
     * Maps a number from one range to another
     * @param a The number
     * @param lower1 lower limit of current range
     * @param upper1 upper limit of current range
     * @param lower2 lower limit of desired range
     * @param upper2 upper limit of desired range
     * @return the mapped number
     */
    public static double map(double a,double lower1,double upper1,double lower2,double upper2)
    {
        return lower2+compress(a,lower1,upper1)*upper2;
    }

    /**
     * Compresses the range of a number to 0-1
     * @param a The number
     * @param lower lower limit of current range
     * @param upper upper limit of current range
     * @return the mapped number
     */
    public static double compress(double a, double lower,double upper)
    {
        return (a-lower)/(upper-lower);
    }

}
