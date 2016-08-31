
/**
 * Write a description of class Homer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Homer extends Enemy
{
    public Homer(double x, double y, double sx, double sy, double sb, double shotFreq, int max, double health)
    {
        super(x, y, sx, sy, sb, shotFreq, max, health);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(Bullethell.hasWon() || Bullethell.hasLost())
        {
            getT().stop();
            return;
        }
        if(getBullets().size() < getMax()) shoot("green ball");
        for(Bullet b : getBullets())
        {
            b.incrementV((Main.bHell().getPlayer().cx() - b.cx())/400.0, (Main.bHell().getPlayer().cy() - b.cy())/400.0);
            b.setV(2);
        }
    }
}
