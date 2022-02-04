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

    /**
     * Sets the fill colour
     * @param a the colour to set the fill to
     */
    public void fill(Color a){
        Fill = a;
        fill = true;
    }

    /**
     * Sets the stroke colour
     * @param a the colour to set the stroke to
     */
    public void stroke(Color a){
        Stroke = a;
        stroke = true;
    }

    /**
     * Turns off fill
     */
    public void noFill(){
        fill = false;
    }

    /**
     * Turns off stroke
     */
    public void noStroke(){
        stroke = false;
    }

    /**
     * Sets the stroke thickness
     * @param sw the thickness to set the stroke to
     */
    public void strokeWeight(int sw){
        strokeWeight = sw;
    }
}

