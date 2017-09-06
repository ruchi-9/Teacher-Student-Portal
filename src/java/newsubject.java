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
public class newsubject extends HttpServlet {
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
                    output+=si.headpart()+"<body onload=\"startTime()\" style=\"\">\n";
                    output+=" <script type=\"text/javascript\">\n" +
"    $(function () {\n" +
"        $(\"#opchk\").click(function () {\n" +
"            if ($(this).is(\":checked\")) {\n" +
"                $(\"#opdiv\").show();\n" +
"            } else {\n" +
"                $(\"#opdiv\").hide();\n" +
"            }\n" +
"        });\n" +
"    });\n" +
"</script>";
                    output+=si.headerpart() +si.menupart();

output+="<div class=\"container\" id=\"sreg\" style=\"margin-top: -20px;  padding: 0px;text-align: center; border-radius: 15px;\">\n" +
"            <div class=\"container\" style=\" max-width: 800px\">\n" +
"                <h2 id=\"pp\" > Add New Course </h2>\n" +
"                <div style=\"all:unset\"><br/>\n" +
"                    <form  class=\"pure-form\" action=\"C_next\" method=\"post\" role=\"form\">\n" +
"                    <fieldset>\n" +
"        	      <legend>Semister and Branch</legend>\n" +
"                      <label for=\"Semister\" id=\"pp\">Semister:</label>\n" +
"        	         <select name=\"sem\" id=\"sem\" style=\"background-color:black\">\n" +
"                            <option>Select semister</option>\n" +
"                            <option>1</option>\n" +
"                            <option>2</option>\n" +
"                            <option>3</option>\n" +
"                            <option>4</option>\n" +
"                            <option>5</option>\n" +
"                            <option>6</option>\n" +
"                            <option>7</option>\n" +
"                            <option>8</option>\n" +
"                         </select>\n" +
"                      <label for=\"Branch\" id=\"pp\">Branch:</label>\n" +
"                      <select name=\"branch\" id=\"branch\" style=\"background-color:black\">\n" +
"                          <option>Select Branch </option>\n" +
"                          <option>Computer Science</option>\n" +
"                          <option>Information Technology</option>\n" +
"                          <option>Electrical Engg</option>\n" +
"                          <option>Electrical and communication</option>\n" +
"                          <option>EEE</option>\n" +
"                      </select>\n" +
"                    </fieldset><br/>\n" +
"              <fieldset>\n" +
"                  \n" +
"        	  <legend>Reguler Subject</legend>\n" +
"                  \n" +
"                  <p id=\"pp\"> <label for=\"sub1\">Subject 1:</label>\n" +
"        	  <input type=\"text\" name=\"SUB1\"  id=\"user-name\">\n" +
"                  <label for=\"sub2\">Subject code 1:</label>\n" +
"        	  <input type=\"text\" name=\"code1\" pattern=\"[1-8]+(cs|CS|ec|EC|ee|EE|ex|EX)+[0-9A-Za-z]{1,3}\" id=\"user-name\"></p><br/>\n" +
"                  <p id=\"pp\">\n" +
"                  <label for=\"sub3\">Subject 2:</label>\n" +
"        	  <input type=\"text\" name=\"SUB1\"  id=\"user-name\">\n" +
"                  <label for=\"sub4\">Subject code 2:</label>\n" +
"        	  <input type=\"text\" name=\"code1\" pattern=\"[1-8]+(cs|CS|ec|EC|ee|EE|ex|EX)+[0-9A-Za-z]{1,3}\" id=\"user-name\"></p><br/>\n" +
"                  <p id=\"pp\">\n" +
"                  <label for=\"sub5\">Subject 3:</label>\n" +
"        	  <input type=\"text\" name=\"SUB1\"  id=\"user-name\">\n" +
"                  <label for=\"sub6\">Subject code 3:</label>\n" +
"        	  <input type=\"text\" name=\"code1\" pattern=\"[1-8]+(cs|CS|ec|EC|ee|EE|ex|EX)+[0-9A-Za-z]{1,3}\" id=\"user-name\"></p><br/>\n" +
"                 <p id=\"pp\">\n" +
"                  <label for=\"sub5\">Subject 4:</label>\n" +
"        	  <input type=\"text\" name=\"SUB1\"  id=\"user-name\">\n" +
"                  <label for=\"sub6\">Subject code 4:</label>\n" +
"        	  <input type=\"text\" name=\"code1\" pattern=\"[1-8]+(cs|CS|ec|EC|ee|EE|ex|EX)+[0-9A-Za-z]{1,3}\" id=\"user-name\"></p><br/>\n" +
"                 <p id=\"pp\"> \n" +
"                 <label for=\"sub5\">Subject 5:</label>\n" +
"        	  <input type=\"text\" name=\"SUB1\"  id=\"user-name\">\n" +
"                  <label for=\"sub6\">Subject code 5:</label>\n" +
"        	  <input type=\"text\" name=\"code1\" pattern=\"[1-8]+(cs|CS|ec|EC|ee|EE|ex|EX)+[0-9A-Za-z]{1,3}\" id=\"user-name\"></p><br/>\n" +
"                 <p id=\"pp\">\n" +
"                 <label for=\"sub5\">Subject 6:</label>\n" +
"        	  <input type=\"text\" name=\"SUB1\"  id=\"user-name\">\n" +
"                  <label for=\"sub6\">Subject code 6:</label>\n" +
"        	  <input type=\"text\" name=\"code1\" pattern=\"[1-8]+(cs|CS|ec|EC|ee|EE|ex|EX)+[0-9A-Za-z]{1,3}\" id=\"user-name\"></p><br/>\n" +
"                                     \n" +
"              </fieldset><br/>\n" +
"              <p style=\" text-align: left; color:black\">\n" +
"              <input type=\"checkbox\" name=\"sports\" id=\"opchk\"> mark for optional subject </p>\n" +
"             \n" +
"              <div id=\"opdiv\" style=\" display:none; \">\n" +
"             <fieldset>\n" +
"                  <legend>Optional Subject</legend>\n" +
"                 <p id=\"pp\">\n" +
"                 <label for=\"sub5\">Subject 1:</label>\n" +
"        	  <input type=\"text\" name=\"SUB1\"  id=\"user-name\">\n" +
"                  \n" +
"                  <label for=\"sub6\">Subject code 1:</label>\n" +
"        	  <input type=\"text\" name=\"code1\" pattern=\"[1-8]+(cs|CS|ec|EC|ee|EE|ex|EX)+[0-9A-Za-z]{1,3}\" id=\"user-name\"></p><br/>\n" +
"                 <p id=\"pp\">\n" +
"                 <label for=\"sub5\">Subject 2:</label>\n" +
"        	  <input type=\"text\" name=\"SUB1\" id=\"user-name\">\n" +
"                  <label for=\"sub6\">Subject code 2:</label>\n" +
"        	  <input type=\"text\" name=\"code1\" pattern=\"[1-8]+(cs|CS|ec|EC|ee|EE|ex|EX)+[0-9A-Za-z]{1,3}\" id=\"user-name\"></p><br/>\n" +
"                 <p id=\"pp\">\n" +
"                 <label for=\"sub5\">Subject 3:</label>\n" +
"        	  <input type=\"text\" name=\"SUB1\"  id=\"user-name\">\n" +
"                  <label for=\"sub6\">Subject code 3:</label>\n" +
"        	  <input type=\"text\" name=\"code1\" pattern=\"[1-8]+(cs|CS|ec|EC|ee|EE|ex|EX)+[0-9A-Za-z]{1,3}\" id=\"user-name\"></p><br/>\n" +
"                             </fieldset>\n" +
"                    </div>\n" +
"            <br/>\n" +
"                <fieldset>\n" +
"                  <legend>Action</legend>\n" +
"                   <input type=\"submit\" value=\"Submit\">\n" +
"        	   <input type=\"reset\" value=\"Reset\">\n" +
"                </fieldset>\n" +
"                </form>\n" +
"              \n" +
"                </div>\n" +
"            \n" +
"       </div>\n" +
"        </div>";
output+="    </body>\n" +
"</html>\n" +
"";
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
