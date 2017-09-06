import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
class subjectname
{
String s2;
database db=new database();
Connection con=db.getCon();
PreparedStatement ps1=null;
subjectname(){}
	subjectname(String code)
	{
		try 
                {
                ps1= con.prepareStatement("select subject from code where code = ?");
                ps1.setString(1,code);
		System.out.println(code);
                   ResultSet rs = ps1.executeQuery();
                 //  System.out.println(rs);
		if( rs.next())
                {
		         s2= rs.getString("subject");
		         System.out.println(s2);
		 }
		 }
                              catch(Exception ex1)
{
	System.out.println(ex1);

}
	}
        public String display(String a)
        {
            try 
                {
                ps1= con.prepareStatement("select subject from code where code = ?");
                ps1.setString(1,a);
                   ResultSet rs = ps1.executeQuery();
                   System.out.println(rs);
		if( rs.next())
                {
		         s2= rs.getString("subject");
		         //System.out.println(s2);
		 }
		 }
                              catch(Exception ex11){}

        return s2;
        }
       public  String show()
        {
        return s2;
        }
 }