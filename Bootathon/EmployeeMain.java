/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootathon;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author rahul
 */
public class EmployeeMain {
    EmployeeMain(ResultSet rs) throws SQLException
    {
        JFrame f = new JFrame();
        JLabel Top = new JLabel("Hi, "+rs.getString("EmpName"));Top.setBounds(20,20,100,30);
        JLabel NameL = new JLabel("Name               :");NameL.setBounds(20,70,150,30);
        JLabel IDL = new JLabel("I.D No              :");IDL.setBounds(20,120,150,30);
        JLabel AddL = new JLabel("Address           :");AddL.setBounds(20,170,150,30);
        JLabel DBL = new JLabel("D.O.B              :");DBL.setBounds(20,220,150,30);
        JLabel PhoneL = new JLabel("Phone No         :");PhoneL.setBounds(20,270,150,30);
        JLabel SalaryL = new JLabel("Salary             :");SalaryL.setBounds(20,320,150,30);
        JLabel CSalaryL = new JLabel("Current Salary   :");CSalaryL.setBounds(20,370,150,30);

        Top.setFont(new Font("Sans-sheriff",Font.BOLD,16));
        NameL.setFont(new Font("Sans-sheriff",Font.PLAIN,16));
        IDL.setFont(new Font("Sans-sheriff",Font.PLAIN,16));
        AddL.setFont(new Font("Sans-sheriff",Font.PLAIN,16));
        DBL.setFont(new Font("Sans-sheriff",Font.PLAIN,16));
        PhoneL.setFont(new Font("Sans-sheriff",Font.PLAIN,16));
        SalaryL.setFont(new Font("Sans-sheriff",Font.PLAIN,16));
        CSalaryL.setFont(new Font("Sans-sheriff",Font.PLAIN,16));


        JTextField NameT = new JTextField(); NameT.setBounds(150,70,200,30); NameT.setEditable(false); 
        NameT.setText("   "+rs.getString("EmpName"));
        JTextField IDT = new JTextField(); IDT.setBounds(150,120,200,30); IDT.setEditable(false); 
        IDT.setText("   "+String.valueOf(rs.getInt("empid")));
        JTextField AddT = new JTextField(); AddT.setBounds(150,170,200,30); AddT.setEditable(false); 
        AddT.setText("   "+rs.getString("Address"));
        JTextField DBT = new JTextField(); DBT.setBounds(150,220,200,30); DBT.setEditable(false); 
        DBT.setText("   "+rs.getString("DOB"));
        JTextField PhoneT = new JTextField(); PhoneT.setBounds(150,270,200,30); PhoneT.setEditable(false); 
        PhoneT.setText("   "+rs.getString("Phone"));
        JTextField SalaryT = new JTextField(); SalaryT.setBounds(150,320,200,30); SalaryT.setEditable(false); 
        SalaryT.setText("   "+String.valueOf(rs.getInt("Salary")));
        JTextField CSalaryT = new JTextField(); CSalaryT.setBounds(150,370,200,30); CSalaryT.setEditable(false); 
        CSalaryT.setText("   "+String.valueOf(rs.getInt("CurSalary")));

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        NameT.setBorder(border);
        IDT.setBorder(border);
        AddT.setBorder(border);
        DBT.setBorder(border);
        PhoneT.setBorder(border);
        SalaryT.setBorder(border);
        CSalaryT.setBorder(border);


        JButton logout = new JButton("Log Out");
        logout.setBounds(140,450,90,30);
        logout.setFont(new Font("Sans-sheriff",Font.BOLD,15));
        
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new LoginPage();
            }
        });
        
        f.setBackground(Color.white);
        logout.setBackground(Color.white);
        logout.setBorder(border);
        logout.setBackground(Color.yellow);

        f.add(Top);
        f.add(NameL);f.add(IDL);f.add(AddL);f.add(DBL);f.add(PhoneL);
        f.add(SalaryL);f.add(CSalaryL);
        f.add(NameT);f.add(IDT);f.add(AddT);f.add(DBT);f.add(PhoneT);
        f.add(SalaryT);f.add(CSalaryT);
        f.add(logout);
        f.setBounds(500,200,450,650);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(3);
    }
}
