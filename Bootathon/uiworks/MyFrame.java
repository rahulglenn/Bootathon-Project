/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootathon.uiworks;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author sk
 */
public class MyFrame extends JFrame{
    
    public MyFrame(int a)
    {
        if(a==1)
        {
            setContentPane(new JLabel(new ImageIcon("C:/Users/rahul/Documents/NetBeansProjects/Bootcamp-Java/src/Bootathon/img/b1.jpg")));
        }
        else if(a==2)
        {
            setContentPane(new JLabel(new ImageIcon("C:/Users/rahul/Documents/NetBeansProjects/Bootcamp-Java/src/Bootathon/img/b2.jpg")));
        }
        else if(a==3)
        {
            setContentPane(new JLabel(new ImageIcon("C:/Users/rahul/Documents/NetBeansProjects/Bootcamp-Java/src/Bootathon/img/b3.jpg")));
        }
        else if(a==4)
        {
             setContentPane(new JLabel(new ImageIcon("C:/Users/rahul/Documents/NetBeansProjects/Bootcamp-Java/src/Bootathon/img/b4.jpg")));
        }
        else if(a==5)
        {
            setContentPane(new JLabel(new ImageIcon("C:/Users/rahul/Documents/NetBeansProjects/Bootcamp-Java/src/Bootathon/img/b5.jpg")));
        }
        
         setIconImage(new ImageIcon("C:/Users/rahul/Documents/NetBeansProjects/Bootcamp-Java/src/Bootathon/img/icon.jpg").getImage());
         setTitle(" Tech i5");
         setResizable(false);
         setDefaultCloseOperation(3);
    }
   
    
     
}
