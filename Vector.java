/**
 * Support for 2D Euclidean Vectors
 * All angles in degrees
 */
public class Vector{
    public double x;
    public double y;
    public Vector(double x,double y){
        this.x = x;
        this.y = y;
    }
    public void add(Vector b)
    {
        this.x+=b.x;
        this.y+=b.y;
    }
    public void sub(Vector b)
    {
        this.x-=b.x;
        this.y-=b.y;
    }
    public void mult(int s)
    {
        this.x*=s;
        this.y*=s;
    }
    public void div(int s)
    {
        this.x/=s;
        this.y/=s;
    }
    public double mag()
    {
        return Math.sqrt(Math.pow(this.x,2)+Math.pow(this.y,2));
    }
    public double magSq()
    {
        return Math.pow(mag(),2);
    }
    public void set(double x,double y)
    {
        this.x = x;
        this.y = y;
    }
    public void set(Vector b)
    {
        this.x = b.x;
        this.y = b.y;
    }

    /**
     * Returns the quadrant of Vector a using its inclination
     * @param a the input Vector
     * @return The quadrant number
     */
    public static int quadrant(Vector a)
    {
        if(a.x>=0&&a.y>=0){
            return 1;
        }else if(a.x<=0&&a.y>=0){
            return 2;
        }else if(a.x<=0&&a.y<=0){
            return 3;
        }else{
            return 4;
        }
    }
    /**
     * Returns the quadrant of the current Vector using inclination
     * @return The quadrant number
     */
    public int quadrant()
    {
        if(this.x>=0&&this.y>=0){
            return 1;
        }else if(this.x<=0&&this.y>=0){
            return 2;
        }else if(this.x<=0&&this.y<=0){
            return 3;
        }else{
            return 4;
        }
    }

    /**
     * @return inclination of the current Vector with respect to the +ve X-axis
     */
    public double heading(){
        int q = quadrant();
        if(q == 1)
            return Utilities.degrees(Math.atan(this.y/this.x));
        else if(q == 4)
            return Utilities.degrees(Math.atan(this.y/this.x))+360;
        else
            return Utilities.degrees(Math.atan(this.y/this.x))+180;
    }
    public static Vector add(Vector a,Vector b)
    {
        return new Vector(a.x+b.x,a.y+b.y);
    }
    public static Vector sub(Vector a,Vector b)
    {
        return new Vector(a.x-b.x,a.y-b.y);
    }
    public static double angleBetween(Vector a,Vector b)
    {
        return Math.abs(a.heading()-b.heading());
    }
    public double dist(Vector b)
    {
        return Vector.sub(this,b).mag();
    }

    /**
     * To find the dot product of the current Vector and b
     * @param b The second Vector
     * @return The dot product
     */
    public double dot(Vector b)
    {
        return (this.x*b.x)+(this.y*b.y);
    }

    public static double dot(Vector a,Vector b)
    {
        return (a.x*b.x)+(a.y*b.y);
    }
    public Vector copy()
    {
        return new Vector(this.x,this.y);
    }
    public void setMag(double m)
    {
        double t = heading();
        this.x = m*Math.cos(Utilities.radians(t));
        this.y = m*Math.sin(Utilities.radians(t));
    }

    /**
     * Set the magnitude to 1, retain direction
     */
    public void normalize()
    {
        setMag(1);
    }

    /**
     * Limit the magnitude to max
     * Not a permanent limit, must be recalled to keep it under the limit
     * @param max the maximum magnitude of the Vector
     */
    public void limit(double max)
    {
        if(mag()>max)
            setMag(max);
    }

    public void lerp(Vector b,int amt)
    {
        this.x = Utilities.map(amt,0,1,this.x,b.x);
        this.y = Utilities.map(amt,0,1,this.y,b.y);
    }
    public void rotate(double angle)
    {
        double t = heading()+angle;
        this.x = mag()*Math.cos(Utilities.radians(t));
        this.y = mag()*Math.sin(Utilities.radians(t));
    }
    public double[] toArray()
    {
        double[] a = {this.x,this.y};
        return a;
    }
    public static Vector fromArray(double[] arr)
    {
        return new Vector(arr[0],arr[1]);
    }
    public static Vector fromAngle(double angle)
    {
        return new Vector(Math.cos(Utilities.radians(angle)),Math.sin(Utilities.radians(angle)));
    }
    public static Vector random()
    {
        return new Vector(Math.random(),Math.random());
    }
}