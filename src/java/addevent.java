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
public class addevent extends HttpServlet {
    
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
     String ID,pass;
     String username;
     int flag=0;
        String event=request.getParameter("event");
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

                    //show add event form 
                    
                    output+="<div class=\"container\" id=\"sreg\" style=\"margin-top: -20px; padding: 0px;text-align: center;  border-radius: 15px;\">\n" +
"            <div class=\"container\" style=\"padding: 0px; text-align: center;max-width: 500px\">\n" +
"                <h2> Add New Event </h2>\n" +
"            <form action=\"addevents\" method=\"post\">\n" +
"                <table class=\"table table-hover\"  >\n" +
"                              <th scope=\"row\" style=\"padding-top: 20px\">Enter Event Information: </th>\n" +
"                            <td><textarea rows=\"2\" cols=\"25\" name=\"event\"></textarea> </td>\n" +
"                          </tr>\n" +
"                    </tbody>\n" +
"                </table>\n" +
"                <input type=\"submit\" value=\"Add Event\"  style=\" margin-bottom: 20px\"/>\n" +
"            </form>\n" +
"                \n" +
"            </div>\n" +
"        </div>";
                    
                    output+="    </body>\n" +
                     "</html>\n";
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
