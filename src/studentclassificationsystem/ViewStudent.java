package studentclassificationsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ViewStudent implements ActionListener{
    
    JFrame f;
    JTextField t;
    JLabel l1,l2;
    JButton b,b2;
    
    ViewStudent(){
        f=new JFrame("View Window");
        f.setBackground(Color.black);
        f.setLayout(null);

//        l1=new JLabel();
//        l1.setBounds(0,0,500,270);
//        l1.setLayout(null);
//        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/view.jpg"));
//        l1.setIcon(img);


        l2=new JLabel("Student Id");
        l2.setVisible(true);
        l2.setBounds(40,60,250,30);
        l2.setForeground(Color.black);
        l2.setFont(new Font("serif",Font.BOLD,30));
        f.add(l2);

        t=new JTextField();
        t.setBounds(240,60,220,30);
        f.add(t);

        b=new JButton("Search");
        b.setBounds(240,150,100,30);
        b.addActionListener(this);
        f.add(b);

        b2=new JButton("Cancel");
        b2.setBounds(360,150,100,30);
        b2.addActionListener(this);
        f.add(b2);

        f.setSize(500,270);
        f.setLocation(450,250);
        f.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){

        if(ae.getSource()==b2){
            f.setVisible(false);
            UserAction d=new UserAction();
        }
        if(ae.getSource()==b){
            f.setVisible(false);
            ShowData p=new ShowData(t.getText());
        }
    }

    public static void main(String[]ar){
        ViewStudent v=new ViewStudent();
    }
    
}
