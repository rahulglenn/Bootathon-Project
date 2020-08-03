/*
 * Description :Maintaining the leave entry of employee by the employer thus deducting from his monthly salary according to the leave days. 
 * Author      :Sai Karthik,Rahul Glenn
 */
package Bootathon;

import Bootathon.database.DBOperations;
import Bootathon.uiworks.MyButton;
import Bootathon.uiworks.MyComboBox;
import Bootathon.uiworks.MyFrame;
import Bootathon.uiworks.MyLabel;
import Bootathon.uiworks.MyTextField;
import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class LeaveEntry {
    LeaveEntry(int id)
    {
        // frame creating
        MyFrame f = new MyFrame(4);           
        f.setBounds(500,100,600,800);
       
        
        //container creating
        Container c = f.getContentPane();
        c.setLayout(null);
        
       //label 1
       MyLabel l1 = new MyLabel("Leave Entry",1);
       l1.setBounds(30,10,300,50);
       c.add(l1);
       
       //select employee
       MyLabel l2 = new MyLabel("Select Employee");
       l2.setBounds(50,100,300,50);
       
       MyComboBox cb = new MyComboBox();
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
            catch(SQLException ee)
            {System.out.println("Cannot retrieve values from employdet"+ee);}

       cb.setBounds(250,110,300,30);
       cb.setEditable(false);
       c.add(l2);
       c.add(cb);
       
       //employee id
       MyLabel l3 = new MyLabel("Employee ID ");

       l3.setBounds(50,170,300,50);
       
       MyTextField t1 = new MyTextField();
       t1.setBounds(250,180,300,30);
       t1.setEditable(false);
       c.add(l3);
       c.add(t1);
       
       //employee name
       MyLabel l4 = new MyLabel("Employee name");
       l4.setBounds(50,240,300,50);
       
       MyTextField t2 = new MyTextField();
       t2.setBounds(250,250,300,30);
       t2.setEditable(false);
       c.add(l4);
       c.add(t2);
       
       //dob
       MyLabel l5 = new MyLabel(" No. of Days  ");
       l5.setBounds(50,310,300,50);
       
       MyTextField t3 = new MyTextField();
       t3.setBounds(250,320,300,30);
       c.add(l5);
       c.add(t3);
       
      //buttons
      MyButton enter = new MyButton("Enter");
      //back action
      MyButton bac = new MyButton("\u2190"+"  BACK");
      bac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainFrame();
            }
        });
      enter.setBounds(400,410,130,30);
      bac.setBounds(400,480,130,30);
      c.add(enter);
      c.add(bac);
      
      //selected employee details are displayed
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
               catch(NumberFormatException ee)
               {
                   System.out.println("The selected item should be integer"+ee);
               }
               catch(SQLException ee)
               {
                   System.out.println("Cannot retrieve values from employdet"+ee);
               }
            }
        });
       
      //Number of leave days entered,accordingly salary deducted
       enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(t1.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(f, "<html><font size=4>select an employee id first!","Select",JOptionPane.ERROR_MESSAGE);
                }
                else if(t3.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(f, "<html><font size=4>specify No. of Days!","Specify",JOptionPane.ERROR_MESSAGE);
                }
                else if(!Pattern.matches("^[0-9]+$", t3.getText()))
                {
                    JOptionPane.showMessageDialog(f, "<html><font size=4>No. of Days is an Integer Value!","Specify",JOptionPane.ERROR_MESSAGE);
                }
                else if(Integer.valueOf(t3.getText())>30)
                {
                    JOptionPane.showMessageDialog(f, "<html><font size=4>No. of Days cannot exceed 30!","Specify",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                     int a=JOptionPane.showConfirmDialog(f,"<html><font size=4>Are you sure?");  
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
                    JOptionPane.showMessageDialog(f,"<html><font size=4>Successfully Marked!","Successful",JOptionPane.INFORMATION_MESSAGE);
                }
                catch(NumberFormatException ee)
               {
                   System.out.println("The selected item should be integer"+ee);
               }
               catch(SQLException ee)
               {
                   System.out.println("Cannot retrieve values from employdet"+ee);
               }
                f.dispose();
                new LeaveEntry(id);
            }}}
        });
       
      f.setVisible(true);        
    }
    public static void main(String[] args) {
       new LoadFrame();
    }
}
 