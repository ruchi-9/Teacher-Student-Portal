import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
public class teacher_record 
{
static String s1,s2,s3,s4,s5,a;
 static String c[] = new String[20]; 
 static  String yrsub1[] = new String[10];
 static  String yrsub2[] = new String[10];
 static  String yrsub3[] = new String[10];
 static  String yrsub4[] = new String[10];
 static  String yr1[] = new String[10];
 static  String yr2[] = new String[10];
  static  String yr3[] = new String[10];
  static  String yr4[] = new String[10];
 static boolean error;
 boolean year1,year2,year3,year4;
 int i,j,k,l,m;

public String name()
{

    return s2;
}
public String[] yrr1()
{

    return yr1;
}
public String[] yrr2()
{

    return yr2;
}
public String[] yrr3()
{

    return yr3;
}
public String[] yrr4()
{

    return yr4;
}
public String[] yrsub1()
{

    return yrsub1;
}
public String[] yrsub2()
{

    return yrsub2;
}
public String[] yrsub3()
{

    return yrsub3;
}
public String[] yrsub4()
{

    return yrsub4;
}

public boolean year1status()
{
return year1;
}
public boolean year2status()
{
return year2;
}
public boolean year3status()
{
return year3;
}
public boolean year4status()
{
return year4;
}
//retriving data from subject code            
public void data()
            {
             j=0;
             k=0;
             l=0;
             m=0;
              // findout year by code of subject
             for(i=0;c[i]!=null;i++)
             {
                     int sem= Integer.parseInt(c[i].substring(0,1));
                         
                 switch (sem) {
                     case 2:
                     case 1:
                         year1=true;
                        yr1[j]=c[i];
                         j++;
                         System.out.println(yr1[j-1]);
                         break;
                     case 3:
                     case 4:
                         year2=true;
                         yr2[k]=c[i];
                         k++;
                         System.out.println(yr2[k-1]);
                         break;
                     case 5:
                     case 6:
                         year3=true;
                         yr3[l]=c[i];
                         l++;
                         System.out.println(yr3[l-1]);
                         break;
                     case 7:
                     case 8:
                         year4=true;
                         yr4[m]=c[i];
                         m++;
                         System.out.println(yr4[m-1]);
                         break;
                     default:
                         break;
                 }
            }}
//sorting of subject into perticuler year 


             teacher_record(String a)
        {
            s1=a;
        try {
              database db=new database();
              Connection con=db.getCon();
              i=0;
              PreparedStatement ps1 = con.prepareStatement("select name,contact_no,mail_id from teacher_login where contact_no = ?");
              ps1.setString(1,s1);
              System.out.println(s1);
              ResultSet rs = ps1.executeQuery();
              System.out.println(rs);
			if( rs.next())
                        {
                              s2= rs.getString("name");
                              s3= rs.getString("contact_no");
                              s4= rs.getString("mail_id");
                              System.out.println(s2);
                              System.out.println(s3);
                              System.out.println(s4);
                               a="Record Found";
                        }     
                    else {
                            System.out.println("wrong id entered");
                            a="Record does not Exist ";
                         }
                         PreparedStatement ps = con.prepareStatement("select * from t_rest where id = ?");
                         ps.setString(1,s1);
                         System.out.println(s1);
                         ResultSet rs1 = ps.executeQuery();
                         System.out.println("result set into teacher record "+rs1);
                         while(rs1.next())
                         {
                             ResultSetMetaData rsmd = rs1.getMetaData();
                             String name = rsmd.getColumnName(2);
                             
                             c[i]=rs1.getString(name);
                             System.out.println("code of subject is "+c[i]);
                             System.out.println("sem of subject is "+Integer.parseInt(c[i].substring(0,1)));
                          i++;
                         }
         }
        
catch(Exception ex1)
{
    System.out.println("some thing worng");
	System.out.println(ex1);
        error=true;
}	}					   
        
}
