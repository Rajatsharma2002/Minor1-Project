package studentclassificationsystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

class ShowData implements ActionListener{
    JFrame f;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13;
    JButton b1,b2;
    ImageIcon icon;
    String stuId,ploe,tp,ms,rds,ws ;

    ShowData(String s_id){
        try{
            conn con = new conn();
            String str = "select * from students where stu_id = '"+s_id+"'";
            ResultSet rs= con.s.executeQuery(str);
            int i = 0 ;
            while(rs.next()){
                stuId = rs.getString("stu_id");
                ploe = rs.getString("ploe");
                tp = rs.getString("testprep");
                ms = rs.getString("mathscore");
                rds = rs.getString("readingscore");
                ws = rs.getString("writingscore");
                i = 1 ;
            }
            if(i==0)
            {
                JOptionPane.showMessageDialog(null,"Id not found");
            }
                
        }catch(Exception e){
            e.printStackTrace();
        }
 
        f=new JFrame("Data");
        f.setVisible(true);
        f.setSize(595,500);
        f.setLocation(450,200);
        f.setLayout(null);


        l1= new JLabel("STUDENET DETAIL");
        l1.setBounds(165,10,250,30);
        l1.setFont(new Font("serif",Font.BOLD,25));
        f.add(l1);

        l2 = new JLabel("Student Id : ");
        l2.setBounds(50,70,140,30);
        l2.setFont(new Font("serif",Font.BOLD,20));
        f.add(l2);

        l3 = new JLabel(stuId);
        l3.setBounds(230,70,200,30);
        l3.setFont(new Font("serif",Font.BOLD,20));
        f.add(l3);

//        l4 = new JLabel("Parental Edu : ");
//        l4.setBounds(50,120,140,30);
//        l4.setFont(new Font("serif",Font.BOLD,20));
//        f.add(l4);
//
//        l5 = new JLabel(ploe);
//        l5.setBounds(230,120,300,30);
//        l5.setFont(new Font("serif",Font.BOLD,20));
//        f.add(l5);

  
        l6 = new JLabel("Test Preperation : "); 
        l6.setBounds(50,120,140,30);
        l6.setFont(new Font("serif",Font.BOLD,20));
        f.add(l6);

        l7 = new JLabel(tp);
        l7.setBounds(230,120,300,30);
        l7.setFont(new Font("serif",Font.BOLD,20));
        f.add(l7);

        l8= new JLabel("Math Score : ");
        l8.setBounds(50,170,200,30);
        l8.setFont(new Font("serif",Font.BOLD,20));
        f.add(l8);

        l9 = new JLabel(ms);
        l9.setBounds(230,170,300,30);
        l9.setFont(new Font("serif",Font.BOLD,20));
        f.add(l9);


        l10= new JLabel("Reading Score : ");  
        l10.setBounds(50,220,140,30);
        l10.setFont(new Font("serif",Font.BOLD,20));
        f.add(l10);

        l11= new JLabel(rds);
        l11.setBounds(230,220,300,30); 
        l11.setFont(new Font("serif",Font.BOLD,20));
        f.add(l11);

        
        l12= new JLabel("Writing Score : ");
        l12.setBounds(50,270,140,30);
        l12.setFont(new Font("serif",Font.BOLD,20));
        f.add(l12);

        l13= new JLabel(ws);
        l13.setBounds(230,270,300,30);
        l13.setFont(new Font("serif",Font.BOLD,20));
        f.add(l13);


        b1=new JButton("cancel");
        b1.setBounds(250,350,100,30);
        b1.addActionListener(this);
        f.add(b1);

    }

    public void actionPerformed(ActionEvent ae){

        if(ae.getSource()==b1){
            f.setVisible(false);
            new ViewStudent();
        }
    }
    public static void main(String[] args){
        new ShowData("Data");
    }

}
