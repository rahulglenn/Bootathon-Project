/*
 * Description : Add workdetails(SD amount) by the employer.
 * Author(s)   : Thulasi Ram,Sai Karthik
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
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class AddWork {
    AddWork(int id){
         // Frame creating
        MyFrame addWorkingFrame = new MyFrame(2);
        addWorkingFrame.setBounds(500,100,600,800);
        
        
        //container creating
        Container c = addWorkingFrame.getContentPane();
        c.setLayout(null);
        
        //title label
       
        MyLabel ll= new MyLabel("Add Work",1);
        ll.setBounds(30,30,200,40);
        c.add(ll);

        
        //name of work
      
        MyLabel label1 = new MyLabel("Name of Work ");
        MyTextField nameOfWork = new MyTextField();
        label1.setBounds(50,100,200,40);
        nameOfWork.setBounds(120,160,430,30);
        c.add(label1);
        c.add(nameOfWork);
        
        //amount
      
        MyLabel label2 = new MyLabel("Amount  ");
        label2.setBounds(60,240,200,40);
        MyTextField amount = new MyTextField();
        amount.setBounds(230,245,300,30);
        c.add(label2);
        c.add(amount);
        
        //date

        MyLabel label3 = new MyLabel("Date  ");        
        MyTextField dateField = new MyTextField();
        MyLabel dateFormat = new MyLabel("(DD/MM/YYYY)");
        label3.setBounds(60,320,200,40);
        dateField.setBounds(230,325,300,30);
        dateFormat.setBounds(230,370,300,30);

        c.add(label3);
        c.add(dateField);
        c.add(dateFormat);
        
        
        //back and submit button
        MyButton back = new MyButton("\u2190"+"  BACK");
        back.setBounds(30,650,150,30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addWorkingFrame.dispose();
                new MainFrame();
            }
        });

        MyButton submit = new MyButton("Submit  "+"\u2192");
        submit.setBounds(300,460,150,30);
        //adding workdetails
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameOfWork.getText().isEmpty() || dateField.getText().isEmpty() || amount.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(addWorkingFrame, "<html><font size=4>Please Fill all the details listed above!","Fill it",JOptionPane.ERROR_MESSAGE);
                }
                else if(nameOfWork.getText().length()>20)
                {
                    JOptionPane.showMessageDialog(addWorkingFrame, "The Name Of Work can have a max of 20 characters only!");
                }
                else if(!Pattern.matches("^[0-9]+$", amount.getText()))
                {
                    JOptionPane.showMessageDialog(addWorkingFrame, "The Amount should be integer value!");
                }
                else if(amount.getText().length()>5)
                {
                    JOptionPane.showMessageDialog(addWorkingFrame, "The amount cannot exceed 99999/-!");
                }
                //date validation
                else if(!Pattern.compile("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$").matcher(dateField.getText()).matches())
                {
                    JOptionPane.showMessageDialog(addWorkingFrame, "<html><font size=4>Invalid Date or Invalid Format! use (dd/mm/yyyy)!","Invalid!",JOptionPane.ERROR_MESSAGE);
                }
                else{
                try{
                    Connection conn = DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("insert into workdet values(?,?,?,?,0,0)");
                    st.setInt(1, id);
                    st.setString(2, nameOfWork.getText());
                    st.setString(3, amount.getText());
                    st.setString(4,dateField.getText());
                    st.executeUpdate();
                    conn.close();
                    JOptionPane.showMessageDialog(addWorkingFrame,"<html><font size=4>Successfully Added.","Successful",JOptionPane.INFORMATION_MESSAGE);
                
                }
                catch(SQLException ee){
                    System.out.println("Cannot insert values into workdet!!"+ee);   
                }
                    
                }
            }
        });
        c.add(submit);
        c.add(back);
        addWorkingFrame.setVisible(true);
    }
    public static void main(String[] args) {
       new LoadFrame();
    }
    
}
