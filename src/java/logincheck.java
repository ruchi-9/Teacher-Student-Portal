import java.time.Year;
import java.util.Calendar;
import java.util.Scanner;
public class logincheck
{
   public static int batch;
   static int flag =0,mca=0;
   public static String s_id ,branch;
   public static int year,branch_chng,sem,bs;      
   logincheck()
   {}   
   void check_id(String id)
      {
           String b,c1,d1,d2,c2;
           c1 ="ebk";    c2="EBK";
           d1="cbk";     d2="CBK";
           b = id.substring(2,5);
           if(b.equals(c1)||b.equals(c2))
              {
               flag=1;
              }
            else if(b.equals(d1)||b.equals(d2))
                 {
                    flag=1; 
                    mca=1;
                 }              
            else 
              { 
                System.out.println(" you enter a wrong id  ");
                System.out.println("  "+b);
              }
      }
	  
  void find_batch(int year)
     {
       int mnth;
       Calendar now = Calendar.getInstance();
       mnth =now.get(Calendar.MONTH);
       mnth=mnth+1;
       if(mnth>6)
            {
              bs=0; 
            }
      else
            {
              bs=1;
            }
     }
	 
  void find_semister()
    {
      int mnth;
      Calendar now = Calendar.getInstance();
      mnth =now.get(Calendar.MONTH);
      mnth=mnth+1;
      if(mnth>6)
           {
             sem=2*(year-1)+1;
           }
      if(mnth>0&&mnth<7)
           {
             sem=2*(year-1)+2;
           }
    }
	
  void find_year(int year2,String ipt)
    {    
      String b;
      int year3,s_yr;
      year3 = year2-2000;
      String s_year = ipt.substring(0,2);        
      s_yr = Integer.parseInt(s_year);
      if(mca==1)
         {
           year = year3+1-s_yr-bs;
         }
      else 
        {
           year = year3 + 1- s_yr+branch_chng-bs;        
        }
    }
	
  void find_branch(String id)
   {
      String b ;
      b = id.substring(5,7);
      String cs1,ec1,mca1,ee1,ex1,it1,cs2,ec2,mca2,ee2,ex2,it2;
      cs1="cs"; ec1 = "ec"; mca1="xx";ee1="ee"; ex1 ="ex"; it1 ="it";
      cs2="CS"; ec2 = "EC"; mca2="XX";ee2="EE"; ex2 ="EX"; it2 ="IT";	  
      if((b.equals(cs1))||(b.equals(cs2)))
         {
           branch="computer science";
         }
      else if((b.equals(ec1))||(b.equals(ec2)))
         {
           branch="electrical communication";
         }
      else if((b.equals(ee1))||(b.equals(ee2)))
         {
           branch="electrical engg";
         }
      else if((b.equals(mca1))||(b.equals(mca2)))
         {
           branch="mca";
         }
      else if((b.equals(ex1))||(b.equals(ex2)))
         {
           branch="electrical and electronic engg";
         }
      else if((b.equals(it1))||(b.equals(it2)))
         {
           branch="information technology";
         }    
    }
  
  void branch_change(String id)
    {
      int a;
      String b;
      b= id.substring(7);
      a = Integer.parseInt(b);
      if(mca==0)
        {
          if(a>200)
            {
               branch_chng=1;
            }
        }
    }
	
  logincheck(String id)
    {   
      int year1 = Year.now().getValue();
      logincheck lg = new logincheck();
      s_id=id;
	  lg.check_id(s_id);
	  
      if(flag==1)
        {
          lg.find_batch(year);
          lg.branch_change(s_id);
          lg.find_year(year1,s_id);
          lg.find_branch(s_id);
	      lg.find_semister();                 
        }
    }
}

