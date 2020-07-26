/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootathon;
import Bootathon.database.DBOperations;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JButton;
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
public class AddRewind {
    AddRewind()
    {
        //Frame creating
        JFrame addrewind=new JFrame();
        addrewind.setBounds(500, 200, 500, 700);
        addrewind.setDefaultCloseOperation(3);
        
        //Container Creating
        Container c=addrewind.getContentPane();
        c.setLayout(new GridLayout(4,1,30,0));
        
        //title label
        JPanel summa1 = new JPanel();
        summa1.setLayout(new FlowLayout(FlowLayout.CENTER,20,30));
        JLabel ll= new JLabel("Add Rewinding Details:");
        ll.setFont(new Font("Comic sans MS",Font.BOLD,35));  
        summa1.add(ll);
        c.add(summa1);
        
        //HorsePower entry
        JPanel panel1 = new JPanel();
        c.add(panel1);
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER,20,30));
        JLabel label1 = new JLabel("Enter HP : ");
        label1.setFont(new Font("Comic sans MS",Font.BOLD,20));
        JTextField enterhp = new JTextField(10);
        enterhp.setFont(new Font("arial",Font.BOLD,18));
        panel1.add(label1);
        panel1.add(enterhp);
        
        //Rewind details entry
        
        JPanel panel3 = new JPanel();
        c.add(panel3);
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
        JLabel l3 = new JLabel("Enter the details"); 
        l3.setFont(new Font("Comic sans MS",Font.BOLD,25));
        panel3.add(l3);
        
        JTextArea ta = new JTextArea(4,20);
       // ta.setEditable(false);
        JScrollPane sp = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel3.add(sp);
        ta.setFont(new Font("arial",Font.PLAIN,20));
        
        //Submit and Back
        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER,50,30));
        c.add(panel4);
        JButton back = new JButton("\u2190"+"  BACK");
        back.setFont(new Font("",Font.BOLD,20));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addrewind.dispose();
                new MainFrame();
            }
        });
        JButton submit = new JButton("Submit  "+"\u2192");
        submit.setFont(new Font("",Font.BOLD,20));
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Connection conn = DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("insert into rewinddet values(?,?)");
                    st.setString(1, enterhp.getText());
                    st.setString(2, ta.getText());
                    st.executeUpdate();
                    conn.close();
                }
                catch(Exception ee)
                {
                    System.out.println(ee);
                }
            }
        });
        panel4.add(submit);
        panel4.add(back);
        addrewind.setVisible(true);
             
    }
    public static void main(String[] args) {
        new AddRewind();
    }
    
}
