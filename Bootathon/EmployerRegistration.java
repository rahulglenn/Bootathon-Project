/**
 * Description: The employer registers his/her organization with the ActivationKey provided by the Admin
 * Author(s)  : Rahul Glenn,Sai Karthik
 */ 
package Bootathon;

import Bootathon.database.DBOperations;
import Bootathon.uiworks.MyButton;
import Bootathon.uiworks.MyFrame;
import Bootathon.uiworks.MyLabel;
import Bootathon.uiworks.MyTextField;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class EmployerRegistration {
       EmployerRegistration()
       {
           //creating frame
            MyFrame emprreg_frame=new MyFrame(5);
            emprreg_frame.setBounds(500,100,600,800);
            
           
            //Container creation
            Container emprreg_con = emprreg_frame.getContentPane();
            emprreg_con.setLayout(null);
                       
            //employername Label creation
            MyLabel emprreg_ername = new MyLabel("Employer Name ");
        
        emprreg_ername.setBounds(50,40,200,50);
        emprreg_con.add(emprreg_ername);
        
        //organization label creation
        MyLabel emprreg_org = new MyLabel("Organization");
        emprreg_org.setBounds(50,200+40,200,50);
        emprreg_con.add(emprreg_org);
 
        //DOB label creation
        MyLabel emprreg_dob = new MyLabel("D.O.B ");
        emprreg_dob.setBounds(50,275+40,200,50);
        emprreg_con.add(emprreg_dob);
        
        //emailid label creation
        MyLabel emprreg_email= new MyLabel("Email ID");
        emprreg_email.setBounds(50,350+40,200,50);
        emprreg_con.add(emprreg_email);
        
        //password label creation
        MyLabel emprreg_pass= new MyLabel("Password");
        emprreg_pass.setBounds(50,425+40,200,50);
        emprreg_con.add(emprreg_pass);
        
        //retypepassword label creation
        MyLabel emprreg_repass= new MyLabel("RetypePassword");
        emprreg_repass.setBounds(50,500+40,200,50);
        emprreg_con.add(emprreg_repass);
        
        //Activation key label creation
        MyLabel emprreg_ak= new MyLabel("Activation key");
        emprreg_con.add(emprreg_ak);
        emprreg_ak.setBounds(50,115,200,50);
       
        //Textfield1 creation
        MyTextField emprreg_t1 = new MyTextField();
        emprreg_t1.setBounds(250,50,300,30);
        emprreg_con.add(emprreg_t1);
        
        //Textfield2 creation
        MyTextField emprreg_t2 = new MyTextField();
        emprreg_t2.setBounds(250,200+50,300,30);
        emprreg_t2.setEditable(false);
        emprreg_con.add(emprreg_t2);
        
        //Textfield3 creation
        MyTextField emprreg_t3 = new MyTextField();
        emprreg_t3.setBounds(250,275+50,300,30);
        emprreg_con.add(emprreg_t3);
        
        //Textfield4 creation
        MyTextField emprreg_t4 = new MyTextField();
        emprreg_t4.setBounds(250,350+50,300,30);
        emprreg_con.add(emprreg_t4);
        
        //Passwordfield t5 creation
        JPasswordField emprreg_t5 = new JPasswordField();
        emprreg_t5.setBounds(250,425+50,300,30);
        emprreg_con.add(emprreg_t5);

        //passwordfield t6 creation
        JPasswordField emprreg_t6 = new JPasswordField();
        emprreg_t6.setBounds(250,500+50,300,30);
        emprreg_con.add(emprreg_t6);
 
        //Textfield7 creation
        MyTextField emprreg_t7 = new MyTextField();
        emprreg_t7.setBounds(250,125,300,30);
        emprreg_con.add(emprreg_t7);

       MyLabel clickhere = new MyLabel("click here \u2191");
       clickhere.setBounds(380,210,150,20);
       emprreg_con.add(clickhere);
        // ActivationKey validation
        MyButton b1 = new MyButton("Validate");
        b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(emprreg_t7.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(emprreg_frame, "Enter your Product Key!");
                    }
                    else
                    {
                    try
                    {
                      Connection conn=DBOperations.getConn();
                      PreparedStatement st=conn.prepareStatement("select * from productinfo where ProductKey=?");
                      st.setString(1, emprreg_t7.getText());
                      ResultSet rs=st.executeQuery();
                      if(rs.next() && rs.getInt("ActivationStatus")==0)
                      {
                          emprreg_t2.setText(rs.getString("Organization"));
                          JOptionPane.showMessageDialog(emprreg_frame, "Validation Succesful!");
                      }
                      else
                      {
                          JOptionPane.showMessageDialog(emprreg_frame, "Invalid Product Key!");
                      }
                      conn.close();
                    }
                    catch(HeadlessException ee)
                {
                    System.out.println("Cannot insert the specified HTML header!"+ee);
                }
                catch(SQLException ee)
                {
                    System.out.println("Cannot retrieve the product info from DB!"+ee);
                }    
                }}
            });
        b1.setBounds(380,180,110,20);
        emprreg_con.add(b1);
        //Employer Registration
        MyButton b = new MyButton("Register");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(emprreg_t2.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(emprreg_frame, "<html><font size=4>Please Validate your Product Key!","Validate",JOptionPane.ERROR_MESSAGE);
                }
                else if(emprreg_t1.getText().isEmpty() || emprreg_t3.getText().isEmpty() || emprreg_t4.getText().isEmpty() || String.valueOf(emprreg_t5.getPassword()).isEmpty() || String.valueOf(emprreg_t5.getPassword()).isEmpty())
                {
                    JOptionPane.showMessageDialog(emprreg_frame, "<html><font size=4>Please Fill all the details listed above!","Fill it",JOptionPane.ERROR_MESSAGE);
                }
                else if(emprreg_t1.getText().length()>20)
                {
                    JOptionPane.showMessageDialog(emprreg_frame, "<html><font size=4>The Employer Name can have a max of 20 Characters only!","Change it",JOptionPane.ERROR_MESSAGE);
                }
                else if(emprreg_t5.getText().length()>15 || emprreg_t5.getText().length()<8)
                {
                    JOptionPane.showMessageDialog(emprreg_frame, "<html><font size=4>The Password should be of size min 8 characters and a max of 15 characters!","Change it",JOptionPane.ERROR_MESSAGE);
                }
                else if(!Pattern.compile("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$").matcher(emprreg_t3.getText()).matches())
                {
                    JOptionPane.showMessageDialog(emprreg_frame, "<html><font size=4>Invalid Date or Invalid Format! use (dd/mm/yyyy)!","Invalid",JOptionPane.ERROR_MESSAGE);
                }
                else if(!Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$").matcher(emprreg_t4.getText()).matches())
                {
                    JOptionPane.showMessageDialog(emprreg_frame, "<html><font size=4>Invalid Email Address!","Invalid",JOptionPane.ERROR_MESSAGE);
                }
                else if(!String.valueOf(emprreg_t6.getPassword()).equals(String.valueOf(emprreg_t5.getPassword())))
                {
                    JOptionPane.showMessageDialog(emprreg_frame, "<html><font size=4>The Password and Retype Password does not match!","re-type Error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                try{
                    Connection conn=DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("insert into employerlogin values(0,?,?,?,?,?,?,?)");
                    st.setString(1 , emprreg_t1.getText());
                    st.setString(2, emprreg_t2.getText());
                    st.setString(3, emprreg_t3.getText());
                    st.setString(4, new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
                    st.setString(5, emprreg_t4.getText());
                    st.setString(6, String.valueOf(emprreg_t5.getPassword()));
                    st.setString(7, emprreg_t7.getText());
                    st.executeUpdate();
                    st=conn.prepareStatement("Select emprid from employerlogin where ProductKey=?");
                    st.setString(1, emprreg_t7.getText());
                    ResultSet rs=st.executeQuery();
                    rs.next();
                    int id=rs.getInt("emprid");
                    st=conn.prepareStatement("insert into logfile values(?,0,?,?,?)");
                    st.setInt(1, id);
                    st.setString(2, emprreg_t1.getText());
                    st.setString(3, new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
                    st.setString(4, "Account Created!");
                    st.executeUpdate();
                    st=conn.prepareStatement("update productinfo set ActivationStatus=1 where ProductKey=?");
                    st.setString(1, emprreg_t7.getText());
                    st.executeUpdate();
                    conn.close();
                    JOptionPane.showMessageDialog(emprreg_frame,"<html><font size=4>Registration Successful","Successful",JOptionPane.INFORMATION_MESSAGE);
                    //creating a new directory for the employer
                    new File("C:\\Electrical Data\\Empr"+String.valueOf(id)).mkdir();
                    emprreg_frame.dispose();
                    new MainFrame(id,emprreg_t1.getText(),"Nil");
                }
                catch(HeadlessException ee)
                {
                    System.out.println("Cannot insert the specified HTML header!"+ee);
                }
                catch(SQLException ee)
                {
                    System.out.println("Cannot insert values into DB!!"+ee);
                }     
            }}
        });
       
        b.setBounds(30,620,150,30);
        emprreg_con.add(b);
       
        MyButton bac = new MyButton("\u2190"+"  BACK");
        bac.setBounds(30,680,150,30);
        emprreg_con.add(bac);
        bac.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    emprreg_frame.dispose();
                    new LoginPage();
                }
            });
        
        emprreg_frame.setVisible(true);
       }
    public static void main(String[] args) {
    new LoadFrame();
    }
}

