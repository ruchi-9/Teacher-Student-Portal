import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class subjectdetails extends HttpServlet {
     static int i,j,k,l,m,flag;
     static String ID, password,output,name,subjectname,pass;
     static String yr1[],yr2[],yr3[],yr4[];
     static String s2,s3,s4,s5,a,url;
     static String evn[]= new String[30];
     boolean year1,year2,year3,year4,error,subjectflag;
     static String s1;
     
     int qidr[]=new int[100];
        String sidr[]=new String[100];
        String scoder[]=new String[100];
        String qsubr[]=new String[100];
        int c=0;
        int qqid=0;
     @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
             String topic[]=new String[10];
             String date[]=new String[10];
             String time[]=new String[10];
             String path[]=new String[10];
     int query=0;
     try{         
     c=Integer.parseInt(request.getParameter("counter"));
     System.out.println("value of c is ="+c);
     }catch(Exception e){}
     try{         
     qqid=Integer.parseInt(request.getParameter("qqid")); 
     System.out.println("value of c is ="+qqid);
     }catch(Exception e){}
      
     int qid=0;
             String filename[] = new String[10];
             String yrsub1[],yrsub2[],yrsub3[],yrsub4[];
     int profile=0;
    try{
        query=Integer.parseInt(request.getParameter("query"));
        }catch(Exception e)
        {
            //System.out.println("in query"+e);
        }
    try{
        qid=Integer.parseInt(request.getParameter("qid"));
        }catch(Exception e)
        {
            System.out.println("in query"+e);
        } 
     try{
        
        profile=Integer.parseInt(request.getParameter("profile"));
        
        //System.out.println("value of profile is ="+profile);
        
        }catch(Exception c){}
            subjectname="6cs4a";
            subjectflag=true;
            subjectname=request.getParameter("subject");
             PrintWriter writer = response.getWriter();
             System.out.println("url of servlet calling is ===="+subjectname);
             System.out.println("in teacher servlet");
             int i,j; 
             Cookie cookie = null;
	         Cookie[] cookies = null;
                 cookies = request.getCookies();
                    for ( i = 0; i < cookies.length; i++)
                    {
                          cookie = cookies[i];
                      if(cookie.getName().equals("name"))
                    {
                         ID=cookie.getValue();
                         //s1=ID;
                         System.out.println("Id = "+ID);
                    }
                      if(cookie.getName().equals("pass"))
                      { pass=cookie.getValue();
                    }}
             String pass=request.getParameter("password");
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
             System.out.println(pass);
             database db=new database();
                 Connection con=db.getCon();
                 //to get details of a teacher 
         if(profile==1)                
         { try {
              PreparedStatement ps1 = con.prepareStatement("select * from teacher_login where contact_no = ?");
              ps1.setString(1,ID);
              System.out.println(ID);
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
                                 a="Record Updated";
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
         }
         // end getting details of a teacher 
         
         
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
                         if(rs.getString("password").equals(pass))
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
                 try
                 {
                     PreparedStatement ps1 = con.prepareStatement("SELECT * FROM files where  subject= ?");
                     ps1.setString(1,subjectname);
                     ResultSet rsub = ps1.executeQuery();
                     i=0;
                      while(rsub.next())
                      {
                          topic[i]=rsub.getString("topic");
                          System.out.println(topic[i]);
                          date[i]=rsub.getString("date");
                          System.out.println(date[i]);
                          time[i]=rsub.getString("time");
                          System.out.println(time[i]);
                          path[i]=rsub.getString("path");
                          filename[i]=rsub.getString("name");
                            i++;
                      }
                 }
                 catch(Exception e)
                 {}
                 System.out.println("value of flag is :-" + flag);
        
                 if(profile==2)
                 {
                  s4 = request.getParameter("password");
                System.out.println("password from techr update"+s4);
                  try {
                         PreparedStatement ps = con.prepareStatement("update teacher_login set password = ? where contact_no = ?");
                              //id
                         ps.setString(1,s4);      //password
                         ps.setString(2,ID); //phone no
			 ps.executeUpdate();
                         a="update sucessfully";      
                      }
                catch(SQLException ex)
                  {
                      System.out.println("exception occur"+ex);
                      a="Update unsucessfully";
                  }
                 }
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
                    System.out.println("value of query is "+query);
                        //System.out.println(request.getParameter("query"));
                    
                    // save the replay of a query which is give by a teacher 
                    if(c!=0)
                    {
                        String studentid,subjectcode,querydata;
                        studentid=null;
                        subjectcode=null;
                        querydata=request.getParameter("querydata");
                    try{
                                PreparedStatement pqrr = con.prepareStatement("select  * from squery where qid=? and counter=1");
		                pqrr.setInt(1,qqid);
		                ResultSet rq = pqrr.executeQuery();
                                System.out.println("in retrive information for save");
                               while(rq.next())
		        	 {
	                           studentid=rq.getString(2);
                                   subjectcode=rq.getString(3);
                                 } 
                    }catch(Exception e){ System.out.println(e);}
                    try
                    {
                      PreparedStatement pq = con.prepareStatement("insert into tquery values(?,?,?,?,?,?)");
                               pq.setInt(1,qqid);
                               pq.setString(2,ID);
                               pq.setString(3,studentid);
                               pq.setString(4,subjectcode);
                               pq.setString(5,querydata);
                               pq.setInt(6,c);
                               pq.executeUpdate();
                    }
                    catch(Exception ee)
                    {
                    System.out.println(ee);
                    }
                    
                    }
                    
                    
                  //retriving query from database
                    if(query==2)
                    {
                    try{
                                PreparedStatement pqr = con.prepareStatement("select  * from squery where tid=? and counter=1");
		                pqr.setString(1,ID);
		                ResultSet rq = pqr.executeQuery();
                                int kk=0;
                                System.out.println("in retrive information ");
                               while(rq.next())
		        	 {
	                           qidr[kk]=rq.getInt(1);
                                   System.out.println(qidr[kk]);
                                   sidr[kk]=rq.getString(2);
                                   System.out.println(sidr[kk]);
                                   scoder[kk]=rq.getString(3);
                                   System.out.println(scoder[kk]);
                                kk++;
                                 } 
                            //retriving subject regarding to subjectcode
                           for(int jj=0;jj<kk;jj++)
                           {
                           subjectname sn = new subjectname(scoder[jj]);
                           qsubr[jj]=sn.show();
                           System.out.println("subject is ="+qsubr[jj]);
                           }
                            }catch(Exception e)
                               {
                                   System.out.println("in retrive query\n"+e);
                               }
                    }
                    
                  //  try{con.close(); }catch(Exception e){System.out.println(e);}
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
             output+="<script src=\"scripts/time.js\"></script>\n";
          output+="<link href=\"css/teachers.css\" rel=\"stylesheet\" type=\"text/css\"/>";
        output+="</head>\n";
        output+="<body onload=\"startTime()\">\n";
            output+="<div class=\"container\">\n";
                output+="<div class=\"row\" style=\" min-height: 150px;padding: 0px; border-radius: 15px;\">\n";
                    output+="<div>\n";
                    output+="<div class=\"col-lg-2\" style=\"text-align: right; float: left\"> \n";
                            output+="<p id=\"time\" style=\"padding-top:30px; padding-bottom: 0px; color:black;font-variant:small-caps; font-style:normal;  \"></p>\n";
                            output+="<p id=\"txt\" style=\"color: black; padding-top: 0px ;font-variant:small-caps; font-style:normal;\" ></p>\n";
                          output+="</div>\n";
                          output+="<div class=\"col-lg-8\" style=\"text-align: center\"> <h1 style=\"color:black ;font-variant:small-caps; font-style:normal; \">Teacher-Student Portal</h1>\n";
                          output+="<p> B.K. Birla Institute Of Engineering and Technology</p>\n";
                          output+="</p>\n";
                   output+=" </div>\n";
                    output+=" <div class=\"col-lg-2\" style=\"background:transparent; text-align: center; float: right \" >\n";
                         output+="<p style=\" color:black\">\n";
                             output+="<br/>\n";
                             output+="Name :"+name+"<br />\n";
                             output+="Login Id :"+ ID+"<br/>\n";
                             output+="<br/><br/>\n";
                             output+="<a href=\"logout\" style=\" color:black \">Logout</a>\n";
                        output+=" </p>       \n";
                     output+="</div>\n";
                    output+="</div>\n";
                output+="</div>\n";
            output+="</div>\n";
            if(profile==2)
        {
         output+="<div class=\"container\"  style=\"padding:0px;padding-top:2px; color: red;text-align: center; border-radius: 15px;\"><p>";
                   output+=a;
                   output+="</p></div>";
        } 
            // main part

            output+="<div class=\"container\" style=\"padding: 0px; margin:auto; padding-top: 2px; border-radius: 15px;\">\n";

                 output+="<div class=\"col-md-3 left\" >";
                     output+="<div style=\" text-align: center\"> <h4>SUBJECT</h4></div>";
                           output+="<div class=\"panel-group\" id=\"accordion\" style=\"background-color:transparent\" role=\"tablist\" aria-multiselectable=\"true\">";
      
                           // define menu bar dynamically
      
                           output+="<div class=\"panel panel-primary\" style=\"background-color:transparent;border-color:white\">\n";
              output+="<div class=\"panel-heading\" role=\"tab\" id=\"headingOne\" style=\"background-color:transparent;border-color: transparent\">\n";
                output+="<h4 class=\"panel-title\" style=\"background-color:transparent\">\n";
                 output+="<a role=\"button\"  data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"\" aria-expanded=\"false\" aria-controls=\"collapseOne\" class=\"collapsed\" style=\"background-color:transparent\">\n";
                    output+="<a href=\"T_login\" style=\"background-color:transparent\">Home</a>\n";
                 output+="</a>\n";
                output+="</h4>\n";
              output+="</div>\n";
           output+="</div>\n";
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
                     output+="<div class=\"panel panel-primary\" style=\"background-color:transparent;border-color:white\">";
        output+="<div class=\"panel-heading\" role=\"tab\" id=\"headingOne\" style=\"background-color:transparent;border-color:white\">";
          output+="<h4 class=\"panel-title\">";
            output+="<a role=\"button\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapseOne\" aria-expanded=\"false\" aria-controls=\"collapseOne\" class=\"collapsed\">";
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
            output+="<a href=\"subjectdetails?subject="+yr1[i]+"\"class=\"list-group-item\"> <span class=\"glyphicon glyphicon-link\" aria-hidden=\"true\"></span>"+ yr1[i]+"</a>";
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
        output+="<div id=\"collapseThree\" style=\"background-color:transparent;border-color:white\" class=\"panel-collapse collapse in\" role=\"tabpanel\" aria-labelledby=\"headingThree\" aria-expanded=\"true\">";
          output+="<div class=\"panel-body\">";
            output+="<div class=\"list-group\" style=\"background-color:transparent;border-color:white\">";
            j=0;  
            while(yr2[j]!=null)
              {
            output+="<a href=\"subjectdetails?subject="+yr2[i]+"\" class=\"list-group-item\"> <span class=\"glyphicon glyphicon-link\" aria-hidden=\"true\"></span>"+ yr2[j]+"</a>";
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
            output+="<a class=\"\" role=\"button\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapseThree\" aria-expanded=\"true\" aria-controls=\"collapseThree\">";
              output+="Year 3 <span class=\"glyphicon glyphicon-plus pull-right\" aria-hidden=\"true\"></span>";
            output+="</a>";
          output+="</h4>";
        output+="</div>";
        output+="<div id=\"collapseThree\" class=\"panel-collapse collapse in\" role=\"tabpanel\" aria-labelledby=\"headingThree\" aria-expanded=\"true\">";
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
      output+="<div class=\"panel panel-primary\"style=\"background-color:transparent;border-color:white\">";
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

               //center division start
               output+="<div class=\"col-md-6 center\">\n";
               //showing query
               if(qid!=0)
               {
                   int counter = 1;
                   String scode,subject;
                   String qsdata[]= new String[100];
                   String qtdata[]= new String[100];
                   int qscounter[]= new int[100];
                   int qtcounter[]= new int[100];
                   int qsc,qtc,c1;
                   qsc=0;
                   qtc=0;
                   c1=0;
                   scode=null;
                   output+="<h4 style=\" text-align: center\">Query </h4>\n";
                   output+="<br/> ";
                   // to retrive subject
                   
                          try{
                                PreparedStatement s = con.prepareStatement("select  s_code from squery where qid=?");
		                s.setInt(1,qid);
		                ResultSet rq = s.executeQuery();
                                if(rq.next())
                                {
                                scode=rq.getString("s_code");
                                }
                              }catch(Exception e){System.out.println(e);}
                                subjectname sn = new subjectname(scode);
                                subject=sn.show();
                   
                          // to retruve data from squery  
                       
                          try{
                                PreparedStatement pqrs = con.prepareStatement("select query,counter from squery where qid=?");
		                pqrs.setInt(1,qid);
		                ResultSet rqs = pqrs.executeQuery();
                                int kk=0;
                                while(rqs.next())
		              	 {
	                           qsdata[kk]=rqs.getString("query");
                                   qscounter[kk]=rqs.getInt("counter");
                                   kk++;
                                   c1++;
                                   qsc++;
                                 }  
                             }catch(Exception e){System.out.println("in student query "+e);}
                          
                          //retrive data from tquery
                          try{
                                PreparedStatement pqrt = con.prepareStatement("select query,counter from tquery where qid=?");
		                pqrt.setInt(1,qid);
		                ResultSet rqt = pqrt.executeQuery();
                                int kk=0;
                                while(rqt.next())
		        	 {
	                           qtdata[kk]=rqt.getString("query");
                                   qtcounter[kk]=rqt.getInt("counter");
                                   kk++;
                                   c1++;
                                   qtc++;
                                 }  
                             }catch(Exception e){}
                          System.out.println(qsc+qtc);
                          //
                          
                   output+="<p style=\"text-align:center\"><label>Query Id =</label><label>"+qid+"</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<label>subject =</label><label>"+subject+"</label></p><br><br>";
                   int iii=0;
                   int jjj=0;
                     output+="<div style=\" height:250px;background-color: white;border-radius: 15px;overflow: auto;\">";
                   //display query 
                   for(int kk=0;kk<c1;kk++)
                   {
                      if(counter==qscounter[iii])
                      {
                          //output+="<p style=\"text-align:left;padding-right:50%;padding-left:10px; width: 250px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; \"><label>"+qsdata[iii]+"</label></p>";
                           output+="<div style=\"text-align:left;padding-right:50%;max-width: 600px;    padding-left:10px; \"><p ><label>"+qsdata[jjj]+"</label></p></div>";
                          
                          output+="<br>";
                          iii++;
                      counter++;
                      }
                      else if(counter==qtcounter[jjj])
                      {
                          output+="<div style=\"text-align:right;padding-left:50%;    padding-right:10px; \"><p ><label>"+qtdata[jjj]+"</label></p></div>";
                          output+="<br>";
                          jjj++;
                          counter++;
                      }
                      
                    }
                   output+="</div>";
                   output+="<br><form action=\"subjectdetails?query=2&qqid="+qid+"&counter="+counter+"\" method=\"post\">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <label>Replay Query :&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label><input type=\"text\" name=\"querydata\" id=\"querydata\" value=\"query\">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input type=\"submit\" value=\"Reply\"> </form>";
               }//query show concept end here 
               else if(query==2)
               {
               int rr=0;
                      output+="<table class=\"table\">\n";
                      output+="<thead>\n";
                      output+="<tr>\n";
                      output+="<th>s_no</th>\n";
                      output+="<th>query id</th>\n";
                      output+="<th>Student Id</th>\n";
                      output+="<th>subject</th>\n";
                      output+="</tr>\n" ;
                      output+="</thead>\n" ;
                  while(qsubr[rr]!=null)
                   {
                   output+="<tbody>\n" ;
                   output+="<tr>\n" ;
                   output+="<td>"+(rr+1)+"</td>\n" ;
                   output+="<td> <a href=\"subjectdetails?qid="+Integer.toString(qidr[rr])+"\" style=\" color: black\">"+qidr[rr]+"</a></td>\n";
                   output+="<td>"+sidr[rr]+"</td>\n" ;
                   output+="<td>"+qsubr[rr]+"</td>\n" ;
                   output+="</tr>\n" ;
                   output+="</tbody>\n" ;
                     rr++;
                   }
                  output+="</table>";
               
               }
               else
               {//to sshow profile 
                 if(profile==1)
               {
               
               output+="<h2 style=\" text-align:center\"> Profile Update </h2>\n";
            output+="<form action=\"subjectdetails?profile=2\" method=\"post\">\n";
            output+="<table class=\"table table-hover\"  >\n";
            output+="<thead class=\"thead-inverse\">\n";
                 output+="<tr>\n";
                 output+="<th>\n";
                 output+="</th>\n";
                 output+="<th>\n";
                 output+="</th>\n";
                 output+="</tr>\n";
                 output+="</thead>\n";
                    output+="<tbody>\n";
                    output+="<tr> \n";
                    output+="<th scope=\"row\">Name : </th>\n";
                    output+="<td><input type=\"text\" value=\""+s2+"\" name=\"name\" id=\"name\" disable /> </td>\n";
                    output+="</tr>\n";
                            output+="<tr> \n";
                            output+="<th scope=\"row\">phone: </th>\n";
                            output+="<td><input type=\"text\" name=\"phone_no\"  value=\""+s4+"\" id=\"phone\" disable/> </td>\n";
                            output+="</tr>\n";
                            output+="<tr> \n";
                            output+="<th scope=\"row\">E_mail : </th>\n";
                            output+="<td><input type=\"text\" value=\""+s5+"\" pattern=\"[A-Za-z0-9._%+-]{2,}@[a-zA-Z]{1,}([.]{1}[a-zA-Z]{2,}|[.]{1}‌​[a-zA-Z]{2,}[.]{1}[a‌​-zA-Z]{2,})\" required  name=\"E_mail\" id=\"mail_id\" /> </td>\n";
                          output+="</tr>\n";
                          output+="<tr> \n";
                            output+="<th scope=\"row\">password : </th>\n";
                            output+="<td><input type=\"password\" name=\"password\" value=\"  \" id=\"password\"/> </td>\n";
                          output+="</tr>\n";
                          output+="<tr> \n";
                            output+="<th scope=\"row\">confirm password : </th>\n";
                            output+="<td><input type=\"password\" name=\"confirm_pass\" id=\"passwordconfirm\" oninput=\"check(this)\"/> </td>\n";
                          output+="</tr>\n";
                          output+="<script language='javascript' type='text/javascript'>\n";
                                output+="function check(input) {\n";
                                output+="if (input.value != document.getElementById('password').value) {\n";
                                output+="input.setCustomValidity('Password Must be Matching.');\n";
                                   output+="} else {\n";
                                 // input is valid -- reset the error message\n";
                                output+="input.setCustomValidity('');\n";
                                output+="}\n";
                                output+="}\n";
                         output+="</script>\n";
                    output+="</tbody>\n";
                    output+="</table>\n";
                    output+="<p style=\"text-align:center\"><input type=\"submit\" value=\"Update\" /></p>\n";
                    output+="</form>\n";
                
               
               
               }  //profile end
               else if(profile!=2)
               {
               if(subjectflag){
                     output+="<div style=\"text-align: center\">\n";
                         output+="<h4>Privious Notes</h4></div>\n";
                    output+="<div style=\" min-height: 200px; max-height: 200px; overflow-y: auto; overflow-x: hidden\">\n";
                    output+="<table class=\"table table-hover\" style=\" background-color: white\">\n";
      output+="<thead>\n";
        output+="<tr>\n";
          output+="<th>S_No</th>\n";
          output+="<th>Topic</th>\n";
          output+="<th>Update Date</th>\n";
          output+="<th>Update Time</th>\n";
          output+="<th>Action</th>\n";
        output+="</tr>\n";
     output+=" </thead>\n";
      i=0;
      while(topic[i]!=null)
      {
      output+="<tbody>\n";
        output+="<tr>\n";
          output+="<th scope=\"row\">"+i+1+"</th>\n";
          output+="<td>"+subjectname+"</td>\n";
          output+="<td>"+date[i]+"</td>\n";
          output+="<td>"+time[i]+"</td>\n";
          System.out.println("path="+path[i]+filename[i]);
          output+="<td><p><a href=\"download?url="+path[i]+filename[i]+"\" ><input type=\"button\" value=\"Download\"> </a>  <input type=\"button\" value=\"View\" id=\"btnShow"+i+"\"> <a  href=\"delete?url="+path[i]+filename[i]+"\" ><input type=\"button\" value=\"Delete\" ></a></p>";
         output+="<p> <script type=\"text/javascript\" src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js\"></script>\n" +
"<script src=\"http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.9/jquery-ui.js\" type=\"text/javascript\"></script>\n" +
"<link href=\"http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.9/themes/blitzer/jquery-ui.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
"<div><script type=\"text/javascript\">\n" +
"    $(function ()  {\n" +
"        var fileName = \"../files/"+filename[i]+"\";\n"; 
        System.out.println("view file path is "+filename[i]); 
output+="        $(\"#btnShow"+i+"\").click(function () { \n" +
"            $(\"#dialog\").dialog({\n" +
"                modal: true,\n" +
"                title: \"file priview\",\n" +
"                width: 640,\n" +
"                height: 650,\n" +
"                buttons: {\n" +
"                    Close: function () {\n" +
"                        $(this).dialog('close');\n" +
"                    }\n" +
"                },\n" +
"                open: function () {\n" +
"                    var object = \"<object data=\\\"{FileName}\\\" type=\\\"application/pdf\\\" width=\\\"600px\\\" height=\\\"500px\\\">\";\n" +
"                    object += \"If you are unable to view file, you can download from <a href = \\\"{FileName}\\\">here</a>\";\n" +
"                  object += \" or download <a target = \\\"_blank\\\" href = \\\"http://get.adobe.com/reader/\\\">Adobe PDF Reader</a> to view the file.\";\n" +
"                    object += \"</object>\";\n" +
"                    object = object.replace(/{FileName}/g, \"Files/\" + fileName);\n" +
"                    $(\"#dialog\").html(object);\n" +
"                }\n" +
"            });\n" +
"        });\n" +
"    });\n" +
"</script>\n" +
"</div>  <div id=\"dialog\" style=\"display: none; overflow-x: hidden\">\n" +
"</div>  </p> </td>\n";
        output+="</tr>\n";
     output+=" </tbody>\n";
     i++;
      }
    output+="</table>\n";
                    output+=" </div>\n";
                     output+="<br/>\n";
                     output+="<div style=\" text-align: center\">\n";

                     output+="<h4>Update New Notes</h4>\n";
                     output+="<br />\n";
                     output+="<form action=\"UploadServlet?subject="+subjectname+"\" method=\"post\" enctype=\"multipart/form-data\">\n";
                         output+="<p style=\" text-align:center\"> Select a file to upload: <input type=\"file\" name=\"file\" size=\"50\"  />\n";
                     output+="</p><br />\n";
                    output+="<input type=\"submit\" value=\"Upload File\"  style=\" alignment-adjust:  central\"/>\n";
                  output+="</form>\n";
                     output+="</div>\n";
                }
               }
        }
               output+="</div>\n";
               //end center division
               
                    output+="<div class=\"col-md-3 right\" >\n";
                         output+="<h4 id=\"aa\">  Event And Notice </h4>\n";
                        output+="<marquee direction=\"up\" scrolldelay=\"200\"  style=\"text-align:center; width: 100%; height:400px\" onmouseover=\"this.stop();\" onmouseout=\"this.start();\">\n";
                       // show event into scrolling tag
                         int ee=0;
                         while(evn[ee]!=null)
                         {
                        output+="<div id=\"\" style=\" height:auto; width: 100%; border-bottom: 1px solid #000; color:black; padding: 10px\">\n";
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
