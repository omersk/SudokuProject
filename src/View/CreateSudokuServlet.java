package View;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CreateSudokuServlet", urlPatterns = "/CreateSudokuServlet")
public class CreateSudokuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        try {
            SudokuCheckFunction scf = new SudokuCheckFunction(request,response);
            response.setContentType("text/html;charset=UTF-8");
            String Diff = request.getParameter("Difficulty").toString();
            String K = "";
            scf.chooseK(K);
            response.sendRedirect("SudokuGame1.jsp");
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
