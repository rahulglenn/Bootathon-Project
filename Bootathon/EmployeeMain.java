/*
 * Description:The details of the Employee is listed once loggedin.
 * Author(s)  :Rahul Glenn,Sai Karthik
 */
package Bootathon;


import Bootathon.uiworks.MyButton;
import Bootathon.uiworks.MyFrame;
import Bootathon.uiworks.MyLabel;
import Bootathon.uiworks.MyTextField;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeMain {
    EmployeeMain(String name,int id,String add,String dob,String phone,int sal,int cur_sal)
    {
        //creating components
        MyFrame empmain_f = new MyFrame(2);
        Container empmain_c = empmain_f.getContentPane();
        MyLabel Top = new MyLabel("Hi, "+name,1);Top.setBounds(20,20,300,40);
        MyLabel NameL = new MyLabel("Name ");NameL.setBounds(20,110,150,30);
        MyLabel IDL = new MyLabel("I.D No   ");IDL.setBounds(20,180,150,30);
        MyLabel AddL = new MyLabel("Address  ");AddL.setBounds(20,240,150,30);
        MyLabel DBL = new MyLabel("D.O.B  ");DBL.setBounds(20,300,150,30);
        MyLabel PhoneL = new MyLabel("Phone No ");PhoneL.setBounds(20,360,150,30);
        MyLabel SalaryL = new MyLabel("Salary  ");SalaryL.setBounds(20,420,150,30);
        MyLabel CSalaryL = new MyLabel("Current Salary   ");CSalaryL.setBounds(20,480,150,30);

        

        //setting bounds of the components and the data of the employee
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
        
        //logout action
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empmain_f.dispose();
                new LoginPage();
            }
        });
        
        
        //adding components to the container
        empmain_c.add(Top);
        empmain_c.add(NameL);empmain_c.add(IDL);empmain_c.add(AddL);empmain_c.add(DBL);empmain_c.add(PhoneL);
        empmain_c.add(SalaryL);empmain_c.add(CSalaryL);
        empmain_c.add(NameT);empmain_c.add(IDT);empmain_c.add(AddT);empmain_c.add(DBT);empmain_c.add(PhoneT);
        empmain_c.add(SalaryT);empmain_c.add(CSalaryT);
        empmain_c.add(logout);
        empmain_c.setBounds(500,100,600,800);
        empmain_c.setLayout(null);
        empmain_f.setVisible(true);
       
    }
    
    public static void main(String[] args) {
        new LoadFrame();
    }
}
