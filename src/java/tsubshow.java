import java.awt.HeadlessException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class tsubshow extends HttpServlet {

   static String s2,s3,s4,s5,a,url;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            StringBuffer sburl = request.getRequestURL();
            url = sburl.toString();
            //for subject and codde
            String sc[]=new String[10];
            String ss[]=new String[10];
            int i=0;
            
            String s1=request.getParameter("ID");
        try {
              database db=new database();
              Connection con=db.getCon();
              PreparedStatement ps1 = con.prepareStatement("select * from teacher_login where contact_no = ?");
              ps1.setString(1,s1);
              
              System.out.println(s1);
              ResultSet rs = ps1.executeQuery();
              System.out.println(rs);
			if( rs.next())
                        {
                              s2= rs.getString("name");
                              s3= rs.getString("password");
                              s4= rs.getString("contact_no");
                              s5= rs.getString("mail_id");
                              System.out.println(s2);
                               System.out.println(s3);
                                System.out.println(s4);
                                 System.out.println(s5);
                                 a="Record Found";
                        System.out.println(url);
                        }     
                    else {
                            System.out.println("wrong id entered");
                            a="Record does not Exist ";
                                    
												   }
         		 }
        
catch(Exception ex1)
{
	System.out.println(ex1);

}						   
        //retrive subject code 
        retrivecode ec = new retrivecode();
        sc=ec.code(s4);
        i=0;
        
        subjectname sn = new subjectname();
        while(!(sc[i]==null))
        {
             
             ss[i]=sn.display(sc[i]);
             System.out.println("subject is "+ss[i]);
        i++;
        }
        PrintWriter writer = response.getWriter();
        savedinfo si =new savedinfo();
        String header = si.headerpart();
        String head = si.headpart();
        String menu = si.menupart();
        String student="";
        String op ="<html>"+head+ "<body onload=\"startTime()\" style=\"\">"+header+menu;
        // to show details into student info
        student+="<div class=\"container\"  style=\"margin-top: -20px; padding:0px; color: red;text-align: center; border-radius: 15px;\"><p>";
           student+=a;
           student+="</p></div>";
            student+="<div class=\"container\" id=\"sreg\" style=\"margin-top: 1px;\">\n";
            student+="<div class=\"container\" id=\"maindiv\">\n";
            student+="<h2> Teacher Subject Define </h2>\n";
           student+="<br>\n";
      student+="<div style=\" text-align: left;\"><h5><label>  &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; Name: </label> &nbsp;&nbsp;&nbsp;<label>"+s2+"</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>Phone Number:</label><label>"+s4+"</label></h5></div>\n";
      student+="<form action=\"trshow?f=1&tid="+s4+"\" method=\"post\">\n";
      student+="<table class=\"table table-hover\"  >\n" +
"                    <tbody>\n" +
"                          <tr> \n" +
"                            <th scope=\"row\" style=\"text-align:center\"><lable>Subject</lable> </th>\n" +
"                            <td  style=\"text-align:center\"><lable>Code</lable></td>\n" ;
student+="</tr>\n";
             i=0;
                            while(!(sc[i]==null))
                                {
                          student+="<tr> \n";
                          student+="<th scope=\"row\" style=\"text-align:center color:black\">"+ss[i]+"</th>\n";
                          student+="<td style=\"text-align:center\"><input type=\"text\" pattern=\"[1-8]+(cs|CS|ec|EC|ee|EE|ex|EX)+[0-9A-Za-z]{1,3}\"  name=\"SID\" required=\"enter Subject Code\" value=\""+sc[i]+"\" /> </td>\n" ;
                          student+="</tr>\n";
                          i++;
                                  }         
                        student+="</tbody>\n" +
"                </table>\n" +
"                <input type=\"submit\" value=\"Update\" />\n" +
"            </form>\n" +
"                \n" +
"            </div>"; 
           
                    student+="</form>\n";
                student+="</div>\n";
            student+="</div>\n";
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
