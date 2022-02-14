/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studentclassificationsystem;

import java.sql.*;
import java.util.Random;
import java.lang.Math;

public class Cluster {
    
    int data[][] = new int[1000][7],i=0,i1=0,i2=1,i3=2,i4=3,i5=4,i6=5,i7=6;
    static int clu1[][] = new int[1][6] ;
    static int clu2[][] = new int[1][6] ;
    static int clu3[][] = new int[1][6] ;
    
    Cluster()
    {
        try 
        {
            conn ccc = new conn();
            String test = "select * from students";                
            ResultSet rs = ccc.s.executeQuery(test); 

            while (rs.next()) 
            {            
                data[i][i1] = rs.getInt(1) ;
                data[i][i2] = rs.getInt(2) ;
                data[i][i3] = rs.getInt(3) ;
                data[i][i4] = rs.getInt(4) ;
                data[i][i5] = rs.getInt(5) ;
                data[i][i6] = rs.getInt(6) ;
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
            for(int j = 0 ; j < 7 ; j++)
            {
                System.out.print(data[k][j] + " ") ;
            }
            System.out.println("\n") ;
        }
    }
    
    public void printClusterData()
    {
        for(int j = 0 ; j < 6 ; j++)
        {
            System.out.print(clu1[0][j] + " ") ;
        }
        System.out.println() ;
        for(int j = 0 ; j < 6 ; j++)
        {
            System.out.print(clu2[0][j] + " ") ;
        }
        System.out.println() ;
        for(int j = 0 ; j < 6 ; j++)
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
        
        for (int a = 0; a < 6; a++) {
            clu1[0][a] = data[randomNum1][a] ;
            clu2[0][a] = data[randomNum2][a] ;
            clu3[0][a] = data[randomNum3][a] ;
        }
    }
    
    public void updateClusterMean()
    {
        for(int i = 0 ; i < 1000 ; i++)
        {
            if(data[i][i7]==0)
            {
                clu1[0][i2] = (clu1[0][i2] + data[i][i2])/2 ;
                clu1[0][i3] = (clu1[0][i3] + data[i][i3])/2 ;
                clu1[0][i4] = (clu1[0][i4] + data[i][i4])/2 ;
                clu1[0][i5] = (clu1[0][i5] + data[i][i5])/2 ;
                clu1[0][i6] = (clu1[0][i6] + data[i][i6])/2 ;
            }
            if(data[i][i7]==1)
            {
                clu2[0][i2] = (clu2[0][i2] + data[i][i2])/2 ;
                clu2[0][i3] = (clu2[0][i3] + data[i][i3])/2 ;
                clu2[0][i4] = (clu2[0][i4] + data[i][i4])/2 ;
                clu2[0][i5] = (clu2[0][i5] + data[i][i5])/2 ;
                clu2[0][i6] = (clu2[0][i6] + data[i][i6])/2 ;
            }
            if(data[i][i7]==2)
            {
                clu3[0][i2] = (clu3[0][i2] + data[i][i2])/2 ;
                clu3[0][i3] = (clu3[0][i3] + data[i][i3])/2 ;
                clu3[0][i4] = (clu3[0][i4] + data[i][i4])/2 ;
                clu3[0][i5] = (clu3[0][i5] + data[i][i5])/2 ;
                clu3[0][i6] = (clu3[0][i6] + data[i][i6])/2 ;
            }
        }
    }
    
    public void groupStudents()
    {
        for(int s = 0 ; s < 15 ; s++)
        {
            for(int i = 0 ; i < 1000 ; i++)
            {
                double dist0 = (Math.pow(clu1[0][i2]-data[i][i2],2)+Math.pow(clu1[0][i3]-data[i][i3],2)+Math.pow(clu1[0][i4]-data[i][i4],2)+Math.pow(clu1[0][i5]-data[i][i5],2)+Math.pow(clu1[0][i6]-data[i][i6],2) );
                double dist1 = (Math.pow(clu2[0][i2]-data[i][i2],2)+Math.pow(clu2[0][i3]-data[i][i3],2)+Math.pow(clu2[0][i4]-data[i][i4],2)+Math.pow(clu2[0][i5]-data[i][i5],2)+Math.pow(clu2[0][i6]-data[i][i6],2) );
                double dist2 = (Math.pow(clu3[0][i2]-data[i][i2],2)+Math.pow(clu3[0][i3]-data[i][i3],2)+Math.pow(clu3[0][i4]-data[i][i4],2)+Math.pow(clu3[0][i5]-data[i][i5],2)+Math.pow(clu3[0][i6]-data[i][i6],2) );
                
                if(dist0<dist1 && dist0<dist2)
                    data[i][i7] = 0 ;
                else if(dist1<dist0 && dist1<dist2)
                    data[i][i7] = 1 ;
                else if(dist2<dist0 && dist2<dist1)
                    data[i][i7] = 2 ;
                else if(dist0==dist1)
                    data[i][i7] = 0 ;
                else if(dist1==dist2)
                    data[i][i7] = 1 ;
                else if(dist0==dist2)
                    data[i][i7] = 2 ;      
            }
            updateClusterMean() ;
        }
    }
 
    
    public static void main(String args[])  
    {  
        Segment obj = new Segment() ;     
//        obj.printData();
        obj.populateClusters();
//        obj.printClusterData();
        obj.groupStudents();
        obj.printData();

    }
    
}
