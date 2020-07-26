/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootathon;

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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author rahul
 */
public class LoginPage {
    LoginPage()
    {
    JFrame loginframe = new JFrame();
    loginframe.setBounds(500,200,370,600);
    loginframe.setDefaultCloseOperation(3);
   
    //creating Login container
    Container log_con=loginframe.getContentPane();
    log_con.setLayout(new GridLayout(5, 1,10,0));
    
    //creating Panel1(Username)
    JPanel p1=new JPanel();
    log_con.add(p1);
    p1.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 50));
    JLabel l1=new JLabel("USERNAME");
    l1.setFont(new Font("Comic sans MS",Font.BOLD, 20));
    JTextField t1=new JTextField(15);
    p1.add(l1);
    p1.add(t1);
    
    //creating Panel2(Password)
    JPanel p2=new JPanel();
    log_con.add(p2);
    p2.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
    JLabel l2=new JLabel("PASSWORD");
    l2.setFont(new Font("Comic sans MS",Font.BOLD,20));
    JPasswordField pass2=new JPasswordField(12);
    JLabel useless=new JLabel("                                     ");
    JCheckBox cb2=new JCheckBox("Show Password");
    cb2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(cb2.isSelected())
                pass2.setEchoChar((char)0);
            else
                pass2.setEchoChar('*');
        }
    });
    p2.add(l2);
    p2.add(pass2);
    p2.add(useless);
    p2.add(cb2);
    
    JPanel p4=new JPanel();
    log_con.add(p4);
    p4.setLayout(new FlowLayout(FlowLayout.LEFT,50,0));
    JRadioButton rb1 = new JRadioButton("Employee");
    JRadioButton rb2 = new JRadioButton("Employer");
    rb1.setFont(new Font("Comic sans MS",Font.BOLD,15));
    rb2.setFont(new Font("Comic sans MS",Font.BOLD,15));
    p4.add(rb1);
    p4.add(rb2);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb2);
        bg.add(rb1);

    //creating panel3(login&back)
    JPanel p3=new JPanel();
    log_con.add(p3);
    p3.setLayout(new FlowLayout(FlowLayout.LEFT,60,0));
    JButton b1=new JButton("LOGIN");
    b1.setFont(new Font("Comic sans MS",Font.BOLD,20));
    JButton b2=new JButton("BACK");
    b2.setFont(new Font("Comic sans MS",Font.BOLD,20));
    p3.add(b1);
    p3.add(b2);
    
    b1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!rb1.isSelected() && !rb2.isSelected())
            {
                JOptionPane.showMessageDialog(null, "Please Select whether Employee or Employer!");
            }
            else if(t1.getText().isEmpty() || pass2.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please enter Login Credentials!");
            }
            else{
            if(rb1.isSelected())
            {
                try{
                    Connection conn=DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("Select * from employdet where Username=? AND Password=?");
                    st.setString(1, t1.getText());
                    st.setString(2, pass2.getText());
                    ResultSet rs=st.executeQuery();
                    if(rs.next())
                    {
                        loginframe.dispose();
                        new EmployeeMain(rs);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Invalid Username or Password!");
                    }
                    conn.close();
                }
                catch(Exception ee)
                {System.out.println(ee);}
            }
            if(rb2.isSelected())
            {
                loginframe.dispose();
                new MainFrame();
            }}
        }
    });
    
    //loginframe
    loginframe.setTitle("LOGIN");
    loginframe.setResizable(false);
    loginframe.setVisible(true);   
    }
    public static void main(String[] args) {
        new LoginPage();
    }
    
}
