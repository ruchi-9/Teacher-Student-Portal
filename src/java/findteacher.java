/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Beniwal
 */
public class findteacher 
{
    
static String s2;
	findteacher(String code)
	{
		try 
                {
                database db=new database();
                Connection con=db.getCon();
                PreparedStatement ps1 = con.prepareStatement("select id from t_rest where code = ?");
                ps1.setString(1,code);
		System.out.println(code);
                        ResultSet rs = ps1.executeQuery();
                        System.out.println(rs);
			if(rs.next())
                         {
                           s2=rs.getString("id");
		             System.out.println(s2);
		         }
		}
                catch(Exception ex1)
                  {
	            System.out.println( "exception into find teacher"+ex1);
                   }
	}
       public  String show()
        {
        return s2;
        }
    
}
