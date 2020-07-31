/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootathon;

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
import java.util.regex.Pattern;


import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author rahul
 */
public class AddEmploy {
    AddEmploy(int id)
    {
        // frame creating
        MyFrame f = new MyFrame(2);           
        f.setBounds(500,100,600,800);
         
        
        //container creating
        Container c = f.getContentPane();
        c.setLayout(null);
        
       //label 1
       MyLabel l1 = new MyLabel("Add Employee",1);
       l1.setBounds(30,10,300,50);
       c.add(l1);
       
       //select employee
       MyLabel l2 = new MyLabel("Employee Name");
       l2.setBounds(50,70,200,50);       
       MyTextField t = new MyTextField();
       t.setBounds(250,80,200,30);
       c.add(l2);
       c.add(t);
       
       //employee id
       MyLabel l3 = new MyLabel("Address");
       l3.setBounds(120,140,200,50);
       
       MyTextField t1 = new MyTextField();
       t1.setBounds(250,150,200,30);
       c.add(l3);
       c.add(t1);
       
       //employee name
       MyLabel l4 = new MyLabel("D.O.B");
       l4.setBounds(140,210,200,50);
       
       MyTextField t2 = new MyTextField();
       t2.setBounds(250,220,200,30);
       c.add(l4);
       c.add(t2);
       
       //dob
       MyLabel l5 = new MyLabel("Phone No");
       l5.setBounds(105,280,200,50);
       
       MyTextField t3 = new MyTextField();
       t3.setBounds(250,290,200,30);
       c.add(l5);
       c.add(t3);
       
       MyLabel l6 = new MyLabel("Salary");
       l6.setBounds(135,350,200,50);
       
       MyTextField t4 = new MyTextField();
       t4.setBounds(250,360,200,30);
       c.add(l6);
       c.add(t4);
       
       MyLabel l7 = new MyLabel("Email ID");
       l7.setBounds(115,420,200,50);
       
       MyTextField t5 = new MyTextField();
       t5.setBounds(250,430,200,30);
       c.add(l7);
       c.add(t5);
       
       MyLabel l8 = new MyLabel("Password");
       l8.setBounds(105,490,200,50);
       
       JPasswordField t6 = new JPasswordField();
       t6.setBounds(250,500,200,30);
       c.add(l8);
       c.add(t6);
       
       MyLabel l9 = new MyLabel("Re-type Password");
       l9.setBounds(30,560,200,50);
       
       JPasswordField t7 = new JPasswordField();
       t7.setBounds(250,570,200,30);
       c.add(l9);
       c.add(t7);
        
      
      MyButton enter = new MyButton("Enter");
      enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(t.getText().isEmpty() || t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty() || t4.getText().isEmpty() || t5.getText().isEmpty() ||String.valueOf(t6.getPassword()).isEmpty() || String.valueOf(t7.getPassword()).isEmpty())
                {
                    JOptionPane.showMessageDialog(f, "<html><font size=4>Please Fill all the details listed above!","Fill it",JOptionPane.ERROR_MESSAGE);
                }
                else if(!Pattern.compile("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$").matcher(t2.getText()).matches())
                {
                    JOptionPane.showMessageDialog(f, "<html><font size=4>Invalid Date Format use (dd/mm/yyy)!","Invalid!",JOptionPane.ERROR_MESSAGE);
                }
                else if(!Pattern.compile("^(.+)@(.+)$").matcher(t5.getText()).matches())
                {
                    JOptionPane.showMessageDialog(f, "<html><font size=4>Invalid Email Address!","Invalid!",JOptionPane.ERROR_MESSAGE);
                }
                else if(!String.valueOf(t6.getPassword()).equals(String.valueOf(t7.getPassword())))
                {
                    JOptionPane.showMessageDialog(f, "<html><font size=4>The Password and Retype Password does not match!","Re-type Error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                try{
                    Connection conn = DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("insert into employdet values(?,0,?,?,?,?,?,?,?,?,0)");
                    st.setInt(1, id);
                    st.setString(2, t.getText());
                    st.setString(3, t1.getText());
                    st.setString(4, t2.getText());
                    st.setString(5, t3.getText());
                    st.setInt(6, Integer.valueOf(t4.getText()));
                    st.setInt(7, Integer.valueOf(t4.getText()));
                    st.setString(8, t5.getText());
                    st.setString(9, String.valueOf(t6.getPassword()));
                    st.executeUpdate();
                    conn.close();
                }
                catch(Exception ee)
                {
                    System.out.println(ee);
                }
                JOptionPane.showMessageDialog(f,"<html><font size=4>Successfully Added.","Successful",JOptionPane.INFORMATION_MESSAGE);
                f.dispose();
                new AddEmploy(id);
            }}
        });
      MyButton bac = new MyButton("\u2190"+"  BACK");
      bac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainFrame();
            }
        });
 
      enter.setBounds(100,670,130,30);
      bac.setBounds(310,670,130,30);
      c.add(enter);
      c.add(bac);
        
      f.setVisible(true);
        
    }
    public static void main(String[] args) {
        new LoginPage();
    }
    
}
