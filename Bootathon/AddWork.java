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
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
/**
 *
 * @author rahul
 */
public class AddWork {
    AddWork(){
         // Frame creating
        JFrame addWorkingFrame = new JFrame();
        addWorkingFrame.setBounds(500,200,500,700);
        addWorkingFrame.setDefaultCloseOperation(3);
        
        //container creating
        Container c = addWorkingFrame.getContentPane();
        c.setLayout(new GridLayout(5,1,30,20));
        
        //title label
        JPanel summa1 = new JPanel();
        summa1.setLayout(new FlowLayout(FlowLayout.CENTER,20,30));
        JLabel ll= new JLabel("Add Work");
        ll.setFont(new Font("Comic sans MS",Font.BOLD,35));  
        summa1.add(ll);
        c.add(summa1);
        
        //name of work
        JPanel panel1 = new JPanel();
        c.add(panel1);
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER,20,30));
        JLabel label1 = new JLabel("Name of Work : ");
        label1.setFont(new Font("Comic sans MS",Font.BOLD,20));
        JTextField nameOfWork = new JTextField(15);
        nameOfWork.setFont(new Font("arial",Font.BOLD,18));
        panel1.add(label1);
        panel1.add(nameOfWork);
        
        //amount
        JPanel panel2 = new JPanel();
        c.add(panel2);
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER,20,30));
        JLabel label2 = new JLabel("        Amount : ");
        label2.setFont(new Font("Comic sans MS",Font.BOLD,20));
        JTextField amount = new JTextField(15);
        amount.setFont(new Font("arial",Font.BOLD,18));
        panel2.add(label2);
        panel2.add(amount);
        
        //date
        JPanel panel3 = new JPanel();
        c.add(panel3);
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        JLabel label3 = new JLabel("      Date  :");
        label3.setFont(new Font("Comic sans MS",Font.BOLD,20));        
        JTextField dateField = new JTextField(15);
        dateField.setFont(new Font("Comic sans MS",Font.PLAIN,20));
        JLabel dateFormat = new JLabel("(DD/MM/YYYY)");
        dateFormat.setFont(new Font("Comic sans MS",Font.PLAIN,15));
        panel3.add(label3);
        panel3.add(dateField);
        panel3.add(dateFormat);
        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER,50,10));
        c.add(panel4);
        
        //back and submit button
        JButton back = new JButton("\u2190"+"  BACK");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addWorkingFrame.dispose();
                new MainFrame();
            }
        });
        back.setFont(new Font("",Font.BOLD,20));
        JButton submit = new JButton("Submit  "+"\u2192");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Connection conn = DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("insert into workdet values(?,?,?,0,0)");
                    st.setString(1, nameOfWork.getText());
                    st.setString(2, amount.getText());
                    st.setString(3,dateField.getText());
                    st.executeUpdate();
                    conn.close();
                    System.out.println("Success");
                    
                }
                catch(Exception ee){
                    System.out.println("Error!!"+ee);
                    
                }
            }
        });
        submit.setFont(new Font("",Font.BOLD,20));
        panel4.add(submit);
        panel4.add(back);
        addWorkingFrame.setVisible(true);
    }
    public static void main(String[] args) {
        new AddWork();
    }
    
}
