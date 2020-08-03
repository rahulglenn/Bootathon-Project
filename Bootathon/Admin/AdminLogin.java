/*
 * Description:The Admin login with username and password.
 * Author(s)  :Raahul Glenn,Sai Karthik
 */
package Bootathon.Admin;

import Bootathon.database.DBOperations;
import Bootathon.uiworks.MyButton;
import Bootathon.uiworks.MyFrame;
import Bootathon.uiworks.MyLabel;
import Bootathon.uiworks.MyTextField;
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

public class AdminLogin {
    AdminLogin()
    {
    
    //creating frame
    MyFrame alogin_frame=new MyFrame(2);
    
    //creating components
    Container alogin_con=alogin_frame.getContentPane();
    MyTextField alogin_t1=new MyTextField();
    JPasswordField alogin_p1=new JPasswordField();
    MyLabel alogin_l1=new MyLabel("Username");
    MyLabel alogin_l2=new MyLabel("Password");
    MyButton alogin_b1=new MyButton("LOGIN");
    
    //layout
    alogin_con.setLayout(null);
    
    //setting bounds of components
    alogin_l1.setBounds(40, 50, 130, 25);
    alogin_l2.setBounds(40, 100, 100, 25);
    alogin_t1.setBounds(180,50,180,25);
    alogin_p1.setBounds(180,100,180,25);
    alogin_b1.setBounds(160,170, 150, 30);
    
    
    //adding components
        alogin_con.add(alogin_l1);
        alogin_con.add(alogin_l2);
        alogin_con.add(alogin_t1);
        alogin_con.add(alogin_p1);
        alogin_con.add(alogin_b1);
    
        //Admin login
        alogin_b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(alogin_t1.getText().isEmpty() || String.valueOf(alogin_p1.getPassword()).isEmpty())
                {
                    JOptionPane.showMessageDialog(alogin_frame, "Enter login credentials!","Admin Login",JOptionPane.ERROR_MESSAGE);
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
                            alogin_frame.dispose();
                            new AdminMain(rs.getString("Name"));
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(alogin_frame, "Invalid Login Credentials!","Invalid",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    catch(HeadlessException ee)
                    {
                        System.out.println("Cannot insert the specified header"+ee);
                    }
                    catch(SQLException ee)
                    {
                        System.out.println("Cannot retrieve values from adminlogin"+ee);
                    }
                }
            }
        });

        alogin_frame.setBounds(500, 200, 400, 270);
        alogin_frame.setVisible(true);
    }
    public static void main(String[] args) {
        new AdminLogin();
    }
    
}
