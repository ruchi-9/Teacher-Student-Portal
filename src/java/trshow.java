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
public class trshow extends HttpServlet {


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
      int s=0;
      String a=null;
      String sub[]=new String[7];
        //to get id pass from cookie of browseer
        int i,j;
        int f=0;
        String tid=null;
        try
        {
         f=Integer.parseInt(request.getParameter("f"));
         }catch(Exception cc)
         {
         }
        tid= request.getParameter("tid");
              try
        {
         s=Integer.parseInt(request.getParameter("s"));
         }catch(Exception cc)
         {
         }
             Cookie cookie = null;
	         Cookie[] cookies = null;
                 cookies = request.getCookies();
                    for ( i = 0; i < cookies.length; i++)
                    {
                          cookie = cookies[i];
                      if(cookie.getName().equals("name"))
                    {
                         ID=cookie.getValue();
                         //s1=ID;
                         System.out.println("Id = "+ID);
                    }
                      if(cookie.getName().equals("pass"))
                      { pass=cookie.getValue();
                    }}
                   PrintWriter writer = response.getWriter();
                   database db=new database();
                   Connection con=db.getCon();
                   i=0;
                   //teacher subject
                   
                   
                 try
                 {
                       System.out.println("error 1");
                       System.out.println(ID);
                       PreparedStatement ps = con.prepareStatement("SELECT ID,password,username FROM admin_login where ID = ?");
                       ps.setString(1,ID);
                       System.out.println("before the exection ");
                       ResultSet rs = ps.executeQuery();
                       System.out.println("after the exection "+rs);
                        System.out.println("error  2");
                        if(rs.next())
		         { 
                         String password;
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
                  System.out.println("admin login error"+e);
                  }
                 
                 if(f==1)
                 {
                   sub=request.getParameterValues("SID");
                 //  try{System.out.print(sub[6]);}catch(Exception e){system.out.println("");}
                   PreparedStatement ps1=null;
                   i=0;
                   for(i=0;i<sub.length;i++)
                   {
                   try
                   {
                      ps1=con.prepareStatement("select * from t_rest where id = ?");
                      ps1.setString(1, tid);
                      ResultSet rs = ps1.executeQuery();
                   if(rs.next())
                   {
                   a="Last record "+tid+"  updated";
                   }
                   else{
                      System.out.println("in update 1");
                      ps1 = con.prepareStatement("insert into t_rest values(?,?)");
                      ps1.setString(1,tid);
                      ps1.setString(2,sub[i]);
                      System.out.println("in update 2");
                      ps1.executeUpdate();
                      System.out.println("in update 3s");
                      a="Last record "+tid+" updated";
                   }}
                   catch(Exception e)
                   {
                   a="Last record not "+tid+" updated";
                   System.out.println(e);
                   }
                  
                   }
                   
                     
                 }
                 System.out.println("f value is "+f+"tid ="+tid);
                   if(flag==1)
                   {
                       savedinfo si = new savedinfo();
                    output+="<!DOCTYPE html>\n" +
                     "<html>\n";
                    output+=si.headpart()+"<body onload=\"startTime()\" style=\"\">\n" +si.headerpart() +si.menupart();
                   if(f==1)
                    {
                    output+="<div class=\"container\" id=\"sreg2\"  ><p>";
                   output+=a;
                   output+="</p></div>";
                    output+="<div class=\"container\" id=\"sreg1\" style=\"background-color:transparent ;\" >\n";
                    }
                   else{  output+="<div class=\"container\" id=\"sreg\">\n";
                   }output+="<div class=\"container\" id=\"maindiv\" style=\"\">\n" +
"                <h2> Teacher Record Show </h2>\n" +
"            <form action=\"tsubshow\" method=\"post\">\n" +
"                <table class=\"table table-hover\"  >\n" +
"                    <tbody>\n" +
"                          <tr> \n" +
"                            <th scope=\"row\">Teacher Phone no: </th>\n" +
"                            <td><input type=\"text\" name=\"ID\" required=\"enter phone number\" /> </td>\n" +
"                            \n" +
"                          </tr>\n" +
"                    </tbody>\n" +
"                </table>\n" +
"                <input type=\"submit\" value=\"Search\" />\n" +
"            </form>\n" +
"                \n" +
"            </div>\n" +
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
