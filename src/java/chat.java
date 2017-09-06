import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author swang
 *
 */
@WebServlet("/chat")
  public class chat extends HttpServlet {
  private static final long serialVersionUID = 1L;
  protected void doGet(HttpServletRequest request,
   HttpServletResponse response) throws ServletException, IOException
 {
 
 }
   protected void doPost(HttpServletRequest request,
   HttpServletResponse response) throws ServletException, IOException {
  // Besides "text/event-stream;", Chrome also needs charset, otherwise
  // does not work
  // "text/event-stream;charset=UTF-8"
   response.setContentType("text/event-stream;charset=UTF-8");
   PrintWriter out = response.getWriter();
   while (true) 
   {
   
   out.print("id: " + "ServerTime" + "\n");
   out.print("data: " + new Date().toLocaleString() + "\n\n");
   out.flush();
   // out.close(); //Do not close the writer!
   try 
   {
    Thread.currentThread().sleep(1000);
   }
   catch (InterruptedException e) {
    e.printStackTrace();
   }
  }
 }

}
