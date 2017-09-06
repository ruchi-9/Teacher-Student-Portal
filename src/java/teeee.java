/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Beniwal
 */
public class teeee {
    static int i,j,k,l,m;
    static boolean year1,year2,year3,year4;
    static String yr1[]= new String[10];
    static String yr2[]= new String[10];
    static String yr3[]= new String[10];
    static String yr4[]= new String[10];
    public void data()
            {
                year1=false;
                year2=false;
                year3=false;
                year4=false;
             j=0;
             k=0;
             l=0;
             m=0;
             String c[]={"6cs8a","7cs6a"};
              // findout year by code of subject
             for(i=0;i<c.length;i++)
             {
                     int sem= Integer.parseInt(c[i].substring(0,1));
                         
                     switch (sem) {
                     case 2:
                     case 1:
                        year1=true;
                         yr1[j]=c[i];
                         j++;
                         System.out.println("step1");
                         break;
                     case 3:
                     case 4:
                         year2=true;
                         yr2[k]=c[i];
                         k++;
                         System.out.println("step2");
                         break;
                     case 5:
                     case 6:
                         year3=true;
                         yr3[l]=c[i];
                         System.out.println(yr3[l]);
                         l++;
                         System.out.println("step3");
                          
                         break;
                     case 7:
                     case 8:
                         year4=true;
                       yr4[m]=c[i];
                         
                         System.out.println("step4");
                         System.out.println(yr4[m]);
                         m++;
                         break;
                     default:
                         break;
                 }
            }}
    public static void main(String a[])
    {
    teeee e =new teeee();
    e.data();
    System.out.println(year1);
    
    System.out.println(year2);
    
    System.out.println(year3);
    
    System.out.println(year4);
    }
    
}
