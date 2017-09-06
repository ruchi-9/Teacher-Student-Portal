import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class notify extends HttpServlet {
   static String email[]=new String[150];
 static int i=0;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code=request.getParameter("code");
        String b = code.substring(0,1);
              String c=code.substring(1,3);
              String d=null;
    int sem;
    
    String sid=null;
         try{ System.out.println("code enter is "+ b);
         System.out.println("branch is  "+ c);
                database db=new database();
              Connection con=db.getCon();
                PreparedStatement ps1 = con.prepareStatement("select student_id from stuent_login");           //for select an id 
		ResultSet rs = ps1.executeQuery();
                 while(rs.next())
		{sid= rs.getString("student_id");
                d=sid.substring(5,7);
                 System.out.println("branch select is "+d);
                    logincheck lc = new logincheck(sid);
                    System.out.println("id is "+ sid);
                    // System.out.println("222222222222");
                  sem = logincheck.sem;	
                  
		  String z=String.valueOf(sem); 
                  System.out.println("sem   is "+ z);
                     if(z.equalsIgnoreCase(b)&&(d.equalsIgnoreCase(c)))
                     {
                         PreparedStatement ps = con.prepareStatement("select Email from stuent_login where student_id=?");
                         ps.setString(1,sid); 
                          System.out.println("in if condition");
		ResultSet rs1 = ps.executeQuery();
                if(rs1.next())
                {email[i]= rs1.getString("Email");
                 System.out.println("for i= "+i);
                       System.out.println("email is   "+email[i]);
                    
                     i++;
                }
                     }
                 
                }
                 
                 }
         catch(Exception ex)
           {
                System.out.println("in student query exception");
                  System.out.println(ex);
           }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
