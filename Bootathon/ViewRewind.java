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
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JScrollPane;


/**
 *
 * @author rahul
 */
public class ViewRewind {
    ViewRewind(int id){
         // TODO code application logic here
        MyFrame f = new MyFrame(5);           
        f.setBounds(500,100,600,800);
        
        //container creating
        Container c = f.getContentPane();
        c.setLayout(null);
        
        
        MyLabel ll= new MyLabel("View details",1);
        ll.setBounds(30,20,300,30);
        c.add(ll);
        
        MyComboBox cb = new MyComboBox();
        cb.setEditable(true);
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
       
        MyLabel l1 = new MyLabel("Select HP");
       l1.setBounds(90,140,200,30);
        cb.setBounds(250,140,300,30);
        c.add(l1);
        c.add(cb);

        
       
        MyLabel l2 = new MyLabel("Horse Power ");        
        c.add(l2);
        l2.setBounds(90,240,200,30);

        MyTextField t = new MyTextField();
        t.setBounds(250,240,300,30);
        t.setEditable(false);
        c.add(t);
        
      
        MyLabel l3 = new MyLabel("Rewinding Details"); 
        l3.setBounds(200,320,200,30);
        MyTextArea ta = new MyTextArea();
        ta.setEditable(false);
        JScrollPane sp = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setBounds(80,370,470,300);
        c.add(l3);
        c.add(sp);
        
      
        MyButton back = new MyButton("\u2190"+"  BACK");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainFrame();
            }
        });
        
        c.add(back);
        
        //
        cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String item=(String)cb.getSelectedItem();
                try{
                    Connection conn = DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("Select * from rewinddet where hp=? and emprid=?");
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
                catch(Exception ee)
                {
                    System.out.println(item +ee);
                }    
            }
        });
        
        back.setBounds(30,700,150,30);
        
        f.setVisible(true);
    }
    public static void main(String[] args) {
       new LoginPage();
    }
}
