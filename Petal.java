
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

public class Petal extends Enemy
{
    private int n1;
    private int n2;
    private double d;
    public Petal(double x, double y, double sx, double sy, double sb, double shotFreq, int max, double health,
                    int n1, int n2, double d)
    {
        super(x, y, sx, sy, sb, shotFreq, max, health);
        this.n1 = n1;
        this.n2 = n2;
        this.d = d;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(Bullethell.hasWon() || Bullethell.hasLost())
        {
            getT().stop();
            return;
        }
        Bullet b = new Bullet(cx()-16, cy()-16, getSB(), 0, "green am");
        Bullet[] bs = b.fan(n1, d);
        for(Bullet i : bs)
            shootFan(n2, 360, i);
    }
}
