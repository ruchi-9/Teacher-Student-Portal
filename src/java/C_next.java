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
public class C_next extends HttpServlet {
 String a;
    /*String s6=null;
  
                   String s5=null;
                String s4=null;
               static String c1,c2,c3;*/
    static String sub[]=new String[9];
    static String code[]=new String[10];
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       int i=0;
                 String sem=request.getParameter("sem");
                String branch=request.getParameter("branch");
                
                  String sub[]=request.getParameterValues("SUB1");   
                  String code[]=request.getParameterValues("code1");   
                 try {
                      System.out.println(sem);
                      System.out.println(branch);
                      database db=new database();
                      Connection con=db.getCon();
                      System.out.println("11111111111111111111");
                      PreparedStatement ps1 = con.prepareStatement("insert into course values(?,?,?,?,?,?,?,?,?,?,?)");
                      System.out.println("2222222222222");
                      ps1.setString(7, code[4]);
                      ps1.setString(8, code[5]);
                                                    ps1.setString(1,sem);
						    ps1.setString(2,branch);
						    ps1.setString(3,code[0]);
				                    ps1.setString(4,code[1]);
                                                    ps1.setString(5,code[2]);
							 ps1.setString(6,code[3]);
                                                         ps1.setString(9,code[6]);
                                                         ps1.setString(10,code[7]);
                                                         ps1.setString(11,code[8]);
                                                          System.out.println("333333333333");
							 ps1.executeUpdate();
                                                          while(!code[i].equals(""))
                                                          {
                   PreparedStatement ps = con.prepareStatement("insert into code values(?,?)");
                   ps.setString(1,code[i]);
                   ps.setString(2,sub[i]);
                   ps.executeUpdate();
                   System.out.println("2222222222222");
                                                       i++;}
                                                       System.out.println("44444444444444");
                                                       response.sendRedirect("index.html");
               a="record updated";
                 }
               catch(Exception ex)
               {
                    System.out.println("000000000000000");
                    System.out.println(ex);
                     a="record not updated entry already exist";
               }
                 PrintWriter out = response.getWriter();
                 String output="";
                         savedinfo si = new savedinfo();
                    output+="<!DOCTYPE html>\n <html>\n";
                    output+=si.headpart()+"<body onload=\"startTime()\" style=\"\">\n" +si.headerpart() +si.menupart();
output+="    </body>\n" +
"</html>\n" +
"";
 output+="<div class=\"container\"  style=\"margin-top: -20px; padding:0px; color: red;text-align: center; border-radius: 15px;\"><p>";
           output+=a;
           output+="</p></div>";
output+="<div class=\"container\" id=\"sreg\" style=\"margin-top: 1px;  padding: 0px;text-align: center;  border-radius: 15px;\">\n" +
"            <div class=\"container\" style=\" max-width: 800px\">\n" +
"                <h2 id=\"pp\"> Add New Course </h2>\n" +
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
"        	  <input type=\"text\" name=\"SUB1\" id=\"user-name\">\n" +
"                  <label for=\"sub2\">Subject code 1:</label>\n" +
"        	  <input type=\"text\" name=\"code1\" id=\"user-name\"></p><br/>\n" +
"                  <p id=\"pp\">\n" +
"                  <label for=\"sub3\">Subject 2:</label>\n" +
"        	  <input type=\"text\" name=\"SUB1\" id=\"user-name\">\n" +
"                  <label for=\"sub4\">Subject code 2:</label>\n" +
"        	  <input type=\"text\" name=\"code1\" id=\"user-name\"></p><br/>\n" +
"                  <p id=\"pp\">\n" +
"                  <label for=\"sub5\">Subject 3:</label>\n" +
"        	  <input type=\"text\" name=\"SUB1\" id=\"user-name\">\n" +
"                  <label for=\"sub6\">Subject code 3:</label>\n" +
"        	  <input type=\"text\" name=\"code1\" id=\"user-name\"></p><br/>\n" +
"                 <p id=\"pp\">\n" +
"                  <label for=\"sub5\">Subject 4:</label>\n" +
"        	  <input type=\"text\" name=\"SUB1\" id=\"user-name\">\n" +
"                  <label for=\"sub6\">Subject code 4:</label>\n" +
"        	  <input type=\"text\" name=\"code1\" id=\"user-name\"></p><br/>\n" +
"                 <p id=\"pp\"> \n" +
"                 <label for=\"sub5\">Subject 5:</label>\n" +
"        	  <input type=\"text\" name=\"SUB1\" id=\"user-name\">\n" +
"                  <label for=\"sub6\">Subject code 5:</label>\n" +
"        	  <input type=\"text\" name=\"code1\" id=\"user-name\"></p><br/>\n" +
"                 <p id=\"pp\">\n" +
"                 <label for=\"sub5\">Subject 6:</label>\n" +
"        	  <input type=\"text\" name=\"SUB1\" id=\"user-name\">\n" +
"                  <label for=\"sub6\">Subject code 6:</label>\n" +
"        	  <input type=\"text\" name=\"code1\" id=\"user-name\"></p><br/>\n" +
"                                     \n" +
"              </fieldset><br/>\n" +
"              <p id=\"pp\" style=\" text-align: left\">\n" +
"              <input type=\"checkbox\" name=\"sports\" id=\"opchk\"> mark for optional subject </p>\n" +
"             \n" +
"              <div id=\"opdiv\" style=\" display:none; \">\n" +
"             <fieldset>\n" +
"                  <legend>Optional Subject</legend>\n" +
"                 <p id=\"pp\">\n" +
"                 <label for=\"sub5\">Subject 1:</label>\n" +
"        	  <input type=\"text\" name=\"SUB1\" id=\"user-name\">\n" +
"                  \n" +
"                  <label for=\"sub6\">Subject code 1:</label>\n" +
"        	  <input type=\"text\" name=\"code1\" id=\"user-name\"></p><br/>\n" +
"                 <p id=\"pp\">\n" +
"                 <label for=\"sub5\">Subject 2:</label>\n" +
"        	  <input type=\"text\" name=\"SUB1\" id=\"user-name\">\n" +
"                  <label for=\"sub6\">Subject code 2:</label>\n" +
"        	  <input type=\"text\" name=\"code1\" id=\"user-name\"></p><br/>\n" +
"                 <p id=\"pp\">\n" +
"                 <label for=\"sub5\">Subject 3:</label>\n" +
"        	  <input type=\"text\" name=\"SUB1\" id=\"user-name\">\n" +
"                  <label for=\"sub6\">Subject code 3:</label>\n" +
"        	  <input type=\"text\" name=\"code1\" id=\"user-name\"></p><br/>\n" +
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
"        </div> ";
out.println(output);    }
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
