/*
 *  Description :View employee details by the employer.
 * Author       :Sai Karthik
 */
package Bootathon;


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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.table.DefaultTableModel;


public class ViewEmploy {
    ViewEmploy(int id)
    {
        //frame creation
        MyFrame frame = new MyFrame(1);
        frame.setBounds(100,200,1350,700);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
        
        //container creation
        Container c = frame.getContentPane();
        c.setLayout(null);
        
        //label l1 creation
        MyLabel label1 = new MyLabel("Employee details",1);
        label1.setBounds(20,5,300,50);
        c.add(label1);
        
        //table creation
        MyTable table = new MyTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("  Emp. Id  ");
        model.addColumn("Emp. Name");
        model.addColumn("Address");
        model.addColumn("D.O.B");
        model.addColumn("Phone No");
        model.addColumn("Salary");
        model.addColumn("Current Salary");
        table.setModel(model);
        
        //data insertion into the table
        try{
            Connection conn=DBOperations.getConn();
            PreparedStatement st=conn.prepareStatement("select * from employdet where emprid=?");
            st.setInt(1, id);
            ResultSet rs=st.executeQuery();
            while(rs.next())
            {
                model.addRow(new Object[]{String.valueOf(rs.getInt("empid")),rs.getString("EmpName"),rs.getString("Address"),rs.getString("DOB"),rs.getString("Phone"),String.valueOf(rs.getInt("Salary")),String.valueOf(rs.getInt("CurSalary"))});
            }
            conn.close();
        }
        catch(SQLException ee)
                {
                    System.out.println("Cannot retrieve values from employdet"+ee);
                }
        
        table.getColumnModel().getColumn(0).setPreferredWidth(70); 
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(500); 
        

        table.setRowHeight(30);        
        JScrollPane scroll = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(20,70,1300,500);
        c.add(scroll);
        
        //back action
        MyButton but = new MyButton("\u2190"+"  BACK");
        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainFrame();
            }
        });
        but.setBounds(30,600,150,30);
        c.add(but);
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        new LoadFrame();
    }
    
}
