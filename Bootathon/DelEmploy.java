/*
 * Description :Remove employee details by the employer.
 * Author      :Sai Karthik
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



import javax.swing.JOptionPane;


public class DelEmploy {
    DelEmploy(int id)
    {
        // TODO code application logic here
        MyFrame f = new MyFrame(2);           
        f.setBounds(500,100,600,800);
        
        
        //container creating
        Container c = f.getContentPane();
        c.setLayout(null);
        
       //label 1
       MyLabel l1 = new MyLabel("Remove Employee",1);      
       l1.setBounds(30,10,300,50);
       c.add(l1);
       
       //select employee
       MyLabel l2 = new MyLabel("Select Employee :");
       l2.setBounds(50,120,200,50);
       
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
               {
                    System.out.println("Cannot retrieve values from employdet!!"+ee); 
               }                
    
    
       cb.setBounds(250,130,250,30);
       cb.setEditable(false);
       c.add(l2);
       c.add(cb);
       
       //employee id
       MyLabel l3 = new MyLabel("Employee ID     :");
     
       l3.setBounds(50,190,200,50);
       
       MyTextField t1 = new MyTextField();
       t1.setEditable(false);
       t1.setBounds(250,200,250,30);
       c.add(l3);
       c.add(t1);
       
       //employee name
       MyLabel l4 = new MyLabel("Employee name  :");

       l4.setBounds(50,260,200,50);
       
       MyTextField t2 = new MyTextField();
       t2.setEditable(false);
       t2.setBounds(250,270,250,30);
       c.add(l4);
       c.add(t2);
       
       //dob
       MyLabel l5 = new MyLabel("         D.O.B  :");
       l5.setBounds(50,330,200,50);
       
       MyTextField t3 = new MyTextField();
       t3.setEditable(false);
       t3.setBounds(250,340,250,30);
       c.add(l5);
       c.add(t3);
       
       //phone number
       MyLabel l6 = new MyLabel("     Phone No   :");
       l6.setBounds(50,400,200,50);
       
       MyTextField t4 = new MyTextField();
       t4.setEditable(false);
       t4.setBounds(250,410,250,30);
       c.add(l6);
       c.add(t4);
       
       //salary
       MyLabel l7 = new MyLabel("        Salary   :");
       l7.setBounds(50,470,200,50);
       
       MyTextField t5 = new MyTextField();
       t5.setEditable(false);
       t5.setBounds(250,480,250,30);
       c.add(l7);
       c.add(t5);
        
      //buttons
      MyButton del = new MyButton("Delete");
      MyButton bac = new MyButton("\u2190"+"  BACK");
      //back action
      bac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 f.dispose();
                 new MainFrame();
            }
        });

      del.setBounds(80,610,130,30);
      bac.setBounds(80,670,130,30);
      c.add(del);
      c.add(bac);
      
      //selected employee details are displayed
      cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                    Connection conn = DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("Select * from employdet where empid=? and emprid=?");
                    st.setInt(1, Integer.valueOf((String)cb.getSelectedItem()));
                    st.setInt(2, id);
                    ResultSet rs=st.executeQuery();
                    rs.next();
                    t1.setText(String.valueOf(rs.getInt("empid")));
                    t2.setText(rs.getString("EmpName"));
                    t3.setText(rs.getString("DOB"));
                    t4.setText(rs.getString("Phone"));
                    t5.setText(String.valueOf(rs.getInt("Salary")));
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
      
      //selected employee details are deleted and employee removed
      del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(t1.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(f, "<html><font size=4>Please select which employee to delete first!","Select",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    int a=JOptionPane.showConfirmDialog(f,"<html><font size=4>Are you sure to Delete Employee?","Alert",JOptionPane.WARNING_MESSAGE);  
                if(a==JOptionPane.YES_OPTION){  
                try
                {
                    Connection conn = DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("delete from employdet where empid=? and emprid=?");
                    st.setInt(1, Integer.valueOf(t1.getText()));
                    st.setInt(2, id);
                    st.executeUpdate();
                    conn.close();
                }
                catch(NumberFormatException ee)
                {
                    System.out.println("The empid should be integer"+ee);
                }
                catch(SQLException ee)
                {
                    System.out.println("Cannot delete values from employdet"+ee);
                }
                f.dispose();
                new DelEmploy(id);
            }}}
        });
      
      f.setVisible(true);
    }
    public static void main(String[] args) {
       new LoadFrame();
    }
}
