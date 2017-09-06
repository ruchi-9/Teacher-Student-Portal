import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T_login extends HttpServlet {
       int i,j,k,l,m,flag;
       String ID,pass, password,output,name;
       String yr1[],yr2[],yr3[],yr4[];
       String evn[]= new String[30];
       String yrsub1[],yrsub2[],yrsub3[],yrsub4[];
       boolean year1,year2,year3,year4,error;
     @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
            int profile=0;
                  ID=request.getParameter("name");
                  pass=request.getParameter("pass");
            PrintWriter writer = response.getWriter();
             System.out.println("in teacher servlet");
             int i,j; 
                    Cookie cookie = null;
	            Cookie[] cookies = null;
                    cookies = request.getCookies();
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
                            {   if(pass==null)
                                pass=cookie.getValue();
                            }}
                              //ID=(String)request.getParameter("name");
             
             Cookie fname = new Cookie("ID",ID);
             fname.setMaxAge(60*60*24);
             response.addCookie( fname );
           //  String pass=(String)request.getParameter("pass");
             
             //retrive data from teacer record 
             teacher_record tr = new teacher_record(ID);
             name = tr.name();
             tr.data();
             error=tr.error;
             yr1=tr.yrr1();
             year1=tr.year1status();
             yr2=tr.yrr2();
             year2=tr.year2status();
             yr3=tr.yrr3();
             year3=tr.year3status();
             yr4=tr.yrr4();
             year4=tr.year4status();
             //data retrive complete from teacer record 
             
             System.out.println(name);
             System.out.println(ID);
             //System.out.println(pass);
             database db=new database();
                 Connection con=db.getCon();
                
                 try{
                     System.out.println("before select into t_login");
                      PreparedStatement ps = con.prepareStatement("SELECT password FROM teacher_login where contact_no = ?");
                      ps.setString(1,ID);
                      System.out.println("before the exection ");
                      ResultSet rs = ps.executeQuery();
                                 System.out.println("after the exection "+rs);
		    if(rs.next())
		     {
                         password=rs.getString("password");
                         System.out.println("password is"+password);
                         if(password.equals(pass))
                            {
		          	flag=1;
                            }
		     
                 
                    }
            
                  }
               catch(Exception ex)
               {
                   System.out.println("exceptionnnnnnnnnnnn");
                   System.out.println(ex);
               }
                 System.out.println("value of flag is :-" + flag);
        
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
                 
                 if(flag==0)
                 {
                  
                  response.sendRedirect("home");
                  
                 }
                 else if(!error)
                 {
                     
                     
                   
             // Top part
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
             output+="<link rel=\"stylesheet\" href=\"https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css\">\n";
             output+="<link href=\"css/teachers.css\" rel=\"stylesheet\" type=\"text/css\"/>";
             output+="<script src=\"scripts/time.js\"></script>\n";
             output+="<script>\n" +
"                 function abc()\n" +
"                 {\n" +
"                     \n" +
"                 var x = document.getElementById(\"ab\");\n" +
"                 var y =x.text;\n" +
"                 document.cookie = \"subject=\"+y;\n" +
"                \n" +
"                 }\n" +
"             </script>";
             
        output+="</head>\n";
        output+="<body onload=\"startTime()\">\n";
            output+="<div class=\"container\">\n";
                output+="<div class=\"row\" style=\" min-height: 150px; padding: 0px; border-radius: 15px;\">\n";
                    output+="<div>\n";
                    output+="<div class=\"col-lg-2 \" style=\"text-align: right; float: left\"> \n";
                            output+="<p id=\"time\" style=\"padding-top:30px; padding-bottom: 0px; color: black;font-variant:small-caps; font-style:normal;  \"></p>\n";
                            output+="<p id=\"txt\" style=\"color: black; padding-top: 0px ;font-variant:small-caps; font-style:normal;\" ></p>\n";

                        output+="</div>\n";

                        output+="<div class=\"col-lg-8\" style=\"text-align: center\"> <h1 style=\"color: black ;font-variant:small-caps; font-style:normal; \">Teacher-Student Portal</h1>\n";
                          output+="<p> B.K. Birla Institute Of Engineering and Technology</p>\n";
                          output+="</p>\n";
                   output+=" </div>\n";
                    output+=" <div class=\"col-lg-2\" style=\""
                            + " text-align: center; float: right \" >\n";
                         output+="<p style=\" color: black\">\n";
                             output+="<br/>\n";
                             output+="Name :"+name+"<br />\n";
                             output+="Login Id :"+ ID+"<br/>\n";
                             output+="<br/><br/>\n";
                             output+="<a href=\"logout\" style=\" color:black\">Logout</a>\n";
                        output+=" </p>       \n";
                     output+="</div>\n";
                    output+="</div>\n";
                output+="</div>\n";

            output+="</div>\n";
 
            // main part

            output+="<div class=\"container left\" >\n";

                 output+="<div class=\"col-md-3 left\" >";
                     output+="<div style=\" text-align: center\"> <h4>SUBJECT</h4 ></div>";
                           output+="<div class=\"panel-group\" id=\"accordion\" role=\"tablist\" aria-multiselectable=\"true\">";
      
                           // define menu bar dynamically
                           output+="<div class=\"panel panel-primary\" style=\"background-color:transparent;border-color:white\">\n";
              output+="<div class=\"panel-heading\" role=\"tab\" id=\"headingOne\" style=\"background-color:transparent;border-color:white\">\n";
                output+="<h4 class=\"panel-title\">\n";
                 output+="<a role=\"button\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"\" aria-expanded=\"false\" aria-controls=\"collapseOne\" class=\"collapsed\">\n";
                    output+="<a href=\"T_login\">Home</a>\n";
                 output+="</a>\n";
                output+="</h4>\n";
              output+="</div>\n";
           output+="</div>\n";
           
           
           //define query in menu
           output+="<div class=\"panel panel-primary\" style=\"background-color:transparent;border-color:white\">\n";
              output+="<div class=\"panel-heading\" role=\"tab\" id=\"headingOne\" style=\"background-color:transparent;border-color:white\">\n";
                output+="<h4 class=\"panel-title\">\n";
                 output+="<a role=\"button\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"\" aria-expanded=\"false\" aria-controls=\"collapseOne\" class=\"collapsed\">\n";
                    output+="<a href=\"subjectdetails?query=2\">Query</a>\n";
                 output+="</a>\n";
                output+="</h4>\n";
              output+="</div>\n";
           output+="</div>\n";
           
                 output+="<div class=\"panel panel-primary\" style=\"background-color:transparent;border-color:white\">\n";
              output+="<div class=\"panel-heading\" role=\"tab\" id=\"headingOne\" style=\"background-color:transparent;border-color:white\">\n";
                output+="<h4 class=\"panel-title\">\n";
                 output+="<a role=\"button\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapseO\" aria-expanded=\"false\" aria-controls=\"collapseOne\" class=\"collapsed\">\n";
                    output+="Deshbord <span class=\"glyphicon glyphicon-plus pull-right\" aria-hidden=\"true\"></span>\n";
                 output+="</a>\n";
                output+="</h4>\n";
              output+="</div>\n";
           output+="<div id=\"collapseO\" class=\"panel-collapse collapse\" role=\"tabpanel\" aria-labelledby=\"headingOne\" aria-expanded=\"false\" style=\"height: 0px;\">\n";
               output+="<div class=\"panel-body\">\n";
                 output+="<div class=\"list-group\">\n";

                   output+="<a  href=\"subjectdetails?profile=1\" class=\"list-group-item\" ><span class=\"glyphicon glyphicon-link\" aria-hidden=\"true\"></span>\n";
                        output+="profile</a>\n";
                 output+="</div>\n";
               output+="</div>\n";
            output+="</div>\n";
           output+="</div>\n";
           
           
                          if(year1) {
                     output+="<div class=\"panel panel-primary\">";
        output+="<div class=\"panel-heading\" role=\"tab\" id=\"headingOne\">";
          output+="<h4 class=\"panel-title\">";
                output+="<a role=\"button\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapseOne\" aria-hidden=\"true\" aria-controls=\"collapseOne\" class=\"collapsed\">";
              output+="Year 1 <span class=\"glyphicon glyphicon-plus pull-right\" aria-hidden=\"true\"></span>";
            output+="</a>";
          output+="</h4>";
        output+="</div>";
        output+="<div id=\"collapseOne\" class=\"panel-collapse collapse\" role=\"tabpanel\" aria-labelledby=\"headingOne\" aria-expanded=\"false\" style=\"height: 0px;\">";
          output+="<div class=\"panel-body\">";
            output+="<div class=\"list-group\">";
            i=0;  
            while(yr1[i]!=null)
              {
            output+="<a href=\"subjectdetails?subject="+yr1[i]+"\" class=\"list-group-item\"> <span class=\"glyphicon glyphicon-link\" aria-hidden=\"true\"></span>"+ yr1[i]+"</a>";
              i++;
              }

output+="</div>";
          output+="</div>";
        output+="</div>";
      output+="</div>";
      
                 }
                           if(year2){
      output+="<div class=\"panel panel-primary\" style=\"background-color:transparent;border-color:white\">";
        output+="<div class=\"panel-heading\" role=\"tab\" id=\"headingThree\" style=\"background-color:transparent;border-color:white\">";
          output+="<h4 class=\"panel-title\">";
            output+="<a class=\"\" role=\"button\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapseThree\" aria-expanded=\"true\" aria-controls=\"collapseThree\">";
              output+="Year 2<span class=\"glyphicon glyphicon-plus pull-right\" aria-hidden=\"true\"></span>";
            output+="</a>";
          output+="</h4>";
        output+="</div>";
        output+="<div id=\"collapseThree\" class=\"panel-collapse collapse in\" role=\"tabpanel\" aria-labelledby=\"headingThree\" aria-expanded=\"false\">";
          output+="<div class=\"panel-body\">";
            output+="<div class=\"list-group\">";
            j=0;  
            while(yr2[j]!=null)
              {
            output+="<a href=\"subjectdetails?subject="+yr2[j]+"\" class=\"list-group-item\"> <span class=\"glyphicon glyphicon-link\" aria-hidden=\"true\"></span>"+ yr2[j]+"</a>";
              j++;
              }
output+="</div>";
          output+="</div>";
        output+="</div>";
      output+="</div>";
                           }
                           
                           if(year3){
      output+="<div class=\"panel panel-primary\" style=\"background-color:transparent;border-color:white\">";
        output+="<div class=\"panel-heading\" role=\"tab\" id=\"headingThree\" style=\"background-color:transparent;border-color:white\">";
          output+="<h4 class=\"panel-title\">";
            output+="<a class=\"\" role=\"button\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapseThree\" aria-expanded=\"false\" aria-controls=\"collapseThree\">";
              output+="Year 3 <span class=\"glyphicon glyphicon-plus pull-right\" aria-hidden=\"true\"></span>";
            output+="</a>";
          output+="</h4>";
        output+="</div>";
        output+="<div id=\"collapseThree\" class=\"panel-collapse collapse in\" role=\"tabpanel\" aria-labelledby=\"headingThree\" aria-expanded=\"\">";
          output+="<div class=\"panel-body\">";
            output+="<div class=\"list-group\">";
               k=0;  
            while(yr3[k]!=null)
              {
            output+="<a href=\"subjectdetails?subject="+yr3[k]+"\" class=\"list-group-item\"> <span class=\"glyphicon glyphicon-link\" aria-hidden=\"true\"></span>"+ yr3[k]+"</a>";
              k++;
              }

output+="</div>";
          output+="</div>";
        output+="</div>";
      output+="</div>";
                           }
                           if(year4){
      output+="<div class=\"panel panel-primary\" style=\"background-color:transparent;border-color:white\">";
        output+="<div class=\"panel-heading\" role=\"tab\" id=\"headingThree\" style=\"background-color:transparent;border-color:white\">";
          output+="<h4 class=\"panel-title\">";
            output+="<a class=\"\" role=\"button\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapseThre\" aria-expanded=\"true\" aria-controls=\"collapseThree\">";
              output+="Year 4 <span class=\"glyphicon glyphicon-plus pull-right\" aria-hidden=\"true\"></span>";
            output+="</a>";
          output+="</h4>";
        output+="</div>";
        output+="<div id=\"collapseThre\" class=\"panel-collapse collapse in\" role=\"tabpanel\" aria-labelledby=\"headingThree\" aria-expanded=\"true\">";
          output+="<div class=\"panel-body\">";
            output+="<div class=\"list-group\">";
l=0;  
            while(yr4[l]!=null)
              {
            output+="<a href=\"subjectdetails?subject="+yr4[l]+"\" class=\"list-group-item\"> <span class=\"glyphicon glyphicon-link\" aria-hidden=\"true\"></span>"+ yr4[l]+"</a>";
              l++;
              }
output+="</div>";
          output+="</div>";
        output+="</div>";
      output+="</div>";
                           }
                           
    output+="</div>";
               output+="</div>";

                 
               //center division
               output+="<div class=\"col-md-6 center\" >\n";

               if(profile==1)
               {
               }
               else if(profile!=2)
               {
               
                     output+="<div style=\"text-align: center; display: inline;\">\n";
                         output+="  <h3 style=\"text-align: center; \"> About B K Birla Institute of Engineering & Technology - BKBIET</h3>\n" +
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
"<h3 style=\"text-align: center; \">Vision</h3>\n" +
"\n" +
"In the age of globalization, to build a truly different institution of engineering and technology in Rajasthan, an institution with a marked difference in quality to serve the local people in global arena, has been our vision – the vision of Birlas.\n" +
"\n" +
" \n" +
"\n" +
"<h3 style=\"text-align: center; \">Mission</h3>\n" +
"\n" +
"Our aim is to build an effervescent community of engineers, where faculty and students are cronies in a mutually inspirational education process, engrossed in learning. This learning process would lead to the inventions and then to discoveries\n" +
"";
                     output+="</div>\n";
               }
                     output+="</div>\n";
                
                 //center div end
                    output+="<div class=\"col-md-3 right\" >\n";
                        output+="<h4 id=\"aa\">  Event And Notice </h4>\n";
                        output+="<marquee direction=\"up\" scrolldelay=\"200\"  style=\"text-align:center; width: 100%; height:400px\" onmouseover=\"this.stop();\" onmouseout=\"this.start();\">\n";
                       // show event into scrolling tag
                         int ee=0;
                         while(evn[ee]!=null)
                         {
                        output+="<div id=\"\" style=\" height:auto; width: 100%; border-bottom: 1px solid #000;  padding: 10px\">\n";
                        output+=evn[ee]+"\n";
                        ee++;
                        output+="</div>\n"; 
                         }
                        output+="</marquee>\n";
                    output+="</div>\n";
             output+=" </div>\n";

        output+="</body>\n";
    output+="</html>\n";

                     
                       
                     writer.println(output);
                 
                 }
                 else
                 {
                 
                 writer.println("error into data base contivity please try after sometime ");
                 }
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
