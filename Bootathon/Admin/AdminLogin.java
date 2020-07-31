/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootathon.Admin;

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
public class AdminLogin {
    AdminLogin()
    {
        JFrame frame=new JFrame();
        //creating components
    Container alogin_con=frame.getContentPane();
    JTextField alogin_t1=new JTextField();
    JPasswordField alogin_p1=new JPasswordField();
    JLabel alogin_l1=new JLabel("Username");
    JLabel alogin_l2=new JLabel("Password");
    JButton alogin_b1=new JButton("LOGIN");
    
    //layout
        alogin_con.setLayout(null);
    
    //setfontandbounds
    alogin_l1.setFont(new Font("Times New Roman",Font.BOLD, 23));
    alogin_l1.setBounds(40, 50, 130, 25);
    alogin_l2.setFont(new Font("Times New Roman",Font.BOLD, 23));
    alogin_l2.setBounds(40, 100, 100, 25);
    alogin_t1.setFont(new Font("arial",Font.PLAIN,18));
    alogin_t1.setBounds(180,50,180,25);
    alogin_p1.setBounds(180,100,180,25);
    alogin_b1.setBounds(160,170, 80, 40);
    
    
    //adding components
        alogin_con.add(alogin_l1);
        alogin_con.add(alogin_l2);
        alogin_con.add(alogin_t1);
        alogin_con.add(alogin_p1);
        alogin_con.add(alogin_b1);
        
        alogin_b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(alogin_t1.getText().isEmpty() || String.valueOf(alogin_p1.getPassword()).isEmpty())
                {
                    JOptionPane.showMessageDialog(frame, "Please enter login credentials!");
                }
                else
                {
                    try{
                        Connection conn=DBOperations.getConn();
                        PreparedStatement st=conn.prepareStatement("Select * from adminlogin where Username=? and Password=?");
                        st.setString(1, alogin_t1.getText());
                        st.setString(2, String.valueOf(alogin_p1.getPassword()));
                        ResultSet rs=st.executeQuery();
                        if(rs.next())
                        {
                            frame.dispose();
                            new AdminMain(rs.getString("Name"));
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(frame, "Invalid Login Credentials!");
                        }
                    }
                    catch(Exception ee)
                    {
                        System.out.println(ee);
                    }
                }
            }
        });
        
        //frame
        frame.setTitle("ADMIN LOGIN");
        frame.setBounds(500, 200, 400, 270);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new AdminLogin();
    }
    
}
