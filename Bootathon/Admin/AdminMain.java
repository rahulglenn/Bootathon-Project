/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootathon.Admin;

import Bootathon.uiworks.MyButton;
import Bootathon.uiworks.MyFrame;
import Bootathon.uiworks.MyLabel;
import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author rahul
 */
public class AdminMain {
    static String name;
    AdminMain(String name)
    {
        this.name=name;
        new AdminMain();
    }
    AdminMain()
    {
        // frame creating
        MyFrame f = new MyFrame(4);           
        f.setBounds(500,200,500,700);
        
        //container creating
        Container c = f.getContentPane();
        c.setLayout(null);
        
       //label 1
       MyLabel l1 = new MyLabel("Hi, "+name,1);
       l1.setBounds(30,30,300,50);
       c.add(l1);  
       
       //Button 1
       MyButton b1 = new MyButton("Add Organization");
       b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new AdminAddOrg();
            }
        });
       b1.setBounds(120,150,250,50);
       c.add(b1);
       
        //Button 2
       MyButton b2 = new MyButton("Employer list");
       b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new EmployerList();
            }
        });
       b2.setBounds(120,220,250,50);
       c.add(b2);
       
       f.setVisible(true);
    }
    public static void main(String[] args) {
        new AdminLogin();
    }
}
