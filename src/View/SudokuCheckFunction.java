package View;

import Model.sudokuChecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class SudokuCheckFunction {
    PrintWriter out;
    HttpServletRequest request;
    HttpServletResponse response;
    SudokuCheckFunction(HttpServletRequest request, HttpServletResponse response)
        {
        this.request = request;
        this.response = response;
    }
    public String isGood() {
        int[][] sudokutable = new int[9][9];
        int s = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    sudokutable[i][j] = Integer.parseInt(this.request.getParameter("z[" + i + "][" + j + "]"));
                }
                catch (java.lang.NumberFormatException e)
                {
                    sudokutable[i][j] = Integer.parseInt(this.request.getParameter("Z[" + i + "][" + j + "]"));
                    s = s+1;
                }
            }
        }
        sudokuChecker checkme = new sudokuChecker();
        checkme.setFinalSudoku(sudokutable);
        if(checkme.checkIfGood())
        {
            return String.valueOf(s);
        }
        else
        {
            return "false";
        }
    }
    public void chooseK(String K)
    {
        String Diff = request.getParameter("Difficulty").toString();
        if (Diff.toString().equals("Easy")){
            K = "20";
        }
        if (Diff.toString().equals("Medium")){
            K = "35";

        }
        if (Diff.toString().equals("Hard")) {
            K = "50";

        }
        request.setAttribute("K", K);
    }
}
