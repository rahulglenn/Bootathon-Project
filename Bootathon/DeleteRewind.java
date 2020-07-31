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
public class DeleteRewind {
    DeleteRewind(int id)
    {
        // frame creating
        MyFrame f = new MyFrame(4);           
        f.setBounds(500,100,600,800);
        
        
        //container creating
        Container c = f.getContentPane();
        c.setLayout(null);
        
        //title panel
      
        MyLabel ll= new MyLabel("Delete details",1);
        ll.setBounds(10,20,200,50);
        c.add(ll);
        
        //panel1 creating

        MyComboBox cb = new MyComboBox();          
        cb.addItem("Select HP");
            //adding values to combobox
            try{
                    Connection conn = DBOperations.getConn();
                    Statement st=conn.createStatement();
                    ResultSet rs=st.executeQuery("Select hp from rewinddet where emprid="+String.valueOf(id));
                    while(rs.next())
                    {
                        cb.addItem(rs.getString("hp"));
                    }
                    conn.close();
            }
            catch(Exception ee)
            {
            }
              
        MyLabel l1 = new MyLabel("Select HP   ");
        l1.setBounds(30,100,150,30);
        cb.setBounds(230,100,300,30);
        cb.setEditable(true);
        c.add(l1);
        c.add(cb);

        
        //panel2 creating

        MyLabel l2 = new MyLabel("Horse Power "); 
        l2.setBounds(30,170,150,30);
        c.add(l2);
        MyTextField t = new MyTextField();
        t.setEditable(false);
        t.setBounds(230,170,300,30);
        c.add(t);

        
        //panel3 creating

        MyLabel l3 = new MyLabel("Rewinding Details"); 
        MyTextArea ta = new MyTextArea();
        l3.setBounds(200,270,200,30);
        ta.setEditable(false);
        JScrollPane sp = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setBounds(70, 320, 500, 350);
        c.add(sp);
        c.add(l3);
        
        MyButton back = new MyButton("\u2190"+"  BACK");
        back.setBounds(300,700,150,30);
        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainFrame();
            }
        });
        
        MyButton delete = new MyButton("Delete");
        delete.setBounds(400,270,100,30);
        c.add(delete);
        c.add(back);
        
         cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String item=(String)cb.getSelectedItem();
                try{
                    Connection conn = DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("Select hp, details from rewinddet where hp=? and emprid=?");
                    st.setString(1, item);
                    st.setInt(2, id);
                    ResultSet rs=st.executeQuery();
                    rs.next();
                    t.setText(rs.getString("hp"));
                    ta.setText(rs.getString("details"));
                    conn.close();
                }
                catch(Exception ee)
                {
                    
                }
 
            }
        });
         
         delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(t.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(f, "<html><font size=4>Please select which detail to delete first!","Select",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    int a=JOptionPane.showConfirmDialog(f,"<html><font size=4>Are you sure to Delete?","Alert",JOptionPane.ERROR_MESSAGE);  
                if(a==JOptionPane.YES_OPTION){  
                try
                {
                    Connection conn = DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("delete from rewinddet where emprid=? and hp=?");
                    st.setInt(1, id);
                    st.setString(2, t.getText());
                    st.executeUpdate();
                    conn.close();
                }
                catch(Exception ee)
                {
                    System.out.println(ee);
                }
                JOptionPane.showMessageDialog(f,"<html><font size=4>Successfully Deleted.","Successful",JOptionPane.INFORMATION_MESSAGE);
                f.dispose();
                new DeleteRewind(id);
            }}}
        });
        
        f.setVisible(true);
    }
    public static void main(String[] args) {
        new LoginPage();
    }
}
