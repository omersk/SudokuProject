package View;
import Control.Controller;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet", urlPatterns = "/RegisterServlet")

public class RegisterServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        PrintWriter out = response.getWriter();
        try {

            Controller dbcon = new Controller();
            response.setContentType("text/html;charset=UTF-8");
            String Fname = request.getParameter("Fname").toString();
            String Lname = request.getParameter("Lname").toString();
            String username = request.getParameter("username").toString();
            String Pw = request.getParameter("Pw").toString();
            String Rpw = request.getParameter("Rpw").toString();
            String email = request.getParameter("email").toString();
            String Country = request.getParameter("Country").toString();
            if (!(Pw.toString().equals(Rpw.toString()))) {
                response.sendRedirect("RegisterFailPassword.jsp");
            } else {
                int worked = dbcon.RegisterUser(username, Fname, Lname, Pw, email, Country);
                if (worked == 1)
                {
                    response.sendRedirect("RegisterFailUsername.jsp");
                }
                else {
                    response.sendRedirect("LoginPage.jsp");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            response.sendRedirect("/SudokuProject_war_exploded/");
            return;
        } finally {
            out.close();
        }
    }
}