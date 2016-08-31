/*Main
//This executes the game.  So far, it makes enemies move and redraws the screen.
//Mason Haberle 4/13/16
*/
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//;

public class Bullethell implements ActionListener
{
    //Declaraion and initialization of main variables
    private long t;
    private ArrayList<Enemy> enemies;
    private ArrayList<Collectible> collects;
    private Player player;
    private Timer timer;
    private static boolean hasWon = false;
    private static boolean hasLost = false;;
    private boolean isSetup;
    private int lvl;
    
    public Bullethell(int lvl)
    {
        isSetup = true;
        hasWon = false;
        hasLost = false;
        player = new Player(225, 500, 50, 50);
        collects = new ArrayList<Collectible>();
        enemies = new ArrayList<Enemy>();
        t = 0;
        this.lvl = lvl;
        
        for(int i = 0; i < lvl+1; i++)
            spawnRand();
        
        timer = new Timer((int)1000.0/Main.FPS, this);
        timer.start();
        isSetup = false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //add enemies to game every once in a while, chose randomly which to add
        if(t % (120 + enemies.size()*(100-lvl*25)) == 0 && !hasLost && !hasWon)
        {
            spawnRand();
            t = 0;
        }
        
        if(Main.kybd().xp() && (hasWon || hasLost))
        {
            timer.stop();
            Main.resetGame();
            return;
        }
        
        draw();
        Main.getP().repaint();
        t++;
    }
    
    //Redraws the screen once evey frame
    private void draw()
    { 
        //Exits if player dead
        if(player.getHealth() <= 0)
        {
            hasLost = true;
            player.setCS(true);
            return;
        }
        
        //Exits if all enemies dead
        if(enemies.size() == 0)
        {
            hasWon = true;
            player.setCS(true);
            return;
        }
        
        //Player moves
        player.move();
        
        //Iterating through the enemy list, enemies move, bounce
        for(int i = 0; i < enemies.size(); i++)
        {
            Enemy enemy = enemies.get(i);
            enemy.bounce();
            enemy.move();
            
            if(enemy.getHealth() <= 0)
            {
                //Removes enemies from the screen
                enemy.spawnCollects();
                enemies.remove(i);
            }
        }
        
        //Put collectibles on the screen
        for(int i = collects.size()-1; i >= 0; i--)
        {
            collects.get(i).move();
            if(collects.get(i).getY() > 660) collects.remove(i);
        }
        
    }
    
    public Player getPlayer()
    {
        return player;
    }
    
    public ArrayList<Enemy> getEnemies()
    {
        return enemies;
    }
    
    public ArrayList<Collectible> getCollects()
    {
        return collects;
    }
    
    public static boolean hasWon()
    {
        return hasWon;
    }
    
    public static boolean hasLost()
    {
        return hasLost;
    }
    
    public boolean isSetup()
    {
        return isSetup;
    }
    
    //Reset timer the screen - THIS IS A SCREENCLEAR!
    public void resetT()
    {
        player.getBullets().clear();
        
        for(Enemy i : enemies)
            i.getBullets().clear();
        
        Main.getP().repaint();
        
        player.setCS(true);
        
        if(player.getHealth() <= 0)
        {
            hasLost = true;
            return;
        }
        
        if(enemies.size() == 0)
        {
            hasWon = true;
            return;
        }
        
        timer.stop();
        timer.setInitialDelay(0);
        timer.start();
    }
    
    //Spawns a random enemy with difficulty based on lvl
    private void spawnRand()
    {
        double random = Math.random();
        if(random < .24)
            enemies.add(new RandomFire((int)(Math.random()*400), 10, 0, 0, 1.5+lvl*.5, random+.2-.1*lvl, 800, 250+50*lvl, random*(-280) + 90));
        else if(random < .48)
            enemies.add(new Enemy((int)(Math.random()*400), 10, 2, 0, 2.5+lvl*.5, 0.5-lvl*.1, 500, 300+50*lvl));
        else if(random < .7)
            enemies.add(new Fanner((int)(Math.random()*400), 10, 1, 0, 1.5+lvl*.5, 0.4-lvl*.1, 800, 250+50*lvl, (int)(1.5*lvl*lvl - 2.5*lvl + 4), 90-lvl*10));
        else if(random < .92)
            enemies.add(new Homer((int)(Math.random()*400), 10, 4, 0, 3, .05, 1 + 2*lvl*lvl, 300));
        else
            enemies.add(new Petal((int)(Math.random()*400), 10, 0, 0, 2.5+lvl*.5, 1-lvl*.2, 1200, 200+50*lvl, 3+lvl*2, 13, 10));
    }
}