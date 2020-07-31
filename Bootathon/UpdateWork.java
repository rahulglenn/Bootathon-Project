/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootathon;
import Bootathon.database.DBOperations;
import Bootathon.uiworks.MyButton;
import Bootathon.uiworks.MyComboBox;
import Bootathon.uiworks.MyFrame;
import Bootathon.uiworks.MyLabel;
import Bootathon.uiworks.MyTextArea;
import Bootathon.uiworks.MyTextField;
import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

import javax.swing.JScrollPane;

/**
 *
 * @author rahul
 */
public class UpdateWork {
    UpdateWork(int id)
    {
         // frame creating
        MyFrame f = new MyFrame(4);           
        f.setBounds(500,100,600,800);
       
        
        //container creating
        Container c = f.getContentPane();
        c.setLayout(null);
        
        MyLabel ll= new MyLabel("Update details",1); 
        ll.setBounds(30,30,200,50);
        c.add(ll);
        
        //String[] amount = { "Rs.2500" , "Rs.3500" , "Rs.4500" ,"Rs.1500" };
        MyComboBox cb = new MyComboBox();
        cb.addItem("Select WorkID");
            //adding values to combobox
            try{
                    Connection conn = DBOperations.getConn();
                    Statement st=conn.createStatement();
                    ResultSet rs=st.executeQuery("Select workid from workdet where emprid="+String.valueOf(id));
                    while(rs.next())
                    {
                        cb.addItem(rs.getString("workid"));
                    }
                    conn.close();
            }
            catch(Exception ee)
            {
                
            }
               
        MyLabel l1 = new MyLabel("Select Work ID  ");
        l1.setBounds(90,140,200,30);
        cb.setBounds(250,140,300,30);
        c.add(l1);
        c.add(cb);
        cb.setEditable(true);

        
        
        MyLabel le = new MyLabel("  Date ");        
        c.add(le);
        MyTextField te = new MyTextField();
        c.add(te); 
        le.setBounds(90,210,200,30);
        te.setBounds(250,210,300,30);
        
      
        MyLabel l2 = new MyLabel("Security Deposit Value ");        
        c.add(l2);
        l2.setBounds(10,270,250,30);
        MyTextField t = new MyTextField();
        c.add(t);
        t.setBounds(250,270,300,30);
        
       
        MyLabel l3 = new MyLabel("Rewinding Details"); 
        l3.setBounds(200,350,200,30);
        c.add(l3);
        MyTextArea ta = new MyTextArea();
        JScrollPane sp = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setBounds(80,400,450,300);
        c.add(sp);

        
       
        MyButton back = new MyButton("\u2190"+"  BACK");
        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainFrame();
            }
        });
        
        MyButton update = new MyButton("Update");
        back.setBounds(340,710,150,30);
        update.setBounds(400,350,130,30);
        c.add(update);
        c.add(back);
        
        cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int item=Integer.valueOf((String)cb.getSelectedItem());
                try{
                    Connection conn = DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("Select Date, workid, Amount, NameOfWork from workdet where workid=? and emprid=?");
                    st.setInt(1, item);
                    st.setInt(2, id);
                    ResultSet rs=st.executeQuery();
                    rs.next();
                    te.setText(rs.getString("Date"));
                    t.setText(rs.getString("Amount"));
                    ta.setText(rs.getString("NameOfWork"));
                    conn.close();
                }
                catch(Exception ee)
                {
                    
                }
            }
        });
        
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(te.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(f, "<html><font size=4>Please select which record to update first!","Select",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    int a=JOptionPane.showConfirmDialog(f,"<html><font size=4>Are you sure to Update?");  
                if(a==JOptionPane.YES_OPTION){  
                try
                {
                    Connection conn = DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("update workdet set Date=?, Amount=?, NameOfWork=? where workid=? and emprid=?");
                    st.setString(1, te.getText());
                    st.setString(2, t.getText());
                    st.setString(3, ta.getText());
                    st.setInt(4, Integer.valueOf((String)cb.getSelectedItem()));
                    st.setInt(5, id);
                    st.executeUpdate();
                    conn.close();
                }
                catch(Exception ee)
                {
                    System.out.println(ee);
                }
                JOptionPane.showMessageDialog(f,"<html><font size=4>Successfully Updated.","Successful",JOptionPane.INFORMATION_MESSAGE);
                }
            }}
        });
        f.setVisible(true);
    }
    public static void main(String[] args) {
       new LoginPage();
    }
    
}
