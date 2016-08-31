
/**
 * Write a description of class Picture here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.Color;
import java.io.*;
public class Picture
{
    private BufferedImage img;
    public Picture(String fileName)
    {
        setImage(fileName);
    }
    public Picture(Picture master,int[]coords)
    {
        img=master.getImg().getSubimage(coords[0],coords[1],coords[2],coords[3]);
    }
    public BufferedImage getImg()
    {
        return img;
    }
    public void draw(Graphics g,int x,int y,double rot)
    {
        Graphics2D cmp2D=(Graphics2D)g;
        if(rot != 0)
        {
            cmp2D.translate(x,y);
            cmp2D.rotate(rot);
            cmp2D.drawImage(img,-img.getWidth()/2,-img.getHeight()/2,img.getWidth(),img.getHeight(),null);
            cmp2D.rotate(-rot);
            cmp2D.translate(-x,-y);
        }
        else
        {
            cmp2D.drawImage(img, x, y, img.getWidth(), img.getHeight(),null);
        }
    }
    public void setImage(String fileName)
    {
        try
        {
            img=ImageIO.read(new File(fileName));
            //img=setColor(img);
        }
        catch(IOException e)
        {
            System.out.println("WRONG NAME "+fileName);
            System.exit(1);
        }
    }
}
