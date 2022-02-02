import java.awt.*;

/**
 * Handles the creation of all the custom rectilinear shapes from Paint
 */
class Polygon
{
    Vertex[] vert = new Vertex[0];
    boolean noFill = false;
    boolean strict = false;
    public void addVertex(Vertex a){
        Vertex[] temp = new Vertex[vert.length+1];
        System.arraycopy(vert, 0, temp, 0, vert.length);
        temp[vert.length] = a;
        vert = temp;
    }
    public void draw(Graphics2D g,Painting_instr p){
        if(!strict){
            sort();
        }
        if(noFill){
            if(p.stroke){
                g.setStroke(new BasicStroke(p.strokeWeight));
                g.setColor(p.Stroke);
                for(int i = 0;i<vert.length-1;i++){
                    vert[i].connect(g,vert[i+1]);
                }
            }
        }else{
            if(p.stroke){
                g.setStroke(new BasicStroke(p.strokeWeight));
                g.setColor(p.Stroke);
                for(int i = 0;i<vert.length-1;i++){
                    vert[i].connect(g,vert[i+1]);
                }
                if(vert.length>0)
                    vert[0].connect(g,vert[vert.length-1]);
            }
            if(p.fill){
                int[] xp = new int[vert.length];
                int[] yp = new int[vert.length];
                for(int i = 0;i<vert.length;i++){
                    xp[i] = (int) vert[i].x;
                    yp[i] = (int) vert[i].y;
                }
                g.setColor(p.Fill);
                g.fillPolygon(xp,yp,vert.length);
            }
        }
    }
    public void sort(){
        Order o = new Order();
        for(Vertex a:vert){
            o.addVertex(a);
        }
        o.start();
        this.vert = o.vert;
    }
}
