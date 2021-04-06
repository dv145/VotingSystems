package V;
import javax.swing.*;
import java.awt.event.*;  
import java.util.*;
public class Main
{
    public static void main(String[] args)
    {
        System.out.println("1.Approval\n2.Score");
        Scanner ob=new Scanner(System.in);
        JFrame frame = new JFrame();
        frame.setBounds(10,10,1000,1000);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        switch(ob.nextInt())
        {
            case 1:{    
                        frame.add(new Approval()); 
                        frame.setVisible(true); 
                        frame.setTitle("APPROVAL VOTING SIMULATION");
                        break;
                   }
            case 2:{    
                        frame.add(new Score()); 
                        frame.setVisible(true);
                        frame.setTitle("SCORE VOTING SIMULATION");
                        break;
                   }
        }        
    }
}
