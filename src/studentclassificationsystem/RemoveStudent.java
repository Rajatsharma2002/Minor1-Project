package studentclassificationsystem;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RemoveStudent implements ActionListener{
    
    JFrame f;
    JTextField t;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8;
    JButton b,b1,b2,b3;
    
    RemoveStudent()
    {
        f=new JFrame("Remove Student");
        f.setLayout(null);
        
        l1=new JLabel("Student Id");
        l1.setBounds(50,50,250,30);
        l1.setForeground(Color.black);
        l1.setFont(new Font("serif",Font.BOLD,25));
        f.add(l1);

        t=new JTextField();
        t.setBounds(250,50,150,30);
        f.add(t);

        b=new JButton("Search");
        b.setBounds(200,100,100,30);
        b.addActionListener(this);
        f.add(b);

        b3=new JButton("back");
        b3.setBounds(360,100,100,30);   
        b3.addActionListener(this);
        f.add(b3);
        
        l2=new JLabel("Math Score : "); 
        l2.setBounds(50,150,250,30);
        l2.setForeground(Color.black);
        l2.setFont(new Font("serif",Font.BOLD,18));
        f.add(l2);
        
        l6=new JLabel();    
        l6.setBounds(200,150,350,30);
        l6.setForeground(Color.black);
        Font F6=new Font("serif",Font.BOLD,18); 
        l6.setFont(F6);
        f.add(l6);
        
        l3=new JLabel("Reading Score : ");
        l3.setBounds(50,200,250,30);
        l3.setForeground(Color.black);
        l3.setFont(new Font("serif",Font.BOLD,18));
        f.add(l3);


        l7=new JLabel();
        l7.setBounds(200,200,350,30);
        l7.setForeground(Color.black);
        l7.setFont(new Font("serif",Font.BOLD,18));
        f.add(l7);

        l4=new JLabel("Writing Score : ");
        l4.setBounds(50,250,250,30);
        l4.setForeground(Color.black);
        l4.setFont(new Font("serif",Font.BOLD,18));
        f.add(l4);

        l8=new JLabel();
        l8.setBounds(200,250,350,30);
        l8.setForeground(Color.black);
        l8.setFont(new Font("serif",Font.BOLD,18));
        f.add(l8);
        
        b1=new JButton("Remove");
        b1.setBounds(120,350,100,30);
        b1.addActionListener(this);
        f.add(b1);

        b2=new JButton("Cancel");
        b2.setBounds(300,350,100,30);
        b2.addActionListener(this);
        f.add(b2);
        
        l2.setVisible(false);
        l3.setVisible(false);
        l4.setVisible(false);
        b1.setVisible(false);
        b2.setVisible(false);
      
        f.setSize(500,500);
        f.setLocation(500,250);  
        f.setVisible(true);
    }               
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()==b){
            try{
                conn con = new conn();
                String str = "select mathscore,readingscore,writingscore from students where stu_id ='"+t.getText()+"' ";
                ResultSet rs = con.s.executeQuery(str);

                int i=0;
                if(rs.next()){
                    String mathscore = rs.getString(1);
                    String readingcore = rs.getString(2);
                    String writingscore =rs.getString(3);

                    l2.setVisible(true);
                    l3.setVisible(true);
                    l4.setVisible(true);
                    b1.setVisible(true);
                    b2.setVisible(true);    
                    i=1;
                    l6.setText(mathscore);
                    l7.setText(readingcore);
                    l8.setText(writingscore);
                }
                if(i==0)
                    JOptionPane.showMessageDialog(null,"Id not found");
            }catch(Exception ex){}
        }
        if(ae.getSource()==b1){
            try{
                conn con = new conn();
                String str = "delete from students where stu_id = '"+t.getText()+"'";
                con.s.executeUpdate(str);
                JOptionPane.showMessageDialog(null,"deleted successfully");
                l2.setVisible(false);
                l3.setVisible(false);
                l4.setVisible(false);
                l6.setVisible(false);
                l7.setVisible(false);
                l8.setVisible(false);
                b1.setVisible(false);
                b2.setVisible(false);

            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Exception occured while delting record "+ex);
            }
        }
        if(ae.getSource()==b2){
            f.setVisible(false);
            UserAction d=new UserAction();
        }
        if(ae.getSource()==b3){
            f.setVisible(false);
            UserAction d=new UserAction();
        }
    }
    
    public static void main(String[]ar){
        new RemoveStudent();
    }
}
