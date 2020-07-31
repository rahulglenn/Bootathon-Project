/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootathon.Admin;

import Bootathon.database.DBOperations;
import Bootathon.uiworks.MyButton;
import Bootathon.uiworks.MyFrame;
import Bootathon.uiworks.MyLabel;
import Bootathon.uiworks.MyTextField;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;
import javax.swing.JOptionPane;

/**
 *
 * @author rahul
 */
public class AdminAddOrg {
    AdminAddOrg()
    {
       MyFrame frame = new MyFrame(2);
       frame.setBounds(500,200,500,700);
       frame.setDefaultCloseOperation(3);
       Container c = frame.getContentPane();
       c.setLayout(null);
       
       MyLabel l1 = new MyLabel("Add organization",1);
       l1.setBounds(10,20,300,50);
       c.add(l1);
       
       MyLabel l2 = new MyLabel("Organization Name");
       l2.setBounds(50,130,200,30);
       c.add(l2);
       MyTextField t1 = new MyTextField();
       t1.setBounds(250,130,200,30);
       c.add(t1);
       
       MyLabel l3 = new MyLabel("Activation key");
       l3.setBounds(50,200,150,30);
       c.add(l3);
       MyTextField t2 = new MyTextField();
       t2.setEditable(false);
       t2.setBounds(250,200,200,30);
       c.add(t2);
       
       MyButton gen = new MyButton("Generate");
       gen.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if(t1.getText().isEmpty())
               {
                    JOptionPane.showMessageDialog(frame, "Please Fill the Organization Name!");
               }
               else
               {
                   UUID key=UUID.randomUUID();
                   t2.setText(key.toString());
               try{
                   Connection conn=DBOperations.getConn();
                   
                   PreparedStatement st=conn.prepareStatement("insert into productinfo values(0,?,?,0)");
                   st.setString(1, t1.getText());
                   st.setString(2, key.toString());
                   st.executeUpdate();
                   conn.close();
               }
               catch(Exception ee)
               {System.out.println(ee);}
                JOptionPane.showMessageDialog(frame,"Successfully Added.","Alert",JOptionPane.WARNING_MESSAGE);
           }}
       });
       gen.setBounds(70,300,150,30);
       c.add(gen);
       
       MyButton bac = new MyButton("\u2190"+"  BACK");
       bac.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               frame.dispose();
               new AdminMain();
           }
       });
       bac.setBounds(270,300,150,30);
       c.add(bac);
       frame.setVisible(true);
    }
    public static void main(String[] args) {
        new AdminLogin();
    }
    
}
