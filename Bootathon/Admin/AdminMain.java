/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootathon.Admin;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        JFrame f = new JFrame();           
        f.setBounds(500,200,500,700);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Admin Form");
        f.setResizable(false); 
        
        //container creating
        Container c = f.getContentPane();
        c.setLayout(null);
        
       //label 1
       JLabel l1 = new JLabel("Hi, "+name);
       l1.setFont(new Font("Comic sans MS",Font.BOLD,25));
       l1.setBounds(30,10,300,50);
       c.add(l1);  
       
       //Button 1
       JButton b1 = new JButton("Add Organization");
       b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new AdminAddOrg();
            }
        });
       b1.setFont(new Font("Comic sans MS",Font.BOLD,18));
       b1.setBounds(120,210,250,50);
       c.add(b1);
       
        //Button 2
       JButton b2 = new JButton("Employee list");
       b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new EmployerList();
            }
        });
       b2.setFont(new Font("Comic sans MS",Font.BOLD,18));
       b2.setBounds(120,300,250,50);
       c.add(b2);
       
       f.setVisible(true);
    }
    public static void main(String[] args) {
        new AdminLogin();
    }
}
