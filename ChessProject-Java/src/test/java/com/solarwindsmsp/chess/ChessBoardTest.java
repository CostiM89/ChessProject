package com.solarwindsmsp.chess;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChessBoardTest extends TestCase {

    private ChessBoard testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = new ChessBoard();
    }

    @Test
    public void testHas_MaxBoardWidth_of_7() {
        assertEquals(7, ChessBoard.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testHas_MaxBoardHeight_of_7() {
        assertEquals(7, ChessBoard.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_0_Y_equals_0() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(0, 0);
        assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_5_Y_equals_5() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(5, 5);
        Assert.assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_5() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(11, 5);
        assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_0_Y_equals_9() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(0, 9);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_0() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(11, 0);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_For_Negative_Y_Values() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(5, -1);
        Assert.assertFalse(isValidPosition);
    }

    @Test
    public void Avoids_Duplicate_Positioning() {
        Pawn firstPawn = new Pawn(PieceColor.BLACK);
        Pawn secondPawn = new Pawn(PieceColor.BLACK);
        testSubject.Add(firstPawn, 6, 3, PieceColor.BLACK);
        testSubject.Add(secondPawn, 6, 3, PieceColor.BLACK);
        assertEquals(6, firstPawn.getXCoordinate());
        assertEquals(3, firstPawn.getYCoordinate());
        assertEquals(-1, secondPawn.getXCoordinate());
        assertEquals(-1, secondPawn.getYCoordinate());
    }

    @Test
    public void testLimits_The_Number_Of_Pawns()
    {
        for (int i = 0; i < 10; i++)
        {
            Pawn pawn = new Pawn(PieceColor.BLACK);
            int row = i / ChessBoard.MAX_BOARD_WIDTH;
            testSubject.Add(pawn, 6 + row, i % ChessBoard.MAX_BOARD_WIDTH, PieceColor.BLACK);
            if (row < 1)
            {
                assertEquals(7 + row, pawn.getXCoordinate());
                assertEquals(i % ChessBoard.MAX_BOARD_WIDTH, pawn.getYCoordinate());
            }
            else
            {
                assertEquals(-1, pawn.getXCoordinate());
                Assert.assertEquals(-1, pawn.getYCoordinate());
            }
        }
    }

    @Test
    public void testAdd() {
        Pawn whitePawn = new Pawn(PieceColor.WHITE);
        Pawn blackPawn = new Pawn(PieceColor.BLACK);
        testSubject.Add(whitePawn, whitePawn.getXCoordinate(), whitePawn.getYCoordinate(), PieceColor.WHITE);
        testSubject.Add(blackPawn, blackPawn.getXCoordinate(), blackPawn.getYCoordinate(), PieceColor.BLACK);
        assertEquals(1, whitePawn.getXCoordinate());
        assertEquals(1, whitePawn.getYCoordinate());
        assertEquals(1, blackPawn.getXCoordinate());
        assertEquals(1, blackPawn.getYCoordinate());
    }

    @Test
    public void testRemove() {
        Pawn whitePawn = new Pawn(PieceColor.WHITE);
        Pawn blackPawn = new Pawn(PieceColor.BLACK);
        testSubject.Remove(whitePawn, whitePawn.getXCoordinate(), whitePawn.getYCoordinate(), PieceColor.WHITE);
        testSubject.Remove(blackPawn, whitePawn.getXCoordinate(), whitePawn.getYCoordinate(), PieceColor.WHITE);
        assertEquals(0, whitePawn.getXCoordinate());
        assertEquals(0, whitePawn.getYCoordinate());
        assertEquals(0, blackPawn.getXCoordinate());
        assertEquals(0, blackPawn.getYCoordinate());
    }
}