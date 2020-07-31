/*
 * Author(s):Thulasi Ram,Rahul Glenn
 * Description:this frame gets employee details
 */
package Bootathon;

import Bootathon.database.DBOperations;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AddEmploy {
    AddEmploy(int id)
    {
        // frame creating
        JFrame f = new JFrame();           
        f.setBounds(500,200,500,700);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Leave entry");
        f.setResizable(false); 
        
        //container creating
        Container c = f.getContentPane();
        c.setLayout(null);
        
       //label 1
       JLabel l1 = new JLabel("Add Employee");
       l1.setFont(new Font("Comic sans MS",Font.BOLD,25));
       l1.setBounds(30,10,300,50);
       c.add(l1);
       
       //select employee
       JLabel l2 = new JLabel("Employee Name :");
       l2.setFont(new Font("Comic sans MS",Font.BOLD,20));
       l2.setBounds(50,70,200,50);
       
       JTextField t = new JTextField();
       t.setFont(new Font("arial",Font.BOLD,18));
       t.setBounds(250,80,200,30);
       c.add(l2);
       c.add(t);
       
       //employee id
       JLabel l3 = new JLabel("     Address    :");
       l3.setFont(new Font("Comic sans MS",Font.BOLD,20));
       l3.setBounds(50,140,200,50);
       
       JTextField t1 = new JTextField();
       t1.setFont(new Font("arial",Font.BOLD,18));
       t1.setBounds(250,150,200,30);
       c.add(l3);
       c.add(t1);
       
       //employee name
       JLabel l4 = new JLabel("         D.O.B  :");
       l4.setFont(new Font("Comic sans MS",Font.BOLD,20));
       l4.setBounds(50,210,200,50);
       
       JTextField t2 = new JTextField();
       t2.setFont(new Font("arial",Font.BOLD,18));
       t2.setBounds(250,220,200,30);
       c.add(l4);
       c.add(t2);
       
       //dob
       JLabel l5 = new JLabel("      Phone No  :");
       l5.setFont(new Font("Comic sans MS",Font.BOLD,20));
       l5.setBounds(50,280,200,50);
       
       JTextField t3 = new JTextField();
       t3.setFont(new Font("arial",Font.BOLD,18));
       t3.setBounds(250,290,200,30);
       c.add(l5);
       c.add(t3);
       
       JLabel l6 = new JLabel("         Salary  :");
       l6.setFont(new Font("Comic sans MS",Font.BOLD,20));
       l6.setBounds(50,350,200,50);
       
       JTextField t4 = new JTextField();
       t4.setFont(new Font("arial",Font.BOLD,18));
       t4.setBounds(250,360,200,30);
       c.add(l6);
       c.add(t4);
       
       JLabel l7 = new JLabel("     Email ID  :");
       l7.setFont(new Font("Comic sans MS",Font.BOLD,20));
       l7.setBounds(50,420,200,50);
       
       JTextField t5 = new JTextField();
       t5.setFont(new Font("arial",Font.BOLD,18));
       t5.setBounds(250,430,200,30);
       c.add(l7);
       c.add(t5);
       
       JLabel l8 = new JLabel("     Password  :");
       l8.setFont(new Font("Comic sans MS",Font.BOLD,20));
       l8.setBounds(50,490,200,50);
       
       JPasswordField t6 = new JPasswordField();
       t6.setFont(new Font("arial",Font.BOLD,18));
       t6.setBounds(250,500,200,30);
       c.add(l8);
       c.add(t6);
       
       JLabel l9 = new JLabel("Re-type Password:");
       l9.setFont(new Font("Comic sans MS",Font.BOLD,20));
       l9.setBounds(25,560,200,50);
       
       JPasswordField t7 = new JPasswordField();
       t7.setFont(new Font("arial",Font.BOLD,18));
       t7.setBounds(250,570,200,30);
       c.add(l9);
       c.add(t7);
        
      
      JButton enter = new JButton("Enter");
      enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(t.getText().isEmpty() || t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty() || t4.getText().isEmpty() || t5.getText().isEmpty() ||String.valueOf(t6.getPassword()).isEmpty() || String.valueOf(t7.getPassword()).isEmpty())
                {
                    JOptionPane.showMessageDialog(f, "Please Fill all the details listed above!");
                }
                else if(!Pattern.compile("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$").matcher(t2.getText()).matches())
                {
                    JOptionPane.showMessageDialog(f, "Invalid Date Format use (dd/mm/yyy)!");
                }
                else if(!Pattern.compile("^(.+)@(.+)$").matcher(t5.getText()).matches())
                {
                    JOptionPane.showMessageDialog(f, "Invalid Email Address!");
                }
                else if(!String.valueOf(t6.getPassword()).equals(String.valueOf(t7.getPassword())))
                {
                    JOptionPane.showMessageDialog(f, "The Password and Retype Password does not match!");
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
                JOptionPane.showMessageDialog(f,"Successfully Added.","Alert",JOptionPane.WARNING_MESSAGE);
                f.dispose();
                new AddEmploy(id);
            }}
        });
      JButton bac = new JButton("\u2190"+"  BACK");
      bac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainFrame();
            }
        });
      enter.setFont(new Font("",Font.BOLD,20));
      enter.setBounds(100,620,130,30);
      bac.setFont(new Font("",Font.BOLD,20));
      bac.setBounds(280,620,130,30);
      c.add(enter);
      c.add(bac);
        
      f.setVisible(true);
        
    }
    public static void main(String[] args) {
        new LoginPage();
    }
    
}
//Copyrights - Team 16
