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
 * @author Rajendra
 */
public class A_login extends HttpServlet {
     String ID=null;
             String pass=null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
                 System.out.println("in admin servlet");
        ID= (String)request.getAttribute("name");
             pass=(String)request.getAttribute("pass");
            System.out.println(ID);
            System.out.println(pass);
         database db=new database();
              Connection con=db.getCon();
               try{
                   PreparedStatement ps = con.prepareStatement("SELECT id,password FROM admin");
               
         System.out.println("444444444444444444444");
           ResultSet rs = ps.executeQuery();
           
          String id  = null;
               while(rs.next()){
                 System.out.println("555555555555111111111111111");
		 if((rs.getString("id").equalsIgnoreCase(ID))&(rs.getString("password").equals(pass)))
		 {
			System.out.println("next page");
	                response.sendRedirect("http://localhost:8080/WebApplication2/admin.html");
						    
		 }
                 
          }
            
    }
               catch(Exception ex)
               {
                   System.out.println("exceptionnnnnnnnnnnn");
                   System.out.println(ex);
               }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       doGet(request,response);
    }
}