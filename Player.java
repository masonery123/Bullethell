//Player
//Player uses arrow keys to move around and shoots straight upwards.
//Mason Haberle 4/13/16

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Player extends Turret
{
    private double s;
    private double w = 30;
    private double h = 68;
    private int score;
    private int power;
    private boolean collectScreen;
    private int justBombed;
   
    public Player(int x, int y, int m, int hp)
    {
        super(x, y, .1, m, hp, 0);
        s = 2;
        score = 0;
        power = 0;
        collectScreen = false;
        justBombed = 0;
    }
  
    public double cx()
    {
        return this.getX() + w/2;
    }
  
    public double cy()
    {
        return this.getY() + h/2;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public int getPower()
    {
        return power;
    }
    
    public void setCS(boolean cs)
    {
        collectScreen = cs;
    }
  
    public void move()
    {
        ArrayList<Bullet> b = this.getBullets();
        
        if(Main.kybd().shiftp())
            s = 2;
        else
            s = 6;
        s*=100.0/Main.FPS;
        if(Main.kybd().up())
            move(0, -1*s);
        if(Main.kybd().dp())
            move(0, s);
        if(Main.kybd().rp())
            move(s, 0);
        if(Main.kybd().lp())
            move(-1*s, 0);
        if(Main.kybd().xp() && power >= 10 && justBombed <= 0)
        {
            Main.bHell().resetT();
            power -= 10;
            justBombed = 20;
        }
            
        
        if(cy() < 150) collectScreen = true;
            
        for(int i = 0; i < b.size(); i++)
        {
            b.get(i).move();
            for(Enemy e : Main.bHell().getEnemies())
            {
                if(b.get(i).attack(e))
                {
                    b.remove(i);
                    break;
                }
            }
        }
        
        for(int i = Main.bHell().getCollects().size()-1; i >= 0; i--)
        {
            Collectible c = Main.bHell().getCollects().get(i);
            if(collectScreen)
            {
                c.falling(false);
                double ds = Math.sqrt((c.getX() - cx())*(c.getX() - cx()) + (c.getY() - cy())*(c.getY() - cy()));
                c.setS(5*(cx()-c.getX())/(ds),5*(cy()-c.getY())/(ds));
            }
            if((c.getX() - cx())*(c.getX() - cx()) + (c.getY() - cy())*(c.getY() - cy()) < 400)
            {
                if(c.getType().equals("power")) power += c.getVal();
                else score += c.getVal();
                Main.bHell().getCollects().remove(i);
            }
        }
        
        if(Main.bHell().getCollects().size() == 0) collectScreen = false;
    }
  
    public void move(double dx, double dy)
    {
        if(cx() + dx > 475) dx = -1*Math.abs(dx);
        if(cx() + dx < 5) dx = Math.abs(dx);
        if(cy() + dy > 605) dy = -1*Math.abs(dy);
        if(cy() + dy < 5) dy = Math.abs(dx);
        this.setPos(this.getX() + dx, this.getY() + dy);
    }
  
    public void dr(Graphics g)
    {
        /*fill(105, 255, 0);
        ellipse((float)this.getX(), (float)this.getY(), (float)w, (float)h);
        fill(255, 255, 255);*/
        ImageIcon i = new ImageIcon("plyr.png");
        i.paintIcon(Main.getP(), g, (int)getX(), (int)getY());
        //g.fillOval((int)getX(), (int)getY(), (int)w, (int)h);
    }
  
    public void shoot()
    {
        ArrayList<Bullet> b = this.getBullets();
        b.add(0, new Bullet(cx()-16, cy()-16, 0, -10, power, "blue am"));
        if(b.size() > this.getMax())
            b.remove(b.size()-1);
    }
  
    public void actionPerformed(ActionEvent e)
    {
        if(Bullethell.hasWon() || Bullethell.hasLost())
        {
            getT().stop();
            return;
        }
        if(justBombed > 0) justBombed--;
        if(Main.kybd().zp())
            shoot();
    }
  
    public void hit(double hp)
    {
        super.hit(hp);
        //this.setPos(320, 420);
        Main.bHell().resetT();
    }
}