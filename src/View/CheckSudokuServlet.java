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

@WebServlet(name = "CheckSudokuServlet",urlPatterns = "/CheckSudokuServlet")
public class CheckSudokuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            SudokuCheckFunction scf = new SudokuCheckFunction(request,response);
            Controller dbcon = new Controller();
            String is_good = scf.isGood();
            if(!is_good.equals("false"))
            {
                HttpSession session = request.getSession();
                String username = dbcon.UsernameBySession(session.toString());
                dbcon.UpdateScore(username, Integer.parseInt(is_good));
                response.sendRedirect("/SudokuProject_war_exploded/Pass.jsp");
                return;
            }
            response.setContentType("text/html;charset=UTF-8");
            response.sendRedirect("/SudokuProject_war_exploded/Fail.jsp");
            return;

        }
        catch(Exception e){
            e.printStackTrace();
            response.sendRedirect("/SudokuProject_war_exploded/");
            return;
        } finally {
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
