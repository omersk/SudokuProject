package View;

import Control.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        //DataBaseGood dbcon = DataBaseGood.getInstance();
        Controller dbcon = new Controller();
        String username = dbcon.UsernameBySession(session.toString());
        if (username.equals("")) {
            PrintWriter out = response.getWriter();
            String userName = request.getParameter("Username");
            String password = request.getParameter("Password");
            boolean b = dbcon.loginUser(userName, password);
            if (b) {
                try {
                    dbcon.LoginSession(session.toString(), userName);
                } catch (SQLException e) {
                    dbcon.UpdateLoginSession(session.toString(), userName);
                }

                out.println("checksum");
                response.sendRedirect("MainPage.jsp");
            } else {
                request.setAttribute("msg","");
                request.getRequestDispatcher("LoginFail.jsp").forward(request, response);
                response.sendRedirect("LoginFail.jsp");
            }


            out.close();
        }
        else
        {
            request.setAttribute("msg"," : Already connected as a user named " + username +
                    " please disconnect the user and than login again");
            request.getRequestDispatcher("LoginFail.jsp").forward(request, response);

        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
