/*
 * Description : This is the first frame which runs, when we open our application. It includes a loading screen.
 * Author : Sai Karthik
 * 
 */
package Bootathon;

import Bootathon.uiworks.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class LoadFrame {
    LoadFrame()
    {
        MyFrame f = new MyFrame(3);
        f.setBounds(500,100,600,800);
        JProgressBar p = new JProgressBar();
        p.setBounds(170,500,200,20);
        p.setForeground(new Color(3, 158, 8));
        p.setValue(0);
        p.setBackground(new Color(255,255,255));
        p.setStringPainted(true);
       
        p.setFont(new Font("",Font.BOLD,12));
        JLabel l = new JLabel(new ImageIcon("C:/Users/rahul/Documents/NetBeansProjects/Bootcamp-Java/src/Bootathon/img/dis1.gif"));
        l.setBounds(150,250,300,150);
        JLabel l1 = new JLabel("\u00A9"+" copyrights - Team 16");
        l1.setBounds(210,550,200,50);
        f.setVisible(true);
        Container c = f.getContentPane();
        c.setLayout(null);
        c.add(p);
        c.add(l);
        c.add(l1);
        try{
            Thread.sleep(500);
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }
        
        for(int i=0;i<101;i++)
        {
            p.setValue(i);
            p.setString("Loading  " + p.getValue() + " %");
            try{
                Thread.sleep(45);
            }
            catch(InterruptedException ee)
            {
                System.out.println(ee);
            }
            
        }
        new LoginPage();
        f.dispose();
    }
    public static void main(String[] args) {
       new LoadFrame();
    }
    
}
