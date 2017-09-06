import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class C_search extends HttpServlet {
 String s1,s2,s3,s4,s5,s6,s7,s8,s9,a;
 String sub[]=new String[10];
  String code[]=new String[9];
      ResultSet rs1;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             String sem=request.getParameter("sem");
             String branch=request.getParameter("branch");
          try {
              database db=new database();
              Connection con=db.getCon();
              System.out.println("in course search");
              PreparedStatement ps1 = con.prepareStatement("select * from course where sem = ? and branch=?");
              ps1.setString(1,sem);
              ps1.setString(2,branch);
               System.out.println(sem);
              System.out.println(branch);
              ResultSet rs = ps1.executeQuery();
              System.out.println("1111111111111111111");
              System.out.println(rs);
			if( rs.next())
                          {
                              code[0]=rs.getString("sub1");
                              code[1]= rs.getString("sub2");
                              code[2]= rs.getString("sub3");
                              code[3]= rs.getString("sub4");
                              code[4]= rs.getString("sub5");
                              code[5]= rs.getString("sub6");
                              code[6]= rs.getString("sub7");
                              code[7]= rs.getString("sub8");
                              code[8]= rs.getString("sub9");
                               System.out.println(s2);
                               System.out.println(s3);
                               System.out.println(s4);
                               System.out.println(s5);
                               System.out.println(s6);
                               a="Record Found ";
                        }
                        else {
                            System.out.println("wrong id entered");
                            a="record not found";
												   
                        }
                         for(int i=0;i<code.length;i++)
                      {
                         PreparedStatement ps = con.prepareStatement("select subject from code where code = ?");
                         ps.setString(1,code[i]);
                         rs1 = ps.executeQuery();
                         System.out.println("1111111111111111111");
                         System.out.println(rs1);
			 if( rs1.next())
                            {
                                sub[i]=rs1.getString("subject");
                            }
                       }
                       }
        // now the code is in array code[ ]  and subject is in array sub[ ]
catch(Exception ex1)
{
	System.out.println(ex1);
}				
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

     student+="<div class=\"container\" id=\"sreg\" style=\"margin-top: 1px;  padding: 0px;text-align: center; border-radius: 15px;\">";
            student+="<div class=\"container\" id=\"maindiv\" style=\" max-width: 800px\">";
                student+="<h2> Add New Course </h2>";
                student+="<div style=\"all:unset\"><br/>";
                    student+="<form  class=\"pure-form\" action=\"C_update\" method=\"post\" role=\"form\">";
                    student+="<fieldset>";
        	      student+="<legend>Semister and Branch</legend>";
                      student+="<label for=\"Semister\" style=\"color:black\">Semester:</label>";
        	         student+="<select name=\"sem\" id=\"sem\" style=\"background-color:white; color:black\">";
                            student+="<option>Select semister</option>";
                          for(int i=0;i<8;i++)
                          {
                              if((i+1)==Integer.parseInt(sem))    
                                  student+="<option selected>"+(i+1)+"</option>";
                             else
                                 student+="<option>"+(i+1)+"</option>";
                          
                          }
                            student+="</select>";
                         String brnch[]={"Computer Science","Information Technology","Electrical Engg","Electrical and communication","EEE"};   
                      student+="<label for=\"Branch\"  style=\"color:black\">Branch:</label>";
                      student+="<select name=\"branch\" id=\"branch\" style=\"color:black\">";
                          student+="<option>Select Branch </option>";
                          for(int j=0;j<5;j++)
                          {
                              if(brnch[j].equals(branch))
                              {
                                  student+="<option selected>"+brnch[j]+"</option>";
                              }
                          else
                              {
                                  student+="<option>"+brnch[j]+"</option>";
                              }
                          
                          }
                      student+="</select>";
                    student+="</fieldset><br/>";
              student+="<fieldset>";
                  
        	  student+="<legend>Reguler Subject</legend>";
                  
                  student+="<p> <label for=\"sub1\">Subject 1:</label>";
        	  student+="<input type=\"text\" name=\"SUB1\"  value=\""+sub[0]+"\" id=\"user-name\">";
                  student+="<label for=\"sub2\">Subject code 1:</label>";
        	  student+="<input type=\"text\" name=\"code1\" pattern=\"[1-8]+(cs|CS|ec|EC|ee|EE|ex|EX)+[0-9A-Za-z]{1,3}\" value=\""+code[0]+"\" id=\"user-name\"></p><br/>";
                  student+="<p>";
                  student+="<label for=\"sub3\">Subject 2:</label>";
        	  student+="<input type=\"text\" name=\"SUB1\" value=\""+sub[1]+"\" id=\"user-name\">";
                  student+="<label for=\"sub4\">Subject code 2:</label>";
        	  student+="<input type=\"text\" name=\"code1\" pattern=\"[1-8]+(cs|CS|ec|EC|ee|EE|ex|EX)+[0-9A-Za-z]{1,3}\" value=\""+code[1]+"\" id=\"user-name\"></p><br/>";
                  student+="<p>";
                  student+="<label for=\"sub5\">Subject 3:</label>";
        	  student+="<input type=\"text\" name=\"SUB1\" value=\""+sub[2]+"\" id=\"user-name\">";
                  student+="<label for=\"sub6\">Subject code 3:</label>";
        	  student+="<input type=\"text\" name=\"code1\" pattern=\"[1-8]+(cs|CS|ec|EC|ee|EE|ex|EX)+[0-9A-Za-z]{1,3}\" value=\""+code[2]+"\" id=\"user-name\"></p><br/>";
                 student+="<p>";
                  student+="<label for=\"sub5\">Subject 4:</label>";
        	  student+="<input type=\"text\" name=\"SUB1\" value=\""+sub[3]+"\" id=\"user-name\">";
                  student+="<label for=\"sub6\">Subject code 4:</label>";
        	  student+="<input type=\"text\" name=\"code1\" pattern=\"[1-8]+(cs|CS|ec|EC|ee|EE|ex|EX)+[0-9A-Za-z]{1,3}\" value=\""+code[3]+"\" id=\"user-name\"></p><br/>";
                 student+="<p> ";
                 student+="<label for=\"sub5\">Subject 5:</label>";
        	  student+="<input type=\"text\" name=\"SUB1\"  value=\""+sub[4]+"\" id=\"user-name\">";
                  student+="<label for=\"sub6\">Subject code 5:</label>";
        	  student+="<input type=\"text\" name=\"code1\" pattern=\"[1-8]+(cs|CS|ec|EC|ee|EE|ex|EX)+[0-9A-Za-z]{1,3}\" value=\""+code[4]+"\" id=\"user-name\"></p><br/>";
                 student+="<p>";
                 student+="<label for=\"sub5\">Subject 6:</label>";
        	  student+="<input type=\"text\" name=\"SUB1\" value=\""+sub[5]+"\" id=\"user-name\">";
                  student+="<label for=\"sub6\">Subject code 6:</label>";
        	  student+="<input type=\"text\" name=\"code1\" pattern=\"[1-8]+(cs|CS|ec|EC|ee|EE|ex|EX)+[0-9A-Za-z]{1,3}\" value=\""+code[5]+"\" id=\"user-name\"></p><br/>";
                  student+="<label for\"sub5\">Optional Subject 1:</label>";
        	  student+="<input type=\"text\" name=\"SUB1\" value=\""+sub[6]+"\" id=\"user-name\">";
                  student+="<label for=\"sub6\">Optional Subject code 1:</label>";
        	  student+="<input type=\"text\" name=\"code1\" pattern=\"[1-8]+(cs|CS|ec|EC|ee|EE|ex|EX)+[0-9A-Za-z]{1,3}\" value=\""+code[6]+"\" id=\"user-name\"></p><br/>";
                 student+="<p>";
                 student+="<label for=\"sub5\">Optional Subject 2:</label>";
        	  student+="<input type=\"text\" name=\"SUB1\" value=\""+sub[7]+"\" id=\"user-name\">";
                  student+="<label for=\"sub6\">Optional Subject code 2:</label>";
        	  student+="<input type=\"text\" name=\"code1\" pattern=\"[1-8]+(cs|CS|ec|EC|ee|EE|ex|EX)+[0-9A-Za-z]{1,3}\" value=\""+code[7]+"\" id=\"user-name\"></p><br/>";
                 student+="<p>";
                 student+="<label for=\"sub5\">Optional Subject 3:</label>";
        	  student+="<input type=\"text\" name=\"SUB1\" value=\""+sub[8]+"\" id=\"user-name\">";
                  student+="<label for=\"sub6\">Optional Subject code 3:</label>";
        	  student+="<input type=\"text\" name=\"code1\" pattern=\"[1-8]+(cs|CS|ec|EC|ee|EE|ex|EX)+[0-9A-Za-z]{1,3}\" value=\""+code[8]+"\" id=\"user-name\"></p><br/>";
                             student+="</fieldset>";
                    student+="</div>";
            student+="<br/>";
                student+="<fieldset>";
                  student+="<legend>Action</legend>";
                   student+="<input type=\"submit\" value=\"Update\">";
        	  
                student+="</fieldset>";
                student+="</form>";
              
                student+="</div>";
            
       student+="</div>";
        student+="</div>";
        op+=student+"</body></html>";
        writer.println(op);
          
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
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
