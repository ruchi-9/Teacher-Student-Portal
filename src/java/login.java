/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
//import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rajendra
 */
public class login extends HttpServlet {
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String a="1";
        String b="2";
        String d="admin";
    System.out.println("in servlet");
    // response.setContentType("text/html;charset=UTF-8");
     try{
                  String name=request.getParameter("name");
                  String pass=request.getParameter("pass");
                  System.out.println("name="+name);
                  System.out.println("pass="+pass);
                       Cookie cookie1 =new Cookie("name",name);
                       Cookie cookie2 =new Cookie("pass",pass);
                       response.addCookie(cookie1);
                       response.addCookie(cookie2);
             String c=name.substring(0,1);
             String z=name.substring(0,5);
             System.out.println(c);
             System.out.println(z);
             System.out.println("in try");
             //request.setAttribute("name",name);
             //request.setAttribute("pass",pass);
          if(b.equals(c)||a.equals(c))
             {  
                System.out.println("in student");
                RequestDispatcher rd=request.getRequestDispatcher("student");
                rd.forward(request,response);
             }
          else if(d.equals(z))
                  {    
                       System.out.println("in admin page"); 
                       RequestDispatcher rd=request.getRequestDispatcher("admin");
                       rd.forward(request,response);
                 }
         else
          {         
                    System.out.println("in teacher page");
                    RequestDispatcher rd=request.getRequestDispatcher("T_login");
                    rd.forward(request,response);
           }
		
          }
          catch(Exception ex)
          {
              System.out.println("++++++++++++++++++++++++++++++++++");
              System.out.println(ex);
          } 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
