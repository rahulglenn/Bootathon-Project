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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicArrowButton;
/**
 *
 * @author rahul
 */
public class MainFrame {
    static String EmpName,date;
    static int id;
    MainFrame(int empr,String name,String dat)
    {
        EmpName=name;
        date=dat;
        id=empr;
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
       try{
       if(sdf.parse(date).getMonth()!=new Date().getMonth())
        {
            Connection conn=DBOperations.getConn();
            PreparedStatement st=conn.prepareStatement("update employdet set CurSalary=Salary");
            st.executeUpdate();
            conn.close();
        }
       }
       catch(Exception ee)
       {
           System.out.println(ee);
       }
        new MainFrame();
    }
    MainFrame()
    {
        // Frame creating
        JFrame mainFrame = new JFrame();
        mainFrame.setBounds(500,200,500,700);
        mainFrame.setDefaultCloseOperation(3);
        
        //container creating
        Container c = mainFrame.getContentPane();
        c.setLayout(new GridLayout(5,1,30,30));
        mainFrame.setVisible(false);
        JPanel panel0 = new JPanel();
        panel0.setLayout(new FlowLayout(FlowLayout.LEFT,10,40));
        JLabel paneLab = new JLabel("Hi,"+EmpName+"               ");
        c.add(panel0);
        panel0.add(paneLab);
        JLabel paneLab1 = new JLabel("Last login on " + date);
        paneLab1.setFont(new Font("Comic sans MS",Font.BOLD,15));
        paneLab.setFont(new Font("Comic sans MS",Font.BOLD,25));
        panel0.add(paneLab1);
        //Rewinding details (panel 1)
        JPanel panel1 = new JPanel();  
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        TitledBorder rewindingDetails = new TitledBorder("Rewinding Details");
        rewindingDetails.setTitleFont(new Font("Comic sans MS",Font.BOLD,25));
        rewindingDetails.setBorder(new LineBorder(Color.black, 3));
        panel1.setBorder(rewindingDetails);
        
        //rewinding buttons
        JButton addRewind = new JButton("  Add ");
        addRewind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new AddRewind(id);
            }
        });
        JButton viewRewind = new JButton(" View ");
        viewRewind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new ViewRewind(id);
            }
        });
        JButton updateRewind = new JButton(" Update ");
        updateRewind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new UpdateRewind(id);
            }
        });
        JButton deleteRewind = new JButton(" Delete ");
        deleteRewind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new DeleteRewind(id);
            }
        });
        addRewind.setFont(new Font("arial",Font.BOLD,15));
        viewRewind.setFont(new Font("arial",Font.BOLD,15));
        updateRewind.setFont(new Font("arial",Font.BOLD,15));
        deleteRewind.setFont(new Font("arial",Font.BOLD,15));
        
        //adding to panel
        panel1.add(addRewind);
        panel1.add(viewRewind);
        panel1.add(updateRewind);
        panel1.add(deleteRewind);
        c.add(panel1);
        
        //work details (panel 2)
        JPanel panel2 = new JPanel();
        
        //panel 2 border
        TitledBorder workDetails = new TitledBorder("Work Details");
        workDetails.setTitleFont(new Font("Comic sans MS",Font.BOLD,25));
        workDetails.setBorder(new LineBorder(Color.black, 3));
        panel2.setBorder(workDetails);
        
        //panel 2 layout
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        
        //panel 2 buttons
        JButton addWork = new JButton("  Add ");
        addWork.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new AddWork(id);
            }
        });
        JButton viewWork= new JButton(" View ");
        viewWork.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new ViewWork(id);
            }
        });
        JButton updateWork = new JButton(" Update ");
        updateWork.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new UpdateWork(id);
            }
        });
        JButton workhis = new JButton(" Work history ");
        workhis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new WorkHistory(id);
            }
        });
        workhis.setFont(new Font("arial",Font.BOLD,15));
        addWork.setFont(new Font("arial",Font.BOLD,15));
        viewWork.setFont(new Font("arial",Font.BOLD,15));
        updateWork.setFont(new Font("arial",Font.BOLD,15));
        
        //adding to panel 2
        panel2.add(addWork);
        panel2.add(viewWork);
        panel2.add(updateWork);
        panel2.add(workhis);
        
        //adding panel to container
        c.add(panel2);
        
        //employee details (panel 3)
        JPanel panel3 = new JPanel(); 
        //panel 3 border
        TitledBorder employeeDetails = new TitledBorder("Employee Details");
        employeeDetails.setTitleFont(new Font("Comic sans MS",Font.BOLD,25));
        employeeDetails.setBorder(new LineBorder(Color.black, 3));
        panel3.setBorder(employeeDetails);
        
        //panel 3  layout
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        
        //panel 3 buttons
        JButton addEmployee = new JButton("  Add ");
        addEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new AddEmploy(id);
            }
        });
        JButton viewEmployee= new JButton(" View ");
        viewEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new ViewEmploy(id);
            }
        });
        JButton deleteEmployee = new JButton(" Delete ");
        deleteEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new DelEmploy(id);
            }
        });
        JButton leaveEntry = new JButton(" Leave Entry ");
        leaveEntry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new LeaveEntry(id);
            }
        });
        addEmployee.setFont(new Font("arial",Font.BOLD,15));
        viewEmployee.setFont(new Font("arial",Font.BOLD,15));
        deleteEmployee.setFont(new Font("arial",Font.BOLD,15));
        leaveEntry.setFont(new Font("arial",Font.BOLD,15));
        
        //adding to panel 3
        panel3.add(addEmployee);
        panel3.add(viewEmployee);
        panel3.add(deleteEmployee);
        panel3.add(leaveEntry);
        
        //adding panel to container
        c.add(panel3);
        
        JPanel last = new JPanel();
        last.setLayout(new FlowLayout(FlowLayout.CENTER,0,20) );
        JButton laa = new JButton("Log out");
        laa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new LoginPage();
            }
        });
        laa.setFont(new Font("arial",Font.BOLD,20));
        last.add(laa);
        c.add(last);
        
        mainFrame.setVisible(true);
    }
    public static void main(String[] args) {
        new LoginPage();
    }
}