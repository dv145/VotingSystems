package V;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class Approval extends JPanel implements MouseListener
{
    private boolean on = false;
    private int x;
    private int y;
    Point p;
    private static int cand;
    ArrayList<Integer> vLoc = new ArrayList<Integer>();
    static ArrayList<Integer> tally = new ArrayList<Integer>();
    int count;
    public Approval()
    {
       cand=0;
       count=0;
       addMouseListener(this);
        setSize(300,300);  
        setLayout(null);  
        setVisible(true);  
    }
    
    public void paint (Graphics g)
    {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,1000,1000);
        g.setColor(Color.gray);
        g.fillRect(45,45,400, 400);
        g.setColor(Color.black);
        for (int i=0; i<50; i++)
        {
           int x=(int)(Math.random()*(430-50)+50);
           int y=(int)(Math.random()*(430-50)+50);
           vLoc.add(x);
           vLoc.add(y);
           g.fillOval(x,y, 5, 5);
        }
        for (int i=0; i<cand; i++)
        {
            g.setColor(colour(i+1));
            String a = "Candidate " + (i+1) + " was approved by " + tally.get(i) + " voters";
            g.drawString(a, 500, 50+i*20);
        }
        g.setColor(Color.black);
        g.fillRect(40,40,405,5);
        g.fillRect(40,40,5, 405);
        g.fillRect(40,445,405,5);
        g.fillRect(445,40,5, 410);
        on=true;
    }
    
    public void mouseClicked(MouseEvent e)
    {
    }
    public void mouseEntered(MouseEvent e)
    {
    }
    public void mouseExited(MouseEvent e)
    {
    }
    public void mousePressed(MouseEvent e)
    {
        if(on){
        if (e.getX()<430&&e.getX()>50 && e.getY()<430&&e.getY()>50)
        {
            Graphics g = getGraphics();
            g.setColor(colour(cand+1));
            g.fillRect(e.getX(), e.getY(), 20, 20);
            g.setColor(Color.DARK_GRAY);
            g.drawOval (e.getX()-90, e.getY()-90, 200, 200);
            g.setColor(colour(cand+1));
            //g.drawRect (e.getX()-80, e.getY()-80, 175, 175); Uncomment this line to use rectangles which are 100% accurate
            Rectangle rect = new Rectangle(e.getX()-80, e.getY()-80, 175, 175);
            for (int i=0; i<vLoc.size();)
            {
                int x=vLoc.get(i);
                int y=vLoc.get(i+1);
                Rectangle voRec=new Rectangle(x, y, 5, 5);
                if(voRec.intersects(rect))
                {
                    count++;
                    g.setColor(colour(cand+1));
                    g.fillOval(x, y, 5, 5);
                }
                
                i+=2;
            }
            tally.add(count);
            count=0;
            cand++;
        }
        if (cand>0)
        {
            for (int i=0; i<cand; i++)
            {
                Graphics g = getGraphics();
                g.setColor(colour(i+1));
                String a = "Candidate " + (i+1) + " was approved by " + tally.get(i) + " voters";
                g.drawString(a, 500, 50+i*20);
            }
        }
        if(cand>0)
        {
            
            int winner = max()+1;
            Graphics g = getGraphics();
            g.setColor(colour(winner));
            g.fillRect(40,40,405,5);
            g.fillRect(40,40,5, 405);
            g.fillRect(40,445,405,5);
            g.fillRect(445,40,5, 410);
        }
    }
        if (cand==9) {on=false;}
    }
    public void mouseReleased(MouseEvent e)
    {
        
    }
    static Color colour(int num)
    {
            switch(num)
            {
                case 1: return Color.orange;
                case 2: return Color.green;
                case 3: return Color.pink;
                case 4: return Color.yellow;
                case 5: return Color.cyan;
                case 6: return Color.red;
                case 7: return Color.magenta;
                case 8: return Color.blue;
            }
            return Color.black;
    }
    static int max()
    {
        int m=0;
        for (int i=0; i<tally.size(); i++)
        {
            if (tally.get(i)>m)
                m=tally.get(i);
        }
        for (int i=0; i<tally.size(); i++)
        {
            if (tally.get(i)==m)
                return(i);
        }
        return 0;
    }
}


