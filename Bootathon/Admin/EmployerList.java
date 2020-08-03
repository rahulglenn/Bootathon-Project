/*
 * Description:Displays the Employer List registered.
 * Author(s)  :Raahul Glenn,Sai Karthik
 */
package Bootathon.Admin;

import Bootathon.database.DBOperations;
import Bootathon.uiworks.MyButton;
import Bootathon.uiworks.MyFrame;
import Bootathon.uiworks.MyLabel;
import Bootathon.uiworks.MyTable;
import java.awt.Container;

import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class EmployerList {
    EmployerList()
    {
        //frame creation
        MyFrame emprlist_frame = new MyFrame(4);
        emprlist_frame.setBounds(100,200,1350,700);
        
        //container
        Container emprlist_con = emprlist_frame.getContentPane();
        emprlist_con.setLayout(null);
        
        //employerlist label
        MyLabel emprlist_l1 = new MyLabel("Employer List",1);
        emprlist_l1.setBounds(30,5,250,50);
        emprlist_con.add(emprlist_l1);
        
        //table creation
        MyTable emprlist_table = new MyTable();
        DefaultTableModel emprlist_model = new DefaultTableModel();
        
        //adding columns
        emprlist_model.addColumn("  Empr.Id  ");
        emprlist_model.addColumn("Empr.Name");
        emprlist_model.addColumn("D.O.B");
        emprlist_model.addColumn("Organization Name");
        emprlist_model.addColumn("Activation date");
        emprlist_model.addColumn("Product key");
        emprlist_table.setModel(emprlist_model);
         
        emprlist_table.getColumnModel().getColumn(1).setPreferredWidth(350);
       
        //retriving employerlist from employerlogin
        try
        {
            Connection conn=DBOperations.getConn();
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery("select * from employerlogin");
            while(rs.next())
            {
                emprlist_model.addRow(new Object[]{String.valueOf(rs.getInt("emprid")),rs.getString("Name"),rs.getString("DOB"),rs.getString("Organization"),rs.getString("ActivationDate"),rs.getString("ProductKey")});
            }
            conn.close();
        }
        catch(SQLException ee)
        {System.out.println("Cannot retrieve values from employerlogin"+ee);}

        emprlist_table.setRowHeight(30);        
        JScrollPane scroll = new JScrollPane(emprlist_table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(20,70,1300,500);
        emprlist_con.add(scroll);
        
        //back button goes to the AdminMain frame
        MyButton emprlist_back = new MyButton("\u2190"+"  BACK");
        emprlist_back.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 emprlist_frame.dispose();
                 new AdminMain();
             }
         });
        emprlist_back.setBounds(700,600,150,30);
        emprlist_con.add(emprlist_back);
        emprlist_frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        new AdminLogin();
    }
    
}
