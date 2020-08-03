/*
 * Description:Main frame of the Admin
 * Author(s)  :Raahul Glenn,Sai Karthik
 */
package Bootathon.Admin;

import Bootathon.uiworks.MyButton;
import Bootathon.uiworks.MyFrame;
import Bootathon.uiworks.MyLabel;
import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMain {
    static String name;
    AdminMain(String nam)
    {
        name=nam;
        new AdminMain();
    }
    AdminMain()
    {
        // frame creating
        MyFrame amain_frame = new MyFrame(4);           
        amain_frame.setBounds(500,200,500,700);
        
        //container creating
        Container amain_con = amain_frame.getContentPane();
        amain_con.setLayout(null);
        
       //label 1
       MyLabel amain_l1 = new MyLabel("Hi, "+name,1);
       amain_l1.setBounds(30,30,300,50);
       amain_con.add(amain_l1);  
       
       //Button 1
       MyButton amain_b1 = new MyButton("Add Organization");
       //AdminAddOrg frame opens
       amain_b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amain_frame.dispose();
                new AdminAddOrg();
            }
        });
       amain_b1.setBounds(120,150,250,50);
       amain_con.add(amain_b1);
       
        //Button 2
       MyButton amain_b2 = new MyButton("Employer list");
       //EmployerList frame opens
       amain_b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amain_frame.dispose();
                new EmployerList();
            }
        });
       amain_b2.setBounds(120,220,250,50);
       amain_con.add(amain_b2);
       
       MyButton logout = new MyButton("Log out");
       logout.setBounds(300,400,150,30);
       amain_con.add(logout);
       logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amain_frame.dispose();
                new AdminLogin();
            }
        });
       amain_frame.setVisible(true);
    }
    public static void main(String[] args) {
        new AdminLogin();
    }
}
