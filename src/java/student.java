import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class student extends HttpServlet {
   String ID,password,username,output,branch,pass,subject;
   String sub[]=new String[15];
   String Subjectname[] = new String[15];
   int sem,i,j;
   String evn[]= new String[30];
   static int flag;
   String scode[]=new String[10]; 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException ,IOException {
        response.setContentType("text/html;charset=UTF-8");
        subject=request.getParameter("subject");
                  ID=request.getParameter("name");
                  System.out.println("id in student ="+ID);
                  pass=request.getParameter("pass");
                 int i,j; 
                 //getting cookie data 
                 Cookie cookie = null;
	         Cookie[] cookies = null;
                 cookies = request.getCookies();
                 if(cookies!=null){  
                 for ( i = 0; i < cookies.length; i++)
                    {
                          cookie = cookies[i];
                        if(cookie.getName().equals("name"))
                     {   if(ID==null)
                         ID=cookie.getValue();
                        //s1=ID;
                         System.out.println("Id = "+ID);
                    }
                      if(cookie.getName().equals("pass"))
                      {    if(pass==null)
                          pass=cookie.getValue();
                    }}
                 }else{}
                 // end getting data
                 PrintWriter writer = response.getWriter();
                 
                 //validation the login 
                 database db=new database();
                 Connection con=db.getCon();
                   try{
                       System.out.println("before select into student login");
                       PreparedStatement ps = con.prepareStatement("SELECT password,s_name FROM stuent_login where student_id = ?");
                       ps.setString(1,ID);
                       System.out.println("before the exection ");
                       ResultSet rs = ps.executeQuery();
                       System.out.println("after the exection "+rs);
		    if(rs.next())
		     {
                         password=rs.getString("password");
                         System.out.println("password is"+password);
                         username=rs.getString("s_name");
                         Cookie cookie2 =new Cookie("username",username);
                         response.addCookie(cookie2);
                         System.out.println("password is ="+password +"matching password is ="+pass);
                         if(password.equals(pass))
                            {
		          	flag=1;
                                logincheck lc = new logincheck(ID);
                                branch=logincheck.branch;
                                sem=logincheck.sem; 
                                System.out.println("sem is "+sem);
                                System.out.println("branch is "+branch);
                            }
		    }
                  }
               catch(Exception ex)
               {
                   System.out.println("exceptionnnnnnnnnnnn");
                   System.out.println(ex);
               }
                   
                   //retriving data from database
                   if (sem!=0)
                   {
                   try
                   {
                       String a=Integer.toString(sem);
                   PreparedStatement ps1 = con.prepareStatement("SELECT * from course where sem=? and branch=?");
                   ps1.setString(1,a);
                   ps1.setString(2,branch);
                   ResultSet rs1 =ps1.executeQuery();
                   i=0;
                   if(rs1.next())
                   {
                       System.out.println("11111");
                       j=3;
                       i=0;
                   while(!((rs1.getString(j).trim()).equals("")))
                   {
                     scode[i]=rs1.getString(j);
                     System.out.println("code "+i+"   "+scode[i]);
                     i++;j++;
                   }
                   }}catch(Exception e){System.out.print("exception in dataretrive"+e);}
                   try{   
                   j=0;
                      i=0;
                      while(!scode[j].equals(null))
                      {
                       sub[i]=scode[i];
                       System.out.println(sub[i]);
                       subjectname sn = new subjectname(sub[i]);
                       Subjectname[i]=sn.show();
                       System.out.println(i);
                       i++;
                       j++;
                     }   
                   System.out.println("length of array"+scode.length);
                   }catch(Exception e)
                   {
                   System.out.println("exception in data define"+e);
                   }
                   }
                   
                   
                   
                   if(flag==0)
                   {
                      response.sendRedirect("home");
                   }
                 else
                   {
                    HttpSession session=request.getSession();
                    session.setAttribute("ID",ID);
                     // events and notice
                    try
                    {
                        PreparedStatement ps1 = con.prepareStatement("SELECT * from event");
                        ResultSet rs =ps1.executeQuery();
                        int event=0;
                        while(rs.next())
		     {
                          evn[event]=rs.getString(2);
                          System.out.println("data inside event ="+evn[event]);
                          event++;
                     }
                    }
                   catch(Exception e)
                    {
                        System.out.println("Excetption in event"+e);
                    }
                   try{con.close(); }catch(Exception e){System.out.println(e);}
                   
                   output="";
                       output="<!DOCTYPE html>\n";
    output+="<html>\n";
        output+="<head>\n";
            output+=" <title>welcome to bkbiet Teacher panel </title>\n";
             output+="<meta charset=\"UTF-8\">\n";
             output+="<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n";
             output+="<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\n";
             output+="<script src=\"jquery/jquery-3.1.1.min.js\"></script>\n";
             output+="<script src=\"js/bootstrap.min.js\"></script>\n";
             output+="<script src=\"https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js\"></script>\n";
             output+="<link href=\"css/teachers.css\" rel=\"stylesheet\" type=\"text/css\"/>";
             output+="<link rel=\"stylesheet\" href=\"https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css\">\n";
             output+="<script src=\"scripts/time.js\"></script>\n";
            
             
            output+="</head>\n";
            output+="<body onload=\"startTime()\">\n";
            output+="<div class=\"container\">\n";
                    output+="<div class=\"row\" style=\" min-height: 150px;padding: 0px; background:transprant; background-color:none; border-radius: 15px;\">\n";
                    output+="<div>\n";
                    output+="<div class=\"col-lg-2\" style=\"text-align: right; float: left\"> \n";
                            output+="<p id=\"time\" style=\"padding-top:30px; padding-bottom: 0px; color: black;font-variant:small-caps; font-style:normal;  \"></p>\n";
                            output+="<p id=\"txt\" style=\"color: black; padding-top: 0px ;font-variant:small-caps; font-style:normal;\" ></p>\n";

                          output+="</div>\n";
                          output+="<div class=\"col-lg-8\" style=\"text-align: center\"> <h1 style=\"color: black ;font-variant:small-caps; font-style:normal; \">Teacher-Student Portal</h1>\n";
                          output+="<p> B.K. Birla Institute Of Engineering and Technology</p>\n";
                          output+="</p>\n";
                          output+=" </div>\n";
                          output+=" <div class=\"col-lg-2\" style=\"background:transparent; text-align: center; float: right \" >\n";
                          output+="<p style=\" color: black\">\n";
                             output+="<br/>\n";
                             output+="Name :"+username+"<br />\n";
                             output+="Login Id :"+ ID+"<br/>\n";
                             output+="<br/><br/>\n";
                             output+="<a href=\"logout\" style=\" color: black\">Logout</a>\n";
                             output+=" </p>       \n";
                             output+="</div>\n";
                             output+="</div>\n";
                             output+="</div>\n";
             
            output+="</div>\n";
            
            
               output+="<div class=\"container\" style=\"padding: 0px; margin:auto; padding-top: 2px; border-radius: 15px;\">\n";
           output+="<div class=\"col-md-3 left\">\n";
   
           //menu bar start
           
         output+="<div class=\"panel-group\" id=\"accordion\" role=\"tablist\" aria-multiselectable=\"true\">\n";
           output+="<div class=\"panel panel-primary\" style=\"background-color:transparent;border-color:white\">\n";
              output+="<div class=\"panel-heading\" role=\"tab\" id=\"headingOne\" style=\"background-color:transparent;border-color:white\">\n";
                output+="<h4 class=\"panel-title\">\n";
                 output+="<a role=\"button\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"\" aria-expanded=\"false\" aria-controls=\"collapseOne\" class=\"collapsed\">\n";
                    output+="<a href=\"student\">Home</a>\n";
                 output+="</a>\n";
                output+="</h4>\n";
              output+="</div>\n";
           output+="</div>\n";
           
           
           output+="<div class=\"panel panel-primary\" style=\"background-color:transparent;border-color:white\">\n";
              output+="<div class=\"panel-heading\" role=\"tab\" id=\"headingOne\" style=\"background-color:transparent;border-color:white\">\n";
                output+="<h4 class=\"panel-title\">\n";
                 output+="<a role=\"button\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapseOne\" aria-expanded=\"false\" aria-controls=\"collapseOne\" class=\"collapsed\">\n";
                    output+="Deshbord <span class=\"glyphicon glyphicon-plus pull-right\" aria-hidden=\"true\"></span>\n";
                 output+="</a>\n";
                output+="</h4>\n";
              output+="</div>\n";
           output+="<div id=\"collapseOne\" class=\"panel-collapse collapse\" role=\"tabpanel\" aria-labelledby=\"headingOne\" aria-expanded=\"false\" style=\"height: 0px;\">\n";
               output+="<div class=\"panel-body\">\n";
                 output+="<div class=\"list-group\">\n";

                   output+="<a  href=\"ssubject?profile=1\" class=\"list-group-item\" ><span class=\"glyphicon glyphicon-link\" aria-hidden=\"true\"></span>\n";
                        output+="profile</a>\n";
                 output+="</div>\n";
               output+="</div>\n";
            output+="</div>\n";
           output+="</div>\n";
           
           //query menu
           output+="<div class=\"panel panel-primary\" style=\"background-color:transparent;border-color:white\">\n";
              output+="<div class=\"panel-heading\" role=\"tab\" id=\"headingOne\" style=\"background-color:transparent;border-color:white\">\n";
                output+="<h4 class=\"panel-title\">\n";
                 output+="<a role=\"button\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapsequery\" aria-expanded=\"false\" aria-controls=\"collapsequery\" class=\"collapsed\">\n";
                   output+="Query <span class=\"glyphicon glyphicon-plus pull-right\" aria-hidden=\"true\"></span>\n";
                   output+="</a>\n";
                   output+="</h4>\n";
                   output+="</div>\n";
                   output+="<div id=\"collapsequery\" class=\"panel-collapse collapse\" role=\"tabpanel\" aria-labelledby=\"headingOne\" aria-expanded=\"false\" style=\"height: 0px;\">\n";
                   output+="<div class=\"panel-body\">\n";
                   output+="<div class=\"list-group\">\n";
                   output+="<a  href=\"ssubject?query=1\" class=\"list-group-item\" ><span class=\"glyphicon glyphicon-link\" aria-hidden=\"true\"></span>\n";
                   output+="New Query</a>\n";
                   output+="<a  href=\"ssubject?query=2\" class=\"list-group-item\" ><span class=\"glyphicon glyphicon-link\" aria-hidden=\"true\"></span>\n";
                   output+="Old Query</a>\n";
                   output+="</div>\n";
               output+="</div>\n";
            output+="</div>\n";
           output+="</div>\n";
           
           
        output+="<div class=\"panel panel-primary\" style=\"background-color:transparent;border-color:white\">\n";
        output+="<div class=\"panel-heading\" role=\"tab\" id=\"headingThree\" style=\"background-color:transparent;border-color:white\">\n";
          output+="<h4 class=\"panel-title\">\n";
            output+="<a class=\"\" role=\"button\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapseThree\" aria-expanded=\"true\" aria-controls=\"collapseThree\">\n";
             output+=" Notes <span class=\"glyphicon glyphicon-plus pull-right\" aria-hidden=\"true\"></span>\n";
            output+="</a>\n";
          output+="</h4>\n";
        output+="</div>\n";
        output+="<div id=\"collapseThree\" class=\"panel-collapse collapse in\" role=\"tabpanel\" aria-labelledby=\"headingThree\" aria-expanded=\"true\">\n";
         output+=" <div class=\"panel-body\">\n";
            output+="<div class=\"list-group\">\n";
             i=0;
               while(sub[i]!=null)
             {
            output+="<a  href=\"ssubject?subject="+sub[i]+"\" class=\"list-group-item\" ><span class=\"glyphicon glyphicon-link\" aria-hidden=\"true\"></span>\n";
                     output+=  Subjectname[i]+"("+sub[i]+")"+" </a>\n";
                     i++;
                    }
            output+="</div>\n"; 
          output+="</div>\n";
       output+=" </div>\n";
      output+="</div>\n";
    output+="</div>\n";
           
           
            output+="</div>\n";
            
            //menu bar end
            
                 output+="<div class=\"col-md-6 center\" >\n";
                        output+="  <h3 style=\"text-align: center; color:black\"> About B K Birla Institute of Engineering & Technology - BKBIET</h3>\n" +
"\n" +
"<h5 style=\"text-align:left\">B K Birla Institute of Engineering & Technology (BKBIET) is a well known name in the field of engineering and tech streams. \n" +
"The institute is located in Pilani, which comes under the Jhunjhunu district, Rajasthan.\n" +
"BKBIET was set up in 2007 with the vision is “to build a truly different institution of engineering and technology in Rajasthan, an institution with a marked difference in quality to serve the local people in global arena”.\n" +
"The college is affiliated to Rajasthan Technical University (RTU) and approved by All India Council of Technical Education (AICTE).\n" +
"The mission of BKBIET is “to build an effervescent community of engineers, where faculty and students are cronies in a mutually inspirational education process, engrossed in learning. \n" +
"This learning process would lead to the inventions and then to discoveries”. \n" +
"BKBIET has a large campus spread over 18 acres of land. \n" +
"The campus comprises of pool of facilities with state of the art infrastructure</h5>\n" +
"\n" +
"\n" +
"\n" +
"<h3 style=\"text-align: center; color:black\">Vision</h3>\n" +
"\n" +
"In the age of globalization, to build a truly different institution of engineering and technology in Rajasthan, an institution with a marked difference in quality to serve the local people in global arena, has been our vision – the vision of Birlas.\n" +
"\n" +
" \n" +
"\n" +
"<h3 style=\"text-align: center; color:black\">Mission</h3>\n" +
"\n" +
"Our aim is to build an effervescent community of engineers, where faculty and students are cronies in a mutually inspirational education process, engrossed in learning. This learning process would lead to the inventions and then to discoveries\n" +
"";
                 output+="</div>\n";
                    output+="<div class=\"col-md-3 right right\" >\n";
                        output+="<h4 id=\"aa\">  Event And Notice </h4>\n";
                        output+="<marquee direction=\"up\" scrolldelay=\"200\"  style=\"text-align:center; width: 100%; height:400px\" onmouseover=\"this.stop();\" onmouseout=\"this.start();\">\n";
                       // show event into scrolling tag
                         int ee=0;
                         while(evn[ee]!=null)
                         {
                        output+="<div id=\"\" style=\" height:auto; width: 100%; border-bottom: 1px solid #000; color: black; padding: 10px\">\n";
                        output+=evn[ee]+"\n";
                        ee++;
                        output+="</div>\n"; 
                         }
                        output+="</marquee>\n";
                    output+="</div>\n";
              output+="</div>\n";
            
            
            output+="</body>\n";
    output+="</html>\n";

                     
                       
                     writer.println(output);
 
                   }
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
