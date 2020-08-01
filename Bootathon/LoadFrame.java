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
        //frame creation
        MyFrame load_frame = new MyFrame(3);
        load_frame.setBounds(500,100,600,800);
        
        //progress bar creation
        JProgressBar load_p = new JProgressBar();
        load_p.setBounds(170,500,200,20);
        load_p.setForeground(new Color(3, 158, 8));
        load_p.setValue(0);
        load_p.setBackground(new Color(255,255,255));
        load_p.setStringPainted(true);
        load_p.setFont(new Font("",Font.BOLD,12));
        
        //label creation
        JLabel load_l = new JLabel(new ImageIcon("C:/Users/rahul/Documents/NetBeansProjects/Bootcamp-Java/src/Bootathon/img/dis1.gif"));
        load_l.setBounds(150,250,300,150);
        
        //copyrights label creation
        JLabel load_l1 = new JLabel("\u00A9"+" copyrights - Team 16");
        load_l1.setBounds(210,550,200,50);
        
        load_frame.setVisible(true);
        
        //container creation
        Container load_con = load_frame.getContentPane();
        load_con.setLayout(null);
        load_con.add(load_p);
        load_con.add(load_l);
        load_con.add(load_l1);
        try{
            Thread.sleep(500);
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }
        
        //loginpage opens after loading
        for(int i=0;i<101;i++)
        {
            load_p.setValue(i);
            load_p.setString("Loading  " + load_p.getValue() + " %");
            try{
                Thread.sleep(45);
            }
            catch(InterruptedException ee)
            {
                System.out.println(ee);
            }
            
        }
        new LoginPage();
        load_frame.dispose();
    }
    public static void main(String[] args) {
       new LoadFrame();
    }
    
}
