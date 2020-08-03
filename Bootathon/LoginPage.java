/*
 * Description:Login for both the employer and employee
 */
package Bootathon;

import Bootathon.uiworks.MyLabel;
import Bootathon.uiworks.MyTextField;
import Bootathon.uiworks.MyFrame;
import Bootathon.uiworks.MyButton;
import Bootathon.database.DBOperations;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

public class LoginPage {
    LoginPage()
    {
    //creating frame
    MyFrame log_f = new MyFrame(1);
    log_f.setBounds(500,100,600,800);
   
   
    //creating Login container
    Container log_c=log_f.getContentPane();
    log_c.setLayout(null);
    
   
   //login label creation
    MyLabel log_l = new MyLabel("Login",1);
    log_l.setBounds(40,10,200,50);
    log_c.add(log_l);
    
    //email label and textfield creation
    MyLabel log_l1 = new MyLabel("Email id");
    log_l1.setBounds(70,100,100,30);
    log_c.add(log_l1);
    MyTextField log_t1 = new MyTextField();
    log_t1.setBounds(200,100,300,30);
    log_c.add(log_t1);
    
    //creating Panel2(Password)
    MyLabel log_l2=new MyLabel("Password");
    log_l2.setBounds(60,200,150,30);
    JPasswordField log_pass2=new JPasswordField(12);
    log_pass2.setBounds(200,200,300,30);
    JCheckBox log_cb2=new JCheckBox("Show Password");
    log_cb2.setBounds(200,250,150,30);
    log_cb2.setOpaque(false);
    
    //password visibiity
    log_cb2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(log_cb2.isSelected())
                log_pass2.setEchoChar((char)0);
            else
                log_pass2.setEchoChar('*');
        }
    });
    log_c.add(log_l2);
    log_c.add(log_pass2);
    log_c.add(log_cb2);
    
    //creating radio button
    JRadioButton log_rb1 = new JRadioButton("<html><Font size = 4>Employee</Font></html>");
    JRadioButton log_rb2 = new JRadioButton("<html><Font size = 4>Employer</Font></html>");
    log_rb1.setBounds(70,320,150,30);
    log_rb2.setBounds(230,320,150,30);
    log_rb1.setOpaque(false);
    log_rb2.setOpaque(false);
    log_c.add(log_rb1);
    log_c.add(log_rb2);
    ButtonGroup bg = new ButtonGroup();
    bg.add(log_rb2);
    bg.add(log_rb1);
    

    //creating panel3(login & signup buttons)
    MyButton log_b1 = new MyButton("Login");
    log_b1.setBounds(50,400,150,30);
    MyButton log_b2=new MyButton("Sign Up");
    log_b2.setBounds(50,480,150,30);
    log_b2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            log_f.dispose();
            new EmployerRegistration();
        }
    });
    log_c.add(log_b2);
    log_c.add(log_b1);
    
    //login button action
    log_b1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!log_rb1.isSelected() && !log_rb2.isSelected())
            {
                JOptionPane.showMessageDialog(log_f, "Please Select whether Employee or Employer!");
            }
            else if(log_t1.getText().isEmpty() || String.valueOf(log_pass2.getPassword()).isEmpty())
            {
                JOptionPane.showMessageDialog(log_f, "Please enter Login Credentials!");
            }
            else{
            if(log_rb1.isSelected()) // employee radio button is selected
            {
                try{
                    Connection conn=DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("Select * from employdet where Username=? AND Password=?");
                    st.setString(1, log_t1.getText());
                    st.setString(2, String.valueOf(log_pass2.getPassword()));
                    ResultSet rs=st.executeQuery();
                    if(rs.next())
                    {
                        if(Integer.valueOf(rs.getString("PassChange"))==0)
                        {
                            log_f.dispose();
                            new EmpPassChange(rs);
                        }
                        else{
                            log_f.dispose();
                            new EmployeeMain(rs.getString("Empname"),rs.getInt("empid"),rs.getString("Address"),rs.getString("DOB"),rs.getString("Phone"),rs.getInt("Salary"),rs.getInt("CurSalary"));
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(log_f, "Invalid Username or Password!");
                    }
                    conn.close();
                }
                catch(HeadlessException ee)
                {
                    System.out.println("Cannot insert the specified HTML header!"+ee);
                }
                catch(NumberFormatException ee)
                {
                    System.out.println("The PassChange should be int"+ee);
                }
                catch(SQLException ee)
                {
                    System.out.println("Cannot retrieve values from DB!!"+ee);
                }
            }
            if(log_rb2.isSelected()) // employer radio button is selected
            {
                 try{
                    Connection conn=DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("Select * from employerlogin where Username=? AND Password=?");
                    st.setString(1, log_t1.getText());
                    st.setString(2, String.valueOf(log_pass2.getPassword()));
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
                        log_f.dispose();
                        new MainFrame(rs.getInt("emprid"),rs.getString("Name"),date);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(log_f, "Invalid Username or Password!");
                    }
                    conn.close();
                    
                 }
                 catch(HeadlessException ee)
                {
                    System.out.println("Cannot insert the specified HTML header!"+ee);
                }
                catch(SQLException ee)
                {
                    System.out.println("Cannot retrieve values from DB!!"+ee);
                }
            }}
        }
    });
   
    log_f.setVisible(true);   
    }
    public static void main(String[] args) {
        new LoadFrame();
    }
    
}
