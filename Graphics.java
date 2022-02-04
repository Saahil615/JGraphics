import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.time.Duration;
import java.time.Instant;

/**
 * This class supports drawing dynamic images.
 *
 * Support for handling keyboard and mouse events is included.
 * protected methods are provided for subclasses to override.
 * protected instance variables are provided for subclasses to use.
 *
 * mouseX and mouseY are the current mouse position.
 * key is the keycode of the last key pressed. Use the Keys class to interpret.
 * fps is the current frames per second.
 * shift_down is true if the shift key is down.
 * ctrl_down is true if the ctrl key is down.
 * alt_down is true if the alt key is down.
 */
public class Graphics extends Paint implements Runnable
{
    protected int mouseX;
    protected int mouseY;
    protected int key;
    private int e_fps = 60;
    protected int fps;
    protected boolean shift_down = false;
    protected boolean ctrl_down = false;
    protected boolean alt_down = false;
    private Thread Animation;

    /**
     * Creates an object of Graphics and initialises all ActionListeners
     */
    public Graphics(){
        setFocusable(true);
        addMouseListener(new MouseAdapter(){

            @Override
            public void mousePressed(MouseEvent e){
                mouseIsPressed(0);
            }
            @Override
            public void mouseClicked(MouseEvent e){
                mouseIsPressed(1);
            }
            @Override
            public void mouseReleased(MouseEvent e){
                mouseIsPressed(2);
            }
            @Override
            public void mouseWheelMoved(MouseWheelEvent e){
                mouseScrolled();
            }
        });
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                key = e.getKeyCode();
                if(key == KeyEvent.VK_SHIFT)
                    shift_down = true;
                else if(key == KeyEvent.VK_CONTROL){
                    ctrl_down = true;
                }else if(key == KeyEvent.VK_ALT)
                    alt_down = true;
                keyIsPressed(0);
            }
            @Override
            public void keyReleased(KeyEvent e){
                key = e.getKeyCode();
                if(key == KeyEvent.VK_SHIFT)
                    shift_down = false;
                else if(key == KeyEvent.VK_CONTROL){
                    ctrl_down = false;
                }else if(key == KeyEvent.VK_ALT)
                    alt_down = false;
                keyIsPressed(1);
            }
            @Override
            public void keyTyped(KeyEvent e){
                key = e.getKeyCode();
                keyIsPressed(2);
            }
        });
        addMouseMotionListener(new MouseMotionListener(){
            @Override
            public void mouseMoved(MouseEvent e){
                mouseX = e.getX();
                mouseY = e.getY();
                mouseIsPressed(4);
            }
            @Override
            public void mouseDragged(MouseEvent e){
                mouseX = e.getX();
                mouseY = e.getY();
                mouseIsPressed(3);
            }
        });
    }
    private void mouseIsPressed(int i){
        if(i == 0)
            mousePressed();
        else if(i == 1)
            mouseClicked();
        else if(i == 2)
            mouseReleased();
        else if(i == 3)
            mouseDragged();
        else if(i == 4)
            mouseMoved();
        repaint();
    }

    /**
     * Ends the animation
     */
    protected final void stop(){
        if(Animation!=null){
            Thread tmp = Animation;
            Animation = null;
            tmp.interrupt();
        }
    }

    protected void setFps(int fps)
    {
        e_fps = fps;
    }

    /**
     * Keeps track of required and current fps and adjusts wait times accordingly
     * Calls draw() every frame
     */
    @SuppressWarnings("BusyWait")
    @Override
    public final void run(){
        long t,prev_time,delta;
        long fps_millis = Math.round(1000.0 / e_fps);
        Instant s,e;
        prev_time = fps_millis;
        while(Thread.currentThread() == Animation){
            s = Instant.now();
            try{
                Thread.sleep(prev_time);
            }catch(InterruptedException ex){
                ex.printStackTrace();
            }
            draw();
            repaint();
            e = Instant.now();
            t = Duration.between(s,e).toMillis();
            delta = t- fps_millis;
            prev_time = t-delta;
            if(prev_time<0){
                prev_time = 0;
            }
            long a_fps;
            if(prev_time == 0){
                a_fps = Math.round(1000.0/t);
            }else{
                a_fps = Math.round(1000.0/prev_time);
            }
            fps = (int) a_fps;
        }
    }

    /**
     * Called when the mouse is pressed
     * Should be overridden to be used
     */
    protected void mousePressed(){

    }

    /**
     * Called when the mouse position is changed
     * Should be overridden to be used
     */
    protected void mouseMoved(){

    }

    /**
     * Called when the mouse is clicked and released
     * Should be overridden to be used
     */
    protected void mouseClicked(){

    }

    /**
     * Called when the mouse is released
     * Should be overridden to be used
     */
    protected void mouseReleased(){

    }

    /**
     * Called when the mouse is dragged
     * Should be overridden to be used
     */
    protected void mouseDragged(){

    }

    /**
     * Called when any key is pressed
     * Should be overridden to be used
     */
    protected void keyPressed(){

    }

    /**
     * Called when any key is released
     * Should be overridden to be used
     */
    protected void keyReleased(){

    }

    /**
     * Called when any key is pressed and then released
     * Should be overridden to be used
     */
    protected void keyTyped(){

    }

    /**
     * Called when the mouse scroll wheel is moved
     * Should be overridden to be used
     */
    protected void mouseScrolled(){

    }

    /**
     * This function is called when the animation is created and executed only once, before the first frame
     * It must contain createCanvas()
     * the default fps is 60, but can be changed by calling setFps(int fps)
     * the default size is (200,200) but can be changed by calling setSize(int width, int height)
     * Both these functions should be called before any drawing is done.
     */
    @Override
    protected void setup(){

    }

    /**
     * Must be overridden
     * Executed every frame
     */
    protected void draw(){

    }
    private void keyIsPressed(int i){
        if(i == 0)
            keyPressed();
        else if(i == 1)
            keyReleased();
        else if(i == 2)
            keyTyped();
        repaint();
    }

    @Override
    protected final void go(){
        setup();
        SwingUtilities.invokeLater(() ->{
            main = new JFrame();
            main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            main.getContentPane().add(this,BorderLayout.CENTER);
            main.setTitle(title);
            main.setResizable(false);
            main.setSize(width,height);
            main.setLocationByPlatform(true);
            main.setVisible(true);
        });
        (Animation = new Thread(this)).start();
    }
}
