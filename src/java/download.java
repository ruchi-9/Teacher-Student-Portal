   
        
import java.io.*;
import java.nio.file.Path;
import javax.servlet.*;
import javax.servlet.http.*;

//D:\\NetBeansProjects\\bkbietproject\\web\\
public class download extends HttpServlet {
 static String filePath;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("APPLICATION/OCTET-STREAM");
        String path = request.getParameter("url");
        System.out.println("22222222222222"+path);
      
        PrintWriter out = response.getWriter();
        int len=path.length();
      
         
        System.out.println(".......................................................");
        filePath ="C:\\Users\\Beniwal\\Documents\\NetBeansProjects\\bkbietproject\\web\\"+path.substring(2,len);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filePath + "\"");
        FileInputStream fl = new FileInputStream(filePath);
        System.out.println("file path is :-"+filePath);
        int i;
        while ((i = fl.read()) != -1) {
            out.write(i);
        }
        fl.close();
        out.close();
    }
}