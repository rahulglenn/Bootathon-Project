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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 *
 * @author rahul
 */
public class DelEmploy {
    DelEmploy()
    {
        // TODO code application logic here
        JFrame f = new JFrame();           
        f.setBounds(500,200,500,700);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("View Form");
        f.setResizable(false); 
        
        //container creating
        Container c = f.getContentPane();
        c.setLayout(null);
        
       //label 1
       JLabel l1 = new JLabel("Remove Employee");
       l1.setFont(new Font("Comic sans MS",Font.BOLD,25));
       l1.setBounds(30,10,300,50);
       c.add(l1);
       
       //select employee
       JLabel l2 = new JLabel("Select Employee :");
       l2.setFont(new Font("Comic sans MS",Font.BOLD,20));
       l2.setBounds(50,100,200,50);
       
       JComboBox cb = new JComboBox();
       cb.addItem("Select EmpID");
               //adding values to combobox
               try{
                   Connection conn = DBOperations.getConn();
                    Statement st=conn.createStatement();
                    ResultSet rs=st.executeQuery("Select empid from employdet");
                    while(rs.next())
                    {
                        cb.addItem(rs.getString("empid"));
                    }
                    conn.close();
               }
               catch(Exception ee)
               {}                
    
       cb.setFont(new Font("arial",Font.BOLD,18));
       cb.setBounds(250,110,200,30);
       cb.setEditable(true);
       c.add(l2);
       c.add(cb);
       
       //employee id
       JLabel l3 = new JLabel("Employee ID     :");
       l3.setFont(new Font("Comic sans MS",Font.BOLD,20));
       l3.setBounds(50,170,200,50);
       
       JTextField t1 = new JTextField();
       t1.setFont(new Font("arial",Font.BOLD,18));
       t1.setEditable(false);
       t1.setBounds(250,180,200,30);
       c.add(l3);
       c.add(t1);
       
       //employee name
       JLabel l4 = new JLabel("Employee name  :");
       l4.setFont(new Font("Comic sans MS",Font.BOLD,20));
       l4.setBounds(50,240,200,50);
       
       JTextField t2 = new JTextField();
       t2.setEditable(false);
       t2.setFont(new Font("arial",Font.BOLD,18));
       t2.setBounds(250,250,200,30);
       c.add(l4);
       c.add(t2);
       
       //dob
       JLabel l5 = new JLabel("         D.O.B  :");
       l5.setFont(new Font("Comic sans MS",Font.BOLD,20));
       l5.setBounds(50,310,200,50);
       
       JTextField t3 = new JTextField();
       t3.setEditable(false);
       t3.setFont(new Font("arial",Font.BOLD,18));
       t3.setBounds(250,320,200,30);
       c.add(l5);
       c.add(t3);
       
       //phone number
       JLabel l6 = new JLabel("     Phone No   :");
       l6.setFont(new Font("Comic sans MS",Font.BOLD,20));
       l6.setBounds(50,380,200,50);
       
       JTextField t4 = new JTextField();
       t4.setEditable(false);
       t4.setFont(new Font("arial",Font.BOLD,18));
       t4.setBounds(250,390,200,30);
       c.add(l6);
       c.add(t4);
       
       //salary
       JLabel l7 = new JLabel("        Salary   :");
       l7.setFont(new Font("Comic sans MS",Font.BOLD,20));
       l7.setBounds(50,450,200,50);
       
       JTextField t5 = new JTextField();
       t5.setEditable(false);
       t5.setFont(new Font("arial",Font.BOLD,18));
       t5.setBounds(250,460,200,30);
       c.add(l7);
       c.add(t5);
        
      //buttons
      JButton del = new JButton("Delete");
      JButton bac = new JButton("\u2190"+"  BACK");
      bac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 f.dispose();
                 new MainFrame();
            }
        });
      del.setFont(new Font("",Font.BOLD,20));
      del.setBounds(100,550,130,30);
      bac.setFont(new Font("",Font.BOLD,20));
      bac.setBounds(280,550,130,30);
      c.add(del);
      c.add(bac);
      
      cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                    Connection conn = DBOperations.getConn();
                    Statement st=conn.createStatement();
                    ResultSet rs=st.executeQuery("Select * from employdet where empid="+(String)cb.getSelectedItem());
                    rs.next();
                    t1.setText(String.valueOf(rs.getInt("empid")));
                    t2.setText(rs.getString("EmpName"));
                    t3.setText(rs.getString("DOB"));
                    t4.setText(rs.getString("Phone"));
                    t5.setText(String.valueOf(rs.getInt("Salary")));
                    conn.close();
                }
                catch(Exception ee)
                {
                    
                }
            }
        });
      
      del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    Connection conn = DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("delete from employdet where empid=?");
                    st.setInt(1, Integer.valueOf(t1.getText()));
                    st.executeUpdate();
                    conn.close();
                }
                catch(Exception ee)
                {}
                f.dispose();
                new DelEmploy();
            }
        });
      
      f.setVisible(true);
    }
    public static void main(String[] args) {
        new DelEmploy();
    }
}
