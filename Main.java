
/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Main
{
    private static JFrame f;
    private static Panel p;
    private static StartPanel sp;
    private static Keyboard kybd;
    private static Bullethell game;
    public static MasterPicture masterImg;
    private static JButton intButton;
    private static JButton ezButton;
    private static JButton hardButton;
    
    public static final int FPS = 60;
    
    public static void main(String[] args)
    {
        f = new JFrame("Bullethell");
        f.setBounds(100, 50, 640, 640);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mainStart();
    }
    
    public static Panel getP()
    {
        return p;
    }
    
    public static Keyboard kybd()
    {
        return kybd;
    }
    
    public static Bullethell bHell()
    {
        return game;
    }
    
    public static void mainStart()
    {
        sp = new StartPanel();
        
        intButton = new JButton("Intermediate");
        intButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                mainEnd(1);
            }
        });
        
        ezButton = new JButton("Easy");
        ezButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                mainEnd(0);
            }
        });
        
        hardButton = new JButton("Hard");
        hardButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                mainEnd(2);
            }
        });
        
        sp.setBounds(100, 50, 640, 640);
        intButton.setBounds(220, 460, 200, 60);
        ezButton.setBounds(220, 380, 200, 60);
        hardButton.setBounds(220, 540, 200, 60);
        f.add(sp);
        sp.add(intButton);
        sp.add(ezButton);
        sp.add(hardButton);
        f.setVisible(true);
        sp.requestFocusInWindow();
        sp.repaint();
    }
    
    public static void mainEnd(int lvl)
    {
        f.remove(sp);
        f.update(f.getGraphics());
        p = new Panel();
        kybd = new Keyboard();
        f.add(p);
        p.addKeyListener(kybd);
        p.requestFocusInWindow();
        f.setVisible(true);
        String[] keys=
        {
            "red ball",
            "blue am",
            "blue ball",
            "purp kun",
            "green am",
            "score",
            "power",
            "green ball"
        };
        int[][] coords=
        {
            {64, 64, 32, 32},
            {6*32, 7*32, 32, 32},
            {8*32, 3*32, 32, 32},
            {3*32, 5*32, 32, 32},
            {10*32, 7*32, 32, 32},
            {7*32, 12*32, 16, 16},
            {5*32, 12*32, 16, 16},
            {10*32, 64, 32, 32}
        };
        masterImg=new MasterPicture("bullets.png",keys,coords);
        
        game = new Bullethell(lvl);
    }
    
    public static void resetGame()
    {
        f.remove(p);
        f.update(f.getGraphics());
        game = null;
        mainStart();
    }
}
