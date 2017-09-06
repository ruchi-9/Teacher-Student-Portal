/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.HeadlessException;
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

/**
 *
 * @author Rajendra
 */
public class student_update extends HttpServlet {
static String a;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
              String name=request.getParameter("name");
              String password=request.getParameter("password");
              String ID=request.getParameter("ID");
              String phone_no=request.getParameter("phone_no");
              String E_mail=request.getParameter("E_mail");
              String father_name=request.getParameter("father_name");
          
               database db=new database();
               Connection con=db.getCon();
               try
               {
                         PreparedStatement ps = con.prepareStatement("update stuent_login set password = ? , s_name = ? , phone_no = ? , father_name = ? , Email = ? where student_id = ?");
                              //id
                         ps.setString(1,password);      //password
                         ps.setString(2,name);  //s_name
                         ps.setString(3, phone_no); //phone no
			 ps.setString(4,father_name); //father name
			 ps.setString(5,E_mail);
                         ps.setString(6, ID);
			 ps.executeUpdate();
                         a="update sucessfully";
               }
                  catch(   SQLException | HeadlessException ex1)
                         {
	                      System.out.println("sql exception");
						    System.out.println("inner catch");
			 a="Unable to update ";             
                         }
               
               // show result after updation 
               
                   PrintWriter writer = response.getWriter();
                   savedinfo si =new savedinfo();
                   String header = si.headerpart();
                   String head = si.headpart();
                   String menu = si.menupart();
                   String student="";
                   String op ="<html>"+head+ "<body onload=\"startTime()\" style=\"\">"+header+menu;
                   student+="<div class=\"container\"  style=\"margin-top: -20px; padding:0px; color: red;text-align: center;  border-radius: 15px;\"><p>";
                   student+=a;
                   student+="</p></div>";
                      student+=" <div class=\"container\" id=\"sreg\" style=\"margin-top:0.5px; \">";
                       student+="<div class=\"container\" id=\"maindiv\">";
                        student+="<h2> Student Record Updation </h2>";
                       student+="<form action=\"s_record\" method=\"post\">";
                        student+="<table class=\"table table-hover\"  >";
                        
                    student+="<tbody>";
                        student+="  <tr> ";
                            student+="<th scope=\"row\">Enter Student Id: </th>";
                            student+="<td><input type=\"text\"  name=\"ID\" required=\"enter Student id\" /> </td>";
                         student+=" </tr>";
                    student+="</tbody>";
                student+="</table>";
                student+="<input type=\"submit\" value=\"Search\" />";
            student+="</form>";
                
            student+="</div>";
        student+="</div>";
        op+=student+"</body></html>";
        writer.println(op);
               
			   }
                     
        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       doGet(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
