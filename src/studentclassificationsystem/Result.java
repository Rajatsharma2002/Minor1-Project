package studentclassificationsystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

class Result extends Segment implements ActionListener{
    JFrame f;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23;
    JButton b1,b2;
    ImageIcon icon;
//    String stuId = new String("90.00504"),ploe,tp,ms,rds,ws ;
   
    Result(double[]arr1){
        /*
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
*/
        String s[] = new String[9];
        for(int i = 0 ; i < 9 ; i++)
        {
            s[i] = String.valueOf(arr1[i]);
        }
 
        f=new JFrame("Info");
        f.setVisible(true);
        f.setSize(800,820);
        f.setLocation(450,0);
        f.setLayout(null);


        l1= new JLabel("CLUSTER 1");
        l1.setBounds(330,10,250,30);
        l1.setFont(new Font("serif",Font.BOLD,25));
        f.add(l1);

        l2 = new JLabel("MATH SCORE : ");
        l2.setBounds(50,70,200,30);
        l2.setFont(new Font("serif",Font.BOLD,20));
        f.add(l2);

        l3 = new JLabel(s[0]);
        l3.setBounds(330,70,200,30);
        l3.setFont(new Font("serif",Font.BOLD,20));
        f.add(l3);

        l4 = new JLabel("READING SCORE : ");
        l4.setBounds(50,120,200,30);
        l4.setFont(new Font("serif",Font.BOLD,20));
        f.add(l4);

        l5 = new JLabel(s[1]);
        l5.setBounds(330,120,200,30);
        l5.setFont(new Font("serif",Font.BOLD,20));
        f.add(l5);

  
        l6 = new JLabel("WRITING SCORE : "); 
        l6.setBounds(50,170,200,30);
        l6.setFont(new Font("serif",Font.BOLD,20));
        f.add(l6);

        l7 = new JLabel(s[2]);
        l7.setBounds(330,170,200,30);
        l7.setFont(new Font("serif",Font.BOLD,20));
        f.add(l7);

        l8= new JLabel("CLUSTER 2");
        l8.setBounds(330,230,250,30);
        l8.setFont(new Font("serif",Font.BOLD,25));
        f.add(l8);

        l10= new JLabel("MATH SCORE : ");  
        l10.setBounds(50,290,200,30);
        l10.setFont(new Font("serif",Font.BOLD,20));
        f.add(l10);

        l11= new JLabel(s[3]);
        l11.setBounds(330,290,200,30); 
        l11.setFont(new Font("serif",Font.BOLD,20));
        f.add(l11);

        
        l12= new JLabel("READING SCORE : ");
        l12.setBounds(50,340,200,30);
        l12.setFont(new Font("serif",Font.BOLD,20));
        f.add(l12);

        l13= new JLabel(s[4]);
        l13.setBounds(330,340,200,30);
        l13.setFont(new Font("serif",Font.BOLD,20));
        f.add(l13);
        
        l14= new JLabel("WRITING SCORE : ");
        l14.setBounds(50,390,200,30);
        l14.setFont(new Font("serif",Font.BOLD,20));
        f.add(l14);

        l15= new JLabel(s[5]);
        l15.setBounds(330,390,200,30);
        l15.setFont(new Font("serif",Font.BOLD,20));
        f.add(l15);
        
        l16= new JLabel("CLUSTER 3");
        l16.setBounds(330,450,250,30);
        l16.setFont(new Font("serif",Font.BOLD,25));
        f.add(l16);

        l17= new JLabel("MATH SCORE : ");
        l17.setBounds(50,510,200,30);
        l17.setFont(new Font("serif",Font.BOLD,20));
        f.add(l17);

        l18= new JLabel(s[6]);
        l18.setBounds(330,510,200,30);
        l18.setFont(new Font("serif",Font.BOLD,20));
        f.add(l18);
        
        l19= new JLabel("READING SCORE : ");
        l19.setBounds(50,560,200,30);
        l19.setFont(new Font("serif",Font.BOLD,20));
        f.add(l19);

        l20= new JLabel(s[7]);
        l20.setBounds(330,560,200,30);
        l20.setFont(new Font("serif",Font.BOLD,20));
        f.add(l20);
        
        l21= new JLabel("WRITING SCORE : ");
        l21.setBounds(50,610,200,30);
        l21.setFont(new Font("serif",Font.BOLD,20));
        f.add(l21);

        l22= new JLabel(s[8]);
        l22.setBounds(330,610,200,30);
        l22.setFont(new Font("serif",Font.BOLD,20));
        f.add(l22);

        b1=new JButton("cancel");
        b1.setBounds(330,700,100,30);
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
        double arr1[] = new double[9] ;
        Segment s = new Segment() ;
        s.populateClusters();
        s.groupStudents();
                   
        arr1 = s.getResult() ;
        for (double i : arr1) {
            System.out.println(i);
        } 
        
        new Result(arr1);
    }

}
