package Model;


import java.lang.*;

public class SudokuGenerator {
    private int N = 9; // Number of Rows and Columns
    private int SQN = 3; // The Square root of N
    private static int DtR;
    protected int[][] SF; // The Sudoku itself

    public SudokuGenerator(int K)// Constructor
    {
         /*
        The constructor of the SudokuGenerator Class

        Args:
        int K                    : the number of numbers that you want to delete after creating sudoku

        Yields:
        constructor              : build the class
        */
        DtR = K;
        SF = new int[N][N];
        fillSudoku();
    }
    public static void SetDtR(int K)
    {
        // simply set function of K
        DtR=K;

    }
    private int [][] GetFinalSudoku()
    {
        // simply get function for the sudoku
        return SF;
    }
    private void SetFinalSudoku(int [][] FinalMatrix)
    {
        // simply set function of the sudoku
        this.SF = FinalMatrix;
    }

    private void fillSudoku()
    {
         /*
         fill all of the sudoku

        Args:
        None
        Yields:
        void              : fill the sudoku
        */
        fillDiagonal();
        fillTheRest(0, SQN);
        removeDigits();
    }

    private void fillDiagonal() {
         /*
         fill all of the sudoku boxes on the diagonal

        Args:
        None
        Yields:
        void              : fill the sudoku boxes on the diagonal
        */
        for (int i = 0; i < N; i += SQN) {
            fillBox(i, i);
        }
    }

    private void fillBox(int rowStart, int colStart) {
        /*
         fill a box

        Args:
        int rowStart      : the row that start the box
        int colStart      : the col that start the box

        Yields:
        void              : fill a box
        */
        int num;
        for (int i = 0; i < SQN; i++) {
            for (int j = 0; j < SQN; j++) {
                do {
                    num = randomGenerator(N);
                } while (!unUsedInBox(rowStart, colStart, num));

                SF[rowStart + i][colStart + j] = num;
            }
        }
    }

    private boolean checksIfSafe(int row, int col , int num) {
        /*
        Check if the number not used in the box/row/column

        Args:
        int row              : the row that you wish to put/to check the number
        int col              : the col that you wish to put/ to check the number
        int num              : the num that you wish to put/to check
        Yields:
        boolean              : true - safe, false - not safe
        */
        return (unUsedInRow(row, num) &&
                unUsedInCol(col, num) &&
                unUsedInBox(row-row%SQN, col-col%SQN, num));
    }


    private boolean unUsedInCol(int col, int num) {
        /*
        Check if the number not used in the column

        Args:
        int row              : the row that you wish to put/to check the number
        int col              : the col that you wish to put/ to check the number
        int num              : the num that you wish to put/to check
        Yields:
        boolean              : true - safe, false - not safe
        */

        for (int i = 0; i<N; i++)
        {
            if (SF[i][col] == num)
                return false;
        }
        return true;
    }

    private boolean unUsedInRow(int row, int num) {
        /*
        Check if the number not used in the row

        Args:
        int row              : the row that you wish to put/to check the number
        int col              : the col that you wish to put/ to check the number
        int num              : the num that you wish to put/to check
        Yields:
        boolean              : true - safe, false - not safe
        */

        for (int j = 0; j<N; j++)
        {
            if (SF[row][j] == num)
                return false;
        }
        return true;
    }

    private boolean unUsedInBox(int rowStart, int colStart, int num) {
        /*
        Check if the number not used in the box

        Args:
        int row              : the row that you wish to put/to check the number
        int col              : the col that you wish to put/ to check the number
        int num              : the num that you wish to put/to check
        Yields:
        boolean              : true - safe, false - not safe
        */

        for (int i = 0; i < SQN; i++)
            for (int j = 0; j < SQN; j++)
                if (SF[rowStart + i][colStart + j] == num)
                    return false;
        return true;
    }

    private int randomGenerator(int num)
    {
        /*
        Return number between 1 and parameter that the function got

        Args:
        int num          : the function will return random number between 1 to this num
        Yields:
        int              : the random number
        */

        return (int) Math.floor((Math.random() * num + 1));
    }

    private boolean fillTheRest(int i, int j)
    {
        /*
        Fill the rest ( not the diagonal ) of the sudoku, recursive function

        Args:
        int i              : the row that you wish to put/to check the number
        int j              : the col that you wish to put/ to check the number
        Yields:
        boolean            : true - worked, false - failed
        */

        if (j>=N && i<N-1)
        {
            i = i + 1;
            j = 0;
        }
        if (i>=N && j>=N)
            return true;

        if (i < SQN)
        {
            if (j < SQN)
                j = SQN;
        }
        else if (i < N-SQN)
        {
            if (j==(int)(i/SQN)*SQN)
                j =  j + SQN;
        }
        else
        {
            if (j == N-SQN)
            {
                i = i + 1;
                j = 0;
                if (i>=N)
                    return true;
            }
        }

        for (int num = 1; num<=N; num++)
        {
            if (checksIfSafe(i, j, num))
            {
                SF[i][j] = num;
                if (fillTheRest(i, j+1))
                    return true;

                SF[i][j] = 0;
            }
        }
        return false;
    }
    public void printSudoku()
    {
        for (int i = 0; i<N; i++)
        {
            for (int j = 0; j<N; j++)
                System.out.print(SF[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    private void removeDigits()
    {
        /*
        Remove digits from the sudoku by the variable DtR
        and remember the places that we deleted.

        Args:
        None.
        Yields:
        void              : remove DtR digits
        */

        int[][] RememberArray = new int[2][DtR];
        for(int i=0;i<2;i++)
        {
            for(int j=0;j<DtR;j++)
            {
                RememberArray[i][j]=10;
            }
        }
        for(int i =0; i<DtR;i++)
        {
            int a = randomGenerator(9);
            int b = randomGenerator(9);
            if(a-1!=RememberArray[0][i]&&b-1!=RememberArray[1][i])
            {
                RememberArray[0][i] = a-1;
                RememberArray[1][i] = b-1;
                SF[a-1][b-1] = 0;
            }
        }
    }

    public int[][] getSoduko() {
        // simply get function
        return SF;
    }
}