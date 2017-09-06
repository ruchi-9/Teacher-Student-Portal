/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.HeadlessException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Beniwal
 */
public class ssubject extends HttpServlet {
    String ID,password,s1,username,output,branch;
    String sub[]=new String[11];
    String subjectnames[]=new String[11];
     int sem;
    String evn[]= new String[30];
     int flag;
     int query=0;
    String s2,s5,s6,s7,s8,s10,a; 
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
        int profile=0;
        int query=0;
        int qf=3;
        int qid=0;
        int qqid=0;
        int c=0;
        int qidr[]=new int[100];
        String sidr[]=new String[100];
        String scoder[]=new String[100];
        String qsubr[]=new String[100];
       String qflag="";
        try
        {
         profile=Integer.parseInt(request.getParameter("profile"));
         }catch(Exception cc)
         {
         }
        
        try
        {
         c=Integer.parseInt(request.getParameter("counter"));
         }catch(Exception cd)
         {
         }
        try
        {
         qqid=Integer.parseInt(request.getParameter("qqid"));
         }catch(Exception ccc)
         {
         }
        
        try
        {
         qid=Integer.parseInt(request.getParameter("qid"));
         }catch(Exception ce)
         {
         }
        
        try
        {
         qf=Integer.parseInt(request.getParameter("qfg"));
         System.out.println("value of query flag ="+qf);
        }
        catch(Exception e)
        {
         //System.out.println(e);
        }
        System.out.println("value of query flag ="+qf);
        try
        {
         query=Integer.parseInt(request.getParameter("query"));
         System.out.println("query="+query);
        }catch(Exception cp)
         {
         //System.out.println("problem in reading data"+c);
         }
        
//String pass=(String)request.getParameter("pass");
        //ID=(String)request.getParameter("name");
        
