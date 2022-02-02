/**
 * Sorts vertices to form a valid polygon
 */
class Order
{

    Vertex[] vert = new Vertex[0];
    Vertex mean;
    public void addVertex(Vertex a){
        Vertex[] temp = new Vertex[vert.length+1];
        System.arraycopy(vert, 0, temp, 0, vert.length);
        temp[vert.length] = a;
        vert = temp;
    }
    public void start(){
        mean = new Vertex(aver(true),aver(false));
        setAngles();
        Sort ob = new Sort();
        ob.sort(vert,0,vert.length-1);
    }
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
    public void setAngles(){
        for(Vertex a:vert){
            a.set(mean);
        }
    }

}