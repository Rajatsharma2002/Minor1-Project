package studentclassificationsystem;

import java.awt.*;
import javax.swing.*;
import java.lang.Thread;
import java.awt.event.*;

public class WelcomeWindow implements ActionListener{
    JFrame f ;
    JLabel l , l2 , l3;
    JButton b ;
    
    WelcomeWindow()
    {
        f = new JFrame("Student Classification System");
        f.setLayout(null);
        
//        l = new JLabel();
//        l.setBounds(0,0,1300,750);
//        l.setLayout(null);
//        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("studentclassificationsystem/pic/student.jpg"));
//        l.setIcon(img);
        
        
//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("C/Users/NEERAJ/Downloads/student.jpg"));
//        Image i2 = i1.getImage().getScaledInstance(700,300,Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon(i2);
//        JLabel l1 = new JLabel(i3);
//        
//        l1.setBounds(0,100,1300,530);
//        f.add(l1);

        l = new JLabel("STUDENT CLASSIFICATION SYSTEM");
        l.setBounds(47,30,1500,100);
        l.setFont(new Font("serif",Font.TRUETYPE_FONT,70));
        l.setForeground(Color.red);
        f.add(l);
        
        b = new JButton("CLICK HERE TO ENTER");
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        b.setBounds(500,600,300,70);
        b.addActionListener(this);
        f.add(b);
        
        f.getContentPane().setBackground(Color.lightGray);

        f.setVisible(true);
        f.setSize(1300,750);
        f.setLocation(100,50);
        
        while(true){
            l.setVisible(false); 
            try{
                Thread.sleep(500); 
            }catch(Exception e){} 
            l.setVisible(true);
            try{
                Thread.sleep(500);
            }catch(Exception e){}
        }

        
    }
    public void actionPerformed(ActionEvent ae){

        if(ae.getSource()==b){
            f.setVisible(false);
            new Login();    
        }
    }
    
    public static void main(String[] arg){
        WelcomeWindow ww = new WelcomeWindow();
    }
    
}
