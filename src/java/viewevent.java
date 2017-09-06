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
public class viewevent extends HttpServlet {

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
     String evn[]= new String[30];
     String evnid[]= new String[30];
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
               // to retruive event from database 
                  try
                    {
                        PreparedStatement ps1 = con.prepareStatement("SELECT * from event");
                        ResultSet rs =ps1.executeQuery();
                        int event=0;
                        while(rs.next())
		     {
                          evn[event]=rs.getString(2);
                          evnid[event]=Integer.toString(rs.getInt(1));
                          System.out.println("data inside event ="+evn[event]);
                          event++;
                     }
                    }
                   catch(Exception e)
                    {
                        System.out.println("Excetption in event"+e);
                    }  
                if(flag==1)
                   {
                       savedinfo si = new savedinfo();
                       
                      
                      int e=0;
                 
                    output+="<!DOCTYPE html>\n" +
"<ht    ml>\n";
                    output+=si.headpart()+"<body onload=\"startTime()\" style=\"\">\n" +si.headerpart() +si.menupart();

                    //discription about event
                    
                    output+="<div class=\"container\" id=\"sreg\" style=\"margin-top: -20px; padding: 0px;text-align: center;  border-radius: 15px;\">\n";
                    output+=" <div class=\"container\" style=\"padding: 0px; color:black; text-align: center;max-width: 600px; min-width: 600px\">\n";
                    output+="<h3 style=\"text-align: center; color:white;  padding:20px\">View Event</h3>";
output+="<table class=\"table table-hover\"  >\n" +
"                    <thead class=\"thead-inverse\">\n" +
"        <tr>\n" +
"            <th style=\"text-align: center\">S_No</th>\n" +
"          <th style=\"text-align: center\">Event</th>\n" +
"          <th style=\"text-align: center\">Action</th>\n" +
"        </tr>\n" +
"      </thead>\n" ;
            
        while(evnid[e]!=null){        
                   output+="" +
                   "<tr>\n" +
                   "<th scope=\"row\"  style=\"text-align: center\">";
                    output+=(e+1)+"</th>\n" +
                   "<td><p style=\"max-height: 30px;\">";
                    output+=evn[e]+"</td>\n" ;
                            System.out.println("inside the event");
                   output+="<td><a href=\"event_delete?id="+evnid[e]+"\"><input type=\"button\" value=\"Delete\"> </a></td>\n" +
                   "\n" +
                   "</tr>\n" +
                   "\n" +
                   "</thead>\n" ;
                   e++;          
                      }
                     output+="</table>\n" +
                              "</div>";
                      output+="</div>";
                    
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
