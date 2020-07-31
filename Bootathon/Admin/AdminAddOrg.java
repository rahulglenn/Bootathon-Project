/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootathon.Admin;

import Bootathon.database.DBOperations;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 *
 * @author rahul
 */
public class AdminAddOrg {
    AdminAddOrg()
    {
       JFrame frame = new JFrame();
       frame.setBounds(500,200,500,700);
       frame.setDefaultCloseOperation(3);
       Container c = frame.getContentPane();
       c.setLayout(null);
       
       JLabel l1 = new JLabel("Add organization");
       l1.setFont(new Font("",Font.BOLD,25));
       l1.setBounds(10,20,300,50);
       c.add(l1);
       
       JLabel l2 = new JLabel("Organization Name");
       l2.setFont(new Font("",Font.BOLD,15));
       l2.setBounds(50,130,150,30);
       c.add(l2);
       JTextField t1 = new JTextField();
       t1.setFont(new Font("",Font.BOLD,15));
       t1.setBounds(250,130,200,30);
       c.add(t1);
       
       JLabel l3 = new JLabel("Activation key");
       l3.setFont(new Font("",Font.BOLD,15));
       l3.setBounds(50,200,150,30);
       c.add(l3);
       JTextField t2 = new JTextField();
       t2.setEditable(false);
       t2.setFont(new Font("",Font.BOLD,15));
       t2.setBounds(250,200,200,30);
       c.add(t2);
       
       JButton gen = new JButton("Generate");
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
       gen.setFont(new Font("",Font.BOLD,20));
       c.add(gen);
       
       JButton bac = new JButton("\u2190"+"  BACK");
       bac.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               frame.dispose();
               new AdminMain();
           }
       });
       bac.setBounds(270,300,150,30);
       bac.setFont(new Font("",Font.BOLD,20));
       c.add(bac);
       frame.setVisible(true);
    }
    public static void main(String[] args) {
        new AdminLogin();
    }
    
}
