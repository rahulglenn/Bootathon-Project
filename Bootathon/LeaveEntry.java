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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 *
 * @author rahul
 */
public class LeaveEntry {
    LeaveEntry(int id)
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
       JLabel l1 = new JLabel("Leave Entry");
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
                    ResultSet rs=st.executeQuery("Select empid from employdet where emprid="+String.valueOf(id));
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
       t1.setBounds(250,180,200,30);
       t1.setEditable(false);
       c.add(l3);
       c.add(t1);
       
       //employee name
       JLabel l4 = new JLabel("Employee name  :");
       l4.setFont(new Font("Comic sans MS",Font.BOLD,20));
       l4.setBounds(50,240,200,50);
       
       JTextField t2 = new JTextField();
       t2.setFont(new Font("arial",Font.BOLD,18));
       t2.setBounds(250,250,200,30);
       t2.setEditable(false);
       c.add(l4);
       c.add(t2);
       
       //dob
       JLabel l5 = new JLabel(" No. of Days    :");
       l5.setFont(new Font("Comic sans MS",Font.BOLD,20));
       l5.setBounds(50,310,200,50);
       
       JTextField t3 = new JTextField();
       t3.setFont(new Font("arial",Font.BOLD,18));
       t3.setBounds(250,320,200,30);
       c.add(l5);
       c.add(t3);
       
      //buttons
      JButton enter = new JButton("Enter");
      JButton bac = new JButton("\u2190"+"  BACK");
      bac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainFrame();
            }
        });
      enter.setFont(new Font("",Font.BOLD,20));
      enter.setBounds(100,410,130,30);
      bac.setFont(new Font("",Font.BOLD,20));
      bac.setBounds(280,410,130,30);
      c.add(enter);
      c.add(bac);
        
      cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
               try{
                    Connection conn = DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("Select empid, EmpName from employdet where empid=? and emprid=?");
                    st.setInt(1, Integer.valueOf((String)cb.getSelectedItem()));
                    st.setInt(2, id);
                    ResultSet rs=st.executeQuery();
                    rs.next();
                    t1.setText(String.valueOf(rs.getInt("empid")));
                    t2.setText(rs.getString("EmpName"));
                    conn.close();
                }
               catch(Exception ee)
               {
                   System.out.println(ee);
               }}
        });
           
       enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(t1.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(f, "Please select an employee id first!");
                }
                else if(t3.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(f, "Please specify No. of Days!");
                }
                else
                {
                     int a=JOptionPane.showConfirmDialog(f,"Are you sure?");  
                if(a==JOptionPane.YES_OPTION){  
                try{
                 Connection conn = DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("Select Salary, CurSalary from employdet where empid=? and emprid=?");
                    st.setInt(1, Integer.valueOf((String)cb.getSelectedItem()));
                    st.setInt(2, id);
                    ResultSet rs=st.executeQuery();
                    rs.next();
                    int cursal=Integer.valueOf(rs.getString("CurSalary")); //current salary
                    int sal=Integer.valueOf(rs.getString("Salary"));       //monthly salary
                    int onedaysal=sal/30;                                  //one day salary
                    PreparedStatement pst=conn.prepareStatement("update employdet set CurSalary=? where empid=? and emprid=?");
                    pst.setInt(1, cursal-(onedaysal*Integer.valueOf(t3.getText())));
                    pst.setInt(2, Integer.valueOf((String)cb.getSelectedItem()));
                    pst.setInt(3, id);
                    pst.executeUpdate();
                    conn.close();
                }
                catch(Exception ee)
                {
                    System.out.println(ee);
                }
                JOptionPane.showMessageDialog(f,"Successfully Marked!","Alert",JOptionPane.WARNING_MESSAGE);
                f.dispose();
                new LeaveEntry(id);
            }}}
        });
       
      f.setVisible(true);        
    }
    public static void main(String[] args) {
       new LoginPage();
    }
}
