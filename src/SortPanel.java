import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.Date;

public class SortPanel extends JPanel implements AlgorithmDelegate
{
    private BufferedImage myCanvas;
    private boolean dirtyCanvas;
    private SortFrame myFrame;
    private int delay_ms;
    private Date lastUpdate;
    
    public SortPanel(SortFrame myFrame)
    {
        super();
        dirtyCanvas = true;
        setBackground(Color.lightGray);
        this.myFrame = myFrame;
        resetTimer();
        delay_ms = 1;
    }


    /**
     * gets a copy of the BufferedImage into which we are drawing our visualization. If the canvas is "dirty," that means
     * we need to generate a new one. Note: "Dirty" does not mean that it has been written on, like a dirty whiteboard;
     * instead, it means that the current one is the wrong size, or hasn't been created yet.
     * @return a BufferedImage the size of this panel.
     */
    public BufferedImage getCanvas()
    {
        if (dirtyCanvas)
        {
            System.out.println("(" + getWidth() + ", " + getHeight() + ")");
            myCanvas = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            dirtyCanvas = false;
        }
        return myCanvas;
    }

    /**
     * Passes along to the parent Window that the algorithm is done and it should update its GUI accordingly.
     */
    public void SortIsFinished()
    {
        myFrame.endRunGUI();
        lastUpdate = new Date(0);// this sets the last update to 12:00am 1/1/1970
    }

    public void setDelayMS(int delay)
    {
        delay_ms = delay;
    }

    /**
     * draws the current visualization to the screen. This is separate from visualizeData, because visualizeData is
     * running as a background thread, perhaps more often than we can update the screen. This just copies the latest
     * version of the "canvas" image.
     * @param g - the Graphics object that represents the panel window.
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (dirtyCanvas)
        {
            // pause a moment to let visualizer catch up.
            try
            {
                Thread.sleep(20);
            }catch (InterruptedException iExp)
            {
                return;
            }
        }
        getCanvas();
        if (myCanvas == null)
            return;
        synchronized (myCanvas)
        {
            g.drawImage(myCanvas,0,0,this);
        }

    }

    /**
     * do any setup for drawing an array of N integers that only needs to be done once, rather than repeatedly
     * @param N - the size of the array you'll be asked to visualize
     */
    public void prepForArrayWithSizeN(int N)
    {
        System.out.println("Prepping - new array size "+N);
        // TODO: Optional. If there are any pre-calculations (such as generating a list of sines and cosines) you wish
        //  to do when N changes, before the algorithm runs, add them here.

    }

    /**
     * draw a representation of the array on the screen.
     * Note: you'll want this to be very fast, so if you are using time-intensive functions, like generating 10,000
     * colors or calling sine and cosine for 10,000 angles, it might make sense to pre-calculate them in "prepForArrayWithSizeN"
     * (above), store them in an array, and then access items in that array here.
     *
     * @param array - an array of N integers, valued 0 <= x < 4N. There will be occasional
     *              duplicates.
     */
    public void visualizeData(int [] array)
    {
        Date now = new Date();
        // if it has been over 1/60 second since last update
        boolean fancy = true;

        System.out.println("Got here.");
        if ((now.getTime() - lastUpdate.getTime())>17) // 33 ms = 1/30s
        {
            // visual_count++;
            getCanvas();
            if (myCanvas == null)
                return;
            synchronized (myCanvas)
            {
                Graphics g = myCanvas.getGraphics();
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());

                int N = array.length;

                //TODO: Enter your code here!
                for (int i=0; i<N; i++)
                {
                    g.setColor(Color.pink);
                    if (N > getWidth())
                    {
                        g.drawLine((i*getWidth())/N, getHeight(), (i*getWidth())/N, getHeight()/(4*N)*array[i]);  ;
                    }
                    else {
                    g.fillRect((i*getWidth())/N,
                            (int)(getHeight()-(1.0*getHeight())/(4*N)*array[i]),
                            (int)(getWidth()/(N*1.0)),
                            (int)(1.0 *getHeight()/(4*N)*array[i]));
                    }
                }



            }
            repaint();
            lastUpdate = new Date();
        }
        try
        {
            Thread.sleep(delay_ms);
        }catch (InterruptedException iExp)
        {
            return;
        }
    }

    /**
     * tell this panel that it needs to make a new canvas, most likely because we have resized the window.
     */
    public void setDirtyCanvas()
    {
        dirtyCanvas = true;
    }

    /**
     * override the lastUpdate timer, setting it to 1970, so the visualizer will run
     * next time, regardless of last actual update.
     */
    public void resetTimer()
    {
        lastUpdate = new Date(0);
    }

}
