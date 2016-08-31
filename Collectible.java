
/**
 * Write a description of class Collectible here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


import java.awt.*;
import javax.swing.*;

public class Collectible
{
    private double x;
    private double y;
    private int value;
    private String type;
    private double sx;
    private double sy;
    private double ax;
    private double ay;
    private boolean falling;
    
    public Collectible(double x, double y, String type, int value)
    {
        this.x = x;
        this.y = y;
        this.value = value;
        this.type = type;
        sx = 0;
        sy = 0;
        ax = 0;
        ay = .05;
        falling = true;
    }
    
    public void move()
    {
        
        Player p = Main.bHell().getPlayer();
        if(falling)
        {
            if((p.cx() - x)*(p.cx() - x)+(p.cy() - y)*(p.cy() - y) < 10000)
                ax = (p.cx() - x)/100.0;
            else
            {
                ax = 0;
                sx = 0;
            }
        }
        else
        {
            ax = 0;
            ay = 0;
        }
        y += sy;
        sy += ay;
        x += sx;
        sx += ax;
        
    }
    
    public void dr(Graphics g)
    {
        Main.masterImg.draw(type, g, (int)x,(int)y, 0);
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public void setS(double speedx, double speedy)
    {
        sx = speedx;
        sy = speedy;
    }
    
    public void falling(Boolean t)
    {
        falling = t;
    }
    
    public int getVal()
    {
        return value;
    }
    
    public String getType()
    {
        return type;
    }
}
