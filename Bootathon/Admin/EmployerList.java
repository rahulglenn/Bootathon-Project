/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;
/**
 *
 * @author rahul
 */
public class EmployerList {
    EmployerList()
    {
        MyFrame frame = new MyFrame(4);
        frame.setBounds(100,200,1350,700);
        Container c = frame.getContentPane();
        c.setLayout(null);
        MyLabel label1 = new MyLabel("Employer List",1);
        label1.setBounds(30,5,250,50);
        c.add(label1);
        MyTable table = new MyTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("  Empr.Id  ");
        model.addColumn("Empr.Name");
        model.addColumn("D.O.B");
        model.addColumn("Organization Name");
        model.addColumn("Activation date");
        model.addColumn("Product key");
        table.setModel(model);
         
        table.getColumnModel().getColumn(1).setPreferredWidth(350);
       
        try
        {
            Connection conn=DBOperations.getConn();
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery("select * from employerlogin");
            while(rs.next())
            {
                model.addRow(new Object[]{String.valueOf(rs.getInt("emprid")),rs.getString("Name"),rs.getString("DOB"),rs.getString("Organization"),rs.getString("ActivationDate"),rs.getString("ProductKey")});
            }
            conn.close();
        }
        catch(Exception ee)
        {System.out.println(ee);}

        table.setRowHeight(30);        
        JScrollPane scroll = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(20,70,1300,500);
        c.add(scroll);
        MyButton but = new MyButton("\u2190"+"  BACK");
        but.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 frame.dispose();
                 new AdminMain();
             }
         });
        but.setBounds(700,600,150,30);
        c.add(but);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        new AdminLogin();
    }
    
}
