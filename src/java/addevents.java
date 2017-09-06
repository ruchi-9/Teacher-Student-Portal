/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Beniwal
 */
public class addevents extends HttpServlet {

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
        String event=request.getParameter("event");
        System.out.println(event);
        String output="";
        PrintWriter writer = response.getWriter();
        String a;
        
          try
               { 
                           database db=new database();
                           Connection con=db.getCon();	
                           PreparedStatement ps = con.prepareStatement("insert into event (eventname) values(?)");
                           ps.setString(1,event);                              
			   ps.execute();
			   a="event insert sucessfully";	 
                           System.out.println("sucessfull");
  
               }
			   catch(Exception ex)
                {
			   System.out.println(ex);
			   a="operation failed";
                           System.out.println("unsucessfull");
                }
   
                       savedinfo si = new savedinfo();
                       
                      
                   
                    output+="<!DOCTYPE html>\n" +
                    "<html>\n";
                    output+=si.headpart()+"<body onload=\"startTime()\" style=\"\">\n" +si.headerpart() +si.menupart();
                    output+="<div class=\"container\"  style=\"margin-top: -21px; margin:1px padding:0px; color: red;text-align: center;border-radius: 15px;\"><p>";
                   output+=a;
                   output+="</p></div>";
                    //show add event form 
                    
                    output+="<div class=\"container\" id=\"sreg\" style=\"margin-top:2px; padding: 0px;text-align: center;  border-radius: 15px;\">\n" +
"            <div class=\"container\" id=\"maindiv\"style=\"padding: 0px; text-align: center;max-width: 500px\">\n" +
"                <h2> Add New Event </h2>\n" +
"            <form action=\"addevents\" method=\"post\">\n" +
"                <table class=\"table table-hover\"  >\n" +

"                    <tbody>\n" +
"                          <tr> \n" +
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
