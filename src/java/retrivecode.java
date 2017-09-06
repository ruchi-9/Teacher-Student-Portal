import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class retrivecode
{
        String code[]=new String[10];
	public String[] code(String id)
	{ int i=0;
		  try {
              database db=new database();
              Connection con=db.getCon();
              PreparedStatement ps1 = con.prepareStatement("select * from t_rest where id = ?");
              ps1.setString(1,id);
			  System.out.println(id);
              ResultSet rs = ps1.executeQuery();
              System.out.println(rs);
              i=0;
			while( rs.next())
                        {
		  code[i]= rs.getString("code");
		 System.out.println(code[i]);
		 i++;
		 }
		  }
                              catch(Exception ex1)
{
	System.out.println(ex1);

}
return code;
	}
	
 
 }