/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootathon;

import Bootathon.database.DBOperations;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author rahul
 */
public class EmpPassChange {
    static String user;
    static int id;
    EmpPassChange(ResultSet rs)
    {
        try{
        user=rs.getString("Username");
        id=rs.getInt("empid");
        }
        catch(Exception ee)
        {System.out.println("Err"+ee);}
        JFrame pchange=new JFrame();
        Container pchange_con=pchange.getContentPane();
    JLabel pchange_l1=new JLabel("Please change your password");
    JLabel pchange_l2=new JLabel("Confirm Password");
    JLabel pchange_l3=new JLabel("Re-enter Password");
    JPasswordField pchange_t1=new JPasswordField();
    JPasswordField pchange_t2=new JPasswordField();
    JButton pchange_b1=new JButton("OK");
    
    pchange_con.setLayout(null);
    
    pchange_l1.setFont(new Font("Comic sans MS",Font.BOLD,25));
        pchange_l1.setBounds(100,10, 400, 35);
        pchange_l2.setFont(new Font("Comic sans MS",Font.BOLD,20));
        pchange_l2.setBounds(40,90, 300, 25);
        pchange_l3.setFont(new Font("Comic sans MS",Font.BOLD,20));
        pchange_l3.setBounds(40,170, 300, 25);
        pchange_t1.setFont(new Font("arial",Font.PLAIN,18));
        pchange_t1.setBounds(270,90,250,30);
        pchange_t2.setFont(new Font("arial",Font.PLAIN,18));
        pchange_t2.setBounds(270,170,250,30);
        pchange_b1.setFont(new Font("arial",Font.PLAIN,30));
        pchange_b1.setBounds(230,250,100,50);
        
         pchange_con.add(pchange_l1);
        pchange_con.add(pchange_l2);
        pchange_con.add(pchange_l3);
        pchange_con.add(pchange_t1);
        pchange_con.add(pchange_t2);
        pchange_con.add(pchange_b1);
    
        pchange_b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(String.valueOf(pchange_t1.getPassword()).isEmpty() || String.valueOf(pchange_t2.getPassword()).isEmpty())
                {
                    JOptionPane.showMessageDialog(pchange, "Please Fill the above Details!");
                }
                else if(!String.valueOf(pchange_t1.getPassword()).equals(String.valueOf(pchange_t2.getPassword())))
                {
                    JOptionPane.showMessageDialog(pchange, "The Password does not match!");
                }
                else
                {  
                try{
                Connection conn=DBOperations.getConn();
                PreparedStatement st=conn.prepareStatement("update employdet set PassChange=1,Password=? where Username=? and empid=?");
                st.setString(1, String.valueOf(pchange_t1.getPassword()));
                st.setString(2, user);
                st.setInt(3, id);
                st.executeUpdate();
                JOptionPane.showMessageDialog(pchange, "Password Change Successful");
                 pchange.dispose();
                 new LoginPage();
        }
        catch(Exception ee)
        {System.out.println("Err"+ee);}
                 
            }}
        });
    pchange.setTitle("PASSWORD CHANGE");
        pchange.setBounds(400,200,550,350);
        pchange.setResizable(false);
        pchange.setDefaultCloseOperation(3);
        pchange.setVisible(true);
    }
    public static void main(String[] args) {
       new LoginPage();
    }
}
