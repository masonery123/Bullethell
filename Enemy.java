//Enemy Class
//Enemies can move around and shoot with different velocities
//Mason Haberle 4/13/16

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Enemy extends Turret
{
    private double startH;
    private int w = 50;
    private int h = 50;
    private double sx;
    private double sy;
    private double sb;
    private double shotFreq;
    private int t;
    private String pic;
   
    public Enemy(double x, double y, double sx, double sy, double sb, double shotFreq, int max, double health)
    {
        super(x, y, shotFreq, max, health, 20);
        startH = health;
        this.sx = sx;
        this.sy = sy;
        this.shotFreq = shotFreq;
        this.sb = sb;
        pic = "e1.png";
        t = 0;
    }
  
    public Enemy(double x, double y, double sx, double sy, int shotFreq, int max, double health)
    {
        super(x, y, shotFreq, max, health, 15);
        this.sx = sx;
        this.sy = sy;
        this.shotFreq = shotFreq;
        sb = 0;
        t = 0;
    }
  
    public double getSX()
    {
        return sx;
    }
  
    public double getSY()
    {
        return sy;
    }
  
    public double getSB()
    {
        return sb;
    }
  
    public double cx()
    {
        return this.getX() + w/2;
    }
  
    public double cy()
    {
        return this.getY() + h/2;
    }
  
    /*public int getShotFreq()
    {
        return shotFreq;
    }*/
  
    public void setSpeed(double sx, double sy)
    {
        this.sx = sx;
        this.sy = sy;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        //shoot("purp kun");
        //shootFan(2, 20, "red ball");
        //for(double i = 0.05; i < 2; i+=.15)
        //Bullet b = new Bullet(cx()-16, cy()-16, 0, 5, "red ball");
        //b.turn((3*t)%360);
        if(Bullethell.hasWon() || Bullethell.hasLost())
        {
            getT().stop();
            return;
        }
        shoot();
        t++;
    }
  
    public void move()
    {
        ArrayList<Bullet> b = this.getBullets();
        this.setPos((this.getX() + sx),(this.getY() + sy));
        for(int i = b.size()-1; i >= 0; i--)
        {
            if(b.get(i).attack(Main.bHell().getPlayer())) break;
            b.get(i).move();
            if(b.get(i).getX() < -30 || b.get(i).getX() > 510 || b.get(i).getY() < -30 || b.get(i).getY() > 670)
            {
                b.remove(i);
            }
            //b.remove(i);
        }
    }
  
    public void dr(Graphics g)
    {
        /*
        fill((int)((startH - this.getHealth())*255/startH), (int)(this.getHealth()*255/startH), 0);
        rect((float)this.getX(), (float)this.getY(), (float)w, (float)h);
        fill(255, 255, 255);
        */
        ImageIcon i = new ImageIcon(pic);
        i.paintIcon(Main.getP(), g, (int)getX(), (int)getY());
        //g.fillRect((int)getX(), (int)getY(), (int)w, (int)h);
    }
  
    public void shoot()
    {
        ArrayList<Bullet> b = this.getBullets();
        b.add(0, new Bullet(cx()-16, cy()-16, sb, 0, "red ball"));
        while(b.size() > this.getMax())
            b.remove(b.size()-1);
    }
  
    public void shoot(String pic)
    {
        ArrayList<Bullet> b = this.getBullets();
        b.add(0, new Bullet(cx()-16, cy()-16, sb, 0, pic));
        while(b.size() > this.getMax())
            b.remove(b.size()-1);
    }
  
    public void shoot(double sx, double sy, String pic)
    {
        ArrayList<Bullet> b = this.getBullets();
        b.add(0, new Bullet(cx()-16, cy()-16, sx, sy, 0, pic));
        while(b.size() > this.getMax())
            b.remove(b.size()-1);
    }
  
    public void shootFan(int n, double d)
    {
        ArrayList<Bullet> b = this.getBullets();
        Bullet[] newB = (new Bullet(cx()-16, cy()-16, sb, 0, "red ball")).fan(n, d);
        for(int i = 0; i < newB.length; i++)
            b.add(0, newB[i]);
        while(b.size() > this.getMax())
            b.remove(b.size()-1);
    }
  
    public void shootFan(int n, double d, String pic)
    {
        ArrayList<Bullet> b = this.getBullets();
        Bullet[] newB = (new Bullet(cx()-16, cy()-16, sb, 0, pic)).fan(n, d);
        for(int i = 0; i < newB.length; i++)
            b.add(0, newB[i]);
        while(b.size() > this.getMax())
            b.remove(b.size()-1);
    }
    
    public void shootFan(int n, double d, Bullet bullet)
    {
        ArrayList<Bullet> b = this.getBullets();
        Bullet[] newB = bullet.fan(n, d);
        for(int i = 0; i < newB.length; i++)
            b.add(0, newB[i]);
        while(b.size() > this.getMax())
            b.remove(b.size()-1);
    }
  
    public void shootFan(int n, double d, double s, String pic)
    {
        ArrayList<Bullet> b = this.getBullets();
        Bullet[] newB = (new Bullet(cx()-16, cy()-16, s, 0, pic)).fan(n, d);
        for(int i = 0; i < newB.length; i++)
            b.add(0, newB[i]);
        while(b.size() > this.getMax())
            b.remove(b.size()-1);
    }
  
    public void bounce()
    {
        if(this.getX() > 480-w)
            sx = -1*Math.abs(sx);
        if(this.getX() < 0)
            sx = Math.abs(sx);
        if(this.getY() > 640-h)
            sy = -1*Math.abs(sy);
        if(this.getY() < 0)
            sy = Math.abs(sy);
    }
    
    public void spawnCollects()
    {
        for(int i = 0; i < 5; i++)
        {
            double mag = Math.random()*50;
            double ang = Math.random()*2*Math.PI;
            Main.bHell().getCollects().add(new Collectible(mag*Math.cos(ang)+cx()-8, mag*Math.sin(ang)+cy()-8, "score", 1000));
    
        }
        for(int i = 0; i < 2; i++)
        {
            double mag = Math.random()*50;
            double ang = Math.random()*2*Math.PI;
            Main.bHell().getCollects().add(new Collectible(mag*Math.cos(ang)+cx()-8, mag*Math.sin(ang)+cy()-8, "power", 3));
        }
    }
    
    
}