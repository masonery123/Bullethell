
/**
 * Write a description of class RandomFire here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RandomFire extends Enemy
{
    private double d;
    public RandomFire(double x, double y, double sx, double sy, double sb, double shotFreq, int max, double health,
                    double d)
    {
        super(x, y, sx, sy, sb, shotFreq, max, health);
        this.d = d;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(Bullethell.hasWon() || Bullethell.hasLost())
        {
            getT().stop();
            return;
        }
        
        Bullet b = new Bullet(cx()-16, cy()-16, getSB(), 0, "blue ball");
        b.turn(Math.random()*d - (d/2));
        
        getBullets().add(0, b);
        while(getBullets().size() > this.getMax())
            getBullets().remove(getBullets().size()-1);
    }
}
