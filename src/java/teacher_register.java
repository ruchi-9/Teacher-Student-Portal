import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import static student_register.a;
public class teacher_register extends HttpServlet {
    static String a;
    @Override
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
              String name=request.getParameter("name");
              String password=request.getParameter("password");
              String contact_no=request.getParameter("contact_no");
              String mail_id=request.getParameter("mail_id");
               try{
                 database db=new database();
                 Connection con=db.getCon();
                 System.out.println("00");
                 // database table sequence   1.name 2.password 3.contact no 4. mail id
                 PreparedStatement ps = con.prepareStatement("insert into teacher_login values(?,?,?,?)");
			ps.setString(1,name);
	         	ps.setString(2,password);
                        ps.setString(3,contact_no);
                        ps.setString(4,mail_id);
                        ps.executeUpdate();
                  a="Register Sucessfully"; 
               }
               catch(MySQLIntegrityConstraintViolationException e)
               {
                   a="Already Present in Database";
               }
	       catch(SQLException | HeadlessException ex)
		   {
		       System.out.println(ex);
                       //a=ex.toString();
                       
                        a="Register Unsucessfully";
		   }
               
               // show output after regirester 
               PrintWriter writer = response.getWriter();
        savedinfo si =new savedinfo();
        String header = si.headerpart();
        String head = si.headpart();
        String menu = si.menupart();
        String student="";
        String op ="<html>"+head+ "<body onload=\"startTime()\" style=\"\">"+header+menu;
           student+="<div class=\"container\"  style=\"margin-top: -18px; margin-bottom:22px; padding:0px; color: red; text-align: center;border-color:#cccccc;  border-radius: 15px; \"><p>";
           student+=a;
           student+="</p></div>";
           student+="<div class=\"container\" id=\"sreg\">";
           student+=" <div class=\"container\" id=\"maindiv\">";
           student+="     <h2 style=\"color: black ;font-variant:small-caps; font-style:normal; \"> New Teacher Registration </h2>";
           student+=" <form action=\"teacher_register\" method=\"post\">";
           student+="<table class=\"table \"  >";
           student+=" <tbody>";
           student+=" <tr> ";
           student+=" <th scope=\"row\">Name : </th>";
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
                            student+="<th scope=\"row\">phone: </th>";
                            student+="<td><input type=\"text\" name=\"contact_no\" pattern=\"[7|8|9][0-9]{9}\" title=\"enter correct phone number \"/> </td>";
                          student+="</tr>";
                          student+="<tr> ";
                            student+="<th scope=\"row\">E_mail : </th>";
                            student+="<td><input type=\"email\" name=\"mail_id\" required /> </td>";
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
