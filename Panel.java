
/**
 * Write a description of class Panel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)30, 
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Panel extends JPanel
{
    public Panel()
    {
        super();
    }
    
    public void paintComponent(Graphics g)
    {
        g.clearRect(0, 0, 640, 640);
        
        if(Main.bHell().hasWon())
        {
            g.setColor(Color.blue);
            g.setFont(new Font("SansSerif", Font.BOLD, 48));
            g.drawString("ALL CLEAR", 100, 300);
            g.setFont(new Font("SansSerif", Font.BOLD, 24));
            g.drawString("Press X to play again!", 100, 350);
        }
        else if(Main.bHell().hasLost())
        {
            g.setColor(Color.red);
            g.setFont(new Font("SansSerif", Font.BOLD, 48));
            g.drawString("YOU LOST", 100, 300);
            g.setFont(new Font("SansSerif", Font.BOLD, 24));
            g.drawString("Press X to play again!", 100, 350);
        }
        else
        {
            Player p = Main.bHell().getPlayer();
            p.dr(g);
            //Contain in player dr!
            for(Bullet i : p.getBullets())
                i.dr(g);
            for(Collectible i : Main.bHell().getCollects())
                i.dr(g);
            for(Enemy i : Main.bHell().getEnemies())
            {
                i.dr(g);
                //Contain in enemy dr!!!
                for(Bullet j : i.getBullets())
                    j.dr(g);
            }
        }
        g.setColor(Color.blue);
        g.fillRect(480, 0, 160, 640);
        g.setColor(Color.white);
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.drawString("Lives: " + ((Main.bHell().getPlayer().getHealth()>=0)?(int)(Main.bHell().getPlayer().getHealth()/10):0), 485, 200);
        g.drawString("Score: " + (int)(Main.bHell().getPlayer().getScore()), 485, 240);
        g.drawString("Power: " + (int)(Main.bHell().getPlayer().getPower()), 485, 280);
        g.drawString("HELP ME", 485, 50);
        g.drawString("I'M JACK", 485, 80);
        g.drawString("HOW 2", 485, 110);
        g.drawString("FLY ROCKET", 485, 140);
    }
}
