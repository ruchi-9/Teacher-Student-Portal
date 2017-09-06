/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import static T_update.a;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rajendra
 */
public class subject_select extends HttpServlet {
static String subject;
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             
          String code = request.getParameter("code");
          
    try{
          
              database db=new database();
              Connection con=db.getCon();
              System.out.println("in course search");
              PreparedStatement ps1 = con.prepareStatement("select subject from course where code = ?");
              ps1.setString(1,code);
              ResultSet rs = ps1.executeQuery();
              if(rs.next())
              {
              subject = rs.getString("subject");
              }
       }
    catch(Exception s)
     {
             
     }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
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
