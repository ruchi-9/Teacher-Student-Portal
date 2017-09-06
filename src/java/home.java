/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Beniwal
 */
@WebServlet(urlPatterns = {"/home"})
public class home extends HttpServlet {

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
        PrintWriter out = response.getWriter();
       int f=0;
       try{
       f=Integer.parseInt(request.getParameter("f"));
       }catch(Exception e){}
       PrintWriter writer = response.getWriter();
       String output="";
       output+="<!DOCTYPE html>\n" +
"<!--[if lt IE 7]> <html class=\"lt-ie9 lt-ie8 lt-ie7\" lang=\"en\"> <![endif]-->\n" +
"<!--[if IE 7]> <html class=\"lt-ie9 lt-ie8\" lang=\"en\"> <![endif]-->\n" +
"<!--[if IE 8]> <html class=\"lt-ie9\" lang=\"en\"> <![endif]-->\n" +
"<!--[if gt IE 8]><!--> <html lang=\"en\"> <!--<![endif]-->\n" +
"<head>\n" +
"  <meta charset=\"utf-8\">\n" +
"  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" +
"  <title>Login Form</title>\n" +
"  <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
"  <!--[if lt IE 9]><script src=\"//html5shim.googlecode.com/svn/trunk/html5.js\"></script><![endif]-->\n" +
"  <style>\n" +
" @import url(http://fonts.googleapis.com/css?family=Roboto:100);\n" +
"@import url(http://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.css);\n" +
"\n" +
"body {\n" +
"  background: #1a1a1a;\n" +
"  font-family: 'Roboto';\n" +
"  background-image:url(\"css/background.jpg\");\n" +
"}\n" +
"a{\n" +
"  color:#ffffff;\n" +
"}\n" +
"a:hover{\n" +
"  color:red;\n" +
"}\n" +
".container{\n" +
"    margin:0 auto;\n" +
"    width:500px;\n" +
"    height:380px;\n" +
"    background-color:#e3e3e3;\n" +
"    opacity: 0.9;\n" +
"}\n" +
".header-icon {\n" +
"  width:80px;\n" +
"  height:80px;\n" +
"  padding-left:10px;\n" +
"  background-color:#39C4C7;\n" +
"  float:left;\n" +
"  \n" +
"}\n" +
".header-text {\n" +
"  position:relative;\n" +
"  height:80px;\n" +
"  text-align:center;\n" +
"  overflow:hidden;\n" +
"  color:#ffffff;\n" +
"  font-size:30px;\n" +
"  background-color:black;\n" +
"  font-weight:bold;\n" +
"}\n" +
".header-text span {\n" +
"  line-height:2.6;\n" +
"  padding-right:50px;\n" +
"}\n" +
".fa-times {\n" +
"  font-size:25px;\n" +
"  position:absolute;\n" +
"  top:5px;\n" +
"  right:10px;\n" +
"}\n" +
"\n" +
".content-body {\n" +
"  border: 1px solid #efefef;\n" +
"  margin: 10px 10px 10px 10px;\n" +
"  background: #fff;\n" +
"  -webkit-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.27), 0 0 40px rgba(0, 0, 0, 0.06) inset;\n" +
"  -moz-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.27), 0 0 40px rgba(0, 0, 0, 0.06) inset;\n" +
"  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.27), 0 0 40px rgba(0, 0, 0, 0.06) inset; }s\n" +
".content-body h1 {\n" +
"\n" +
"  /* padding-left:10px; */\n" +
"}\n" +
".show {\n" +
"  display:block; \n" +
"}\n" +
"#login {\n" +
"  font-size:25px;\n" +
"  padding:5px 0 5px 5px;\n" +
"  height:240px;\n" +
"}\n" +
".form-action {\n" +
"    padding: 0px 20px;\n" +
"    position: relative;\n" +
"}\n" +
"\n" +
".form-action {\n" +
"    font-size: 42px;\n" +
"    padding-bottom: 10px;\n" +
"}\n" +
"\n" +
"form {\n" +
"    padding-right: 20px ;\n" +
"}\n" +
"form input[type=\"text\"], form input[type=\"password\"] {\n" +
"    width: 96%;\n" +
"    height: 40px;\n" +
"    margin-bottom: 10px;\n" +
"    padding-left: 15px;\n" +
"    background: none repeat scroll 0% 0% #ffffff ;\n" +
"    border: 1px solid #e3e3e3;\n" +
"    color: rgb(231, 76, 60);\n" +
"    outline: medium none;\n" +
"}\n" +
".facebook a{\n" +
"  float: left;\n" +
" width: 155px;\n" +
" height: auto;\n" +
" padding: 10px;\n" +
" color: white;\n" +
" text-align: left;\n" +
" font-size: 15px;\n" +
"  border-bottom: 2px solid rgb(32, 109, 165);\n" +
" text-shadow: 1px 1px 1px rgb(32, 109, 165);\n" +
"  background: #297fb8;\n" +
"  font-weight:bold;\n" +
"}\n" +
".twitter a{\n" +
"  float: right;\n" +
"  width: 145px;\n" +
"  height: auto;\n" +
"  padding: 10px;\n" +
"  color: white;\n" +
"  text-align: left;\n" +
"  font-size: 15px;\n" +
"  border-bottom: 2px solid rgb(32, 109, 165);\n" +
"  text-shadow: 1px 1px 1px rgb(32, 109, 165);\n" +
"  background:  #297fb8;\n" +
"  font-weight:bold;\n" +
"  \n" +
"}\n" +
".twitter a:hover{background-color: #2886cc;}\n" +
".facebook a:hover{ background-color: #206da5;}\n" +
"hr {\n" +
"  border:1px solid grey;\n" +
"}\n" +
".login{\n" +
"  width: auto; \n" +
"  height: auto; \n" +
"  padding: 10px; \n" +
"  text-align: center; \n" +
"  font-size: 20px; \n" +
"  color: white; \n" +
"  text-shadow: 1px 1px 1px #c0392b;\n" +
"  box-shadow: 0px 3px 0px #c0392b;\n" +
"  margin-top: 10PX;\n" +
"  background-color: #e74c3c;\n" +
"  border:0px; \n" +
"  -moz-border-radius: 5px; \n" +
"  -webkit-border-radius: 5px;\n" +
"  -khtml-border-radius: 5px;\n" +
"  font-weight:bold;\n" +
"  }\n" +
"\n" +
".login:hover {\n" +
"  background-color: #c0392b;\n" +
"}\n" +
".fa {\n" +
"  verticle-align:30;\n" +
"  text-align:center;\n" +
"  margin-top:10px;\n" +
"  padding-right:10px;\n" +
"}\n" +
"  </style>\n" +
"\n" +
"</head>\n" +
"<body><link rel=\"stylesheet\" href=\"//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.min.css\" />\n" +
"<body>\n" +
"    <div style=\" text-align: center; color: red; padding-top: 100px; margin-bottom: -160px\">Login Incorrect </div>\n" +
"    <div class=\"container\" style=\"margin-top:180px;\">\n" +
"    <header>\n" +
"      <div class=\"header-icon\">\n" +
"        <i class=\"fa fa-html5 fa-5x\"></i>\n" +
"      </div>\n" +
"      <div class=\"header-text\">\n" +
"        <span>LOGIN PORTAL</span>\n" +
"        <a href=\"#\"><i class=\"fa fa-times \"></i></a>\n" +
"      </div>\n" +
"    </header>\n" +
"    <div class=\"content\">\n" +
"      <div class=\"content-body\">\n" +
"        <div id=\"login\" class=\"form-action show\">\n" +
"            <form action=\"login\" method=\"post\">\n" +
"            <ul>\n" +
"              <li><input type=\"text\" placeholder=\"USERNAME\" id=\"name\" name=\"name\"></input></li>\n" +
"              <li>\n" +
"                <input type=\"password\" placeholder=\"PASSWORD\" id=\"pass\" name=\"pass\"></input></li>\n" +
"              <li>\n" +
"                  <div class=\"login\"> <button style=\" background-color:none; width:380px; height:50px; padding:0px\">LOGIN</button></div>\n" +
"                \n" +
"                </div>\n" +
"              </li>\n" +
"            </ul>\n" +
"          </form>\n" +
"          \n" +
"        </div>        \n" +
"      </div>\n" +
"     \n" +
"    </div>\n" +
"  </div>\n" +
"</body>\n" +
"</body>\n" +
"</html>";
       writer.print(output);
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