             String subject;
               String topic[]=new String[10];
               String date[]=new String[10];
                String time[]=new String[10];
              String path[]=new String[10];
                String filename[] = new String[10];
    
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
                         s1=ID;
                         System.out.println("Id = "+ID);
                    }
                      if(cookie.getName().equals("username"))
                         username=cookie.getValue();
                    }
                          subject=request.getParameter("subject");
                          System.out.println("subject is ="+subject);
                          PrintWriter writer = response.getWriter();
                          database db=new database();
                           Connection con=db.getCon();
                           
                           //to save query into database
                           if(query==2)
                           {
                           String s_code=request.getParameter("scode");
                           System.out.println("s code for query is "+s_code);
                           String qdata=request.getParameter("querydata");
                           System.out.println("q data ="+qdata);
                           qid q=new qid();
                           int qidd=q.generatePin();
                           findteacher ft = new findteacher(s_code);
                           String tid=ft.show();
                           System.out.println("qidd is "+qidd);
                           int counter=1;
                           String sid=ID;
                           System.out.println("sid is "+sid);
                           try
                           {
                               PreparedStatement pq = con.prepareStatement("insert into squery values(?,?,?,?,?,?)");
                               pq.setInt(1,qidd);
                               pq.setString(2,sid);
                               pq.setString(3,s_code);
                               pq.setInt(4,counter);
                               pq.setString(5,qdata);
                               pq.setString(6,tid);
                               pq.executeUpdate();
                            qflag="Query submited sucessfully";
                            //response.sendRedirect("student?query=3");
                           }catch(Exception e){System.out.println(e);
                           qflag="please try after some time somthing error";
                            //response.sendRedirect("student?query=3");
                           }
                           }
                           //end save query
                           //retrive privious query 
                           
                           if(query==2)
                           {
                            try{
                                PreparedStatement pqr = con.prepareStatement("select  * from squery where s_id=? ");
		                pqr.setString(1,s1);
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
                        //end retrive query   
                           
                           // to get branch and sem of a student 
                           flag=1;
                                 logincheck lc = new logincheck(ID);
                                 branch=logincheck.branch;
                                 sem=logincheck.sem; 
                                 System.out.println("sem is "+sem);
                                 System.out.println("branch is "+branch);
                             try
                 {
                     
       // retriving the files of a perticuler subject 
                    
                     PreparedStatement ps1 = con.prepareStatement("SELECT * FROM files where  subject= ?");
                     ps1.setString(1,subject);
                     ResultSet rsub = ps1.executeQuery();
                      i =0;
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
                 {
                    System.out.println(e);
                 }
                             try {
                PreparedStatement ps1 = con.prepareStatement("select  * from stuent_login where student_id=?");
		ps1.setString(1,s1);
		ResultSet rs = ps1.executeQuery();
              
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
       /// retriving data end
                             
                             if (sem!=0)
                   {
                   try
                   {
                   String a=Integer.toString(sem);
                   PreparedStatement ps1 = con.prepareStatement("SELECT * from course where sem=? and branch=?");
                   System.out.println("value of sem in ssubject page "+sem+branch);
                   ps1.setString(1,a);
                   ps1.setString(2,branch);
                   ResultSet rs1 =ps1.executeQuery();
                   i=0;
                   if(rs1.next())
                   {
                       j=3;
                       while(!(((rs1.getString(j)).trim()).equals("")))
                     {
                     System.out.println("22222222222");
                     sub[i]=rs1.getString(j);
                     subjectname sn = new subjectname(sub[i]);
                     subjectnames[i]=sn.show();
                     System.out.println("33333333333333");
                     i++;j++;
                     }   
                   }
                   }
                   catch(Exception e)
                   {
                   
                   System.out.println(e);
                   }
                   }
                             //update student record 
                  if(profile==2)
                  {
                  String phone_no=request.getParameter("phone_no");
                  String E_mail=request.getParameter("E_mail");
                  String password=request.getParameter("password");
                  String father_name=request.getParameter("father_name");
                  System.out.println("trying to update ");
                  try
               {
                         PreparedStatement ps = con.prepareStatement("update stuent_login set password = ? , phone_no = ? , father_name = ? , Email = ? where student_id = ?");
                              //id
                         ps.setString(1,password);      //password
                        
                         ps.setString(2, phone_no); //phone no
			 ps.setString(3,father_name); //father name
			 ps.setString(4,E_mail);
                         ps.setString(5,ID);
                        
			 ps.executeUpdate();
                         a="update sucessfully";
               }
                  catch(   SQLException | HeadlessException ex1)
                         {
	                      System.out.println("sql exception");
						    System.out.println(ex1);
			 a="Unable to update ";       
                        
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
                    
                    //save the reply 
                    if(c!=0)
                    {
                        String studentid,subjectcode,querydata,teacherid;
                        studentid=null;
                        subjectcode=null;
                        teacherid=null;
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
                                   teacherid=rq.getString(6);
                                 } 
                         }catch(Exception e){ System.out.println(e);}
                       try
                         {
                          PreparedStatement pq = con.prepareStatement("insert into squery values(?,?,?,?,?,?)");
                               pq.setInt(1,qqid);
                               pq.setString(2,studentid);
                               pq.setString(3,subjectcode);
                               pq.setInt(4,c);
                               pq.setString(5,querydata);
                               pq.setString(6,teacherid);
                               pq.executeUpdate();
                          }
                    catch(Exception ee)
                    {
                    System.out.println(ee);
                    }
                    
                    }
                    //end save replay 
                  // try{con.close(); }catch(Exception e){System.out.println(e);}
                   
                   
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
             output+="<link href=\"css/teachers.css\" rel=\"stylesheet\" type=\"text/css\"/>";
             output+="<script src=\"https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js\"></script>\n";
             output+="<link rel=\"stylesheet\" href=\"https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css\">\n";
             output+="<script src=\"scripts/time.js\"></script>\n";
               output+="</head>\n";
        output+="<body onload=\"startTime()\">\n";
        
            output+="<div class=\"container\">\n";
                output+="<div class=\"row\" style=\" min-height: 150px;padding: 0px; border-radius: 15px;\">\n";
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
                             output+="Login Id :"+ID+"<br/>\n";
                             output+="<br/><br/>\n";
                             output+="<a href=\"logout\" style=\" color: black\">Logout</a>\n";
                        output+=" </p>       \n";
                     output+="</div>\n";
                    output+="</div>\n";
                output+="</div>\n";
             
            output+="</div>\n";
            
        if(profile==2)
        {
         output+="<div class=\"container\"  style=\"padding:0px; color: red;text-align: center;  border-radius: 15px;\"><p>";
                   output+=a;
                   output+="</p></div>";
        }
        if(query==3)
             {
                   output+="<div class=\"container\"  style=\"padding:0px; color: red;text-align: center; border-radius: 15px;\"><p>";
                   output+=qflag;
                   output+="</p></div>";
              }
      
               output+="<div class=\"container\" style=\"padding: 0px; margin:auto; padding-top: 2px; border-radius: 15px;\">\n";
           output+="<div class=\"col-md-3 left\" >\n";
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

           //query for menu 
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
                     output+=  subjectnames[i]+"("+sub[i]+")"+" </a>\n";
                     i++;
                    }
            output+="</div>\n";
          output+="</div>\n";
       output+=" </div>\n";
      output+="</div>\n";
    output+="</div>\n";
            
            output+="</div>\n";

            //center menu bar
            
            
            output+="<div class=\"col-md-6 center\" >\n";
       //show query data 
       if(qid!=0)
       {
       int counter = 1;
                   String sscode,ssubject;
                   String qsdata[]= new String[100];
                   String qtdata[]= new String[100];
                   int qscounter[]= new int[100];
                   int qtcounter[]= new int[100];
                   int qsc,qtc,c1;
                   qsc=0;
                   qtc=0;
                   ssubject=null;
                   c1=0;
                   sscode=null;
                   output+="<h4 style=\" text-align: center\">Query </h4>\n";
                   output+="<br/> ";
                   // to retrive subject
                   
                          try{
                                PreparedStatement s = con.prepareStatement("select  s_code from squery where qid=?");
		                s.setInt(1,qid);
		                ResultSet rq = s.executeQuery();
                                if(rq.next())
                                {
                                sscode=rq.getString("s_code");
                                }
                              }catch(Exception e){System.out.println(e);}
                                subjectname sn = new subjectname(sscode);
                                ssubject=sn.show();
                   
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
                          
                   output+="<p style=\"text-align:center\"><label>Query Id =</label><label>"+qid+"</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<label>subject =</label><label>"+ssubject+"</label></p><br><br>";
                   int iii=0;
                   int jjj=0;
                     output+="<div style=\" height:250px;background-color: white;border-radius: 15px;overflow: auto;\">";
                   //display query 
                   for(int kk=0;kk<c1;kk++)
                   {
                      if(counter==qscounter[iii])
                      {
                          output+="<p style=\"text-align:left;padding-right:50%;padding-left:10px; width: 250px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; \"><label>"+qsdata[iii]+"</label></p>";
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
                   output+="<br><form action=\"ssubject?query=2&qqid="+qid+"&counter="+counter+"\" method=\"post\">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <label>Replay Query :&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label><input type=\"text\" name=\"querydata\" id=\"querydata\" value=\"query\">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input type=\"submit\" value=\"Reply\"> </form>";
       System.out.println(c1);
       
       }
       else if(profile==1)
                 {
                output+="<h2 style=\" text-align:center\"> Student Updation </h2>\n";
                output+="<form action=\"ssubject?profile=2\" method=\"post\">\n";
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
                            output+="<th scope=\"row\">phone: </th>\n";
                            output+="<td><input type=\"text\" name=\"phone_no\"  value=\""+s7+"\"  /> </td>\n";
                          output+="</tr>\n";
                          output+="<tr> \n";
                            output+="<th scope=\"row\">E_mail : </th>\n";
                            output+="<td><input type=\"text\" value=\""+s10+"\" pattern=\"[A-Za-z0-9._%+-]{2,}@[a-zA-Z]{1,}([.]{1}[a-zA-Z]{2,}|[.]{1}‌​[a-zA-Z]{2,}[.]{1}[a‌​-zA-Z]{2,})\" required  name=\"E_mail\" /> </td>\n";
                          output+="</tr>\n";
                          output+="<tr> \n";
                            output+="<th scope=\"row\">father name : </th>\n";
                            output+="<td><input type=\"text\" name=\"father_name\"  value=\""+s8+"\"  /> </td>\n";
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
           }
            //to show query 
            else {
                if(query==1)
                 {
                    output+="<form action=\"ssubject?query=2    \" method=\"post\">";
                    output+="<h4 style=\" text-align: center\">Add new query</h4>\n" +
                          "<br/> \n" +
                          "<p style=\" text-align: center; color: white\">\n" +
                          "<div style=\" text-align: center\"><h5><label>Select subject</label>\n" +
                          "<select id = \"scode\" name=\"scode\">\n" ;
                
                     i=0;
                     while(sub[i]!=null)
                        {
                            output+="<option value = \""+sub[i]+"\">"+sub[i]+"</option>\n";
                             i++;
                        }
                
                           output+= "</select></h5>\n" +
                          "</div>\n" +
                          "<div style=\" text-align: center\">\n" +
                          "<br/>\n" +
                          "<br/>\n" +
                          "<textarea rows=\"8\" cols=\"60\" name=\"querydata\" id=\"querydata\">\n" +
                          "Enter Query here...</textarea></div>\n" +
                          "<div style=\" text-align: center\">\n" +
                          "<br>\n" +
                          "<input type=\"submit\">\n" +
                          "</div>";
                          output+=" </form>";
                  }
                
                // show privious query 
                else{ if(query==2)
                {
                    int rr=0;
                      output+="<table class=\"table\">\n";
                      output+="<thead>\n";
                      output+="<tr>\n";
                      output+="<th>s_no</th>\n";
                      output+="<th>query id</th>\n";
                      output+="<th>scode</th>\n";
                      output+="<th>subject</th>\n";
                      output+="</tr>\n" ;
                      output+="</thead>\n" ;
                  while(qsubr[rr]!=null)
                   {
                   output+="<tbody>\n" ;
                   output+="<tr>\n" ;
                   output+="<td>"+(rr+1)+"</td>\n" ;
                   output+="<td> <a href=\"ssubject?qid="+Integer.toString(qidr[rr])+"\" style=\" color: black\">"+qidr[rr]+"</a></td>\n";
                   output+="<td>"+scoder[rr]+"</td>\n" ;
                   output+="<td>"+qsubr[rr]+"</td>\n" ;
                   output+="</tr>\n" ;
                   output+="</tbody>\n" ;
                     rr++;
                   }
                   
                   output+="</table>";
                 }
                //end privious  query
                else
                 {
                         output+="<div style=\"text-align: center\">\n";
                         output+="<h4>Updated Notes</h4></div>\n";
                         output+="<div style=\" min-height: 200px; max-height: 200px; overflow-y: auto; overflow-x: hidden\">\n";
                         output  +="<table class=\"table table-hover\" style=\" background-color: white\">\n";
                         output+="<thead>\n";
                         output+="<tr>\n";
          output+="<th>S_No</th>\n";
          output+="<th>Topic</th>\n";
          output+="<th>Update Date</th>\n";
          output+="<th>Update Time</th>\n";
          output+="<th>Action</th>\n";
        output+="</tr>\n";
      output+="</thead>\n";
      output+="<tbody>\n";
        output+="<tr>\n";
          
        i=0;
       while(topic[i]!=null)
      {
          output+="<tbody>\n";
          output+="<tr>\n";
          output+="<th scope=\"row\">"+i+1+"</th>\n";
          output+="<td>"+subject+"</td>\n";
          output+="<td>"+date[i]+"</td>\n";
          output+="<td>"+time[i]+"</td>\n";
          System.out.println("path="+path[i]+filename[i]);
          output+="<td><p><a href=\"download?url="+path[i]+filename[i]+"\" ><input type=\"button\" value=\"Download\"> </a>  <input id=\"btnShow"+i+"\" type=\"button\" value=\"View\"></p><p> <script type=\"text/javascript\" src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js\"></script>\n" +
"<script src=\"http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.9/jquery-ui.js\" type=\"text/javascript\"></script>\n" +
"<link href=\"http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.9/themes/blitzer/jquery-ui.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
"<div><script type=\"text/javascript\">\n" +
"    $(function ()  {\n" +
"        var fileName = \""+path[i]+filename[i]+"\";\n" +
"        $(\"#btnShow"+i+"\").click(function () {\n" +
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
                     output+="</div>\n";
                    output+=" <br/>\n";
                 }}}  
            //end center menu         
                 output+="</div>\n";
                    output+="<div class=\"col-md-3 right\">\n";
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
