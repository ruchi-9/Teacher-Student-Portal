<%-- 
    Document   : header
    Created on : Mar 8, 2017, 8:26:42 PM
    Author     : Beniwal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
                <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="jquery/jquery-3.1.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
        <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="scripts/time.js"></script>
    
    </head>
    <body onload="startTime()">
        <div class="container">
            <div class="row" style="background-color: #1b6d85;padding: 0px; border-radius: 15px;">
                <div>
                <div class="col-lg-2" style="background:transparent; text-align: center " >
                        <img src="images\bkbiet.jpg" class="img-rounded" class="img-responsive" alt="Cinque Terre" width="150" height="150"> 
                </div>
                    <div class="col-lg-8" style="text-align: center"> <h1 style="color: #fff ;font-variant:small-caps; font-style:normal; ">Teacher-Student Portal</h1>
                      <p> B.K. Birla Institute Of Engineering and Technology</p>
                      </p>
                </div>
                <div class="col-lg-2" style="text-align: right; float: right"> 
                        <p id="time" style="padding-top:30px; padding-bottom: 0px; color: #fff;font-variant:small-caps; font-style:normal;  "></p>
                        <p id="txt" style="color: #fff; padding-top: 0px ;font-variant:small-caps; font-style:normal;" ></p>
                        
                    </div>
                </div>
            </div>
            
        </div>
        <div class="container" style="padding: 0px;">
                <nav class="navbar navbar-default" style="background-color: #1b6d85; border-radius: 15px; ">
                    <div class="container" >
              <!-- Brand and toggle get grouped for better mobile display -->
               <div class="navbar-header">
                  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                     <span class="sr-only">Toggle navigation</span>
                     <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                  </button>
                   <a class="navbar-brand" href="admin.html" style="color: #fff">Bkbiet</a>
               </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="color: #fff">
           <ul class="nav navbar-nav">
               <li ><a href="#" style="color: #fff"> Home <span class="sr-only">(current)</span></a></li>
               <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="color: #fff">Teacher<span class="caret"></span></a>
                   <ul class="dropdown-menu" style="background-color:#1b6d85 ">
                       <li><a href="tregister.html" style="color: #fff" onclick="document.getElementById('treg').style.visibility='visible' ;">New Registration</a></li>
                     <li role="separator" class="divider"></li>
                     <li><a href="#" style="color: #fff">Update Details</a></li>
                     <li role="separator" class="divider"></li>
                     <li><a href="#" style="color: #fff">Delete Records</a></li>
                   </ul>
              </li>
               <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="color: #fff">Student<span class="caret"></span></a>
                <ul class="dropdown-menu" style="background-color:#1b6d85 ">
                     <li><a href="studentregister.html" style="color: #fff">New Registration</a></li>
                     <li role="separator" class="divider"></li>
                     <li><a href="#" style="color: #fff">Update Details</a></li>
                     <li role="separator" class="divider"></li>
                     <li><a href="#" style="color: #fff">Delete Records</a></li>
                   </ul>
              </li>
          </ul>
             <form class="navbar-form navbar-left">
                 <div class="form-group">
                   <input type="text" class="form-control" placeholder="Search">
                 </div>
             <button type="submit" class="btn btn-default" >Submit</button>
             </form>
              <ul class="nav navbar-nav navbar-right">
                 <li><a href="#" style="color: #fff">Chat</a></li>
                 <li><a href="#" style="color: #fff">Logout</a></li>
              </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
            </div>
    </body>
</html>
