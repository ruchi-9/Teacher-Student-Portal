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
public class courseupdate extends HttpServlet {
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
output+="   <div class=\"container\" id=\"sreg\" style=\"margin-top: -20px; padding: 0px;text-align: center;  border-radius: 15px;\">\n" +
"            <div class=\"container\" style=\"padding: 0px; text-align: center;max-width: 500px\">\n" +
"                <h2 id=\"pp\"> Course Updation </h2>\n" +
"            <form action=\"C_search\" method=\"post\">\n" +
"                <fieldset>\n" +
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
"                    </fieldset>\n" +
"                    <br/>\n" +
"                <fieldset>\n" +
"                  <legend>Action</legend>\n" +
"                   <input type=\"submit\" value=\"Submit\">\n" +
"        	   <input type=\"reset\" value=\"Reset\">\n" +
"                </fieldset>\n" +
"            </form>\n" +
"                \n" +
"            </div>\n" +
"        </div>";
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