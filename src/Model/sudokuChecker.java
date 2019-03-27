package Model;


public class sudokuChecker {
    /*
    This class came up to the world in order to check
    our sudoku after sloving one.
     */
    private int N = 9; // Number of Rows and Columns
    private int SQN = 3; // The Square root of N
    private int[][] Matrix;

    public sudokuChecker()
    {
        /*
        This is the constructor of the sudokuChecker class.

        Args:
        None

        Yields:
        int              : zero our matrix

        */
        Matrix = new int[N][N];
        for(int i = 0; i<N;i++)
        {
            for(int j=0; j<N; j++)
            {
                Matrix[i][j]=0;
            }
        }
    }
    public void setFinalSudoku(int [][] FinalMatrix)
    {
        /*
        This function set our matrix to a matrix parameter.

        Args:
        int[][] FinalMatrix   : the matrix which you want to replace our matrix.

        Yields:
        void                  : set this matrix to the FinalMatrix

        */
        this.Matrix = FinalMatrix;
    }
    public boolean checkIfGood()
    {
        /*
        This function check if our sudoku solved good.

        Args:
        None.

        Yields:
        boolean              : the sudoku is right or not.

        */
        int index = 0;
        for(int i = 0; i<N;i++)
        {
            for(int j=0; j<N; j++)
            {
                index++;
                boolean b = !checksIfSafe2(i,j,Matrix[i][j]);
                if(b)
                {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checksIfSafe2(int row, int col , int num) {
        /*
        Check if the number not used in the box/row/column

        Args:
        int row              : the row that you wish to put/to check the number
        int col              : the col that you wish to put/ to check the number
        int num              : the num that you wish to put/to check
        Yields:
        boolean              : true - safe, false - not safe
        */
        return (unUsedInRow2(row,col, num) &&
                unUsedInCol2(row, col, num) &&
                unUsedInBox2(row, col, num));
    }

    private boolean unUsedInCol2(int row, int col, int num) {
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
            if(i!=row)
            {
                if (Matrix[i][col] == num)
                    return false;
            }
        }
        return true;
    }

    private boolean unUsedInRow2(int row,int col, int num) {
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
            if(j!=col)
            {
                if (Matrix[row][j] == num)
                    return false;
            }
        }
        return true;
    }

    private boolean unUsedInBox2(int rowStart, int colStart, int num) {
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
                if(i!=rowStart%SQN&&j!=colStart%SQN)
                {
                    if (Matrix[rowStart-rowStart%SQN + i][colStart-colStart%SQN + j] == num)
                        return false;
                }
        return true;
    }


}