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

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;

import java.sql.*;

public class servlet extends HttpServlet {

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
            String username = "";

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

            out.println(
                    "<div class='container' style='align-items: center;justify-content: center;display: flex'width : 100%>");
            if (request.getParameter("sname") != null) {

                String name = request.getParameter("sname");
                String email = request.getParameter("semail");
                String pass = request.getParameter("spass");
                username = name;
                smt.executeUpdate(
                        "insert into user(name,email,password)values('" + name + "','" + email + "','" + pass + "')");
                // out.println("<script>alert('Your account has been succesfully created. Shop
                // Now !!')</script>");

            } else if (request.getParameter("lemail") != null) {
                String email = request.getParameter("lemail");
                String gpass = request.getParameter("lpass");
                ResultSet result = smt.executeQuery("select * from user where email = '" + email + "'");
                int check = 0;
                while (result.next()) {
                    if (result.getString(3).equals(gpass)) {
                        out.println("<script>alert('Welcome !!');</script>");
                        check = 1;
                        break;
                    }
                }

                if (check == 0) {
                    out.println(
                            "<script>alert('Enter correct password and email');window.location.href='/demo';</script>");

                }

            }

            ResultSet result = smt.executeQuery("select * from items");
            int tshirt_cost = 0;
            int shirt_cost = 0;
            int pant_cost = 0;
            int coat_cost = 0;
            out.println("<form action='bill' method='post'>");
            while (result.next()) {
                out.println("<div class='card' style='width: 18rem;'><div class='card-body'><h5 class='card-title'>"
                        + result.getString(1) + "</h5Some ><h6 class='card-subtitle mb-2 text-muted'>"
                        + result.getInt(2)
                        + "</h6><p class='card-text'>100% cotton, 50% off</p><label for='exampleInputPassword1'>Qunatity : </label><input type='number' value=0 class='form-control m-3' name='"
                        + result.getString(1) + "'></div></div>");

            }

            out.println(
                    "<center><button type='submit' class='btn btn-primary btn-lg m-3'>Buy</button></center></form>");

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
