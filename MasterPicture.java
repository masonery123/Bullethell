
/**
 * Write a description of class Picture here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import java.util.HashMap;
public class MasterPicture extends Picture
{
    private HashMap<String,Picture> pictures;
    public MasterPicture(String fileName,String[] keys,int[][] parts_coords)
    {
        super(fileName);
        pictures=new HashMap<String,Picture>();
        for(int i=0;i<keys.length;i++)
        {
            pictures.put(keys[i],new Picture(this,parts_coords[i]));
        }
    }
    public Picture getPic(String key)
    {
        return pictures.get(key);
    }
    public void draw(String key,Graphics g,int x,int y,double rot)
    {
        pictures.get(key).draw(g,x,y,rot);
    }
}
