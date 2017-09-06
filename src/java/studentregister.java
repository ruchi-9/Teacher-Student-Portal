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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Beniwal
 */
public class studentregister extends HttpServlet {
    static String ID,pass;
    static String username;
    static int flag;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      String output="";
        //to get id pass from cookie of browseer
                   ID=request.getParameter("name");
                   pass=request.getParameter("pass");
        int i,j; 
             Cookie cookie = null;
	          Cookie[] cookies = null;
                  cookies = request.getCookies();
                    for ( i = 0; i < cookies.length; i++)
                    {
                          cookie = cookies[i];
                      if(cookie.getName().equals("name"))
                    {    if(ID==null)
                         ID=cookie.getValue();
                         //s1=ID;
                         System.out.println("Id = "+ID);
                    }
                      if(cookie.getName().equals("pass"))
                      {   if(pass==null)
                          pass=cookie.getValue();
                    }}
                   PrintWriter writer = response.getWriter();
                   database db=new database();
                   Connection con=db.getCon();
                 try
                 {
                     System.out.println("error 1");
                       PreparedStatement ps = con.prepareStatement("SELECT ID,password,username FROM admin_login where ID = ?");
                       ps.setString(1,ID);
                       System.out.println("before the exection ");
                       ResultSet rs = ps.executeQuery();
                       System.out.println("after the exection "+rs);
                        System.out.println("error  2");
                        if(rs.next())
		     {   String password;
                         password=rs.getString("password");
                        System.out.println("error 3");
                         System.out.println("password is"+password);
                         username=rs.getString("username");
                         Cookie cookie2 =new Cookie("username",username);
                         response.addCookie(cookie2);
                         System.out.println("password is ="+password +"matching password is ="+pass);
                         if(password.equals(pass))
                            {
		          	flag=1;
                            }
		    }
                  }catch(Exception e)
                  {
                  System.out.println(e);
                  }
                   if(flag==1)
                   {
                       savedinfo si = new savedinfo();
                    output+="<!DOCTYPE html>\n" +
"<html>\n";
                    output+=si.headpart()+"<body onload=\"startTime()\" style=\"\">\n" +si.headerpart() +si.menupart();
output+="    </body>\n" +
"</html>\n" +
"";
output+="<div class=\"container\" id=\"sreg\" style=\"margin-top: -20px; padding: 0px;text-align: center; border-radius: 15px;\">\n" +
"            <div class=\"container\" id=\"maindiv\">\n" +
"                <h2> New Student Registration </h2>\n" +
"            <form action=\"student_register\" method=\"post\">\n" +
"                <table class=\"table table-hover\"  >\n" +
"                    <tbody>\n" +
"                          <tr> \n" +
"                            <th scope=\"row\">Name : </th>\n" +
"                            <td><input type=\"text\" name=\"name\" /> </td>\n" +
"                          </tr>\n" +
"                          <tr> \n" +
"                            <th scope=\"row\">password : </th>\n" +
"                            <td><input type=\"password\" name=\"password\" id=\"password\"/> </td>\n" +
"                          </tr>\n" +
"                          <tr> \n" +
"                            <th scope=\"row\">confirm password : </th>\n" +
"                            <td><input type=\"password\" name=\"confirm_pass\" id=\"passwordconfirm\" oninput=\"check(this)\"/> </td>\n" +
"                          </tr>\n" +
"                          <script language='javascript' type='text/javascript'>\n" +
"                                function check(input) {\n" +
"                                if (input.value != document.getElementById('password').value) {\n" +
"                                input.setCustomValidity('Password Must be Matching.');\n" +
"                                   } else {\n" +
"                                 // input is valid -- reset the error message\n" +
"                                input.setCustomValidity('');\n" +
"                                }\n" +
"                                }\n" +
"                         </script>\n" +
"                          <tr> \n" +
"                            <th scope=\"row\">Id : </th>\n" +
"                            <td><input type=\"text\" name=\"ID\" pattern=\"[1]+[0-5]+(EBK|ebk)+(cs|CS|ec|EC|ee|EE|ex|EX)+[0-9]{1,3}\" /> </td>\n" +
"                          </tr>\n" +
"                          <tr> \n" +
"                            <th scope=\"row\">phone: </th>\n" +
"                            <td><input type=\"text\" name=\"phone_no\" pattern=\"[7|8|9][0-9]{9}\" title=\"enter correct phone number \"/> </td>\n" +
"                          </tr>\n" +
"                          <tr> \n" +
"                            <th scope=\"row\">E_mail : </th>\n" +
"                            <td><input type=\"email\" required  name=\"E_mail\" title=\"enter correct email id \" /> </td>\n" +
"                          </tr>\n" +
"                          <tr> \n" +
"                            <th scope=\"row\">father name : </th>\n" +
"                            <td><input type=\"text\" name=\"father_name\" /> </td>\n" +
"                          </tr>\n" +
"                    </tbody>\n" +
"                </table>\n" +
"                <input type=\"submit\" value=\"register\" />\n" +
"            </form>\n" +
"                \n" +
"            </div>\n" +
"        </div> ";
  writer.println(output);
                   } 
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
