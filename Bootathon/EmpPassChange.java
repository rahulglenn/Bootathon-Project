/*
 * Description:Change the employer provided password by the employee for the first time.
 * Author(s)  :Rahul Glenn,Sai Karthik
 */
package Bootathon;

import Bootathon.database.DBOperations;
import Bootathon.uiworks.MyButton;
import Bootathon.uiworks.MyFrame;
import Bootathon.uiworks.MyLabel;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;


public class EmpPassChange {
    static String user;
    static int id;
    EmpPassChange(ResultSet rs)
    {
        try{
        user=rs.getString("Username");
        id=rs.getInt("empid");
        }
        catch(SQLException ee)
        {System.out.println("Cannot get the value from Resultset"+ee);}
        
        //frame creation
        MyFrame pchange=new MyFrame(2);
        
        //container creation
        Container pchange_con=pchange.getContentPane();
   
   //Passwordchange labels creation     
   MyLabel pchange_l1=new MyLabel("Please change your password");
   MyLabel pchange_l2=new MyLabel("Confirm Password");
   MyLabel pchange_l3=new MyLabel("Re-enter Password");
   
    //Passwordfields creation
    JPasswordField pchange_t1=new JPasswordField();
    JPasswordField pchange_t2=new JPasswordField();
    
    //OK button creaation
    MyButton pchange_b1=new MyButton("OK");
    
    //layout
    pchange_con.setLayout(null);
    
        //setting bounds
        pchange_l1.setBounds(100,10, 400, 35);

        pchange_l2.setBounds(40,90, 300, 25);

        pchange_l3.setBounds(40,170, 300, 25);

        pchange_t1.setBounds(270,90,250,30);
  
        pchange_t2.setBounds(270,170,250,30);

        pchange_b1.setBounds(230,250,100,50);
        
        //adding components to the container
         pchange_con.add(pchange_l1);
        pchange_con.add(pchange_l2);
        pchange_con.add(pchange_l3);
        pchange_con.add(pchange_t1);
        pchange_con.add(pchange_t2);
        pchange_con.add(pchange_b1);
    
        //password changed by the employee
        pchange_b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(String.valueOf(pchange_t1.getPassword()).isEmpty() || String.valueOf(pchange_t2.getPassword()).isEmpty())
                {
                    JOptionPane.showMessageDialog(pchange, "<html><font size=4>Please Fill the above Details!","Fill it",JOptionPane.ERROR_MESSAGE);
                }
                else if(!String.valueOf(pchange_t1.getPassword()).equals(String.valueOf(pchange_t2.getPassword())))
                {
                    JOptionPane.showMessageDialog(pchange, "<html><font size=4>The Password does not match!","re-type Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(String.valueOf(pchange_t1.getPassword()).length()>15 || String.valueOf(pchange_t1.getPassword()).length()<8)
                {
                    JOptionPane.showMessageDialog(pchange, "<html><font size=4>The Password should be of size min 8 characters and a max of 15 characters!","Change it",JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(pchange, "<html><font size=4>Password Change Successful","Successful",JOptionPane.INFORMATION_MESSAGE);
                 pchange.dispose();
                 new LoginPage();
        }
        catch(  HeadlessException ee)
        {System.out.println("Cannot insert the Specified Header"+ee);}
                catch(SQLException ee)
                {
                    System.out.println("Cannot update the employdet"+ee);
                }
                 
            }}
        });
        pchange.setTitle("PASSWORD CHANGE");
        pchange.setBounds(500,100,550,350);
       
        pchange.setVisible(true);
    }
    public static void main(String[] args) {
       new LoadFrame();
    }
}
