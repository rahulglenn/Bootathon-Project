/*
 * Description:Main Frame of the Employer.
 * Author(s)  :Rahul Glen,Sai Karthik
 */
package Bootathon;
import Bootathon.database.DBOperations;
import Bootathon.uiworks.MyButton;
import Bootathon.uiworks.MyFrame;
import Bootathon.uiworks.MyLabel;
import Bootathon.uiworks.MyPanel;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class MainFrame {
    static String EmpName,date;
    static int id;
    MainFrame(int empr,String name,String dat)
    {
        EmpName=name;
        date=dat;
        id=empr;
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        
       //if difference in month is encountered,the monthly salary is resetted
       try{
       if(sdf.parse(date).getMonth()!=new Date().getMonth())
        {
            Connection conn=DBOperations.getConn();
            PreparedStatement st=conn.prepareStatement("update employdet set CurSalary=Salary");
            st.executeUpdate();
            conn.close();
        }
       }
       catch(ParseException ee)
       {
           if(!date.equals("Nil"))
           System.out.println("Invalid Date Format"+ee);
       }
       catch(SQLException ee)
        {
           System.out.println("Cannot update values into DB!!"+ee);
        }
        new MainFrame();
    }
    MainFrame()
    {
        // Frame creating
        MyFrame mainFrame = new MyFrame(2);
        mainFrame.setBounds(500,100,600,800);
     
        
        //container creating
        Container c = mainFrame.getContentPane();
        c.setLayout(new GridLayout(5,1,30,30));
        mainFrame.setVisible(false);
        MyPanel panel0 = new MyPanel();
        panel0.setLayout(null);
        MyLabel paneLab = new MyLabel("Hi,"+EmpName+"               ",1);
        paneLab.setBounds(30,60,300,30);
        c.add(panel0);
        panel0.add(paneLab);
        MyButton laa = new MyButton("Log out");
        laa.setBounds(460,60,100,30);
        laa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new LoginPage();
            }
        });
       
        panel0.add(laa);
        
        //Rewinding details (panel 1)
        MyPanel panel1 = new MyPanel();  
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        TitledBorder rewindingDetails = new TitledBorder("<html><font size=6>Rewinding Details");
        rewindingDetails.setBorder(new LineBorder(Color.black, 3));
        panel1.setBorder(rewindingDetails);
        
        //rewinding buttons
        MyButton addRewind = new MyButton("  Add ");
        addRewind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new AddRewind(id);
            }
        });
        MyButton viewRewind = new MyButton(" View ");
        viewRewind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new ViewRewind(id);
            }
        });
        MyButton updateRewind = new MyButton(" Update ");
        updateRewind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new UpdateRewind(id);
            }
        });
        MyButton deleteRewind = new MyButton(" Delete ");
        deleteRewind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new DeleteRewind(id);
            }
        });
        
        
        //adding to panel
        panel1.add(addRewind);
        panel1.add(viewRewind);
        panel1.add(updateRewind);
        panel1.add(deleteRewind);
        c.add(panel1);
        
        //work details (panel 2)
        MyPanel panel2 = new MyPanel();
        
        //panel 2 border
        TitledBorder workDetails = new TitledBorder("<html><font size=6>Work Details");
        workDetails.setBorder(new LineBorder(Color.black, 3));
        panel2.setBorder(workDetails);
        
        //panel 2 layout
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        
        //panel 2 buttons
        MyButton addWork = new MyButton("Add");
        addWork.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new AddWork(id);
            }
        });
        MyButton viewWork= new MyButton(" View ");
        viewWork.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new ViewWork(id);
            }
        });
        MyButton updateWork = new MyButton(" Update ");
        updateWork.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new UpdateWork(id);
            }
        });
        MyButton workhis = new MyButton(" Work history ");
        workhis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new WorkHistory(id);
            }
        });
        
        
        //adding to panel 2
        panel2.add(addWork);
        panel2.add(viewWork);
        panel2.add(updateWork);
        panel2.add(workhis);
        
        //adding panel to container
        c.add(panel2);
        
        //employee details (panel 3)
        MyPanel panel3 = new MyPanel(); 
        //panel 3 border
        TitledBorder employeeDetails = new TitledBorder("<html><font size=6>Employee Details");
        employeeDetails.setBorder(new LineBorder(Color.black, 3));
        panel3.setBorder(employeeDetails);
        
        //panel 3  layout
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        
        //panel 3 buttons
        MyButton addEmployee = new MyButton("  Add ");
        addEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new AddEmploy(id);
            }
        });
        MyButton viewEmployee= new MyButton(" View ");
        viewEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new ViewEmploy(id);
            }
        });
        MyButton deleteEmployee = new MyButton(" Delete ");
        deleteEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new DelEmploy(id);
            }
        });
        MyButton leaveEntry = new MyButton(" Leave Entry ");
        leaveEntry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new LeaveEntry(id);
            }
        });
        
        
        //adding to panel 3
        panel3.add(addEmployee);
        panel3.add(viewEmployee);
        panel3.add(deleteEmployee);
        panel3.add(leaveEntry);
        
        //adding panel to container
        c.add(panel3);
        
        MyPanel last = new MyPanel();
        last.setLayout(new FlowLayout(FlowLayout.LEFT,50,20) );
        MyLabel paneLab1 = new MyLabel("Last login on " + date);
        
        last.add(paneLab1);
        c.add(last);
        
        mainFrame.setVisible(true);
    }
    public static void main(String[] args) {
        new LoadFrame();
    }
}