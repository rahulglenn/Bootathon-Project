/*
 * Description:Admin adds the employer's organization and provides the Activation key.
 * Author(s)  :Raahul Glenn,Sai Karthik
 */
package Bootathon.Admin;

import Bootathon.database.DBOperations;
import Bootathon.uiworks.MyButton;
import Bootathon.uiworks.MyFrame;
import Bootathon.uiworks.MyLabel;
import Bootathon.uiworks.MyTextField;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import javax.swing.JOptionPane;

public class AdminAddOrg {
    AdminAddOrg()
    {
       //frame creation
       MyFrame addorg_frame = new MyFrame(2);
       addorg_frame.setBounds(500,200,500,700);
       addorg_frame.setDefaultCloseOperation(3);
       Container c = addorg_frame.getContentPane();
       c.setLayout(null);
       
       //add organization label creation
       MyLabel addorg_l1 = new MyLabel("Add organization",1);
       addorg_l1.setBounds(10,20,300,50);
       c.add(addorg_l1);
       
       //organization name label and textfield creation
       MyLabel orgname_l2 = new MyLabel("Organization Name");
       orgname_l2.setBounds(50,130,200,30);
       c.add(orgname_l2);
       MyTextField orgname_t1 = new MyTextField();
       orgname_t1.setBounds(250,130,200,30);
       c.add(orgname_t1);
       
       //activation key label and textfield creation
       MyLabel ak_l3 = new MyLabel("Activation key");
       ak_l3.setBounds(50,200,150,30);
       c.add(ak_l3);
       MyTextField ak_t2 = new MyTextField();
       ak_t2.setEditable(false);
       ak_t2.setBounds(250,200,200,30);
       c.add(ak_t2);
       
       //generate button creation
       MyButton gen = new MyButton("Generate");
       
       //UUID generation
       gen.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if(orgname_t1.getText().isEmpty())
               {
                    JOptionPane.showMessageDialog(addorg_frame, "Please Fill the Organization Name!");
               }
               else if(orgname_t1.getText().length()>20)
               {
                    JOptionPane.showMessageDialog(addorg_frame, "The Organization Name can have a max of 20 characters!");
               }
               else
               {
                   UUID key=UUID.randomUUID();
                   ak_t2.setText(key.toString());
               try{
                   Connection conn=DBOperations.getConn();
                   
                   PreparedStatement st=conn.prepareStatement("insert into productinfo values(0,?,?,0)");
                   st.setString(1, orgname_t1.getText());
                   st.setString(2, key.toString());
                   st.executeUpdate();
                   conn.close();
               }
               catch(SQLException ee)
               {System.out.println("Cannot insert values into DB"+ee);}
                JOptionPane.showMessageDialog(addorg_frame,"Successfully Added.","Alert",JOptionPane.WARNING_MESSAGE);
           }}
       });
       gen.setBounds(70,300,150,30);
       c.add(gen);
       
       //back goes to the Admin Main frame
       MyButton back = new MyButton("\u2190"+"  BACK");
       back.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               addorg_frame.dispose();
               new AdminMain();
           }
       });
       back.setBounds(270,300,150,30);
       c.add(back);
       addorg_frame.setVisible(true);
    }
    public static void main(String[] args) {
        new AdminLogin();
    }
    
}
