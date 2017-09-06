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


/**
 *
 * @author Rajendra
 */
public class s_record extends HttpServlet {
public static String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,q1,a;
    
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
           String s0=request.getParameter("ID");
           System.out.println("get data is "+s0);
        try {
            s1=s0;
                database db=new database();
                Connection con=db.getCon();
                PreparedStatement ps1 = con.prepareStatement("select  * from stuent_login where student_id=?");
		ps1.setString(1,s1);
		ResultSet rs = ps1.executeQuery();
                System.out.println(s0);
		         if(rs.next())
		        	 {
	                                    s2= rs.getString("password");
	                                    s6= rs.getString("s_name");
                                            s7= rs.getString("phone_no"); 
                                            s8= rs.getString("father_name");
                                            s5= rs.getString("branch");
                                            s10=rs.getString("Email");
                                 }     
                         else {
						System.out.println("wrong enter id");
						a="record not exist "; 
						   }
                         System.out.println(s1);
                         System.out.println(s2);
                         System.out.println(s6);
                         System.out.println(s7);
                         System.out.println(s8);
                         System.out.println(s5);
            }
        catch(Exception ex)
        {
             System.out.println("EXCEPTION");
             System.out.println(ex);
        }
        // to provide output after teh search sucessfully 
        PrintWriter writer = response.getWriter();
        savedinfo si =new savedinfo();
        String header = si.headerpart();
        String head = si.headpart();
        String menu = si.menupart();
        String student="";
        String op ="<html>"+head+ "<body onload=\"startTime()\" style=\"\">"+header+menu;
        // to show details into student info 
        student+="<div class=\"container\"  style=\"margin-top: -20px; padding:0px; color: red;text-align: center;  border-radius: 15px;\"><p>";
                   student+=a;
                   student+="</p></div>";
         student+="<div class=\"container\" id=\"sreg\" style=\"margin-top: 1px; padding: 0px;text-align: center; border-radius: 15px;\">\n";
            student+="<div class=\"container\" id=\"maindiv\">\n";
                student+="<h2> Student Updation </h2>\n";
            student+="<form action=\"student_update\" method=\"post\">\n";
                student+="<table class=\"table table-hover\"  >\n";
                   
                    student+="<tbody>\n";
                          student+="<tr> \n";
                            student+="<th scope=\"row\">Name : </th>\n";
                            student+="<td><input type=\"text\" value=\""+s6+"\" name=\"name\" /> </td>\n";
                          student+="</tr>\n";
                          student+="<tr> \n";
                            student+="<th scope=\"row\">password : </th>\n";
                            student+="<td><input type=\"text\" name=\"password\" value=\""+s2+"  \" id=\"password\"/> </td>\n";
                          student+="</tr>\n";
                          student+="<tr> \n";
                            student+="<th scope=\"row\">confirm password : </th>\n";
                            student+="<td><input type=\"text\" name=\"confirm_pass\" id=\"passwordconfirm\" oninput=\"check(this)\"/> </td>\n";
                          student+="</tr>\n";
                          student+="<script language='javascript' type='text/javascript'>\n";
                                student+="function check(input) {\n";
                                student+="if (input.value != document.getElementById('password').value) {\n";
                                student+="input.setCustomValidity('Password Must be Matching.');\n";
                                   student+="} else {\n";
                                 // input is valid -- reset the error message\n";
                                student+="input.setCustomValidity('');\n";
                                student+="}\n";
                                student+="}\n";
                         student+="</script>\n";
                          student+="<tr> \n";
                            student+="<th scope=\"row\">Id : </th>\n";
                            student+="<td><input type=\"text\" name=\"ID\" pattern=\"[1]+[0-5]+(EBK|ebk)+(cs|CS|ec|EC|ee|EE|ex|EX)+[0-9]{1,3}\"  value=\""+s1+"\"  /> </td>\n";
                          student+="</tr>\n";
                          student+="<tr> \n";
                            student+="<th scope=\"row\">phone: </th>\n";
                            student+="<td><input type=\"text\" name=\"phone_no\"  value=\""+s7+"\"  /> </td>\n";
                          student+="</tr>\n";
                          student+="<tr> \n";
                            student+="<th scope=\"row\">E_mail : </th>\n";
                            student+="<td><input type=\"text\" value=\""+s10+"\" pattern=\"[A-Za-z0-9._%+-]{2,}@[a-zA-Z]{1,}([.]{1}[a-zA-Z]{2,}|[.]{1}‌​[a-zA-Z]{2,}[.]{1}[a‌​-zA-Z]{2,})\" required  name=\"E_mail\" /> </td>\n";
                          student+="</tr>\n";
                          student+="<tr> \n";
                            student+="<th scope=\"row\">father name : </th>\n";
                            student+="<td><input type=\"text\" name=\"father_name\"  value=\""+s8+"\"  /> </td>\n";
                          student+="</tr>\n";
                    student+="</tbody>\n";
                student+="</table>\n";
                student+="<input type=\"submit\" value=\"Update\" />\n";
            student+="</form>\n";
                
            student+="</div>\n";
        student+="</div>\n";
        op+=student+"</bpdy></html>";
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
