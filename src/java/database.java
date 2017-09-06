/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.awt.HeadlessException;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author Rajendra
 */
public class database {
    Connection con;
    public  Connection getCon()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
           // System.out.println("Connecting to database...");
                         String url = "jdbc:mysql://localhost:3306/student";
			 //  String url =  "jdbc:mysql://node21754-bkbiet.njs.jelastic.vps-host.net/student";
                           //System.out.println("before insert query");
                           con = DriverManager.getConnection(url,"root","");                    //change this also for connection
			//con=DriverManager.getConnection(url,"bkbiet","bkbiet");
                             // System.out.println("before query");
        } catch (ClassNotFoundException | SQLException ex) {
             System.out.println("data base file exception");
        }  
         //System.out.println("database class out");
    return con;
}
    
}
