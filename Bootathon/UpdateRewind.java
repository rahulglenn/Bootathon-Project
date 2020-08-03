/*
 * Description :Update the rewinding details if any by the employer.
 * Author(s)   :Pradakshina,Raahul Glenn,Sai Karthik
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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author rahul
 */
public class UpdateRewind {
    UpdateRewind(int id){
          // frame creating
        MyFrame f = new MyFrame(4);           
        f.setBounds(500,100,600,800);
         
        
        //container creating
        Container c = f.getContentPane();
        c.setLayout(null);
        
        
        MyLabel ll= new MyLabel("Update details",1);
        ll.setBounds(30,30,200,50);
        c.add(ll);
        
        
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
            catch(SQLException ee)
            {
                System.out.println("Cannot retrieve values from rewinddet"+ee);
            }      
            
        //select hp combobox    
        MyLabel l1 = new MyLabel("Select   HP");
        l1.setBounds(90,140,200,30);
        cb.setBounds(250,140,300,30);
        cb.setEditable(false);
        c.add(l1);
        c.add(cb);
     
        //horse power
        MyLabel l2 = new MyLabel("Horse Power ");        
        c.add(l2);
        l2.setBounds(90,240,200,30);
        MyTextField t = new MyTextField();
        t.setBounds(250,240,300,30);
        t.setEditable(false);
        c.add(t);
        
        //rewind details textarea creation
        MyLabel l3 = new MyLabel("Rewinding Details"); 
        l3.setBounds(200,320,200,30);
        MyTextArea ta = new MyTextArea();
        JScrollPane sp = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setBounds(80,370,470,300);
        c.add(l3);
        c.add(sp);
      
        //back action
        MyButton back = new MyButton("\u2190"+"  BACK");
        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainFrame();
            }
        });

        MyButton update = new MyButton("Update");
        back.setBounds(370,700,150,30);
        update.setBounds(400,320,150,30);
        c.add(update);
        c.add(back);
        //the chosen hp,rewinding details displayed
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
                    String path=rs.getString("details").replace('@', '\\');
                    BufferedReader buff=new BufferedReader(new FileReader(path));
                    int i;
                    String det="";
                    while((i=buff.read())!=-1)
                    {
                        det+=(char)i;
                    }
                    buff.close();
                    ta.setText(det);
                    conn.close();
                }
                catch(SQLException ee)
                {
                    System.out.println("Cannot retrieve values from rewinddet"+ee);
                }
                catch(IOException ee)
                {
                    System.out.println("Cannot find the file in the Specified path"+ee);
                }
 
            }
        });
         //updated details overwritten
         update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(t.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(f, "<html><font size=4>Please select which detail to update first!","Select",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    int a=JOptionPane.showConfirmDialog(f,"<html><font size=4>Are you sure to Update?");  
                if(a==JOptionPane.YES_OPTION){  
                try
                {
                   String path="C:\\Electrical Data\\Empr"+String.valueOf(id)+"\\"+String.valueOf(id)+t.getText()+".txt";
                   BufferedWriter buff=new BufferedWriter(new FileWriter(path));
                   buff.write(ta.getText());
                   buff.close();
                   JOptionPane.showMessageDialog(f,"<html><font size=4>Successfully Updated.","Successful",JOptionPane.INFORMATION_MESSAGE);
                }
                catch(IOException ee)
                {
                    System.out.println("Cannot find the file in the Specified path"+ee);
                }
                
            }}}
        });
        
        f.setVisible(true);
    }
    public static void main(String[] args) {
        new LoadFrame();
    }
    
}
