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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author Rajendra
 */
public class T_details extends HttpServlet {
 String s2,s3,s4,s5,a;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            String s1=request.getParameter("ID");
        try {
              database db=new database();
              Connection con=db.getCon();
              PreparedStatement ps1 = con.prepareStatement("select * from teacher_login where contact_no = ?");
              ps1.setString(1,s1);
              
              System.out.println(s1);
              ResultSet rs = ps1.executeQuery();
              System.out.println(rs);
			if( rs.next())
                        {
                              s2= rs.getString("name");
                              s3= rs.getString("password");
                              s4= rs.getString("contact_no");
                              s5= rs.getString("mail_id");
                              System.out.println(s2);
                               System.out.println(s3);
                                System.out.println(s4);
                                 System.out.println(s5);
                                 a="Record Found";
                        }     
                    else {
                            System.out.println("wrong id entered");
                   a="Record Does Not Exist";												   }
         		 }
        
catch(Exception ex1)
{
	System.out.println(ex1);

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
            student+="<div class=\"container\" id=\"sreg\" style=\"margin-top: 1px; padding: 0px;text-align: center;  border-radius: 15px;\">\n";
            student+="<div class=\"container\" style=\"padding: 0px; text-align: center;max-width: 500px\">\n";
            student+="<h2> Teacher Reacord Delete </h2>\n";
            student+="<form action=\"T_delete\" method=\"post\">\n";
            student+="<table class=\"table table-hover\"  >\n";
            student+="<thead class=\"thead-inverse\">\n";
                 student+="<tr>\n";
                 student+="<th>\n";
                 student+="</th>\n";
                 student+="<th>\n";
                 student+="</th>\n";
                 student+="</tr>\n";
                 student+="</thead>\n";
                    student+="<tbody>\n";
                    student+="<tr> \n";
                    student+="<th scope=\"row\">Name : </th>\n";
                    student+="<td><input type=\"text\" value=\""+s2+"\" name=\"name\" /> </td>\n";
                    student+="</tr>\n";
                            student+="<tr> \n";
                            student+="<th scope=\"row\">phone: </th>\n";
                            student+="<td><input type=\"text\" name=\"phone_no\"  value=\""+s4+"\"  /> </td>\n";
                            student+="</tr>\n";
                            student+="<tr> \n";
                            student+="<th scope=\"row\">E_mail : </th>\n";
                            student+="<td><input type=\"text\" value=\""+s5+"\" pattern=\"[A-Za-z0-9._%+-]{2,}@[a-zA-Z]{1,}([.]{1}[a-zA-Z]{2,}|[.]{1}‌​[a-zA-Z]{2,}[.]{1}[a‌​-zA-Z]{2,})\" required  name=\"E_mail\" /> </td>\n";
                          student+="</tr>\n";
                          student+="<tr> \n";
                          student+="<th scope=\"row\">Password: </th>\n";
                          student+="<td><input type=\"text\" name=\"Password\"  value=\""+s3+"\"  /> </td>\n";
                          student+="</tr>\n";
                    student+="</tbody>\n";
                    student+="</table>\n";
                    student+="<input type=\"submit\" value=\"Delete\" />\n";
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
