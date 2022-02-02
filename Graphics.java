import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.time.Duration;
import java.time.Instant;


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

    /**
     * Internal handling of different MouseEvents
     * @param i Type of mouseEvent
     */
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
    //---------------------
    // Events to be overridden by child classes
    //---------------------
    protected void mousePressed(){

    }
    protected void mouseMoved(){

    }
    protected void mouseClicked(){

    }
    protected void mouseReleased(){

    }
    protected void mouseDragged(){

    }
    protected void keyPressed(){

    }
    protected void keyReleased(){

    }
    protected void keyTyped(){

    }
    protected void mouseScrolled(){

    }
    @Override
    protected void setup(){

    }

    /**
     * Must be overridden
     * Executed every frame
     */
    protected void draw(){

    }

    /**
     * Internal handling of various KeyEvents
     * @param i Representation of a KeyEvent
     */
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
