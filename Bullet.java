//Bullet class
//Bullets move with a velocity across the screen and can be aimed or unaimed.
//Mason Haberle 4/11/16

import java.awt.*;
import javax.swing.*;

public class Bullet
{
    private double w = 32;
    private double h = 32;
    private double sx;
    private double sy;
    private double x;
    private double y;
    private double hRad;
    private Player player;
    private int[] picLoc;
    private String pic;
    private int power;
  
    public Bullet(double x, double y, double sx, double sy, int power)
    {
        this(x,y,sx,sy,power,"red ball");
    }
  
    public Bullet(double x, double y, double sx, double sy, int power, String picture)
    {
        this.x = x - w/2;
        this.y = y - h/2;
        this.sx = sx;
        this.sy = sy;
        hRad = 10;
        player = Main.bHell().getPlayer();
        pic = picture;
        this.power = power;
    }
    
  
    /*Possibly now useless
    public Bullet(double x, double y, double sx, double sy, double w, double h)
    {
        this.x = x - w/2;
        this.y = y - h/2;
        this.sx = sx;
        this.sy = sy;
        this.w = w;
        this.h = h;
        hRad = 10;
        player = Bullethell.getPlayer();
        pic = "b-rc1.png";
    }*/
  
    public Bullet(double x, double y, double v, int power, String picture)
    {
        this.x = x - w/2;
        this.y = y - h/2;
        player = Main.bHell().getPlayer();
        this.sx = player.cx() - x;
        this.sy = player.cy() - y;
        hRad = 10;
        this.sx = sx;
        this.sy = sy;
        double h = Math.sqrt(sx*sx + sy*sy);
        sx *= (v/h);
        sy *= (v/h);
        pic = picture;
        this.power = power;
    }
  
    public Bullet(Bullet b)
    {
        x = b.getX();
        y = b.getY();
        sx = b.getSX();
        sy = b.getSY();
        pic = b.getPic();
        hRad = 10;
        power = b.getPower();
    }
  
    public double getX()
    {
        return x;
    }
  
    public double getY()
    {
        return y;
    }
  
    public double cx()
    {
        return x + w/2;
    }
  
    public double cy()
    {
        return y + h/2;
    }
  
    public double getSX()
    {
        return sx;
    }
  
    public double getSY()
    {
        return sy;
    }
  
    public String getPic()
    {
        return pic;
    }
    
    public int getPower()
    {
        return power;
    }
    
    public void incrementV(double vx, double vy)
    {
        sx += vx;
        sy += vy;
    }
    
    public void setV(double v)
    {
        double v1 = Math.sqrt(sx*sx + sy*sy);
        sx *= v/v1;
        sy *= v/v1;
    }
  
    public void move()
    {
        x += 100.0*sx/(float)Main.FPS;
        y += 100.0*sy/(float)Main.FPS;
    }
  
    public void dr(Graphics g)
    {
        /*
        ImageIcon i = new ImageIcon(pic);
        i.paintIcon(Main.getP(), g, (int)getX(), (int)getY());
        */
        double angle = 0;
        if(sx == 0)
        {
            if(sy > 0) angle = Math.PI;
            else if(sy < 0) angle = 0;
        }
        else
        {
            angle = -(Math.PI/2.0 - Math.atan(sy/sx));
            if(sx > 0) angle += Math.PI;
        }
        Main.masterImg.draw(pic,g,(int)cx(),(int)cy(), angle);
    }
  
    public void turn(double d)
    {
        d = Math.toRadians(d);
        double r1 = Math.sqrt(sx*sx+sy*sy);
        double sxc = sx;
        double syc = sy;
        sx = sxc*Math.cos(d)-syc*Math.sin(d);
        sy = sxc*Math.sin(d)+syc*Math.cos(d);
        double r2 = Math.sqrt(sx*sx+sy*sy);
        sx *= r1/r2;
        sy *= r1/r2;
        
    }
    
    public Bullet[] fan(int n, double d)
    {
        this.turn(360-d/2);
        Bullet[] b = new Bullet[n];
        for(int i = 1; i < n; i++)
        {
            b[i] = (new Bullet(this));
            b[i].turn(i*d/(n-1));
        }
        b[0] = this;
        return b;
    }
    
    public boolean attack(Turret t)
    {
        if(Math.sqrt((cx() - t.cx())*(cx() - t.cx()) + (cy() - t.cy())*(cy() - t.cy())) < t.hRad() + hRad)
        {
            t.hit(10+power);
            return true;
        }
        return false;
    }
}