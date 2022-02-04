/**
 * Sorts vertices to form a valid polygon
 * This is a helper class for the Polygon class
 */
class Order
{

    Vertex[] vert = new Vertex[0];
    Vertex mean;

    /**
     * adds a vertex to the array
     * @param a vertex to be added
     */
    public void addVertex(Vertex a){
        Vertex[] temp = new Vertex[vert.length+1];
        System.arraycopy(vert, 0, temp, 0, vert.length);
        temp[vert.length] = a;
        vert = temp;
    }

    /**
     * sorts the vertices in order of angle
     */
    public void start(){
        mean = new Vertex(aver(true),aver(false));
        setAngles();
        Sort ob = new Sort();
        ob.sort(vert,0,vert.length-1);
    }

    /**
     * calculates the average x and y coordinates
     * @param x true if x is to be calculated, false if y is to be calculated
     * @return the average
     */
    public int aver(boolean x){
        int n = 0;
        for (Vertex vertex : vert) {
            if (x) {
                n += vertex.x;
            } else {
                n += vertex.y;
            }
        }
        n = n/vert.length;
        return n;
    }

    /**
     * sets the angles of the vertices
     */
    public void setAngles(){
        for(Vertex a:vert){
            a.set(mean);
        }
    }

}