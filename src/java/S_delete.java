
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

public class S_delete extends HttpServlet {

    @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
           String s1=request.getParameter("ID");
             try {
                database db=new database();
                Connection con=db.getCon();
                PreparedStatement ps1 = con.prepareStatement("select  student_id from stuent_login where student_id=?");           //for select an id 
		ps1.setString(1,s1);
		ResultSet rs = ps1.executeQuery();
	              if( rs.next())                                   //check weather id is present or not in database                                             
			 {			 
		            PreparedStatement ps = con.prepareStatement("delete from stuent_login where student_id=?");
                            ps.setString(1, s1);                              
                            int rs1 = ps.executeUpdate();
                            System.out.println("data is deleted successfully");
                            //response.sendRedirect("");
                         }
        		else
					{
						 System.out.println("enter id not present in the database");
						// response.sendRedirect("");
					}        
        
                  }
              catch(Exception ex)
                 {
                       System.out.println("exception in the code ");
                       System.out.println(ex);
                 }
             PrintWriter writer = response.getWriter();
             String output="";
             savedinfo si = new savedinfo();
                    output+="<!DOCTYPE html>\n" +
"<html>\n";
                    output+=si.headpart()+"<body onload=\"startTime()\" style=\"\">\n" +si.headerpart() +si.menupart();
output+="    </body>\n" +
"</html>\n" +
"";
 output+="<div class=\"container\"  style=\"margin-top: -20px; padding:0px; color: red;text-align: center;  border-radius: 15px;\"><p>";
           output+="Record Deleted Successfully";
           output+="</p></div>";
output+="<div class=\"container\" id=\"sreg\" style=\"margin-top: 2px; \">\n" +
"            <div class=\"container\"id=\"maindiv\">\n" +
"                <h2> Student Record Deletion </h2>\n" +
"            <form action=\"S_search\" method=\"post\">\n" +
"                <table class=\"table table-hover\"  >\n"+
"                    <tbody>\n" +
"                          <tr> \n" +
"                            <th scope=\"row\">Enter Student Id: </th>\n" +
"                            <td><input type=\"text\"  name=\"ID\" required=\"enter Student id\" /> </td>\n" +
"                          </tr>\n" +
"                    </tbody>\n" +
"                </table>\n" +
"                <input type=\"submit\" value=\"Search\" />\n" +
"            </form>\n" +
"                \n" +
"            </div>\n" +
"        </div> ";
writer.println(output);
             
             
             
      }
       @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
             doGet(request, response);
               }
        @Override
        public String getServletInfo() {
        return "Short description";
    }
}
