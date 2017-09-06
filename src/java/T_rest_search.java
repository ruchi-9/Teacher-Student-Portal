import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class T_rest_search extends HttpServlet {
static String s2,s3,s4,s5,a;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String s1=request.getParameter("ID");
        try {
              database db=new database();
              Connection con=db.getCon();
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
                         PreparedStatement ps = con.prepareStatement("select code from t_rest where contact_no = ?");
              ps.setString(1,s1);
              
              System.out.println(s1);
              ResultSet rs1 = ps.executeQuery();
         		 }
        
catch(Exception ex1)
{
	System.out.println(ex1);

}						   
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       doGet(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
