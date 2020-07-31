/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rahul
 */
public class WorkHistory {
    WorkHistory(int id)
    {
         MyFrame frame = new MyFrame(1);
        frame.setBounds(100,200,1350,700);
        
        Container c = frame.getContentPane();
        c.setLayout(null);
        MyLabel label1 = new MyLabel("Work details",1);
        label1.setBounds(30,5,200,50);
        c.add(label1);
        MyTable table = new MyTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("  Work Id  ");
        model.addColumn("Name of Work");
        model.addColumn("Security deposit");
        model.addColumn("Date");
        model.addColumn("SD collection status");
       
        table.setModel(model);
         
        table.getColumnModel().getColumn(1).setPreferredWidth(350);
        
        String coll_status[]={"Pending","Collected"};
        try
        {
            Connection conn=DBOperations.getConn();
            PreparedStatement st=conn.prepareStatement("select * from workdet where emprid=?");
            st.setInt(1, id);
            ResultSet rs=st.executeQuery();
            while(rs.next())
            {
                model.addRow(new Object[]{String.valueOf(rs.getInt("workid")),rs.getString("NameOfWork"),rs.getString("Amount"),rs.getString("Date"),coll_status[rs.getInt("CollectionStatus")]});
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
                 new MainFrame();
             }
         });
        but.setBounds(30,600,150,30);
        c.add(but);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new LoginPage();
    }
}
