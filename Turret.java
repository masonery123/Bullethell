//Turret class
//Turrets are entities that can shoot
//Mason Haberle 4/13/16

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

//Declaration of the abstract turret class - parent to the onscreen shooting entities
abstract public class Turret implements ActionListener
{
    //Instance variables - coords, bullet list + length, health and hitbox
    private double x;
    private double y;
    private int maxB;
    private ArrayList<Bullet> b;
    private double health;
    private double hRad;
    private Timer timer;
    
    //Constructor - perhaps user does not need max - put in more constructors
    public Turret(double x, double y, double evtTime, int max, double health, double r)
    {
        this.x = x;
        this.y = y;
        b = new ArrayList<Bullet>();
        maxB = max;
        this.health = health;
        hRad = r;
        timer = new Timer((int)(evtTime*1000*Main.FPS/60.0), this);
        timer.start();
    }
  
    //Returns hp
    public double getHealth()
    {
        return health;
    }
  
    //Returns x
    public double getX()
    {
        return x;
    }
  
    //Returns y
    public double getY()
    {
        return y;
    }
  
    //Returns centered x - right now turret has no w and h so these are abstract
    public abstract double cx();
  
    //Returns centered y
    public abstract double cy();
  
    //Returns the maximum number of bullets a turret can produce at a time
    public int getMax()
    {
        return maxB;
    }
    
    public Timer getT()
    {
        return timer;
    }
    
    //Returns the list of bullets currently present 
    public ArrayList<Bullet> getBullets()
    {
        return b;
    }
  
    //Returns the hitbox radius
    public double hRad()
    {
        return hRad;
    }
  
    //Sets the position of the turret
    public void setPos(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public abstract void actionPerformed(ActionEvent e);
    
    //Turrets all have to be able to redraw themselves every frame - that's what move is
    //Maybe change to redraw or something more general
    abstract public void move();
    
    //Shoots a bullet in the direction specified by its x and y velocities
    //Adds it to bullet list, removes oldest bullet from list if no more room
    public void shoot(double sx, double sy)
    {
        ArrayList<Bullet> b = this.getBullets();
        b.add(0, new Bullet(cx(), cy(), sx, sy, 10));
        while(b.size() > this.getMax())
            b.remove(b.size()-1);
    }
  
    //Shoots an aimed bullet with the velocity specified by v
    public void shoot(double v)
    {
        ArrayList<Bullet> b = this.getBullets();
        b.add(0, new Bullet(cx(), cy(), v, 10, "red ball"));
        while(b.size() > this.getMax())
            b.remove(b.size()-1);
    }
  
    //Hits the turret for hp health
    //SHOULD return a bool telling the game if dead instead of game checking...
    public void hit(double hp)
    {
        health -= hp;
    }
}