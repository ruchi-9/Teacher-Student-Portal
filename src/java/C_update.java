/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rajendra
 */
public class C_update extends HttpServlet {
String a;
    //static String sub[]=new String[9];
   // static String code[]=new String[10];
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("11111111");
               String sem=request.getParameter("sem");
                String branch=request.getParameter("branch");
                
                  String sub[]=request.getParameterValues("SUB1");   
                  String code[]=request.getParameterValues("code1");   
                  try {
                      System.out.println("222222222222");
              database db=new database();
              Connection con=db.getCon();                  //check the query
               PreparedStatement ps1 = con.prepareStatement("update course set sub1 = ? , sub2 = ? ,sub3 = ?,sub4 = ?,sub5 = ?,sub6 = ?,sub7 = ?,sub8 = ?,sub9 = ? where sem = ? and branch=?");
        			ps1.setString(9, code[4]);
                 ps1.setString(8, code[5]);
                           ps1.setString(10,sem);
						   ps1.setString(11,branch);
						    ps1.setString(1,code[0]);
							 ps1.setString(2,code[1]);
                            ps1.setString(3,code[2]);
							 ps1.setString(4,code[3]);
                                                         ps1.setString(5,code[6]);
                                                         ps1.setString(6,code[7]);
                                                         ps1.setString(7,code[8]);
                                                          ps1.executeUpdate();
                         System.out.println("update sucessfully in 1 table");    
                         for(int i=0;i<code.length;i++)
                      {
            PreparedStatement ps = con.prepareStatement("update code set subject=? where code=?");
              ps.setString(1,sub[i]);
               ps.setString(2,code[i]);
                ps.executeUpdate();
         a="Update Sucessfully";    
                      }
                         
         		 }
        
catch(Exception ex1)
{
	System.out.println(ex1);
        a="problem in data update";

}			
                                         savedinfo si = new savedinfo();
                  String output="";     
                 PrintWriter out = response.getWriter();           
                   
                    output+="<!DOCTYPE html>\n" +
"<html>\n";
                    output+=si.headpart()+"<body onload=\"startTime()\" style=\"\">\n" +si.headerpart() +si.menupart();
output+="    </body>\n" +
"</html>\n" +
"";
 output+="<div class=\"container\"  style=\"margin-top: -20px; padding:0px; padding-bottom:1px; color: red;text-align: center;  border-radius: 15px;\"><p>";
           output+=a;
           output+="</p></div>";

output+="   <div class=\"container\" id=\"sreg\" style=\"margin-top: 1px; padding: 0px;text-align: center; border-radius: 15px;\">\n" +
"            <div class=\"container\" style=\"padding: 0px; text-align: center;max-width: 500px\">\n" +
"                <h2 id=\"pp\"> Course Updation </h2>\n" +
"            <form action=\"C_search\" method=\"post\">\n" +
"                <fieldset>\n" +
"        	      <legend>Semister and Branch</legend>\n" +
"                      <label for=\"Semister\" id=\"pp\">Semister:</label>\n" +
"        	         <select name=\"sem\" id=\"sem\" style=\"color:black\">\n" +
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
"                      <select name=\"branch\" id=\"branch\" style=\"color:black\">\n" +
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
out.println(output);
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
