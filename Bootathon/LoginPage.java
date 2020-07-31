/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootathon;

import Bootathon.uiworks.MyLabel;
import Bootathon.uiworks.MyTextField;
import Bootathon.uiworks.MyFrame;
import Bootathon.uiworks.MyButton;
import Bootathon.database.DBOperations;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonGroup;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author rahul
 */
public class LoginPage {
    LoginPage()
    {
    MyFrame f = new MyFrame(1);
    f.setBounds(500,100,600,800);
   
   
    //creating Login container
    Container c=f.getContentPane();
    c.setLayout(null);
    
   
   //username
    MyLabel l = new MyLabel("Login",1);
    l.setBounds(40,10,200,50);
    c.add(l);
    MyLabel l1 = new MyLabel("Email id");
    l1.setBounds(70,100,100,30);
    c.add(l1);
    MyTextField t1 = new MyTextField();
    t1.setBounds(200,100,300,30);
    c.add(t1);
    
    //creating Panel2(Password)
    
    MyLabel l2=new MyLabel("Password");
    l2.setBounds(60,200,150,30);
    JPasswordField pass2=new JPasswordField(12);
    pass2.setBounds(200,200,300,30);
    JCheckBox cb2=new JCheckBox("Show Password");
    cb2.setBounds(200,250,150,30);
    cb2.setOpaque(false);
    cb2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(cb2.isSelected())
                pass2.setEchoChar((char)0);
            else
                pass2.setEchoChar('*');
        }
    });
    c.add(l2);
    c.add(pass2);
    c.add(cb2);
    
    
    JRadioButton rb1 = new JRadioButton("<html><Font size = 4>Employee</Font></html>");
    JRadioButton rb2 = new JRadioButton("<html><Font size = 4>Employer</Font></html>");
    rb1.setBounds(70,320,150,30);
    rb2.setBounds(230,320,150,30);
    rb1.setOpaque(false);
    
    rb2.setOpaque(false);
    c.add(rb1);
    c.add(rb2);
    ButtonGroup bg = new ButtonGroup();
    bg.add(rb2);
    bg.add(rb1);
    

    //creating panel3(login&back)
    
    MyButton b1 = new MyButton("Add");
    b1.setBounds(50,400,150,30);
    MyButton b2=new MyButton("Sign Up");
    b2.setBounds(50,480,150,30);
    b2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            f.dispose();
            new EmployerRegistration();
        }
    });
    
    c.add(b2);
    c.add(b1);
    
    b1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!rb1.isSelected() && !rb2.isSelected())
            {
                JOptionPane.showMessageDialog(f, "Please Select whether Employee or Employer!");
            }
            else if(t1.getText().isEmpty() || String.valueOf(pass2.getPassword()).isEmpty())
            {
                JOptionPane.showMessageDialog(f, "Please enter Login Credentials!");
            }
            else{
            if(rb1.isSelected())
            {
                try{
                    Connection conn=DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("Select * from employdet where Username=? AND Password=?");
                    st.setString(1, t1.getText());
                    st.setString(2, String.valueOf(pass2.getPassword()));
                    ResultSet rs=st.executeQuery();
                    if(rs.next())
                    {
                        if(Integer.valueOf(rs.getString("PassChange"))==0)
                        {
                            f.dispose();
                            new EmpPassChange(rs);
                        }
                        else{
                            f.dispose();
                            new EmployeeMain(rs.getString("Empname"),rs.getInt("empid"),rs.getString("Address"),rs.getString("DOB"),rs.getString("Phone"),rs.getInt("Salary"),rs.getInt("CurSalary"));
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(f, "Invalid Username or Password!");
                    }
                    conn.close();
                }
                catch(Exception ee)
                {System.out.println(ee);}
            }
            if(rb2.isSelected())
            {
                 try{
                    Connection conn=DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("Select * from employerlogin where Username=? AND Password=?");
                    st.setString(1, t1.getText());
                    st.setString(2, String.valueOf(pass2.getPassword()));
                    ResultSet rs=st.executeQuery();
                    if(rs.next())
                    {
                        st=conn.prepareStatement("select * from logfile where emprid=? and logid=(select max(logid) from logfile where emprid=?)");
                        st.setInt(1, rs.getInt("emprid"));
                        st.setInt(2, rs.getInt("emprid"));
                        ResultSet rs2=st.executeQuery();                        
                        rs2.next();
                        String date=rs2.getString("Date");
                        st=conn.prepareStatement("insert into logfile values(?,0,?,?,?)");
                        st.setInt(1, rs.getInt("emprid"));
                        st.setString(2, rs.getString("Name"));
                        st.setString(3, new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
                        st.setString(4, "Logged in!");
                        st.executeUpdate();
                        f.dispose();
                        new MainFrame(rs.getInt("emprid"),rs.getString("Name"),date);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(f, "Invalid Username or Password!");
                    }
                    conn.close();
                    
                 }
                 catch(Exception ee)
                 {System.out.println("Err3"+ee);}
            }}
        }
    });
    
    
    
    
    f.setVisible(true);   
    }
    public static void main(String[] args) {
        new LoginPage();
    }
    
}
