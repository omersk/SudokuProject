package View;

import Control.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AdminServlet",urlPatterns = "/AdminServlet")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Controller dbcon = new Controller();
        HttpSession session2 = request.getSession();
        String MySession = session2.toString();
        String username = dbcon.UsernameBySession(MySession);
        String buttonvalue = request.getParameter("button");
        switch (buttonvalue)
        {
            case "Make Admin": dbcon.MakeAdmin(request.getParameter("username"));
                break;
            case "Remove":
                System.out.println(request.getParameter("username"));
                String passed = dbcon.DeleteUser(request.getParameter("username").replaceAll("\"", " "));
                if(!username.equals(request.getParameter(username))) {
                    request.setAttribute("msg","");
                }
                else
                {
                    passed = "failed";
                }
                if (passed.equals("failed"))
                {
                    request.setAttribute("msg","You can't delete yourself or other admins");
                    request.getRequestDispatcher("Admin.jsp").forward(request, response);

                }
                break;
            case "Zero Score": dbcon.ZeroScore(request.getParameter("username"));
                break;
        }
        request.setAttribute("msg","");
        request.getRequestDispatcher("Admin.jsp").forward(request, response);
    }
}
