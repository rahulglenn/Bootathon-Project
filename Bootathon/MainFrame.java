/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootathon;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JLabel paneLab = new JLabel("Hi,Mama               ");
        c.add(panel0);
        panel0.add(paneLab);
        JLabel paneLab1 = new JLabel("Last login on " + "26-07-2020");
        paneLab1.setFont(new Font("Comic sans MS",Font.BOLD,15));
        paneLab.setFont(new Font("Comic sans MS",Font.BOLD,25));
        panel0.add(paneLab1);
        //Rewinding details (panel 1)
        JPanel panel1 = new JPanel();  
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        TitledBorder rewindingDetails = new TitledBorder("Rewinding Details");
        rewindingDetails.setTitleFont(new Font("Comic sans MS",Font.BOLD,25));
        rewindingDetails.setBorder(new LineBorder(Color.black, 5));
        panel1.setBorder(rewindingDetails);
        
        //rewinding buttons
        JButton addRewind = new JButton("  Add ");
        addRewind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new AddRewind();
            }
        });
        JButton viewRewind = new JButton(" View ");
        viewRewind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new ViewRewind();
            }
        });
        JButton updateRewind = new JButton(" Update ");
        updateRewind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new UpdateRewind();
            }
        });
        addRewind.setFont(new Font("arial",Font.BOLD,15));
        viewRewind.setFont(new Font("arial",Font.BOLD,15));
        updateRewind.setFont(new Font("arial",Font.BOLD,15));
        
        //adding to panel
        panel1.add(addRewind);
        panel1.add(viewRewind);
        panel1.add(updateRewind);
        c.add(panel1);
        
        //work details (panel 2)
        JPanel panel2 = new JPanel();
        
        //panel 2 border
        TitledBorder workDetails = new TitledBorder("Work Details");
        workDetails.setTitleFont(new Font("Comic sans MS",Font.BOLD,25));
        workDetails.setBorder(new LineBorder(Color.black, 5));
        panel2.setBorder(workDetails);
        
        //panel 2 layout
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        
        //panel 2 buttons
        JButton addWork = new JButton("  Add ");
        addWork.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new AddWork();
            }
        });
        JButton viewWork= new JButton(" View ");
        viewWork.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new ViewWork();
            }
        });
        JButton updateWork = new JButton(" Update ");
        updateWork.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new UpdateWork();
            }
        });
        JButton workhis = new JButton(" Work history ");
        workhis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
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
        employeeDetails.setBorder(new LineBorder(Color.black, 5));
        panel3.setBorder(employeeDetails);
        
        //panel 3  layout
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        
        //panel 3 buttons
        JButton addEmployee = new JButton("  Add ");
        addEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new AddEmploy();
            }
        });
        JButton viewEmployee= new JButton(" View ");
        
        JButton deleteEmployee = new JButton(" Delete ");
        deleteEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new DelEmploy();
            }
        });
        JButton leaveEntry = new JButton(" Leave Entry ");
        leaveEntry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new LeaveEntry();
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
        JLabel laa = new JLabel("Log out");
        laa.setFont(new Font("arial",Font.BOLD,20));
        last.add(laa);
        c.add(last);
        
        mainFrame.setVisible(true);
    }
    public static void main(String[] args) {
        
        new MainFrame();
        
    }
}