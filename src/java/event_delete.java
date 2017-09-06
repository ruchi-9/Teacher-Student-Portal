import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class event_delete extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    String event=request.getParameter("id");   
 String a;
 String output="";
 String evn[]= new String[30];
     String evnid[]= new String[30];
 database db=new database();
                   Connection con=db.getCon();
     try
               { 
                           PreparedStatement ps = con.prepareStatement("delete from event where eventid=?");
                           ps.setString(1,event);                              
			   int rs1 = ps.executeUpdate();
			   System.out.println("delete from database where event id is ="+event);
		           a="event delete sucessfully";	 
                           System.out.println("sucessfull");
  
               }
			   catch(Exception ex)
                {
			   System.out.println(ex);
			   a="operation failed";
                           System.out.println("unsucessfull");
                }
   
     try
                    {
                        PreparedStatement ps1 = con.prepareStatement("SELECT * from event");
                        ResultSet rs =ps1.executeQuery();
                        int events=0;
                        while(rs.next())
		     {
                          evn[events]=rs.getString(2);
                          evnid[events]=Integer.toString(rs.getInt(1));
                          System.out.println("data inside event ="+evn[events]);
                          events++;
                     }
                    }
                   catch(Exception e)
                    {
                        System.out.println("Excetption in event"+e);
                    }  
                       savedinfo si = new savedinfo();
                       
                      
                      int e=0;
                 
                    output+="<!DOCTYPE html>\n" +
"<ht    ml>\n";
                    output+=si.headpart()+"<body onload=\"startTime()\" style=\"\">\n" +si.headerpart() +si.menupart();
 output+="<div class=\"container\"  style=\"margin-top: -20px; margin-bottom:20px; padding:0px; color: red;text-align: center; border-radius: 15px;\"><p>";
                   output+=a;
                   output+="</p></div>";
                    
                    //discription about event
                    PrintWriter writer = response.getWriter();
                    output+="<div class=\"container\" id=\"sreg\" style=\"margin-top: 20 px; padding: 0px;text-align: center;  border-radius: 15px;\">\n";
                    output+=" <div class=\"container\" id=\"maindiv\"style=\"padding: 0px; color:black;  text-align: center;max-width: 600px; min-width: 600px\">\n";
                    output+="<h3 style=\"text-align: center; color:white;  padding:20px\">View Event</h3>";
output+="<table class=\"table table-hover\"  >\n" +
"                    <thead class=\"thead-inverse\">\n" +
"        <tr>\n" +
"            <th style=\"text-align: center\">S_No</th>\n" +
"          <th style=\"text-align: center\">Event</th>\n" +
"          <th style=\"text-align: center\">Action</th>\n" +
"        </tr>\n" +
"      </thead>\n" ;
            
        while(evnid[e]!=null){        
                   output+="<tbody>\n" +
                   "<tr>\n" +
                   "<th scope=\"row\"  style=\"text-align: center\">";
                    output+=(e+1)+"</th>\n" +
                   "<td><p style=\"max-height: 30px;\">";
                    output+=evn[e]+"</td>\n" ;
                            System.out.println("inside the event");
                   output+="<td><a href=\"event_delete?id="+evnid[e]+"\"><input type=\"button\" value=\"Delete\"> </a></td>\n" +
                   "\n" +
                   "</tr>\n" +
                   "\n" +
                   "</tbody>\n" ;
                   e++;          
                      }
                     output+="</table>\n" +
                              "</div>";
                      output+="</div>";
                    
                    output+="    </body>\n" +
"</html>\n" +
"";
                     writer.println(output);
                 
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
