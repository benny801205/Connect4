package c4;

import static org.junit.Assert.*;

import org.junit.Test;

public class C4Tests
{

    @Test(expected = IllegalArgumentException.class) 
    public void testC4Boarderr ()
    {
        C4Board c4= new C4Board(3, 3);
    }

    
        @Test(expected = IllegalArgumentException.class) 
    public void testMoveToerr ()
    {
            C4Board c4= new C4Board(6, 7);
            c4.moveTo(7);    
    }
    
        @Test
        public void testMoveto ()
        {
            C4Board c4= new C4Board(6, 7);
            assertEquals(0,c4.moveTo(6));
            assertEquals(0,c4.moveTo(6));
            
        }
        @Test
        public void testGetToMove ()
        {
            C4Board c4 = new C4Board(6, 7);
            assertEquals(1,c4.getToMove());


        }
    
    @Test
    public void testMovetoandGetToMove ()
    {

        C4Board c4= new C4Board(6, 7);
        assertEquals(1,c4.getToMove());
        assertEquals(0,c4.moveTo(6));
        assertEquals(2,c4.getToMove());
        assertEquals(0,c4.moveTo(6));
        assertEquals(1,c4.getToMove());
        assertEquals(0,c4.moveTo(5));
        assertEquals(2,c4.getToMove());
        assertEquals(0,c4.moveTo(5));
        assertEquals(1,c4.getToMove());
        assertEquals(0,c4.moveTo(4));
        assertEquals(2,c4.getToMove());
        assertEquals(0,c4.moveTo(4));
        assertEquals(1,c4.getToMove());
        assertEquals(1,c4.moveTo(3));// red win
        assertEquals(0,c4.getToMove());
    }        



    @Test
    public void testGetOccupant ()
    {
        C4Board c4 = new C4Board(6, 7);
        c4.moveTo(5);
        c4.getToMove();
        assertEquals(1,c4.getOccupant(0, 5));
        c4.moveTo(5);
        c4.getToMove();
        assertEquals(2,c4.getOccupant(1, 5));
        c4.moveTo(3);
        c4.getToMove();
        assertEquals(1,c4.getOccupant(0, 3));
        
        
    }

    @Test
    public void testNewGame ()
    {
        C4Board c4 = new C4Board(6, 7);
        assertEquals(1,c4.getToMove());//P1 moves first
        c4.moveTo(5);
        c4.getToMove();
        assertEquals(1,c4.getOccupant(0, 5));
        c4.newGame();
        assertEquals(0,c4.getOccupant(0, 5));
        assertEquals(2,c4.getToMove()); //P2 moves first
    }

    
    @Test
    public void testGetWinsForP1 ()
    {
        C4Board c4= new C4Board(6, 7);
        assertEquals(0,c4.getWinsForP1());
        assertEquals(1,c4.getToMove());
        assertEquals(0,c4.moveTo(6));
        assertEquals(2,c4.getToMove());
        assertEquals(0,c4.moveTo(6));
        assertEquals(1,c4.getToMove());
        assertEquals(0,c4.moveTo(5));
        assertEquals(2,c4.getToMove());
        assertEquals(0,c4.moveTo(5));
        assertEquals(1,c4.getToMove());
        assertEquals(0,c4.moveTo(4));
        assertEquals(2,c4.getToMove());
        assertEquals(0,c4.moveTo(4));
        assertEquals(1,c4.getToMove());
        assertEquals(1,c4.moveTo(3));// red win
        assertEquals(1,c4.getWinsForP1());
        
        
    }

    @Test
    public void testGetWinsForP2 ()
    {

        C4Board c4= new C4Board(6, 7);
        assertEquals(0,c4.getWinsForP2());
        c4.newGame();
        assertEquals(2,c4.getToMove());
        assertEquals(0,c4.moveTo(6));
        assertEquals(1,c4.getToMove());
        assertEquals(0,c4.moveTo(6));
        assertEquals(2,c4.getToMove());
        assertEquals(0,c4.moveTo(5));
        assertEquals(1,c4.getToMove());
        assertEquals(0,c4.moveTo(5));
        assertEquals(2,c4.getToMove());
        assertEquals(0,c4.moveTo(4));
        assertEquals(1,c4.getToMove());
        assertEquals(0,c4.moveTo(4));
        assertEquals(2,c4.getToMove());
        assertEquals(2,c4.moveTo(3));// yellow win
        assertEquals(1,c4.getWinsForP2());
        
        
    }


}
