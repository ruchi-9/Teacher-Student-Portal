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
public class student_register extends HttpServlet {
static String name;
static String ID;
static String a,b,c;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
              name=request.getParameter("name");
              String password=request.getParameter("password");
              String confirm_pass=request.getParameter("confirm_pass");
              ID=request.getParameter("ID");
              String phone_no=request.getParameter("phone_no");
              String E_mail=request.getParameter("E_mail");
              String father_name=request.getParameter("father_name");
              int s5 = 0,s4 = 0;
              String s3 = null;
        try{
                String s1=ID;
		int year,sem;
		String branch;
		       logincheck ob=new logincheck(s1);			
                        year = ob.year;
                        branch = ob.branch;
                        sem = ob.sem;	
			String z=String.valueOf(sem); 
                 
                     s5=sem;			
                  
					s3=branch;
					
					String z1=String.valueOf(year); 

                     s4=year;
           System.out.println("sucessful step 1");
        }
          catch(Exception ex)
          {
              a="Error into data Retriveing of Student ";
          }
        
    
             if (password.equals(confirm_pass))
           { database db=new database();
              Connection con=db.getCon();
               try{PreparedStatement ps = con.prepareStatement("insert into stuent_login values(?,?,?,?,?,?,?)");
                         ps.setString(1, ID);     //id
                         ps.setString(2,password);      //password
                    	 ps.setString(3,name);  //s_name
                         ps.setString(4, phone_no); //phone no
			 ps.setString(5,father_name); //father name
			 ps.setString(6,s3);// branch
                         ps.setString(7,E_mail);
						ps.executeUpdate();
                                                a="record updated sucessfully";
                                                 try
                                                 {  System.out.println("before select the id ,year");
						    PreparedStatement ps1 = con.prepareStatement("select  student_id,branch,from stuent_login where student_id=?");
					            ps1.setString(1,ID);
					            ResultSet rs1 = ps1.executeQuery();
							 	  
					         if( rs1.next())
						     {
                                                         System.out.println("......................in if");
                                                         
                                                        a="Data alreay present into the database"; // problem in explain
						         
                                                     
						     }                                 //if id same and other are different then the store in database
                            else {
						    System.out.println("..................in else");
							a="data regirestered sucessfully";
                                   }}
                  catch(   SQLException | HeadlessException ex1)
                         {
	                      System.out.println("sql exception");
						    System.out.println("inner catch");
			              }		
			   }
          catch (SQLException ex) 
                {       System.out.println(ex);
                        a="Record already Exist";
				}
				
            }
			
          else
           {
			   System.out.println("Password Does Not Match");
            } 
          //to provide output after registeration og student      
        PrintWriter writer = response.getWriter();
        savedinfo si =new savedinfo();
        String header = si.headerpart();
        String head = si.headpart();
        String menu = si.menupart();
        String student="";
        String op ="<html>"+head+ "<body onload=\"startTime()\" style=\"\">"+header+menu;
           student+="<div class=\"container\"  style=\"margin-top: -20px; padding:0px; color: red;text-align: center; border-radius: 15px;\"><p>";
           student+=a;
           student+="</p></div>";
        //to show new registeration form 
                     student+="<div class=\"container\" id=\"sreg\" style=\"margin-top: 1px; padding: 0px;text-align: center;\">";
                     student+="<div class=\"container\" id=\"maindiv\">";
                    student+=" <h2> New Student Registration </h2>";
                     student+="<form action=\"student_register\" method=\"post\">";
                     student+="<table class=\"table table-hover\"  >";
                                         student+="<tbody>";
                          student+="<tr> ";
                            student+="<th scope=\"row\">Name : </th>";
                            student+="<td><input type=\"text\" name=\"name\" /> </td>";
                          student+="</tr>";
                          student+="<tr> ";
                            student+="<th scope=\"row\">password : </th>";
                            student+="<td><input type=\"password\" name=\"password\" id=\"password\"/> </td>";
                          student+="</tr>";
                          student+="<tr> ";
                            student+="<th scope=\"row\">confirm password : </th>";
                            student+="<td><input type=\"password\" name=\"confirm_pass\" id=\"passwordconfirm\" oninput=\"check(this)\"/> </td>";
                          student+="</tr>";
                          student+="<script language='javascript' type='text/javascript'>";
                                student+="function check(input) {";
                                student+="if (input.value != document.getElementById('password').value) {";
                                student+="input.setCustomValidity('Password Must be Matching.');";
                                   student+="} else {";
                                 // input is valid -- reset the error message
                                student+="input.setCustomValidity('');";
                                student+="}";
                                student+="}";
                         student+="</script>";
                          student+="<tr> ";
                            student+="<th scope=\"row\">Id : </th>";
                            student+="<td><input type=\"text\" name=\"ID\" /> </td>";
                          student+="</tr>";
                          student+="<tr> ";
                            student+="<th scope=\"row\">phone: </th>";
                            student+="<td><input type=\"text\" name=\"phone_no\" pattern=\"[7|8|9][0-9]{9}\" title=\"enter correct phone number \" /> </td>";
                          student+="</tr>";
                          student+="<tr> ";
                            student+="<th scope=\"row\">E_mail : </th>";
                            student+="<td><input type=\"email\"  required  name=\"E_mail\" title=\"enter correct email id \" /> </td>";
                          student+="</tr>";
                          student+="<tr> ";
                            student+="<th scope=\"row\">father name : </th>";
                            student+="<td><input type=\"text\" name=\"father_name\" /> </td>";
                          student+="</tr>";
                    student+="</tbody>";
                student+="</table>";
                student+="<input type=\"submit\" value=\"register\" />";
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
