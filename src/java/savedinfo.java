

public class savedinfo
{
public static String header;
public String headpart()
{
    String head ="" ;
    head+="<head>\n";
    head+="<title>welcome to bkbiet admin panel </title>\n";
    head+="<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n";
    head+="<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\n";
    head+="<script src=\"jquery/jquery-3.1.1.min.js\"></script>\n";
    head+="<script src=\"js/bootstrap.min.js\"></script>\n";
    head+="<script src=\"https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js\"></script>\n";
    head+="<link rel=\"stylesheet\" href=\"https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css\">\n";
    head+="<script src=\"scripts/time.js\"></script>\n";
    head+="<link href=\"css/admin.css\" rel=\"stylesheet\" type=\"text/css\"/>";
    head+="</head>\n";
return head;
}
public String headerpart()
{
String header="";
       header+="<div class=\"container\">\n";
         header+="<div class=\"row\" style=\"background-color:none ;padding: 0px; border-radius: 15px;\">\n";
                header+="<div>\n";
                header+="<div class=\"col-lg-2\" style=\"background:transparent; text-align: center \" >\n";
                        header+="<img src=\"images\\bkbiet.jpg\" class=\"img-rounded\" class=\"img-responsive\" alt=\"Cinque Terre\" width=\"150\" height=\"150\"> \n";
                header+="</div>\n";
                    header+="<div class=\"col-lg-8\" style=\"text-align: center\"> <h1 style=\"color: black ;font-variant:small-caps; font-style:normal; \">Teacher-Student Portal</h1>\n";
                      header+="<p> B.K. Birla Institute Of Engineering and Technology</p>\n";
                      header+="</p>\n";
                header+="</div>\n";
                header+="<div class=\"col-lg-2\" style=\"text-align: right; float: right\"> \n";
                        header+="<p id=\"time\" style=\"padding-top:30px; padding-bottom: 0px; color: black;font-variant:small-caps; font-style:normal;  \"></p>\n";
                        header+="<p id=\"txt\" style=\"color: black; padding-top: 0px ;font-variant:small-caps; font-style:normal;\" ></p>\n";
                   header+="</div>\n";
                header+="</div>\n";
            header+="</div>\n";
            header+="</div>\n";
             
return header;
}
public String menupart()
{
String menu="";

        menu+="<div class=\"container\" style=\"padding: 0px;\">\n";
        menu+="<nav class=\"navbar navbar-default\" style=\"background-color:none; background:  transparent; border-radius: 15px; \">\n";
                            menu+="<div class=\"container\" >\n";
                          menu+="<!-- Brand and toggle get grouped for better mobile display -->\n";
               menu+="<div class=\"navbar-header\">\n";
                  menu+="<button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\" aria-expanded=\"false\">\n";
                     menu+="<span class=\"sr-only\">Toggle navigation</span>\n";
                     menu+="<span class=\"icon-bar\"></span>\n";
                     menu+="<span class=\"icon-bar\"></span>\n";
                     menu+="<span class=\"icon-bar\"></span>\n";
                  menu+="</button>\n";
                   menu+="<a class=\"navbar-brand\" href=\"home.html\" style=\"color: black;font-variant:small-caps; font-style:normal;\">Bkbiet</a>\n";
               menu+="</div>\n";

            menu+="<!-- Collect the nav links, forms, and other content for toggling -->\n";
            menu+="<div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\" style=\"color:black\">\n";
           menu+="<ul class=\"nav navbar-nav\">";
               menu+="<li ><a href=\"admin\" style=\"color: black\"> Home <span class=\"sr-only\">(current)</span></a></li>\n";
               menu+="<li class=\"dropdown\">\n";
                menu+="<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\" style=\"color: black\">Teacher<span class=\"caret\"></span></a>\n";
                   menu+="<ul class=\"dropdown-menu\" style=\"background-color:none \">\n";
                       menu+="<li><a href=\"T_register\" style=\"color: black\" onclick=\"document.getElementById('treg').style.visibility='visible' ;\">New Registration</a></li>\n";
                       
                     menu+="<li role=\"separator\" class=\"divider\"></li>\n";
                     menu+="<li><a href=\"teacherupdate\" style=\"color: black\">Update Details</a></li>\n";
                     menu+="<li role=\"separator\" class=\"divider\"></li>\n";
                     menu+="<li><a href=\"trecorddefine\" style=\"color:black\">Define Teacher Subject</a></li>\n";
                    menu+="<li role=\"separator\" class=\"divider\"></li>\n";
                     menu+="<li><a href=\"trshow\" style=\"color: black\"> Teacher subject details</a></li>\n";
                      menu+="<li role=\"separator\" class=\"divider\"></li>\n";
                     menu+="<li><a href=\"teacherdelete\" style=\"color: black\">Delete Record</a></li>\n";
                     
                     menu+="</ul>\n";
              menu+="</li>\n";
               menu+="<li class=\"dropdown\">\n";
                menu+="<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\" style=\"color: black\">Student<span class=\"caret\"></span></a>\n";
                menu+="<ul class=\"dropdown-menu\" style=\"background-color:none \">\n";
                     menu+="<li><a href=\"studentregister\" style=\"color: black\">New Registration</a></li>\n";
                     menu+="<li role=\"separator\" class=\"divider\"></li>\n";
                     menu+="<li><a href=\"studentupdate\" style=\"color: black\">Update Details</a></li>\n";
                     menu+="<li role=\"separator\" class=\"divider\"></li>\n";
                     menu+="<li><a href=\"studentdelete\" style=\"color: black\">Delete Records</a></li>\n";
                   menu+="</ul>\n";
              menu+="</li>\n";
              
              menu+="<li class=\"dropdown\">";
                menu+="<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\" style=\"color: black\">Course<span class=\"caret\"></span></a>";
                menu+="<ul class=\"dropdown-menu\" style=\"background-color:none \">";
                     menu+="<li><a href=\"newsubject\" style=\"color:black\">New Course</a></li>";
                     menu+="<li role=\"separator\" class=\"divider\"></li>";
                     menu+="<li><a href=\"courseupdate\" style=\"color:black\">Update Existing Course</a></li>";
                    menu+="</ul>";
              menu+="</li>";
              
              menu+="<li class=\"dropdown\">";
                menu+="<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\" style=\"color: black\">Event<span class=\"caret\"></span></a>";
                menu+="<ul class=\"dropdown-menu\" style=\"background-color:none \">";
                     menu+="<li><a href=\"viewevent\" style=\"color: black\">View Event</a></li>";
                     menu+="<li role=\"separator\" class=\"divider\"></li>";
                     menu+="<li><a href=\"addevent\" style=\"color: black\">Add Event</a></li>";
                    menu+="</ul>";
              menu+="</li>";
          menu+="</ul>\n";
            
              menu+="<ul class=\"nav navbar-nav navbar-right\">\n";
                 
                 menu+="<li><a href=\"logout\" style=\"color: black\">Logout</a></li>\n";
              menu+="</ul>\n";
    menu+="</div> \n";
    menu+="</div>  \n";
    menu+="</nav> \n";
    menu+="</div>\n";
return menu;
}


public static void main(String args[])
{
}

}
