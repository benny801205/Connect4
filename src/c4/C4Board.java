package c4;

import java.util.ArrayList;

/**
 * @author Ping Cheng Chung
 *
 */
/**
 * Represents the state of a Connect Four board on which multiple games can be played. The board consists of a
 * rectangular grid of squares in which playing pieces can be placed. Squares are identified by their zero-based row and
 * column numbers, where rows are numbered starting with the bottom row, and columns are numbered by starting from the
 * leftmost column.
 * 
 * Multiple games can be played on a single board. Whenever a new game begins, the board is empty. There are two
 * players, identified by the constants P1 (Player 1) and P2 (Player 2). P1 moves first in the first game, P2 moves
 * first in the second game, and so on in alternating fashion.
 * 
 * A C4Board also keeps track of the outcomes of the games that have been played on it. It tracks the number of wins by
 * P1, the number of wins by P2, and the number of ties. It does not track games that were abandoned before being
 * completed.
 */

public class C4Board
{
    /** The number used to indicate Player 1 */
    public static final int P1 = 1;

    /** The number used to indicate Player 2 */
    public static final int P2 = 2;

    /** The number used to indicate a tie */
    public static final int TIE = 3;

    /**
     * columns of the board
     */
    private int Boardcol;
    /** rows of the board */    
    private int Boardrow;
    /** a two dimension arrays to simulate the board */
    private int[][] recoBoard;
    /** how many times p1 won */
    private int p1win =0;
    /** how many times p2 won*/
    private int p2win = 0;
    /** how many times ties*/
    private int ties=0;
    /** the turn of each game*/
    private int turmcount=1;
    /**whose turn */
    private int whoseturn = P1;
    /** the maxium turm for each game rows*colums */
    private int totalturm;
    /** who moves first */
    private int whoisfirst=P1;
    /** who moves second*/
    private int whoissecond=P2;
    
    private ArrayList<int[][]> record;
    
    /**
     * Creates a C4Board with the specified number of rows and columns. There should be no pieces on the board and it
     * should be player 1's turn to move.
     * 
     * However, if either rows or cols is less than four, throws an IllegalArgumentException.
     */
    public C4Board (int rows, int cols)
    {
        
        if(rows < 4 || cols < 4)
        {
            throw new IllegalArgumentException("the boundaries are too small");
        }
        totalturm=rows*cols;
        Boardcol=cols;
        Boardrow=rows;
        recoBoard= new int[rows][cols];
        record = new ArrayList<>();
        record.add(new int[rows][cols]);
    }

    /**
     * Sets up the board to play a new game, whether or not the current game is complete. All of the pieces should be
     * removed from the board. The player who had the second move in the previous game should have the first move in the
     * new game.
     */
    public void newGame ()
    {
       // System.out.println("2");
        recoBoard= new int[Boardrow][Boardcol];
        turmcount=1;
        switchtheFirst();
        record=new ArrayList<>();
        record.add(new int[Boardrow][Boardcol]);
        System.out.println(whoisfirst+"\t"+ whoissecond);
    }

    /**
     * Records a move, by the player whose turn it is to move, in the first open square in the specified column. Returns
     * P1 if the move resulted in a win for player 1, returns P2 if the move resulted in a win for player 2, returns TIE
     * if the move resulted in a tie, and returns 0 otherwise.
     * 
     * If the column is full, or if the game is over because a win or a tie has previously occurred, does nothing but
     * return zero.
     * 
     * If a non-existent column is specified, throws an IllegalArgumentException.
     */
    public int moveTo (int col)
    {
        if(col >= Boardcol)
        {
            throw new IllegalArgumentException("the column is over the array.");
        }
            
            
        if(turmcount>totalturm) //if the board is full skip putting token in and return 0
        {
            System.out.println("board is full");
            return 0;
        }

        if((!FourInARow.fourInRow(recoBoard))||(turmcount==1))// check if it finished or not
        {
                if (!putin(col))// if put the number in the array without success
                {
                    System.out.println("colum is full, idiot");
                    return 0;
                }
                else {                                  //if put the number with success
                    if(FourInARow.fourInRow(recoBoard)) // check if someone won or not
                    {
                        turmcount=0;
                        someoneWin(whoseturn);
                        //recode who won
                        return whoseturn;//someone win
                        }
                    else if(turmcount>totalturm)  // if the board is full, it's a tie.
                    {
                        ties++;
                        turmcount=0;
                        return TIE;
                    }
                    return 0;               // nothing happen, go ahead return 0
                }
                
        }
            System.out.println("the game is already end");
             return 0;          // if the game finished(has winer) return 0

    }

    /**
     * Reports the occupant of the board square at the specified row and column. If the row or column doesn't exist,
     * throws an IllegalArgumentException. If the square is unoccupied, returns 0. Otherwise, returns P1 (if the square
     * is occupied by player 1) or P2 (if the square is occupied by player 2).
     */
    public int getOccupant (int row, int col)
    {
        
        return recoBoard[row][col];
    }

    /**
     * Reports whose turn it is to move. Returns P1 (if it is player 1's turn to move), P2 (if it is player 2's turn to
     * move, or 0 (if it is neither player's turn to move because the current game is over because of a win or tie).
     */
    public int getToMove ()
    {
       System.out.println(turmcount);
        if(turmcount==0) {
            return 0;
            }
        else if(turmcount> totalturm) // the board is full
        {

                    return 0;
        }
        else if(turmcount%2==1)
        {
            return whoseturn=whoisfirst; 
        }
        else if(turmcount%2==0)
        {
            return whoseturn=whoissecond; 
        }
        
        return 0;
    }

    /**
     * Reports how many games have been won by player 1 since this board was created.
     */
    public int getWinsForP1 ()
    {
        return p1win;
    }

    /**
     * Reports how many games have been won by player 2 since this board was created.
     */
    public int getWinsForP2 ()
    {
        return p2win;
    }

    /**
     * Reports how many ties have occurred since this board was created.
     */
    public int getTies ()
    {
        return ties;
    }
    
    
    public static int[][] deepCopyIntMatrix(int[][] input) {
        if (input == null)
            return null;
        int[][] result = new int[input.length][];
        for (int r = 0; r < input.length; r++) {
            result[r] = input[r].clone();
        }
        return result;
    }
    
    ////////////////////
    /**
     * put the color number into the array and return true, if the column was full return false.
     * 
     */
    public boolean putin(int col)
    {
        for(int i = 0; i < Boardrow;i++)
        {
            if (recoBoard[i][col]==0)
            {
                
                recoBoard[i][col]=whoseturn;
                
                record.add(turmcount,deepCopyIntMatrix(recoBoard));
                
                turmcount++;//put the token with success then next round
                return true;
            }
            
        }
        
        return false;// the column is full
    }

    public void undo()
    {
        if(turmcount>1) {
        turmcount--;
        record.remove(turmcount);
        recoBoard=deepCopyIntMatrix(record.get(turmcount-1));
        
        }
        else {
            System.out.println("Stop it idiot");
        }
        
        
    }
    
    
    
    
    
    /**
     * The player who had the second move in the previous game should have the first move in the
     * new game.
     */
    public void switchtheFirst()
    {
        int store = whoisfirst;
        whoisfirst= whoissecond;
        whoissecond=store;
    }
    /**
     * update who won the game
     */
    public void someoneWin(int num) {
        if (num==P1)
        {
            p1win++;
        }
        else 
        {
            p2win++;
            }
        
    }
    

    
    
    
    
}
