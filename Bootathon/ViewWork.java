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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JScrollPane;

/*
 * @author rahul
 */
public class ViewWork {
    ViewWork(int id){
        // TODO code application logic here
         // frame creating
        MyFrame f = new MyFrame(4);           
        f.setBounds(500,100,600,800);
       
        
        //container creating
        Container c = f.getContentPane();
        c.setLayout(null);
        
        MyLabel ll= new MyLabel("View details",1); 
        ll.setBounds(30,30,200,50);
        c.add(ll);
        
        //String[] amount = { "Rs.2500" , "Rs.3500" , "Rs.4500" ,"Rs.1500" };
        MyComboBox cb = new MyComboBox();
        cb.addItem("Select WorkID");
            //Adding values to combobox
            try{
                    Connection conn = DBOperations.getConn();
                    Statement st=conn.createStatement();
                    ResultSet rs=st.executeQuery("Select Date, workid ,CollectionStatus from workdet where emprid="+String.valueOf(id));
                    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                    Calendar cal=Calendar.getInstance();
                    while(rs.next())
                    {
                        cal.setTime(sdf.parse(rs.getString("Date")));
                        cal.add(Calendar.YEAR, 1);
                        if(cal.getTime().before(new Date()) && rs.getInt("CollectionStatus")==0)
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
        
      
        MyLabel l2 = new MyLabel("Security Deposit Value ");        
        c.add(l2);
        l2.setBounds(10,230,250,30);
        MyTextField t = new MyTextField();
        t.setEditable(false);
        c.add(t);
        t.setBounds(250,230,300,30);
        
       
        MyLabel l3 = new MyLabel("Rewinding Details"); 
        l3.setBounds(200,350,200,30);
        c.add(l3);
        MyTextArea ta = new MyTextArea();
        ta.setEditable(false);
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
        });;
        MyButton refund=new MyButton("SD Collected");
       
        refund.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Connection conn = DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("update workdet set CollectionStatus=1 where NameOfWork=? and emprid=?");
                    st.setString(1, ta.getText());
                    st.setInt(2, id);
                    st.executeUpdate();
                    conn.close();
                }
                catch(Exception ee)
                {
                    
                }
                f.dispose();
                new ViewWork(id);
            }
        });
        
        
        //Displaying the selected item from combobox
        cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String item=(String)cb.getSelectedItem();
                try{
                    Connection conn = DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("Select NameOfWork, Amount, workid from workdet where workid=? and emprid=?");
                    st.setString(1,item);
                    st.setInt(2, id);
                    ResultSet rs=st.executeQuery();
                    rs.next();
                    t.setText(rs.getString("Amount"));
                    ta.setText(rs.getString("NameOfWork"));
                    conn.close();
                }
                catch(Exception ee)
                {
                    System.out.println("Error"+ee);
                }
            }
        });
        back.setBounds(340,710,150,30);
       refund.setBounds(400,350,150,30);
        c.add(refund);
        c.add(back);
        f.setVisible(true);
    }
    public static void main(String[] args) {
        new LoginPage();
    }
    
}
