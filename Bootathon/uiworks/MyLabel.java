/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootathon.uiworks;

import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author sk
 */
public class MyLabel extends JLabel {
    public MyLabel(String text)
    {
        setFont(new Font("cambria",Font.BOLD,20));
        setText(text);
    }
    public MyLabel(String text,int a)
    {
        setFont(new Font("cambria",Font.BOLD,30));
        setText(text);
    }
}
