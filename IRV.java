package V;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class IRV extends JPanel implements MouseListener
{
    private boolean on = false;
    private int x;
    private int y;
    Point p;
    private static int cand;
    ArrayList<Integer> vLoc = new ArrayList<Integer>();
    static ArrayList<Integer> tally = new ArrayList<Integer>();
    static double[][] distances = new double[3][100];
    static int[] v1 = new int[3];
    static int[] v2 = new int[3];
    static int[] v3 = new int[3];
    int elim = 10;
    public IRV()
    {
       cand=0;
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
        for (int i=0; i<100; i++)
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
            String a = "Candidate " + (i+1) + " scored " + tally.get(i) + " points";
            g.drawString(a, 500, 50+i*20);
        }
        g.setColor(Color.black);
        g.fillRect(40,40,405,5);
        g.fillRect(40,40,5, 405);
        g.fillRect(40,445,405,5);
        g.fillRect(445,40,5, 410);
        on=true;
    }
    double getDist(int cX, int cY, int x, int y)
    {
        double dis = Math.sqrt(Math.pow((cX-x),2)+Math.pow((cY-y),2));
        return dis;
    }
    void rank1()
    {
        for(int i=0; i<100; i++)
        {
            if(distances[0][i]<=distances[1][i])
            {
                if(distances[0][i]<=distances[2][i])
                {
                    if(distances[1][i]<=distances[2][i])
                    {
                         v1[0]++;
                         v2[1]++;
                         v3[2]++;
                    }
                    else
                    {
                        v1[0]++;
                        v3[1]++;
                        v2[2]++;
                    }
                }
            }
            if (distances[1][i]<=distances[0][i])
            {
                if(distances[1][i]<=distances[2][i])
                {
                    if(distances[0][i]<=distances[2][i])
                    {
                         v2[0]++;
                         v1[1]++;
                         v3[2]++;
                    }
                    else
                    {
                        v2[0]++;
                        v3[1]++;
                        v1[2]++;
                    }
                }
            }
            if (distances[2][i]<=distances[1][i])
            {
                if(distances[2][i]<=distances[0][i])
                {
                    if(distances[0][i]<=distances[1][i])
                    {
                         v3[0]++;
                         v1[1]++;
                         v2[2]++;
                    }
                    else
                    {
                        v2[0]++;
                        v2[1]++;
                        v3[2]++;
                    }
                }
            }
        }
    }
    void rank2()
    {
        for (int i=0; i<100; i++)
        {
            if (elim == 0)
            {
                v1[0]=0;
                if(distances[1][i]<=distances[2][i])
                    v2[0]++;
                else
                    v3[0]++;
            }
            else if(elim == 1)
            {
                v2[0]=0;
                if(distances[0][i]<=distances[2][i])
                    v1[0]++;
                else
                    v3[0]++;
            }
            else if (elim == 2)
            {
                v3[0]=0;
                if(distances[0][i]<=distances[1][i])
                    v1[0]++;
                else
                    v2[0]++;
            }
        }
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
            
            for (int i=0; i<vLoc.size();)
            {
                int x=vLoc.get(i);
                int y=vLoc.get(i+1);
                for (int j=0; j<100; j++)
                {
                    distances[cand][j]=getDist(e.getX(), e.getY(), x, y);
                    System.out.println(getDist(e.getX(), e.getY(), x, y));
                }
                i+=2;
            }
            if(cand >=0)
            {
            rank1();
            int score=0;
            switch (cand)
            {
                case 0: score = v1[0]; break;
                case 1: score = v2[0]; break;
                case 2: score = v3[0]; break;
            }
            
                if(score<50)
                {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(e.getX(), e.getY(), 20, 20);
                    g.setColor(Color.red);
                    g.drawString("X",e.getX(), e.getY());
                    elim = cand;
                    rank2();
                    switch (cand)
                    {
                        case 0: score += v1[0]; break;
                        case 1: score += v2[0]; break;
                        case 2: score += v3[0]; break;
                    }
                }
            
            
            System.out.println(score);
            tally.add(score);
        }
            cand++;
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
        if (cand==4) {on=false;}
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


