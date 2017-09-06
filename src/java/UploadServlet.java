import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
import java.time.Year;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;
import java.time.LocalDateTime;
import com.oreilly.servlet.MultipartRequest;
import javax.servlet.RequestDispatcher;
public class UploadServlet extends HttpServlet {
   static String subject,time,date,topic,fileName;
   private boolean isMultipart;
   private static String filePath,fu;
   private int maxFileSize = 250 * 1024*1024*1024;
   private int maxMemSize = 204 * 1024*1024*1024;
   private File file ;
  static int mnth,year;
   public void init( ){
      // Get the file location where it would be stored.
      filePath = "C://Users/Beniwal/Documents/NetBeansProjects/bkbietproject/web/files/";  
   fu="../files/";      
   }
      public void doPost(HttpServletRequest request,HttpServletResponse response)
              throws ServletException, java.io.IOException {
      // Check that we have a file upload request
     // topic = request.getParameter("topic");
      subject=request.getParameter("subject");
     // MultipartRequest m=new MultipartRequest(request, "C:\\Temp");
       String url = request.getRequestURL().toString();
       String tp;
       char[] ch=url.toCharArray();
      topic ="not now"; //m.getParameter("topic");
      isMultipart = ServletFileUpload.isMultipartContent(request);
      response.setContentType("text/html");
      System.out.println(filePath);
      System.out.println(isMultipart);
      java.io.PrintWriter out = response.getWriter( );
      if( !isMultipart ){
         out.println("<html>");
         out.println("<head>");
         out.println("<title>Servlet upload</title>");  
         out.println("</head>");
         out.println("<body>");
         out.println("<p>No file uploaded</p>"); 
         out.println("</body>");
         out.println("</html>");
         return;
      }
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File("c:\\temp"));
      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );

      try{ 
      // Parse the request to get file items.
      List fileItems = upload.parseRequest(request);
      // Process the uploaded file items
      Iterator i = fileItems.iterator();
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet upload</title>");  
      out.println("</head>");
      out.println("<body>");
      while ( i.hasNext () ) 
      {
         FileItem fi = (FileItem)i.next();
         if ( !fi.isFormField () )	
         {
             //find year month day hours,mint sec          
         
               LocalDateTime now = LocalDateTime.now();
               String year = Integer.toString(now.getYear());
               String month = Integer.toString(now.getMonthValue());
               String day = Integer.toString(now.getDayOfMonth());
               String hour = Integer.toString(now.getHour());
               String minute = Integer.toString(now.getMinute());
               String second = Integer.toString(now.getSecond()); 
               date=day+"//"+month+"//"+year;
               time=hour+":"+minute+":"+second;
            // Get the uploaded file parameters
            String fieldName = fi.getFieldName();
            fileName = subject+"_"+day+"_"+month+"_"+year+"_"+hour+"_"+minute+"_"+second+"-"+fi.getName();
            System.out.println(fileName);
            String contentType = fi.getContentType();
            boolean isInMemory = fi.isInMemory();
            long sizeInBytes = fi.getSize();
            // Write the file
            if( fileName.lastIndexOf("\\") >= 0 ){
               file = new File( filePath + 
               fileName.substring( fileName.lastIndexOf("\\"))) ;
            }else{
               file = new File( filePath + 
               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
            }
            fi.write( file ) ;
                      out.println("<div> file upload sucessfully </div>");
                       RequestDispatcher rd=request.getRequestDispatcher("subjectdetails");
                       rd.include(request, response);
                
         }
      }
      out.println("</body>");
      out.println("</html>");
      try
      {     // topic ="sumit";
              database db=new database();
              Connection con=db.getCon();
              PreparedStatement ps = con.prepareStatement("insert into files values(?,?,?,?,?,?)");
                         ps.setString(1,subject);
                         ps.setString(2,topic);     //topic
                         ps.setString(3,date);      //date
                    	 ps.setString(4,time);  //time
                         ps.setString(5,fileName); //filename 
                         ps.setString(6,fu);  //filepaths
                         ps.executeUpdate();
                         notification nf = new notification();
                         String[] email=nf.notify(subject);
                         String ss= "new notes updated ";
                         String message = "notes updated \n"+"subject :="+subject+"\n date:="+date;
                         mail m = new mail(email,ss,message);
                         
      }catch(Exception e)
      {
          System.out.println(e);
      }
   }catch(Exception ex) {
       System.out.println(ex);
   }
   }
   public void doGet(HttpServletRequest request, 
                       HttpServletResponse response)
        throws ServletException, java.io.IOException {
        
        throw new ServletException("GET method used with " +
                getClass( ).getName( )+": POST method required.");
   } 
}
