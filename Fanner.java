
/**
 * Write a description of class FanEnemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Fanner extends Enemy
{
    private int n;
    private double d;
    public Fanner(double x, double y, double sx, double sy, double sb, double shotFreq, int max, double health,
                    int n, double d)
    {
        super(x, y, sx, sy, sb, shotFreq, max, health);
        this.n = n;
        this.d = d;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(Bullethell.hasWon() || Bullethell.hasLost())
        {
            getT().stop();
            return;
        }
        
        shootFan(n, d, "purp kun");
    }
}
