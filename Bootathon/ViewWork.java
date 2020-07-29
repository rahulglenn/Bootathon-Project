/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootathon;

import Bootathon.database.DBOperations;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 *
 * @author rahul
 */
public class ViewWork {
    ViewWork(){
        // TODO code application logic here
        JFrame f = new JFrame();           
        f.setBounds(500,200,500,700);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("View Form");
        f.setResizable(true); 
        
        //container creating
        Container c = f.getContentPane();
        c.setLayout(new GridLayout(5,1));
        
        //title panel
        JPanel summa1 = new JPanel();
        summa1.setLayout(new FlowLayout(FlowLayout.CENTER,20,30));
        JLabel ll= new JLabel("View details");
        ll.setFont(new Font("Comic sans MS",Font.BOLD,35));  
        summa1.add(ll);
        c.add(summa1);
        
        //panel1 creating
        JPanel panel1 = new JPanel();
        c.add(panel1);
        JComboBox cb = new JComboBox();                
        cb.addItem("Select WorkID");
            //Adding values to combobox
            try{
                    Connection conn = DBOperations.getConn();
                    Statement st=conn.createStatement();
                    ResultSet rs=st.executeQuery("Select Date, workid ,CollectionStatus from workdet");
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
        cb.setFont(new Font("arial",Font.PLAIN,20));        
        JLabel l1 = new JLabel("Select WorkID  : ");
        panel1.add(l1);
        panel1.add(cb);
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
        l1.setFont(new Font("Comic sans MS",Font.BOLD,25));
        
        //panel2 creating
        JPanel panel2 = new JPanel();
        c.add(panel2);
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
        JLabel l2 = new JLabel("Security Deposit Value :");        
        panel2.add(l2);
        l2.setFont(new Font("Comic sans MS",Font.BOLD,25));
        JTextField t = new JTextField(10);
        t.setBackground(Color.white);
        t.setEditable(false);
        t.setFont(new Font("arial",Font.BOLD,20));
        panel2.add(t);
        
        //panel3 creating
        
        JPanel panel3 = new JPanel();
        c.add(panel3);
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        JLabel l3 = new JLabel("Rewinding Details"); 
        l3.setFont(new Font("Comic sans MS",Font.BOLD,25));
//        panel3.add(l3);
        
        JTextArea ta = new JTextArea(4,20);
        ta.setEditable(false);
        JScrollPane sp = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        
        panel3.add(sp);
        ta.setFont(new Font("arial",Font.PLAIN,20));
        
        //
        JPanel panel5 = new JPanel();
        c.add(panel5);
        panel5.setLayout(new FlowLayout(FlowLayout.CENTER,30,30));
        JButton back = new JButton("\u2190"+"  BACK");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainFrame();
            }
        });
        back.setFont(new Font("arial",Font.BOLD,20));
        JButton refund=new JButton("SD Collected");
        refund.setFont(new Font("arial",Font.BOLD,20));
        refund.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Connection conn = DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("update workdet set CollectionStatus=1 where NameOfWork=?");
                    st.setString(1, ta.getText());
                    st.executeUpdate();
                    conn.close();
                }
                catch(Exception ee)
                {
                    
                }
                f.dispose();
                new ViewWork();
            }
        });
        panel5.add(refund);
        panel5.add(back);
        
        //Displaying the selected item from combobox
        cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String item=(String)cb.getSelectedItem();
                try{
                    Connection conn = DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("Select NameOfWork, Amount, workid from workdet where workid=?");
                    st.setString(1,item);
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
        
        f.setVisible(true);
    }
    public static void main(String[] args) {
        new ViewWork();
    }
    
}
