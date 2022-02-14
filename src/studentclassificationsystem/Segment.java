package studentclassificationsystem;
import java.sql.*;
import java.util.Random;
import java.lang.Math;

public class Segment {
    
    static int data[][] = new int[1000][6],i=0,i1=0,i2=1,i3=2,i4=3,i5=4,i6=5,i7=6,i8=7,i9=8,i10=9,i11=10;
    static double clu1[][] = new double[1][10] ;
    static double clu2[][] = new double[1][10] ;
    static double clu3[][] = new double[1][10] ;
    
    Segment()
    {
        try 
        {
            conn ccc = new conn();
            String test = "select * from students";                
            ResultSet rs = ccc.s.executeQuery(test); 

            while (rs.next()) 
            {            
                data[i][i1] = rs.getInt(1) ;
//                if(rs.getInt(2)==0)
//                {
//                    data[i][i2]=0;
//                    data[i][i3]=0;
//                    data[i][i4]=0;
//                    data[i][i5]=0;
//                    data[i][i6]=0;
//                }
//                else if(rs.getInt(2)==1)
//                {
//                    data[i][i2]=1;
//                    data[i][i3]=0;
//                    data[i][i4]=0;
//                    data[i][i5]=0;
//                    data[i][i6]=0;
//                }
//                else if(rs.getInt(2)==2)
//                {
//                    data[i][i2]=0;
//                    data[i][i3]=1;
//                    data[i][i4]=0;
//                    data[i][i5]=0;
//                    data[i][i6]=0;
//                }
//                else if(rs.getInt(2)==3)
//                {
//                    data[i][i2]=0;
//                    data[i][i3]=0;
//                    data[i][i4]=1;
//                    data[i][i5]=0;
//                    data[i][i6]=0; 
//                }
//                else if(rs.getInt(2)==4)
//                {
//                    data[i][i2]=0;
//                    data[i][i3]=0;
//                    data[i][i4]=0;
//                    data[i][i5]=1;
//                    data[i][i6]=0;
//                }
//                else if(rs.getInt(2)==5)
//                {
//                    data[i][i2]=0;
//                    data[i][i3]=0;
//                    data[i][i4]=0;
//                    data[i][i5]=0;
//                    data[i][i6]=1;
//                }
                data[i][i2] = rs.getInt(3) ;
                data[i][i3] = rs.getInt(4) ;
                data[i][i4] = rs.getInt(5) ;
                data[i][i5] = rs.getInt(6) ;
                i+=1 ;
            }   
        }catch(Exception e){
            e.printStackTrace();
            }
    } 
    
    public void printData()
    {
        for(int k = 0 ; k < 1000 ; k++)
        {
            for(int j = 0 ; j < 6 ; j++)
            {
                System.out.print(data[k][j] + " ") ;
            }
            System.out.println("\n") ;
        }
    }
    
    public void printClusterData()
    {
        for(int j = 0 ; j < 5 ; j++)
        {
            System.out.print(clu1[0][j] + " ") ;
        }
        System.out.println() ;
        for(int j = 0 ; j < 5 ; j++)
        {
            System.out.print(clu2[0][j] + " ") ;
        }
        System.out.println() ;
        for(int j = 0 ; j < 5 ; j++)
        {
            System.out.print(clu3[0][j] + " ") ;
        }
    }
    
    public void populateClusters()
    {
        Random rand = new Random();
        int randomNum1 = rand.nextInt(1000) ;
        int randomNum2= rand.nextInt(1000) ;
        int randomNum3 = rand.nextInt(1000) ;
        
//        System.out.println("\n" + randomNum1 + " " + randomNum2 + " " + randomNum3 + "\n") ;
        
        for (int a = 0; a < 5; a++) {
            clu1[0][a] = data[randomNum1][a] ;
            clu2[0][a] = data[randomNum2][a] ;
            clu3[0][a] = data[randomNum3][a] ;
        }
    }
    
    public void updateClusterMean()
    {
        for(int i = 0 ; i < 1000 ; i++)
        {
            if(data[i][i6]==0)
            {
                clu1[0][i2] = (clu1[0][i2] + data[i][i2])/2 ;
                clu1[0][i3] = (clu1[0][i3] + data[i][i3])/2 ;
                clu1[0][i4] = (clu1[0][i4] + data[i][i4])/2 ;
                clu1[0][i5] = (clu1[0][i5] + data[i][i5])/2 ;
            }
            if(data[i][i6]==1)
            {
                clu2[0][i2] = (clu2[0][i2] + data[i][i2])/2 ;
                clu2[0][i3] = (clu2[0][i3] + data[i][i3])/2 ;
                clu2[0][i4] = (clu2[0][i4] + data[i][i4])/2 ;
                clu2[0][i5] = (clu2[0][i5] + data[i][i5])/2 ;
            }
            if(data[i][i6]==2)
            {
                clu3[0][i2] = (clu3[0][i2] + data[i][i2])/2 ;
                clu3[0][i3] = (clu3[0][i3] + data[i][i3])/2 ;
                clu3[0][i4] = (clu3[0][i4] + data[i][i4])/2 ;
                clu3[0][i5] = (clu3[0][i5] + data[i][i5])/2 ;
            }
        }
    }
    
