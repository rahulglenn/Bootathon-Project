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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rahul
 */
public class ViewEmploy {
    ViewEmploy()
    {
        JFrame frame = new JFrame();
        frame.setBounds(100,200,1350,700);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
        Container c = frame.getContentPane();
        c.setLayout(null);
        JLabel label1 = new JLabel("Employee details");
        label1.setFont(new Font("Comic sans MS",Font.BOLD,20));
        label1.setBounds(10,5,200,50);
        c.add(label1);
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("  Emp. Id  ");
        model.addColumn("Emp. Name");
        model.addColumn("Address");
        model.addColumn("D.O.B");
        model.addColumn("Phone No");
        model.addColumn("Salary");
        model.addColumn("Current Salary");
        table.setModel(model);
        
        try{
            Connection conn=DBOperations.getConn();
            PreparedStatement st=conn.prepareStatement("select * from employdet");
            ResultSet rs=st.executeQuery();
            while(rs.next())
            {
                model.addRow(new Object[]{String.valueOf(rs.getInt("empid")),rs.getString("EmpName"),rs.getString("Address"),rs.getString("DOB"),rs.getString("Phone"),String.valueOf(rs.getInt("Salary")),String.valueOf(rs.getInt("CurSalary"))});
            }
            conn.close();
        }
        catch(Exception ee)
        {System.out.println(ee);}
        
        table.getColumnModel().getColumn(0).setPreferredWidth(70); 
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(500); 
        

        table.setRowHeight(30);        
        table.setFont(new Font("arial",Font.PLAIN,20));
        JScrollPane scroll = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(20,70,1300,500);
        c.add(scroll);
        JButton but = new JButton("\u2190"+"  BACK");
        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainFrame();
            }
        });
        but.setFont(new Font("",Font.PLAIN,20));
        but.setBounds(700,600,150,30);
        c.add(but);
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        new ViewEmploy();
    }
    
}