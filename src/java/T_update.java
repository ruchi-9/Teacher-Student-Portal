/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author Rajendra
 */
public class T_update extends HttpServlet {
static String a;
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          String s1=request.getParameter("phone_no");
             String s2,s3,s4;
             s2 = request.getParameter("name");
             s3 = request.getParameter("E_mail");
             s4 = request.getParameter("Password");
             System.out.println(s1);
         System.out.println(s2);
           System.out.println(s3);
             System.out.println(s4);
             try {
             database db=new database();
              Connection con=db.getCon();
                        PreparedStatement ps = con.prepareStatement("update teacher_login set name = ? , password = ? , mail_id = ? where contact_no = ?");
                              //id
                         ps.setString(1,s2);  //s_name
                         ps.setString(2,s3);      //password
                         ps.setString(3,s4);
                         ps.setString(4,s1); //phone no
			 
			 ps.executeUpdate();
                         a="update sucessfully";      
               }
         catch(SQLException ex)
         {
                System.out.println("exception occur");
                a="Update unsucessfully";
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
        student+="<div class=\"container\" id=\"sreg\" style=\"margin-top: 1px;\">";
            student+="<div class=\"container\" id=\"maindiv\">";
                student+="<h2> Teacher Record Updation </h2>";
            student+="<form action=\"T_search\" method=\"post\">";
                student+="<table class=\"table table-hover\"  >";
                    
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
        doGet (request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