    public void groupStudents()
    {
        conn cc = new conn();
        
        for(int itr = 0 ; itr < 15 ; itr++)
        {
            for(int i = 0 ; i < 1000 ; i++)
            {
                double dist0 = Math.pow((Math.pow(clu1[0][i2]-data[i][i2],2)+Math.pow(clu1[0][i3]-data[i][i3],2)+Math.pow(clu1[0][i4]-data[i][i4],2)+Math.pow(clu1[0][i5]-data[i][i5],2)),0.5);
                double dist1 = Math.pow((Math.pow(clu2[0][i2]-data[i][i2],2)+Math.pow(clu2[0][i3]-data[i][i3],2)+Math.pow(clu2[0][i4]-data[i][i4],2)+Math.pow(clu2[0][i5]-data[i][i5],2)),0.5);
                double dist2 = Math.pow((Math.pow(clu3[0][i2]-data[i][i2],2)+Math.pow(clu3[0][i3]-data[i][i3],2)+Math.pow(clu3[0][i4]-data[i][i4],2)+Math.pow(clu3[0][i5]-data[i][i5],2)),0.5);
               
                if(dist0<dist1 && dist0<dist2)
                    data[i][i6] = 0 ;
                else if(dist1<dist0 && dist1<dist2)
                    data[i][i6] = 1 ;
                else if(dist2<dist0 && dist2<dist1)
                    data[i][i6] = 2 ;
                else if(dist0==dist1)
                    data[i][i6] = 0 ;
                else if(dist1==dist2)
                    data[i][i6] = 1 ;
                else if(dist0==dist2)
                    data[i][i6] = 2 ;    
                
//                if(itr==14)
//                {
//                    try
//                    {   System.out.println(data[i][i6] + " ") ;                 
//                        String q = "insert into label values('"+data[i][i6]+"')";
//                        cc.s.executeUpdate(q);
//
//                    }catch(Exception ee){
//                        System.out.println("The error is:"+ee);
//                    }
//                }
            }
            updateClusterMean() ;                             
        }  
        for(int i=0 ; i<1000 ; i++)
        {       
            try
            {   
                System.out.println(data[i][i6] + " ") ;                 
                String q = "insert into label values('"+data[i][i6]+"')";
                cc.s.executeUpdate(q);

                }catch(Exception ee){
                    System.out.println("The error is:"+ee);
                }
        }        
    }
        
    public double[] getResult()
    {   
        double len_0=0,sum_0_maths=0,sum_0_read=0,sum_0_write=0,len_1=0,sum_1_maths=0,sum_1_read=0,sum_1_write=0,len_2=0,sum_2_maths=0,sum_2_read=0,sum_2_write=0;

        double arr[] = new double[9] ;
        for(int j = 0 ; j < 1000 ; j++)
        {
            if(data[j][i6]==0)
            {
                len_0+=1 ;
            }
            if(data[j][i6]==1)
            {
                len_1+=1 ;
            }
            if(data[j][i6]==2)
            {
                len_2+=1 ;
            }
        }

        for(int j = 0 ; j < 1000 ; j++)
        {
            if(data[j][i6]==0)
            {
                sum_0_maths = sum_0_maths + data[j][i3] ;
                sum_0_read = sum_0_read + data[j][i4] ;
                sum_0_write = sum_0_write + data[j][i5] ;
            }
            if(data[j][i6]==1)
            {
                sum_1_maths = sum_1_maths + data[j][i3] ;
                sum_1_read = sum_1_read + data[j][i4] ;
                sum_1_write = sum_1_write + data[j][i5] ;
            }
            if(data[j][i6]==2)
            {
                sum_2_maths = sum_2_maths + data[j][i3] ;
                sum_2_read = sum_2_read + data[j][i4] ;
                sum_2_write = sum_2_write + data[j][i5] ;
            }
        }
        sum_0_maths = sum_0_maths/len_0 ;
        sum_0_read = sum_0_read/len_0 ;
        sum_0_write = sum_0_write/len_0 ;
        sum_1_maths = sum_1_maths/len_1 ;
        sum_1_read = sum_1_read/len_1 ;
        sum_1_write = sum_1_write/len_1 ;
        sum_2_maths = sum_2_maths/len_2 ;
        sum_2_read = sum_2_read/len_2 ;
        sum_2_write = sum_2_write/len_2 ;
        
        arr[0] = sum_0_maths ;
        arr[1] = sum_0_read ;
        arr[2] = sum_0_write ;
        arr[3] = sum_1_maths ;
        arr[4] = sum_1_read ;
        arr[5] = sum_1_write ;
        arr[6] = sum_2_maths ;
        arr[7] = sum_2_read ;
        arr[8] = sum_2_write ;     
        
//        System.out.println(sum_0_maths) ;
//        System.out.println(sum_0_read) ;
//        System.out.println(sum_0_write) ;
//        System.out.println(sum_1_maths) ;
//        System.out.println(sum_1_read) ;
//        System.out.println(sum_1_write) ;
//        System.out.println(sum_2_maths) ;
//        System.out.println(sum_2_read) ;
//        System.out.println(sum_2_write) ;        
        
        return arr ;
    }
    
    public static void main(String args[])  
    {  
        Segment obj = new Segment() ;     
//        obj.printData();
        obj.populateClusters();
//        obj.printClusterData();
        obj.groupStudents();
//        obj.printData();
//        obj.getResult() ;
//        obj.getLabel() ;
    }
}
