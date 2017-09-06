import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class tsubupdates extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String id=request.getParameter("id");
   
           String code=request.getParameter("code");
           
          try{
                 database db=new database();
              Connection con=db.getCon();
                         PreparedStatement ps = con.prepareStatement("insert into t_rest values(?,?)");
	       ps.setString(1,id);
               ps.setString(2,code);
               ps.executeUpdate();
               System.out.println("222222222222222222");
              
          }
            catch(SQLException | HeadlessException ex)
			   {
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
