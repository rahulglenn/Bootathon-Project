/*
 * Description:Adding the Rewinding details by the employer to the database.
 * Author(s)  :Pradakshina,rahul Glenn,Sai Karthik
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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class AddRewind {
    AddRewind(int id)
    {
        //Frame creating
        MyFrame addrewind=new MyFrame(5);
        addrewind.setBounds(500, 100, 600, 800);
      
        
        //Container Creating
        Container addrew_c=addrewind.getContentPane();
        addrew_c.setLayout(null);
        
        //add rewinding details label creation
        MyLabel addrew_ll= new MyLabel("Add Rewinding Details",1);
        addrew_ll.setBounds(30,30,350,50);
        addrew_c.add(addrew_ll);
        
        
        //HorsePower entry
        MyLabel addrew_label1 = new MyLabel("Enter HP ");
        MyTextField enterhp = new MyTextField();
        addrew_label1.setBounds(50,130,100,30);
        enterhp.setBounds(200,130,300,30);
        addrew_c.add(addrew_label1);
        addrew_c.add(enterhp);
        
        //Rewind details entry
        MyLabel addrew_l3 = new MyLabel("Enter the details");
        addrew_l3.setBounds(200, 230, 200, 30);
        addrew_c.add(addrew_l3);
        
        MyTextArea addrew_ta = new MyTextArea();
        
       // ta.setEditable(false);
        JScrollPane sp = new JScrollPane(addrew_ta,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setBounds(30,280,550,400);
        addrew_c.add(sp);
 
        
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
        //the data is stored as files in local drive and path is stored in the database
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(enterhp.getText().isEmpty() || addrew_ta.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(addrewind, "<html><font size=4>Please Fill all the details listed above!","Fill it",JOptionPane.ERROR_MESSAGE);
                }
                else if(enterhp.getText().length()>10)
                {
                    JOptionPane.showMessageDialog(addrewind, "The HP value can have a max of 10 characters only!");
                }
                else{
                try{
                    String path="C:\\Electrical Data\\Empr"+String.valueOf(id)+"\\"+String.valueOf(id)+enterhp.getText()+".txt";
                    BufferedWriter buff=new BufferedWriter(new FileWriter(path));
                    buff.write(addrew_ta.getText());
                    buff.close();
                    Connection conn = DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("insert into rewinddet values(?,?,?)");
                    st.setInt(1, id);
                    st.setString(2, enterhp.getText());
                    st.setString(3, path.replace('\\','@'));
                    st.executeUpdate();
                    conn.close();
                    JOptionPane.showMessageDialog(addrewind,"<html><font size=4>Successfully Added.","Successful",JOptionPane.INFORMATION_MESSAGE);
                }
                catch(SQLException ee)
                {
                    System.out.println("Cannot insert values into rewinddet"+ee);
                }
                catch(IOException ee)
                {
                    System.out.println("Cannot find the file in the Specified path"+ee);
                }
                
                }
            }
        });
        addrew_c.add(submit);
        addrew_c.add(back);
        addrewind.setVisible(true);
             
    }
    public static void main(String[] args) {
      new LoadFrame();
    }
    
}
