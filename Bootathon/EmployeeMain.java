/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootathon;


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
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author rahul
 */
public class EmployeeMain {
    EmployeeMain(String name,int id,String add,String dob,String phone,int sal,int cur_sal)
    {
        MyFrame f = new MyFrame(2);
        Container c = f.getContentPane();
        MyLabel Top = new MyLabel("Hi, "+name,1);Top.setBounds(20,20,300,40);
        MyLabel NameL = new MyLabel("Name ");NameL.setBounds(20,110,150,30);
        MyLabel IDL = new MyLabel("I.D No   ");IDL.setBounds(20,180,150,30);
        MyLabel AddL = new MyLabel("Address  ");AddL.setBounds(20,240,150,30);
        MyLabel DBL = new MyLabel("D.O.B  ");DBL.setBounds(20,300,150,30);
        MyLabel PhoneL = new MyLabel("Phone No ");PhoneL.setBounds(20,360,150,30);
        MyLabel SalaryL = new MyLabel("Salary  ");SalaryL.setBounds(20,420,150,30);
        MyLabel CSalaryL = new MyLabel("Current Salary   ");CSalaryL.setBounds(20,480,150,30);

        


        MyTextField NameT = new MyTextField(); NameT.setBounds(250,110,300,30); NameT.setEditable(false); 
        NameT.setText("   "+name);
        MyTextField IDT = new MyTextField(); IDT.setBounds(250,180,300,30); IDT.setEditable(false); 
        IDT.setText("   "+String.valueOf(id));
        MyTextField AddT = new MyTextField(); AddT.setBounds(250,240,300,30); AddT.setEditable(false); 
        AddT.setText("   "+add);
        MyTextField DBT = new MyTextField(); DBT.setBounds(250,300,300,30); DBT.setEditable(false); 
        DBT.setText("   "+dob);
        MyTextField PhoneT = new MyTextField(); PhoneT.setBounds(250,360,300,30); PhoneT.setEditable(false); 
        PhoneT.setText("   "+phone);
        MyTextField SalaryT = new MyTextField(); SalaryT.setBounds(250,420,300,30); SalaryT.setEditable(false); 
        SalaryT.setText("   "+String.valueOf(sal));
        MyTextField CSalaryT = new MyTextField(); CSalaryT.setBounds(250,480,300,30); CSalaryT.setEditable(false); 
        CSalaryT.setText("   "+String.valueOf(cur_sal));

  

        MyButton logout = new MyButton("Log Out");
        logout.setBounds(400,30,120,30);
        
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new LoginPage();
            }
        });
        
        
  
        c.add(Top);
        c.add(NameL);c.add(IDL);c.add(AddL);c.add(DBL);c.add(PhoneL);
        c.add(SalaryL);c.add(CSalaryL);
        c.add(NameT);c.add(IDT);c.add(AddT);c.add(DBT);c.add(PhoneT);
        c.add(SalaryT);c.add(CSalaryT);
        c.add(logout);
        f.setBounds(500,100,600,800);
        c.setLayout(null);
        f.setVisible(true);
       
    }
    
    public static void main(String[] args) {
        new LoginPage();
    }
}
