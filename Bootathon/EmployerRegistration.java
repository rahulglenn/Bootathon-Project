/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootathon;
import Bootathon.database.DBOperations;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author rahul
 */
public class EmployerRegistration {
       EmployerRegistration()
       {
           //creating frame
            JFrame f=new JFrame();
            f.setBounds(500,200,500,700);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle(" New Registration ");
            f.setResizable(false);
            
            //Container creation
            Container c = f.getContentPane();
            c.setLayout(null);
                        
            //Label creation
            JLabel l = new JLabel();
        l.setText("Employer Name : ");
        l.setBounds(50,50,200,50);
        c.add(l);
        l.setFont(new Font("Candara Light",Font.BOLD,25));  
        JLabel l2 = new JLabel();
        l2.setText("Organization    : ");
        l2.setBounds(50,125,200,50);
        c.add(l2);
        l2.setFont(new Font("Candara Light",Font.BOLD,25));
        JLabel l3 = new JLabel();
        l3.setText("D.O.B                : ");
        l3.setBounds(50,200,200,50);
        c.add(l3);
        l3.setFont(new Font("Candara Light",Font.BOLD,25));
        JLabel l4 = new JLabel();
        l4.setText("Username        : ");
        l4.setBounds(50,275,200,50);
        c.add(l4);
        l4.setFont(new Font("Candara Light",Font.BOLD,25));
        JLabel l5 = new JLabel();
        l5.setText("Password         : ");
        l5.setBounds(50,350,200,50);
        c.add(l5);
        l5.setFont(new Font("Candara Light",Font.BOLD,25));
        JLabel l6 = new JLabel();
        l6.setText("RetypePassword:");
        l6.setBounds(50,425,200,50);
        c.add(l6);
        l6.setFont(new Font("Candara Light",Font.BOLD,25));
        
        //Textfield creation
        JTextField t1 = new JTextField();
        t1.setBounds(250,50,200,50);
        c.add(t1);
        t1.setFont(new Font("Comic sans MS",Font.BOLD,25));
        JTextField t2 = new JTextField();
        t2.setBounds(250,125,200,50);
        c.add(t2);
        t2.setFont(new Font("Comic sans MS",Font.BOLD,25));
        JTextField t3 = new JTextField();
        t3.setBounds(250,200,200,50);
        c.add(t3);
        t3.setFont(new Font("Comic sans MS",Font.BOLD,25));
        JTextField t4 = new JTextField();
        t4.setBounds(250,275,200,50);
        c.add(t4);
        t4.setFont(new Font("Comic sans MS",Font.BOLD,25));
        JPasswordField t5 = new JPasswordField();
        t5.setBounds(250,350,200,50);
        c.add(t5);
        t5.setFont(new Font("Comic sans MS",Font.BOLD,25));
        JPasswordField t6 = new JPasswordField();
        t6.setBounds(250,425,200,50);
        c.add(t6);
        t6.setFont(new Font("Comic sans MS",Font.BOLD,25));
        
        // creating button
        JButton b = new JButton();
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Connection conn=DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("insert into employerlogin values(?,?,?,?,?)");
                    st.setString(1 , t1.getText());
                    st.setString(2, t2.getText());
                    st.setString(3, t3.getText());
                    st.setString(4, t4.getText());
                    st.setString(5, t5.getText());
                    st.executeUpdate();
                    conn.close();
                    
                }
                catch(Exception ee)
                {System.out.println(ee);}
            }
        });
        b.setText("Register");
        b.setBounds(300,520,100,40);
        c.add(b);
        
        f.setVisible(true);
       }
    public static void main(String[] args) {
        try{
            Connection conn=DBOperations.getConn();
            PreparedStatement st=conn.prepareStatement("select * from employerlogin");
            ResultSet rs=st.executeQuery();
            if(rs.next())
            {
                new LoginPage();
            }
            else{
                new EmployerRegistration();
            }
        }
        catch(SQLException ee)
        {}
    }
    
}