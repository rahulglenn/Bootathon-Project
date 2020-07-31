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
import Bootathon.uiworks.MyTextArea;
import Bootathon.uiworks.MyTextField;
import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import javax.swing.JScrollPane;



/**
 *
 * @author rahul
 */
public class AddRewind {
    AddRewind(int id)
    {
        //Frame creating
        MyFrame addrewind=new MyFrame(5);
        addrewind.setBounds(500, 100, 600, 800);
      
        
        //Container Creating
        Container c=addrewind.getContentPane();
        c.setLayout(null);
        
        //title label
        
        MyLabel ll= new MyLabel("Add Rewinding Details",1);
        ll.setBounds(30,30,350,50);
        c.add(ll);
        
        
        //HorsePower entry
      
        MyLabel label1 = new MyLabel("Enter HP ");
        MyTextField enterhp = new MyTextField();
        label1.setBounds(50,130,100,30);
        enterhp.setBounds(200,130,300,30);
        c.add(label1);
        c.add(enterhp);
        
        //Rewind details entry
        

        MyLabel l3 = new MyLabel("Enter the details");
        l3.setBounds(200, 230, 200, 30);
        c.add(l3);
        
        MyTextArea ta = new MyTextArea();
        
       // ta.setEditable(false);
        JScrollPane sp = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setBounds(30,280,550,400);
        c.add(sp);
 
        
        //Submit and Back
        
        MyButton back = new MyButton("\u2190"+"  BACK");
        back.setBounds(30,700,150,30);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addrewind.dispose();
                new MainFrame();
            }
        });
        MyButton submit = new MyButton("Submit  "+"\u2192");
        submit.setBounds(400, 230, 150, 30);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(enterhp.getText().isEmpty() || ta.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(addrewind, "<html><font size=4>Please Fill all the details listed above!","Fill it",JOptionPane.ERROR_MESSAGE);
                }
                else{
                try{
                    Connection conn = DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("insert into rewinddet values(?,?,?)");
                    st.setInt(1, id);
                    st.setString(2, enterhp.getText());
                    st.setString(3, ta.getText());
                    st.executeUpdate();
                    conn.close();
                }
                catch(Exception ee)
                {
                    System.out.println(ee);
                }
                JOptionPane.showMessageDialog(addrewind,"<html><font size=4>Successfully Added.","Successful",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        c.add(submit);
        c.add(back);
        addrewind.setVisible(true);
             
    }
    public static void main(String[] args) {
      new LoginPage();
    }
    
}
