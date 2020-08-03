/*
 * Description : Delete the rewiding details if needed by the employer.
 * Author(s)   : Pradakshina,Raahul Glenn,Sai Karthik
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
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javax.swing.JScrollPane;


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
            catch(SQLException ee)
            {
                System.out.println("Cannot retrieve values from rewinddet"+ee);
            }
              
        MyLabel l1 = new MyLabel("Select HP   ");
        l1.setBounds(30,100,150,30);
        cb.setBounds(230,100,300,30);
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
        //the rewinding details are deleted along with file as wellas the path in the database 
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
                    String path="C:\\Electrical Data\\Empr"+String.valueOf(id)+"\\"+String.valueOf(id)+t.getText()+".txt";
                    new File(path).delete();
                    st.executeUpdate();
                    conn.close();
                    JOptionPane.showMessageDialog(f,"<html><font size=4>Successfully Deleted.","Successful",JOptionPane.INFORMATION_MESSAGE);
                }
                catch(SQLException ee)
                {
                    System.out.println("Cannot delete the values from rewinddet"+ee);
                }
                
                f.dispose();
                new DeleteRewind(id);
            }}}
        });
        
        f.setVisible(true);
    }
    public static void main(String[] args) {
        new LoadFrame();
    }
}
