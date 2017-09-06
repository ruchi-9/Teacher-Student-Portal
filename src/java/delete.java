/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Rajendra
 */
public class delete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
             response.setContentType("APPLICATION/OCTET-STREAM");
             String path = request.getParameter("url");
             System.out.println(path);
             PrintWriter out = response.getWriter();
             int len=path.length();
                System.out.println(len);
                String a= path.substring(9,len);
                System.out.println(a);
                String filePath ="C:\\Users\\Beniwal\\Documents\\NetBeansProjects\\bkbietproject\\web"+path.substring(2,len);
        System.out.println("file path is ======"+filePath);
                try
          { 
              database db=new database();
              Connection con=db.getCon();
              PreparedStatement ps = con.prepareStatement("delete from files where name=?");
              ps.setString(1, a);                              
              int rs1 = ps.executeUpdate();
              System.out.println("delete from database"); 
              File file=new File(filePath);//   in place of filepath write the path of file
              if( file.delete())
              {
                  System.out.println(file.getName()+"is deleted from folder");
               }
               else
		{
		    System.out.println("not  deleted from folder");
                }
              response.sendRedirect("subjectdetails");
               }
               catch (Exception ex) 
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
