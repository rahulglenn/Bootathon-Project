/*
 * Description :Adding employee details by the employer.
 * Author      :Sai Karthik
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
import java.sql.SQLException;
import java.util.regex.Pattern;


import javax.swing.JOptionPane;
import javax.swing.JPasswordField;


public class AddEmploy {
    AddEmploy(int id)
    {
        // frame creating
        MyFrame addemp_f = new MyFrame(2);           
        addemp_f.setBounds(500,100,600,800);
         
        
        //container creating
        Container c = addemp_f.getContentPane();
        c.setLayout(null);
        
       //label1
       MyLabel l1 = new MyLabel("Add Employee",1);
       l1.setBounds(30,10,300,50);
       c.add(l1);
       
       //employee name
       MyLabel l2 = new MyLabel("Employee Name");
       l2.setBounds(50,70,200,50);       
       MyTextField t = new MyTextField();
       t.setBounds(250,80,200,30);
       c.add(l2);
       c.add(t);
       
       //address
       MyLabel l3 = new MyLabel("Address");
       l3.setBounds(120,140,200,50);
       
       MyTextField t1 = new MyTextField();
       t1.setBounds(250,150,200,30);
       c.add(l3);
       c.add(t1);
       
       //DOB
       MyLabel l4 = new MyLabel("D.O.B");
       l4.setBounds(140,210,200,50);
       
       MyTextField t2 = new MyTextField();
       t2.setBounds(250,220,200,30);
       c.add(l4);
       c.add(t2);
       
       //phone no
       MyLabel l5 = new MyLabel("Phone No");
       l5.setBounds(105,280,200,50);
       
       MyTextField t3 = new MyTextField();
       t3.setBounds(250,290,200,30);
       c.add(l5);
       c.add(t3);
       
       //salary
       MyLabel l6 = new MyLabel("Salary");
       l6.setBounds(135,350,200,50);
       
       MyTextField t4 = new MyTextField();
       t4.setBounds(250,360,200,30);
       c.add(l6);
       c.add(t4);
       
       //email id
       MyLabel l7 = new MyLabel("Email ID");
       l7.setBounds(115,420,200,50);
       
       MyTextField t5 = new MyTextField();
       t5.setBounds(250,430,200,30);
       c.add(l7);
       c.add(t5);
       
       //password
       MyLabel l8 = new MyLabel("Password");
       l8.setBounds(105,490,200,50);
       
       JPasswordField t6 = new JPasswordField();
       t6.setBounds(250,500,200,30);
       c.add(l8);
       c.add(t6);
       
       //retype password
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
                    JOptionPane.showMessageDialog(addemp_f, "<html><font size=4>Please Fill all the details listed above!","Fill it",JOptionPane.ERROR_MESSAGE);
                }
                else if(t.getText().length()>20)
                {
                    JOptionPane.showMessageDialog(addemp_f, "<html><font size=4>The Employee Name can have a max of 20 Characters only!","Change it",JOptionPane.ERROR_MESSAGE);
                }
                else if(t1.getText().length()>25)
                {
                    JOptionPane.showMessageDialog(addemp_f, "<html><font size=4>The Address can have a max of 25 Characters only!","Change it",JOptionPane.ERROR_MESSAGE);
                }
                else if(t3.getText().length()>10)
                {
                    JOptionPane.showMessageDialog(addemp_f, "<html><font size=4>The Phone No. can have a max size of 10 only!","Change it",JOptionPane.ERROR_MESSAGE);
                }
                else if(t6.getText().length()>15 || t6.getText().length()<8)
                {
                    JOptionPane.showMessageDialog(addemp_f, "<html><font size=4>The Password should be of size min 8 characters and a max of 15 characters!","Change it",JOptionPane.ERROR_MESSAGE);
                }
                else if(!Pattern.matches("^[0-9]+$", t4.getText()))
                {
                    JOptionPane.showMessageDialog(addemp_f, "<html><font size=4>The Salary should be integer value!","Change it",JOptionPane.ERROR_MESSAGE);
                }
                else if(Integer.valueOf(t4.getText())>100000)
                {
                    JOptionPane.showMessageDialog(addemp_f, "<html><font size=4>The Salary cannot be greater than 1 Lakh!","Change it",JOptionPane.ERROR_MESSAGE);
                }
                else if(!Pattern.compile("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$").matcher(t2.getText()).matches())//date validation
                {
                    JOptionPane.showMessageDialog(addemp_f, "<html><font size=4>Invalid Date or Invalid Format! use (dd/mm/yyyy)!","Invalid!",JOptionPane.ERROR_MESSAGE);
                }
                else if(!Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$").matcher(t5.getText()).matches())//mail validation
                {
                    JOptionPane.showMessageDialog(addemp_f, "<html><font size=4>Invalid Email Address!","Invalid!",JOptionPane.ERROR_MESSAGE);
                }
                else if(!String.valueOf(t6.getPassword()).equals(String.valueOf(t7.getPassword())))
                {
                    JOptionPane.showMessageDialog(addemp_f, "<html><font size=4>The Password and Retype Password does not match!","Re-type Error",JOptionPane.ERROR_MESSAGE);
                }
                //inserting employee data to the database
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
                    JOptionPane.showMessageDialog(addemp_f,"<html><font size=4>Successfully Added.","Successful",JOptionPane.INFORMATION_MESSAGE);
                }
                catch(NumberFormatException ee)
                {
                    System.out.println("Salary value should be an integer value"+ee);
                }
                catch(SQLException ee)
                {
                    System.out.println("Couldnt insert values into employdet"+ee);
                }
                addemp_f.dispose();
                new AddEmploy(id);
            }}
        });
      //back action
      MyButton bac = new MyButton("\u2190"+"  BACK");
      bac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addemp_f.dispose();
                new MainFrame();
            }
        });
 
      enter.setBounds(100,670,130,30);
      bac.setBounds(310,670,130,30);
      c.add(enter);
      c.add(bac);
        
      addemp_f.setVisible(true);
        
    }
    public static void main(String[] args) {
        new LoadFrame();
    }
    
}
