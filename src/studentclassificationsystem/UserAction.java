package studentclassificationsystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class UserAction implements ActionListener{
    
    JFrame f;
    JLabel l1,l2;
    JButton b1,b2,b3,b4,b5;
    
    UserAction()
    {
        f=new JFrame("User Actions");
        f.setBackground(Color.white);
        f.setLayout(null);
        
        
        l2 = new JLabel("Student Details");
        l2.setBounds(255,40,200,40);
        l2.setFont(new Font("serif",Font.BOLD,25));
        l2.setForeground(Color.black);
        f.add(l2);

        b1=new JButton("ADD");
        b1.setBounds(100,120,150,40);
        b1.setFont(new Font("serif",Font.BOLD,15));
        b1.addActionListener(this);
        f.add(b1) ;

        b2=new JButton("VIEW");
        b2.setBounds(430,120,150,40);
        b2.setFont(new Font("serif",Font.BOLD,15));
        b2.addActionListener(this);
        f.add(b2);

        b3=new JButton("REMOVE");
        b3.setBounds(100,220,150,40);
        b3.setFont(new Font("serif",Font.BOLD,15));
        b3.addActionListener(this);
        f.add(b3);

        b4=new JButton("CLUSTER");
        b4.setBounds(430,220,150,40);
        b4.setFont(new Font("serif",Font.BOLD,15));
        b4.addActionListener(this);
        f.add(b4);
        
        b5=new JButton("CLASSIFY");
        b5.setBounds(265,320,150,40);
        b5.setFont(new Font("serif",Font.BOLD,15));
        b5.addActionListener(this);
        f.add(b5);
        
        f.setVisible(true);
        f.setSize(700,500);
        f.setLocation(450,200);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b1){
            f.setVisible(false);
            new AddStudent();
        }
        if(ae.getSource()==b2){
            f.setVisible(false);
            new ViewStudent();
        }
        if(ae.getSource()==b3){
            f.setVisible(false);
            new RemoveStudent();
        }
        if(ae.getSource()==b4){
            f.setVisible(false);
            new Temp();
        }
        if(ae.getSource()==b5){
            f.setVisible(false);
            new Classify();
        }
    }
    
    
    public static void main(String[ ] arg){
        UserAction u = new UserAction();
    }
    
}
