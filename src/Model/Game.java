package Model;


import java.util.Scanner;

public class Game {
    int Level;
    Game(int l)
    {
        this.Level = l;
    }
    public void play()
    {
        SudokuGenerator Sudoku = new SudokuGenerator(Level);
        Sudoku.printSudoku();

        Scanner s = new Scanner(System.in);

        int row = s.nextInt();
        int col = s.nextInt();
        int num = s.nextInt();

        Sudoku.SF[row][col] = num;
        Sudoku.printSudoku();
        sudokuChecker SC = new sudokuChecker();
        SC.setFinalSudoku(Sudoku.SF);
        SC.checkIfGood();

    }
}
