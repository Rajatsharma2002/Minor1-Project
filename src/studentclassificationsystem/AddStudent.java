package studentclassificationsystem;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

class AddStudent implements ActionListener{

    JFrame f;
    JLabel id,id1,id2,id3,id4,id5,id6;
    JTextField t1,t2,t3,t4,t5,t6;
    JButton b,b1;

    AddStudent(int i){}
    
    AddStudent()
    {
        f = new JFrame("Add Student");
        f.setBackground(Color.white);
        f.setLayout(null);

        id = new JLabel("New Student Details");
        id.setBounds(320,30,500,50);
        id.setFont(new Font("serif",Font.ITALIC,25));
        id.setForeground(Color.black);
        f.add(id);

 
        id1 = new JLabel("ID");
        id1.setBounds(50,150,100,30);
        id1.setFont(new Font("serif",Font.BOLD,20));
        f.add(id1);

        t1 = new JTextField();
        t1.setBounds(200,150,150,30);
        f.add(t1);

//        id2 = new JLabel("Parental Level Of Education");
//        id2.setBounds(400,150,200,30);
//        id2.setFont(new Font("serif",Font.BOLD,20));
//        f.add(id2);
//
//        t2=new JTextField();
//        t2.setBounds(600,150,150,30);
//        f.add(t2);

        id3= new JLabel("Test Preperation");
        id3.setBounds(50,200,100,30);
        id3.setFont(new Font("serif",Font.BOLD,20));
        f.add(id3);

        t3=new JTextField();
        t3.setBounds(200,200,150,30);
        f.add(t3);

        id4= new JLabel("Math Score");  
        id4.setBounds(400,200,200,30);
        id4.setFont(new Font("serif",Font.BOLD,20));
        f.add(id4);

        t4=new JTextField();
        t4.setBounds(600,200,150,30);
        f.add(t4);

        id5= new JLabel("Reading Score");
        id5.setBounds(50,250,100,30);
        id5.setFont(new Font("serif",Font.BOLD,20));
        f.add(id5);

        t5=new JTextField();
        t5.setBounds(200,250,150,30);
        f.add(t5);

        id6= new JLabel("Writing Score");
        id6.setBounds(400,150,200,30);
        id6.setFont(new Font("serif",Font.BOLD,20));
        f.add(id6);

        t6=new JTextField();
        t6.setBounds(600,150,150,30);
        f.add(t6);

        b = new JButton("Submit");
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        b.setBounds(250,550,150,40);
        b.addActionListener(this);
        f.add(b);

        b1=new JButton("Cancel");   
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(450,550,150,40);
        b1.addActionListener(this);
        f.add(b1);
      
                
        f.setVisible(true);
        f.setSize(900,700);
        f.setLocation(400,150);
    }

    public void actionPerformed(ActionEvent ae){
        
        String stuID = t1.getText();
//        String ploe = t2.getText();
        String tp = t3.getText();
        String ms = t4.getText();
        String rs = t5.getText();
        String ws = t6.getText();
//        int ploe = Integer.parseInt(t2.getText());
//        int tp = Integer.parseInt(t3.getText());
//        int ms = Integer.parseInt(t4.getText());
//        int rs = Integer.parseInt(t5.getText());
//        int ws = Integer.parseInt(t6.getText());

        if(ae.getSource() == b){
            try{
                conn cc = new conn();
                String q = "insert into students values('"+stuID+"','"+0+"','"+tp+"','"+ms+"','"+rs+"','"+ws+"')";
                cc.s.executeUpdate(q);
                JOptionPane.showMessageDialog(null,"Details Successfully Inserted");
                f.setVisible(false);
                new UserAction();
            }catch(Exception ee){
                System.out.println("The error is:"+ee);
            }
        }else if(ae.getSource() == b1){
            f.setVisible(false);
            new UserAction();
        }
    }
    
    public static void main(String[ ] arg){
        new AddStudent();
    }
}