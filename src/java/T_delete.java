



import java.awt.HeadlessException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

public class T_delete extends HttpServlet {

static String a;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String s1=request.getParameter("phone_no");
        System.out.println(s1);
        try {
             database db=new database();
              Connection con=db.getCon();
                        PreparedStatement ps = con.prepareStatement("delete from teacher_login where contact_no=?");
                        ps.setString(1,s1);                              
                        int rs1 = ps.executeUpdate();
                        System.out.println("data is deleted from dtabase");
                        a="data deleted Sucessfully";
            }
               catch (HeadlessException ex) 
                  {
                      a="Error in deletion of data ";
                    System.out.println(ex);
                  } catch (SQLException ex) {
            Logger.getLogger(T_delete.class.getName()).log(Level.SEVERE, null, ex);
        a="Error in deletion of data ";
                  }
        
        PrintWriter writer = response.getWriter();
        savedinfo si =new savedinfo();
        String header = si.headerpart();
        String head = si.headpart();
        String menu = si.menupart();
        String student="";
        String op ="<html>"+head+ "<body onload=\"startTime()\" style=\"\">"+header+menu;
        // to show details into student info 
        student+="<div class=\"container\"  style=\"margin-top: -20px; padding:0px; color: red;text-align: center;  border-radius: 15px;\"><p>";
           student+=a;
           student+="</p></div>";
        student+="<div class=\"container\" id=\"sreg\" style=\"margin-top: 2px; padding: 0px;text-align: center;  border-radius: 15px;\">";
            student+="<div class=\"container\" id=\"maindiv\">";
                student+="<h2> Teacher Record Deletion </h2>";
            student+="<form action=\"T_details\" method=\"post\">";
                student+="<table class=\"table table-hover\"  >";
                    student+="<thead class=\"thead-inverse\">";
                        student+="<tr>";
                            student+="<th>";
                            student+="</th>";
                            student+="<th>";
                            student+="</th>";
                        student+="</tr>";
                    student+="</thead>";
                    student+="<tbody>";
                          student+="<tr> ";
                            student+="<th scope=\"row\">Teacher Phone no: </th>";
                            student+="<td><input type=\"text\" name=\"ID\" required=\"enter phone number\" /> </td>";
                          student+="</tr>";
                    student+="</tbody>";
                student+="</table>";
                student+="<input type=\"submit\" value=\"Search\" />";
            student+="</form>\n";
                
            student+="</div>\n";
            student+="</div>\n";
            op+=student+"</body></html>";
        writer.println(op);
        
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
