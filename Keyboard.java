
/**
 * Listens to the keyboard, and contains static methods for retieval of key states
 * 
 * @author Mason Haberle 
 * @version 5/7/16
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Keyboard implements KeyListener
{
    
    private boolean shiftP = false;
    private boolean uP = false;
    private boolean dP = false;
    private boolean rP = false;
    private boolean lP = false;
    private boolean zP = false;
    private boolean xP = false;
    
    //Detects when a key is pressed and updates booleans to reflect
    public void keyPressed(KeyEvent e)
    {
        int id = e.getID();
        
        if(id != KeyEvent.KEY_TYPED)
        {
            int keyCode = e.getKeyCode();
            
            if(keyCode == KeyEvent.VK_SHIFT)
                shiftP = true;
            if(keyCode == KeyEvent.VK_UP)
                uP = true;
            if(keyCode == KeyEvent.VK_DOWN)
                dP = true;
            if(keyCode == KeyEvent.VK_RIGHT)
                rP = true;
            if(keyCode == KeyEvent.VK_LEFT)
                lP = true;
            if(keyCode == KeyEvent.VK_Z)
                zP = true;
            if(keyCode == KeyEvent.VK_X)
                xP = true;
        }
    }
     
    //Detects when a key is released and updates booleans to reflect
    public void keyReleased(KeyEvent e)
    {
        int id = e.getID();
        
        if(id != KeyEvent.KEY_TYPED)
        {
            int keyCode = e.getKeyCode();
            
            if(keyCode == KeyEvent.VK_SHIFT)
                shiftP = false;
            if(keyCode == KeyEvent.VK_UP)
                uP = false;
            if(keyCode == KeyEvent.VK_DOWN)
                dP = false;
            if(keyCode == KeyEvent.VK_RIGHT)
                rP = false;
            if(keyCode == KeyEvent.VK_LEFT)
                lP = false;
            if(keyCode == KeyEvent.VK_Z)
                zP = false;
            if(keyCode == KeyEvent.VK_X)
                xP = false;
        }
    }
    
    //No keys typed, but need to implement method
    public void keyTyped(KeyEvent e)
    {
    }
    
    public boolean shiftp()
    {
        return shiftP;
    }
    
    public boolean up()
    {
        return uP;
    }
    
    public boolean dp()
    {
        return dP;
    }
    
    public boolean rp()
    {
        return rP;
    }
    
    public boolean lp()
    {
        return lP;
    }
    
    public boolean zp()
    {
        return zP;
    }
    
    public boolean xp()
    {
        return xP;
    }
}
