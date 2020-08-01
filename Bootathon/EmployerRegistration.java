 package Bootathon;

import Bootathon.database.DBOperations;
import Bootathon.uiworks.MyButton;
import Bootathon.uiworks.MyFrame;
import Bootathon.uiworks.MyLabel;
import Bootathon.uiworks.MyTextField;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;


/**
 *
 * @author rahul
 */
public class EmployerRegistration {
       EmployerRegistration()
       {
           //creating frame
            MyFrame f=new MyFrame(5);
            f.setBounds(500,100,600,800);
            
           
            //Container creation
            Container c = f.getContentPane();
            c.setLayout(null);
                       
            //Label creation
            MyLabel l = new MyLabel("Employer Name ");
        
        l.setBounds(50,40,200,50);
        c.add(l);

        MyLabel l2 = new MyLabel("Organization");
      
        l2.setBounds(50,200+40,200,50);
        c.add(l2);
 ;
        MyLabel l3 = new MyLabel("D.O.B ");
       
        l3.setBounds(50,275+40,200,50);
        c.add(l3);
       
        MyLabel l4 = new MyLabel("Email ID");
        l4.setBounds(50,350+40,200,50);
        c.add(l4);
 
        MyLabel l5 = new MyLabel("Password");

        l5.setBounds(50,425+40,200,50);
        c.add(l5);
        MyLabel l6 = new MyLabel("RetypePassword");

        l6.setBounds(50,500+40,200,50);
        c.add(l6);

        MyLabel l7 = new MyLabel("Activation key");
        c.add(l7);

        l7.setBounds(50,115,200,50);
       
        //Textfield creation
        MyTextField t1 = new MyTextField();
        t1.setBounds(250,50,300,30);
        c.add(t1);

        MyTextField t2 = new MyTextField();
        t2.setBounds(250,200+50,300,30);
        t2.setEditable(false);
        c.add(t2);

        MyTextField t3 = new MyTextField();
        t3.setBounds(250,275+50,300,30);
        c.add(t3);

        MyTextField t4 = new MyTextField();
        t4.setBounds(250,350+50,300,30);
        c.add(t4);

        JPasswordField t5 = new JPasswordField();
        t5.setBounds(250,425+50,300,30);
        c.add(t5);

        JPasswordField t6 = new JPasswordField();
        t6.setBounds(250,500+50,300,30);
        c.add(t6);
 
        MyTextField t7 = new MyTextField();
        t7.setBounds(250,125,300,30);
        c.add(t7);

       MyLabel summa = new MyLabel("click here \u2191");
       summa.setBounds(380,210,150,20);
       c.add(summa);
        // creating button
        MyButton b1 = new MyButton("Validate");
        b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(t7.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(f, "Enter your Product Key!");
                    }
                    else
                    {
                    try
                    {
                      Connection conn=DBOperations.getConn();
                      PreparedStatement st=conn.prepareStatement("select * from productinfo where ProductKey=?");
                      st.setString(1, t7.getText());
                      ResultSet rs=st.executeQuery();
                      if(rs.next() && rs.getInt("ActivationStatus")==0)
                      {
                          t2.setText(rs.getString("Organization"));
                          JOptionPane.showMessageDialog(f, "Validation Succesful!");
                      }
                      else
                      {
                          JOptionPane.showMessageDialog(f, "Invalid Product Key!");
                      }
                      conn.close();
                    }
                    catch(Exception ee)
                    {System.out.println(ee);
                    }
                }}
            });
        b1.setBounds(380,180,110,20);
        c.add(b1);
        MyButton b = new MyButton("Register");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(t2.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(f, "<html><font size=4>Please Validate your Product Key!","Validate",JOptionPane.ERROR_MESSAGE);
                }
                else if(t1.getText().isEmpty() || t3.getText().isEmpty() || t4.getText().isEmpty() || String.valueOf(t5.getPassword()).isEmpty() || String.valueOf(t5.getPassword()).isEmpty())
                {
                    JOptionPane.showMessageDialog(f, "<html><font size=4>Please Fill all the details listed above!","Fill it",JOptionPane.ERROR_MESSAGE);
                }
                else if(!Pattern.compile("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$").matcher(t3.getText()).matches())
                {
                    JOptionPane.showMessageDialog(f, "<html><font size=4>Invalid Date Format use (dd/mm/yyyy)!","Invalid",JOptionPane.ERROR_MESSAGE);
                }
                else if(!Pattern.compile("^(.+)@(.+)$").matcher(t4.getText()).matches())
                {
                    JOptionPane.showMessageDialog(f, "<html><font size=4>Invalid Email Address!","Invalid",JOptionPane.ERROR_MESSAGE);
                }
                else if(!String.valueOf(t6.getPassword()).equals(String.valueOf(t5.getPassword())))
                {
                    JOptionPane.showMessageDialog(f, "<html><font size=4>The Password and Retype Password does not match!","re-type Error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                try{
                    Connection conn=DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("insert into employerlogin values(0,?,?,?,?,?,?,?)");
                    st.setString(1 , t1.getText());
                    st.setString(2, t2.getText());
                    st.setString(3, t3.getText());
                    st.setString(4, new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
                    st.setString(5, t4.getText());
                    st.setString(6, String.valueOf(t5.getPassword()));
                    st.setString(7, t7.getText());
                    st.executeUpdate();
                    st=conn.prepareStatement("Select emprid from employerlogin where ProductKey=?");
                    st.setString(1, t7.getText());
                    ResultSet rs=st.executeQuery();
                    rs.next();
                    int id=rs.getInt("emprid");
                    st=conn.prepareStatement("insert into logfile values(?,0,?,?,?)");
                    st.setInt(1, id);
                    st.setString(2, t1.getText());
                    st.setString(3, new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
                    st.setString(4, "Account Created!");
                    st.executeUpdate();
                    st=conn.prepareStatement("update productinfo set ActivationStatus=1 where ProductKey=?");
                    st.setString(1, t7.getText());
                    st.executeUpdate();
                    conn.close();
                    JOptionPane.showMessageDialog(f,"<html><font size=4>Registration Successful","Successful",JOptionPane.INFORMATION_MESSAGE);
                    //System.out.println(id+t1.getText()+"Nil");
                    new File("C:\\Electrical Data\\Empr"+String.valueOf(id)).mkdir();
                    f.dispose();
                    //System.out.println(id+t1.getText()+"Nil");
                    new MainFrame(id,t1.getText(),"Nil");
                }
                catch(Exception ee)
                {System.out.println(ee);
                }
               
                
            }}
        });
       
        b.setBounds(30,620,150,30);
        c.add(b);
       
        f.setVisible(true);
       }
    public static void main(String[] args) {
    new LoginPage();
    }
   
}

