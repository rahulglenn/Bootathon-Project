/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootathon.uiworks;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author sk
 */
public class MyButton extends JButton{
    public MyButton(String text)
    {
        setBackground(new Color(33, 140, 0));
        setText(text);
        setBorderPainted(false);
        setForeground(new Color(255,255,255));
        setFont(new Font("arial",Font.PLAIN,20));
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(2, 179, 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(33, 140, 0));
            }
        });
    }
}
