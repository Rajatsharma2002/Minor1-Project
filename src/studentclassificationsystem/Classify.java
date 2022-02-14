package studentclassificationsystem;
import java.sql.*;
import java.util.Random;

public class Classify {
    
    static int data[][] = new int[1100][4],i,l,i1=0,i2=1,i3=2,i4=3 ;
    static int label[][] = new int[1100][3] ;
    static int trainData[][] = new int[800][4], testData[][] = new int[250][4] ;
    static int trainLabel[][] = new int[800][3], testLabel[][] = new int[250][3] ;
    static int len ,n_x=4,n_y=3,n_h=800 , iterations = 1000;
    static double w1[][] = new double[n_h][n_x] ; 
    static double b1[][] = new double[n_h][1] ;
    static double w2[][] = new double[n_y][n_h] ; 
    static double b2[][] = new double[n_y][1] ;
    static double learningRate = 0.05 ;
    static double z1[][] = new double[n_h][800], a1[][] = new double[n_h][800] ;
    static double z2[][] = new double[n_y][800], a2[][] = new double[n_y][800] ;
    static double cost ;
    static double trainData_T[][] = new double[4][800], y[][] = new double[3][800] ;
    static double dw2[][] = new double[3][800],db2[][] = new double[3][1],dw1[][] = new double[800][4],db1[][] = new double[800][1] ;
    static double d_a1[][] = new double[n_h][800] ;
    static int labels[] = new int[800] ;
    
    Classify()
    {
        conn ccc = new conn();
        len = 0 ;
        i=0;
        int temp = 0 ;
        try 
        {           
            String query1 = "select * from students";                
            ResultSet rs = ccc.s.executeQuery(query1);

            while (rs.next()) 
            {            
                data[i][i1] = rs.getInt(3) ;
                data[i][i2] = rs.getInt(4) ;
                data[i][i3] = rs.getInt(5) ;
                data[i][i4] = rs.getInt(6) ;
                
                i+=1 ;
                len +=  1 ;                
            }   
        }catch(Exception e){
            e.printStackTrace();
            }
        
        try
        {
            l=0;
            String query2 = "select * from label" ;
            ResultSet rs2 = ccc.s.executeQuery(query2) ;
            
            while(rs2.next())
            {
                if(rs2.getInt(1)==0)
                {
                    label[l][0] = 1 ;
                    label[l][1] = 0 ;
                    label[l][2] = 0 ;
                }
                if(rs2.getInt(1)==1)
                {
                    label[l][0] = 0 ;
                    label[l][1] = 1 ;
                    label[l][2] = 0 ;
                }
                if(rs2.getInt(1)==2)
                {
                    label[l][0] = 0 ;
                    label[l][1] = 0 ;
                    label[l][2] = 1 ;
                }
                
                l+=1 ;
                
                if(temp<800)
                {
                    labels[temp] = rs2.getInt(1) ;
                    temp+=1 ;
                }
            }
           
        }catch(Exception e){
            e.printStackTrace();
            }
    }
    
    public static void printData()
    {
        for(int i = 0 ; i < 1000 ; i++)
        {
            for(int k = 0 ; k < 4 ; k++)
            {
                System.out.print(data[i][k] + " ") ;
            } 
            System.out.println() ;
        }
        
        System.out.println("\n\nLABELS\n") ;
        
        for(int k = 0 ; k < len ; k++)
        {
            for(int i = 0 ; i < 3 ; i++)
            {
                System.out.print(label[k][i] + " ") ;
            } 
            System.out.println() ;
        }
    }
    
