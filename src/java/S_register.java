

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.HeadlessException;
import java.io.IOException;
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
public class S_register extends HttpServlet {
 static String s1;
 static int s5 ,s4 ;
        static  String s3 ;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
              String name=request.getParameter("name");
              String password=request.getParameter("password");
              String confirm_pass=request.getParameter("confirm_pass");
              String ID=request.getParameter("ID");
              String phone_no=request.getParameter("phone_no");
              String E_mail=request.getParameter("E_mail");
              String father_name=request.getParameter("father_name");
               s1=ID;
               int a;
              a=check_id(s1);
              
               if (password.equals(confirm_pass)&&a==1)
            //if (check_id(s1))
           { database db=new database();
              Connection con=db.getCon();
               try{PreparedStatement ps = con.prepareStatement("insert into stuent_login values(?,?,?,?,?,?)");
                         ps.setString(1, ID);     //id
                         ps.setString(2,password);      //password
                    	 ps.setString(3,name);  //s_name
                         ps.setString(4, phone_no); //phone no
			 ps.setString(5,father_name); //father name
			 ps.setString(6,s3);// branch
						ps.executeUpdate();
                                                 try
                                                 {  System.out.println("before select the id ,year");
						    PreparedStatement ps1 = con.prepareStatement("select  student_id,branch from stuent_login where student_id=?");
					            ps1.setString(1,ID);
					            ResultSet rs1 = ps1.executeQuery();
							 	  
					         if( rs1.next())
						     {
                                                         System.out.println("Data Saved Successfully in the database"); // problem in explain
						         System.out.println("move to next page after insertion of entry");
                                                     response.sendRedirect("http://localhost:8080/WebApplication2/studentregister.html");
						     }                                 //if id same and other are different then the store in database
                            else		   {
						    
							 System.out.println("already present in the database");
                                                   }}
                  catch(   SQLException | HeadlessException ex1)
                         {
	                      System.out.println("sql exception");
						    System.out.println("inner catch");
			              }		
			   }
          catch (SQLException ex) 
                {      
                         // when all the entry are same that is all primary key are same
                        System.out.println(ex);
                     //throw ex;						
					    System.out.println("outer catch");
				}
				
            }
			
          else
           {
			   System.out.println("Password Does Not Match or mail id is not in correct pattern");
            } 
           
    }
    protected int check_id(String s)
    {  System.out.println("in check id mathod");
     System.out.println(s);
        try{
             
		int year,sem;
		String branch;
		       logincheck ob=new logincheck(s1);			
                        year = logincheck.year;
                        branch = logincheck.branch;
                        sem = logincheck.sem;	
			String z=String.valueOf(sem); 
                        s5=sem;			
                        s3=branch;
			String z1=String.valueOf(year); 
                        s4=year;
        System.out.println("sucessful step 1");
        return 1;
        }
          catch(Exception ex)
          {
              System.out.println("valid pattern of id is not enter");
        System.out.println("unsucesfull step 1");
     return 0;
          }
         
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            doGet(request, response);
     }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
