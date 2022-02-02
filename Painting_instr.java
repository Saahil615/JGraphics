import java.awt.*;

/**
 * Bundles the specifics of the drawing
 * Fill colour
 * Stroke colour
 * strokeWeight
 *
 * Handles all operations related to these
 */
class Painting_instr
{
    Color Fill =  Color.WHITE;
    Color Stroke = Color.BLACK;
    boolean fill = true;
    boolean stroke = true;
    int strokeWeight =  1;
    public void fill(Color a){
        Fill = a;
        fill = true;
    }
    public void stroke(Color a){
        Stroke = a;
        stroke = true;
    }
    public void noFill(){
        fill = false;
    }
    public void noStroke(){
        stroke = false;
    }
    public void strokeWeight(int sw){
        strokeWeight = sw;
    }
}

