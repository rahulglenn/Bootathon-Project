/*
 * Description:View the entered rewinding details by the employer.
 * Author(s)  :Pradhakshina,Rahul Glenn,Sai Karthik
 */
package Bootathon;
import Bootathon.database.DBOperations;
import Bootathon.uiworks.MyButton;
import Bootathon.uiworks.MyComboBox;
import Bootathon.uiworks.MyFrame;
import Bootathon.uiworks.MyLabel;
import Bootathon.uiworks.MyTextArea;
import Bootathon.uiworks.MyTextField;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JScrollPane;


public class ViewRewind {
    ViewRewind(int id){
         // TODO code application logic here
        MyFrame viewrew_f = new MyFrame(5);           
        viewrew_f.setBounds(500,100,600,800);
        
        //container creating
        Container viewrew_c = viewrew_f.getContentPane();
        viewrew_c.setLayout(null);
        
        
        MyLabel ll= new MyLabel("View details",1);
        ll.setBounds(30,20,300,30);
        viewrew_c.add(ll);
        
        MyComboBox cb = new MyComboBox();
        cb.setEditable(false);
        cb.addItem("Select HP");
            //adding values to combobox
            try{
                    Connection conn = DBOperations.getConn();
                    Statement st=conn.createStatement();
                    ResultSet rs=st.executeQuery("Select hp from rewinddet where emprid="+String.valueOf(id));
                    while(rs.next())
                    {
                        cb.addItem(rs.getString("hp"));
                    }
                    conn.close();
            }
            catch(SQLException ee)
            {
                System.out.println("Cannot retrieve values from rewinddet"+ee);
            } 
       
        //HP selection
        MyLabel viewrew_l1 = new MyLabel("Select HP");
        viewrew_l1.setBounds(90,140,200,30);
        cb.setBounds(250,140,300,30);
        viewrew_c.add(viewrew_l1);
        viewrew_c.add(cb);

        //displaying Horse Power 
        MyLabel viewrew_l2 = new MyLabel("Horse Power ");        
        viewrew_c.add(viewrew_l2);
        viewrew_l2.setBounds(90,240,200,30);

        MyTextField viewrew_t = new MyTextField();
        viewrew_t.setBounds(250,240,300,30);
        viewrew_t.setEditable(false);
        viewrew_c.add(viewrew_t);
        
       //displaying rewinding details
        MyLabel viewrew_l3 = new MyLabel("Rewinding Details"); 
        viewrew_l3.setBounds(200,320,200,30);
        MyTextArea ta = new MyTextArea();
        ta.setEditable(false);
        JScrollPane sp = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setBounds(80,370,470,300);
        viewrew_c.add(viewrew_l3);
        viewrew_c.add(sp);
        
       //back action
        MyButton back = new MyButton("\u2190"+"  BACK");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewrew_f.dispose();
                new MainFrame();
            }
        });
        
        viewrew_c.add(back);
        
        //fetches and displays the rewinding details from the local drive using the path in database once the item(HP) chosen
        cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String item=(String)cb.getSelectedItem();
                try{
                    Connection conn = DBOperations.getConn();
                    PreparedStatement st=conn.prepareStatement("Select * from rewinddet where hp=? and emprid=?");
                    st.setString(1, item);
                    st.setInt(2, id);
                    ResultSet rs=st.executeQuery();
                    rs.next();
                    viewrew_t.setText(rs.getString("hp"));
                    String path=rs.getString("details").replace('@', '\\');
                    BufferedReader buff=new BufferedReader(new FileReader(path));
                    int i;
                    String det="";
                    while((i=buff.read())!=-1)
                    {
                        det+=(char)i;
                    }
                    buff.close();
                    ta.setText(det);
                    conn.close();
                }
                catch(SQLException ee)
                {
                    System.out.println("Cannot retrieve values from rewinddet"+ee);
                }
                catch(IOException ee)
                {
                    System.out.println("Cannot find the file in the Specified path"+ee);
                }   
            }
        });
        
        back.setBounds(30,700,150,30);
        
        viewrew_f.setVisible(true);
    }
    public static void main(String[] args) {
       new LoadFrame();
    }
}
