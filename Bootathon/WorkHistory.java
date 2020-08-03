/*
 * Description : Work history includes the work details and the SD collection status. 
 * Author(s)   : Thulasi Ram,Sai Karthik 
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


public class WorkHistory {
    WorkHistory(int id)
    {
        //frame creation
         MyFrame wh_frame = new MyFrame(1);
        wh_frame.setBounds(100,200,1350,700);
        
        //container creation
        Container wh_c = wh_frame.getContentPane();
        wh_c.setLayout(null);
        
        //work details label craetion
        MyLabel label1 = new MyLabel("Work details",1);
        label1.setBounds(30,5,200,50);
        wh_c.add(label1);
        
        //table creation
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
        //data insertion to the table
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
        catch(SQLException ee)
                {
                    System.out.println("Cannot retrieve values from workdet"+ee);
                }

        table.setRowHeight(30);        
        JScrollPane scroll = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(20,70,1300,500);
        wh_c.add(scroll);
        //back action
        MyButton back = new MyButton("\u2190"+"  BACK");
        back.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 wh_frame.dispose();
                 new MainFrame();
             }
         });
        back.setBounds(30,600,150,30);
        wh_c.add(back);
        wh_frame.setVisible(true);
    }
    public static void main(String[] args) {
        new LoadFrame();
    }
}