    public static void getTranspose()
    {
        //transpose of feature array
        for (int i = 0; i < 800; ++i)
        {
            for (int j = 0; j < 4; ++j)
            {
                trainData_T[j][i] = trainData[i][j];
            }
        }
        //transpose of trainLabel
        
        for (int i = 0; i < 800; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                y[j][i] = trainLabel[i][j];
            }
        }
        
    }

       
    public static void splitData()
    {
        int k = 0 ;
        for(int i = 0 ; i < len ; i++)
        {
            for(int j = 0 ; j < 4 ; j++)
            {
                if(i<800)
                {
                    trainData[i][j] = data[i][j] ;
                }
                else
                {
                    testData[k][j] = data[i][j] ;
                    if(j==3)
                    {
                        k+=1 ;
                    }
                }
                
            } 
        }
        int a = 0 ;
        for(int i = 0 ; i < len ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                if(i<800)
                {
                    trainLabel[i][j] = label[i][j] ;
                }
                else
                {
                    testLabel[a][j] = label[i][j] ;
                    if(j==2)
                    {
                        a+=1 ;
                    }
                }
                
            } 
        }
        
//        for(int i = 0 ; i < 200 ; i++)
//        {
//            for(int j = 0 ; j < 3 ; j++)
//            {
//                System.out.print(testLabel[i][j] + " ") ;
//            } 
//            System.out.println() ;
//        }     
    }
    
    public static void initialize_parameters()
    {
        Random r = new Random();
//        double max = 0.015 ;
//        double min = -0.015 ;
        
        for(int i = 0 ; i < n_h ; i++)
        {
            for(int j = 0 ; j < n_x ; j++)
            {
                w1[i][j] = r.nextDouble();
            } 
        }
        for(int i = 0 ; i < n_h ; i++)
        {
            b1[i][0] = 0;
        }
        for(int i = 0 ; i < n_y ; i++)
        {
            for(int j = 0 ; j < n_h ; j++)
            {
                w2[i][j] = r.nextDouble();
            } 
        }
        for(int i = 0 ; i < n_y ; i++)
        {
            b2[i][0] = 0;  
        }
        
        
//        for(int i = 0 ; i < n_h ; i++)
//        {
//            for(int j = 0 ; j < n_x ; j++)
//            {
//                System.out.print(w1[i][j] + " ") ;
//            } 
//            System.out.println() ;
//        }
        
    }
    
    public static void tanh()
    {
        for (int c = 0; c < 800; c++)
        {
            for (int d = 0; d < 800; d++)
            {
                a1[c][d] = (Math.exp(z1[c][d]) - Math.exp(-z1[c][d])) / (Math.exp(z1[c][d]) + Math.exp(-z1[c][d]));
            }
        }
    }
    
    public static void softmax()
    {       
        for (int c = 0; c < 3; c++)
        {
            for (int d = 0; d < 800; d++)
            {
                a2[c][d] = Math.exp(z2[c][d]) ;
            }
        }
        
        double sum = 0 ;
        
        for (int c = 0; c < 800; c++)
        {
            for (int d = 0; d < 3; d++)
            {
                sum = sum + a2[d][c] ;
            }
           
            for (int i = 0; i < 3; i++)
            {
                a2[i][c] = a2[i][c] / sum ;
            }     
            sum = 0 ;
        }
    }
    
    public static void derivative_tanh()
    {
        for (int c = 0; c < n_h; c++)
        {
            for (int d = 0; d < 800; d++)
            {
                d_a1[c][d] = 1 - (a1[c][d] * a1[c][d]) ;
             }
        }
    }

    public static void forwardPropagation()
    {   
        //z1 = np.dot(w1, x) + b1
        double sum=0;
        for (int c = 0; c < 800; c++)
        {
            for (int d = 0; d < 800; d++)
            {
                for (int k = 0; k < 4; k++)
                {
                    sum = sum + w1[c][k] * trainData_T[k][d];
                }

                z1[c][d] = sum;
                sum = 0;
            }
        }
        for (int c = 0; c < 800; c++)
        {
            for (int d = 0; d < 800; d++)
            {
                z1[c][d] = z1[c][d] + b1[c][0];
            }
        }
        //a1 = tanh(z1)
        tanh() ;
        
        //z2 = np.dot(w2, a1) + b2
        for (int c = 0; c < 3; c++)
        {
            for (int d = 0; d < 800; d++)
            {
                for (int k = 0; k < 800; k++)
                {
                    sum = sum + w2[c][k] * a1[k][d];
                }
                z2[c][d] = sum;
                sum = 0;
            }
        }
        for (int c = 0; c < 3; c++)
        {
            for (int d = 0; d < 800; d++)
            {
                z2[c][d] = z2[c][d] + b2[c][0];
            }
        }
        
        //a2 = softmax(z2)
        softmax() ;
    } 
    
    public static void costFunction()
    {
        //    cost = -(1/m)*np.sum(y*np.log(a2))        
        double sum = 0 ; 
        //    log(a2))
        double log_a2[][] = new double[n_y][800], y_log_a2[][] = new double[3][800] ;
        for (int c = 0; c < 3; c++)
        {
            for (int d = 0; d < 800; d++)
            {
                log_a2[c][d] = Math.log(a2[c][d]) ;
            }
        }      
        //    y*log(a2)
        for (int c = 0; c < 3; c++)
        {
            for (int d = 0; d < 800; d++)
            {
                y_log_a2[c][d] = y[c][d]*log_a2[c][d] ;               
            }
        }
        //    sum(y*log(a2))
        for (int c = 0; c < 3; c++)
        {
            for (int d = 0; d < 800; d++)
            {
                sum = sum + y_log_a2[c][d] ;               
            }
        }
        
        cost = -(1/800.0)*sum ;    
    }
    
    public static void backwardPropagation()
    {
        double dz2[][] = new double[3][800] , dz1[][] = new double[800][800] , a1_T[][] = new double[800][n_h];
        //dz2 = (a2 - y)
        for (int c = 0; c < 3; c++)
        {
            for (int d = 0; d < 800; d++)
            {
                dz2[c][d] = a2[c][d] - y[c][d] ;               
            }
        }
        //dw2 = (1/m)*np.dot(dz2, a1.T)
        for (int i = 0; i < 800; ++i)
        {
            for (int j = 0; j < n_h; ++j)
            {
                a1_T[j][i] = a1[i][j];
            }
        }
        double sum=0;
        for (int c = 0; c < 3; c++)
        {
            for (int d = 0; d < 800; d++)
            {
                for (int k = 0; k < 800; k++)
                {
                    sum = sum + dz2[c][k] * a1_T[k][d];
                }
                dz2[c][d] = sum;
                dz2[c][d] = dz2[c][d]/800.0 ;
                sum = 0;
            }
        }
        //db2 = (1/m)*np.sum(dz2, axis = 1)
        for (int c = 0; c < 3; c++)
        {           
            for (int d = 0; d < 800; d++)
            {
                sum = sum + dz2[c][d] ;
            }
            db2[c][0] = sum ;
            sum = 0 ;
        }
        
        //dz1 = (1/m)*np.dot(w2.T, dz2)*derivative_tanh(a1)
        derivative_tanh() ;
        double w2_T[][] = new double[n_h][n_y] ;
        
        //transpose of w2 
        for (int i = 0; i < n_y; ++i)
        {
            for (int j = 0; j < n_h; ++j)
            {
                w2_T[j][i] = w2[i][j];
            }
        }
        
        for (int c = 0; c < 800; c++)
        {
            for (int d = 0; d < 800; d++)
            {
                for (int k = 0; k < 3; k++)
                {
                    sum = sum + w2_T[c][k] * dz2[k][d];
                }
                dz1[c][d] = sum;
                dz1[c][d] = (1/800.0)*dz1[c][d]*a1_T[c][d] ;
                sum = 0;
            }
        }
        //dw1 = (1/m)*np.dot(dz1, x.T)
        for (int c = 0; c < 800; c++)
        {
            for (int d = 0; d < 4; d++)
            {
                for (int k = 0; k < 800; k++)
                {
                    sum = sum + dz1[c][k] * trainData[k][d];
                }
                dw1[c][d] = sum;
                dw1[c][d] = (1/800.0)*dw1[c][d] ;
                sum = 0;
            }
        }
        //db1 = (1/m)*np.sum(dz1, axis = 1)
        for (int c = 0; c < 800; c++)
        {           
            for (int d = 0; d < 800; d++)
            {
                sum = sum + dz1[c][d] ;
            }
            db1[c][0] = sum ;
            sum = 0 ;
        }       
    }
    
    public static void updateParameters()
    {
        //    w1 = w1 - learning_rate*dw1
        for (int c = 0; c < 800; c++)
        {           
            for (int d = 0; d < 4; d++)
            {
                w1[c][d] = w1[c][d] - (learningRate*dw1[c][d]) ;
            }
        }
        //    b1 = b1 - learning_rate*db1
        for (int c = 0; c < 800; c++)
        {           
            for (int d = 0; d < 1; d++)
            {
                b1[c][d] = b1[c][d] - (learningRate*db1[c][d]) ;
            }
        }
        //    w2 = w2 - learning_rate*dw2
        for (int c = 0; c < 3; c++)
        {           
            for (int d = 0; d < 800; d++)
            {
                w2[c][d] = w2[c][d] - (learningRate*dw2[c][d]) ;
            }
        }
        //    b2 = b2 - learning_rate*db2
        for (int c = 0; c < 3; c++)
        {           
            for (int d = 0; d < 1; d++)
            {
                b2[c][d] = b2[c][d] - (learningRate*db2[c][d]) ;
            }
        }
    }
    
    public static void accuracy()
    {
        int train_matrix[] = new int[800] ;
        forwardPropagation() ;
        for (int c = 0; c < 800; c++)
        {           
            if(a2[0][c] > a2[1][c] & a2[0][c] > a2[2][c])
            {
                train_matrix[c] = 0 ;
            }
            else if(a2[1][c] > a2[0][c] & a2[1][c] > a2[2][c])
            {
                train_matrix[c] = 1 ;
            }
            else if(a2[2][c] > a2[0][c] & a2[2][c] > a2[1][c])
            {
                train_matrix[c] = 2 ;
            }
            else if(a2[0][c] > a2[1][c] & a2[1][c] == a2[2][c])
            {
                train_matrix[c] = 0 ;
            }
            else if(a2[1][c] > a2[0][c] & a2[0][c] == a2[2][c])
            {
                train_matrix[c] = 1 ;
            }
            else if(a2[2][c] > a2[1][c] & a2[1][c] == a2[0][c])
            {
                train_matrix[c] = 2 ;
            }
            else if(a2[1][c] == a2[2][c] & a2[1][c] > a2[2][c])
            {
                train_matrix[c] = 0 ;
            }
            else if(a2[0][c] == a2[2][c] & a2[1][c] > a2[2][c])
            {
                train_matrix[c] = 1 ;
            }
            else if(a2[1][c] == a2[2][c] & a2[2][c] > a2[0][c])
            {
                train_matrix[c] = 2 ;
            }
        }
        int count = 0 ;
        for (int c = 0; c < 800; c++)
        {
            if(train_matrix[c]==labels[c])
            {
                count+=1 ;
            }
        }
        System.out.println("Training accuracy is " + (count/800.0)*100) ;
        
        
    }
       
    public static void model()
    {
        int temp = 0 ;
        splitData() ;
        getTranspose() ;
        initialize_parameters() ;
        
        while(temp<iterations)
        {
            forwardPropagation() ;
            costFunction() ;
            backwardPropagation() ;
            updateParameters() ;
            
            if(temp%(iterations/10) == 0)
            {
                System.out.println("Cost after " +temp+ " iterations : " + cost) ;
            }
            
            temp+=1 ;
        }       
    }
    
    public static void main(String[] args)
    {
        Classify obj = new Classify() ;
//        printData() ;
        
//        splitData() ;  
//        getTranspose() ;
//        initialize_parameters() ;
//        forwardPropagation() ;
//        costFunction() ;
//        backwardPropagation() ;
//        updateParameters() ;
//        System.out.print(cost) ;

        model() ;
        accuracy() ;
//
//        for(int i = 0 ; i < 3 ; i++)
//        {
//            for(int j = 0 ; j < 1 ; j++)
//            {
//                System.out.print(b2[i][j] + " ") ;
//            } 
//            System.out.println() ;
//        }  
//        for(int j = 0 ; j < 800 ; j++)
//        {
//            System.out.print(labels[j] + " ") ;
//        } 
    }    
}

