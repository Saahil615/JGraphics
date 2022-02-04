/**
 * Provides a simple vector class for 2D euclidean vectors.
 */
public class Vector{
    public double x;
    public double y;
    public Vector(double x,double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Performs vector addition between the current Vector and b as well as assigns the result to the current Vector
     * @param b The second Vector
     */
    public void add(Vector b)
    {
        this.x+=b.x;
        this.y+=b.y;
    }

    /**
     * Performs vector subtraction between the current Vector and b as well as assigns the result to the current Vector
     * @param b The second Vector
     */
    public void sub(Vector b)
    {
        this.x-=b.x;
        this.y-=b.y;
    }

    /**
     * Multiplies the current Vector by a scalar
     * @param s The scalar
     */
    public void mult(int s)
    {
        this.x*=s;
        this.y*=s;
    }

    /**
     * Divides the current Vector by a scalar
     * @param s The scalar
     */
    public void div(int s)
    {
        this.x/=s;
        this.y/=s;
    }

    /**
     * Returns the magnitude of the current Vector
     * @return The magnitude
     */
    public double mag()
    {
        return Math.sqrt(Math.pow(this.x,2)+Math.pow(this.y,2));
    }

    /**
     * Returns the magnitude of the current Vector squared
     * @return The magnitude squared
     */
    public double magSq()
    {
        return Math.pow(mag(),2);
    }

    /**
     * Sets the vector to the specified position
     * @param x The x-coordinate
     * @param y The y-coordinate
     */
    public void set(double x,double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the current Vector to the same position as the input Vector
     * @param b The input Vector
     */
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
     * @return angle between the current Vector and the +ve X-axis in degrees
     */
    public double headingDegrees(){
        int q = quadrant();
        if(q == 1)
            return Utilities.degrees(Math.atan(this.y/this.x));
        else if(q == 4)
            return Utilities.degrees(Math.atan(this.y/this.x))+360;
        else
            return Utilities.degrees(Math.atan(this.y/this.x))+180;
    }

    /**
     * @return the angle between the current Vector and the +ve X-axis in radians
     */
    public double heading(){
        return Math.atan2(this.y,this.x);
    }

    /**
     * Adds the two input vectors and returns the result
     * @param a The first Vector
     * @param b The second Vector
     * @return The result of the addition
     */
    public static Vector add(Vector a,Vector b)
    {
        return new Vector(a.x+b.x,a.y+b.y);
    }

    /**
     * Subtracts the second input vector from the first and returns the result
     * @param a The first Vector
     * @param b The second Vector
     * @return The result of the subtraction
     */
    public static Vector sub(Vector a,Vector b)
    {
        return new Vector(a.x-b.x,a.y-b.y);
    }

    /**
     * returns the angle between the two input vectors in radians
     * @param a The first Vector
     * @param b The second Vector
     * @return The angle between the two vectors in radians
     */
    public static double angleBetween(Vector a,Vector b)
    {
        return Math.abs(a.heading()-b.heading());
    }
    public static double angleBetweenDegrees(Vector a,Vector b){
        return Utilities.degrees(angleBetween(a,b));
    }
    /**
     * Returns the distance between the current Vector and the input Vector
     * @param b The input Vector
     * @return The distance between the two Vectors
     */
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

    /**
     * To find the dot product of the two input vectors
     * @param a The first Vector
     * @param b The second Vector
     * @return The dot product
     */
    public static double dot(Vector a,Vector b)
    {
        return (a.x*b.x)+(a.y*b.y);
    }

    /**
     * Copies the current Vector and returns it
     * @return The copy of the current Vector
     */
    public Vector copy()
    {
        return new Vector(this.x,this.y);
    }

    /**
     * Sets the magnitude of the current Vector to the input value
     * @param m The input value
     */
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

    /**
     * Returns the linear interpolation between the current Vector and the input Vector at the input percentage
     * @param b The input Vector
     * @param amt The input percentage
     */
    public void lerp(Vector b,double amt)
    {
        this.x = Utilities.map(amt,0,1,this.x,b.x);
        this.y = Utilities.map(amt,0,1,this.y,b.y);
    }

    /**
     * Rotates the current Vector by the input angle in radians
     * @param angle The input angle
     */
    public void rotate(double angle)
    {
        double t = heading()+angle;
        this.x = mag()*Math.cos(t);
        this.y = mag()*Math.sin(t);
    }
    public void rotateDegrees(double angle){
        rotate(Utilities.radians(angle));
    }

    /**
     * Returns an array representation of the current Vector in the format [ x , y ]
     * @return The array representation of the current Vector
     */
    public double[] toArray()
    {
        return new double[] {this.x,this.y};
    }

    /**
     * Returns the Vector represented by the input array in the format [ x , y ]
     * @param arr The input array
     * @return The Vector represented by the input array
     */
    public static Vector fromArray(double[] arr)
    {
        return new Vector(arr[0],arr[1]);
    }

    /**
     * Returns a unit vector in the direction of the angle in radians
     * @param angle The input angle
     * @return The unit vector
     */
    public static Vector fromAngle(double angle)
    {
        return new Vector(Math.cos(angle),Math.sin(angle));
    }

    /**
     * Returns a unit vector in the direction of the angle in degrees
     * @param angle The input angle
     * @return The unit vector
     */
    public static Vector fromAngleDegrees(double angle){
        return fromAngle(Utilities.radians(angle));
    }
    /**
     * Returns a random unit vector
     * @return The random unit vector
     */
    public static Vector random()
    {
        return fromAngle(Math.random()*Math.PI*2);
    }
}