package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class delete extends HttpServlet {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/servlet";

    // Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /*
         * Usage of some methods in HttpServletResponse and ServletResponse interfaces
         */
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...\n");
            Statement smt = conn.createStatement();
            out = response.getWriter();

            out.println("<html>");
            out.println("<body>");
            out.println(
                    "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css' integrity='sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm' crossorigin='anonymous'>");
            out.println(
                    "<script src='https://code.jquery.com/jquery-3.2.1.slim.min.js' integrity='sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN' crossorigin='anonymous'></script>");
            out.println(
                    "<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js' integrity='sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q' crossorigin='anonymous'></script>");
            out.println(
                    "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js' integrity='sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl' crossorigin='anonymous'></script>");

            out.println("<div class='container' style='align-items: center;justify-content: center;'>");
            // smt.executeUpdate("insert into bill(name,amount)
            // values('"+username+"',"++")");

            if (request.getParameter("t-shirts") != null) {
                smt.executeUpdate("delete from items where name='t-shirts'");
                out.println("<script>alert('item deleted');window.location.href='/demo/admin';</script>");
            }
            if (request.getParameter("shirts") != null) {
                smt.executeUpdate("delete from items where name='shirts'");
                out.println("<script>alert('item deleted');window.location.href='/demo/admin';</script>");
            }
            if (request.getParameter("coats") != null) {
                smt.executeUpdate("delete from items where name='coats'");
                out.println("<script>alert('item deleted');window.location.href='/demo/admin';</script>");
            }
            if (request.getParameter("pants") != null) {
                smt.executeUpdate("delete from items where name='pants'");
                out.println("<script>alert('item deleted');window.location.href='/demo/admin';</script>");
            }

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException se) {
            // Handle errors for JDBC
            System.out.print(se);
        } catch (Exception e) {
            // Handle errors for Class.forName
            System.out.print(e);
        }
    }

    @Override

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
