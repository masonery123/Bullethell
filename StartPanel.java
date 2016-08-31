
/**
 * Write a description of class StartPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class StartPanel extends JPanel
{
    public StartPanel()
    {
        super();
        setLayout(null);
    }
    
    public void paintComponent(Graphics g)
    {
        g.clearRect(0, 0, 640, 640);
        g.setColor(Color.blue);
        g.fillRect(0, 0, 640, 640);
        g.setColor(Color.white);
        g.setFont(new Font("SansSerif", Font.BOLD, 36));
        g.drawString("Jack Flies A Spaceship and", 90, 50);
        g.drawString(" Gets Hit:  The Musical", 130, 90);
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.drawString("To play, use the arrow keys to move your ship.", 80, 150);
        g.drawString("Use shift to move slowly and hold Z to shoot.", 80, 180);
        g.drawString("You need to destroy all of the enemies to win,", 80, 210);
        g.drawString("but don't let your ship's core get hit by their bullets.", 60, 240);
        g.drawString("Catch the little falling blue and red powerups", 90, 270);
        g.drawString("to get a higher score or more power, respectively.", 75, 300);
        g.drawString("Move to the top of the screen to collect all powerups.", 70, 330);
        g.drawString("Consume 10 of your power to clear the screen with the X key!", 30, 360);
        ImageIcon i = new ImageIcon("plyr.png");
        Image img = i.getImage();
        Image newimg = img.getScaledInstance(120, 240, Image.SCALE_SMOOTH);
        i = new ImageIcon(newimg);
        i.paintIcon(Main.getP(), g, 470, 370);
        
        ImageIcon i2 = new ImageIcon("e1.png");
        Image img2 = i2.getImage();
        Image newimg2 = img2.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        i2 = new ImageIcon(newimg2);
        i2.paintIcon(Main.getP(), g, 5, 390);
    }
}
