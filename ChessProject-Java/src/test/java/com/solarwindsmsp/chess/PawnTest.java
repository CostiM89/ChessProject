package com.solarwindsmsp.chess;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PawnTest {

    private ChessBoard chessBoard;
    private Pawn testSubject;
    private Pawn testSubjectWhite;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new Pawn(PieceColor.BLACK);
        this.testSubjectWhite = new Pawn(PieceColor.WHITE);
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() {
        this.chessBoard.Add(testSubject, 6, 3);
        assertEquals(6, testSubject.getXCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        this.chessBoard.Add(testSubject, 6, 3);
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.Add(testSubject, 6, 3);
        testSubject.Move(MovementType.MOVE, 7, 3);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.Add(testSubject, 6, 3);
        testSubject.Move(MovementType.MOVE, 4, 3);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.Add(testSubject, 6, 3);
        testSubject.Move(MovementType.MOVE, 6, 2);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(2, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_MoveTwoSpaces_FromStaringPositionBlack_DoesMove() {
        chessBoard.Add(testSubject, 5, 6);
        testSubject.Move(MovementType.MOVE, 5, 4);
        assertEquals(5, testSubject.getXCoordinate());
        assertEquals(4, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_MoveTooManySpaces_DoesNotMove() {
        chessBoard.Add(testSubject, 5, 5);
        testSubject.Move(MovementType.MOVE, 5, 3);
        assertEquals(5, testSubject.getXCoordinate());
        assertEquals(5, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_RemovesOldPositionPawn() {
        chessBoard.Add(testSubject, 3, 4);
        testSubject.Move(MovementType.MOVE, 3, 3);
        assertEquals(3, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
        assertTrue(chessBoard.isFreePosition(3, 4));
    }

    @Test
    public void testWhitePawn_Move_LegalCoordinates_Forward_MoveTooManySpaces_DoesNotMove() {
        chessBoard.Add(testSubjectWhite, 2, 2);
        testSubjectWhite.Move(MovementType.MOVE, 2, 4);
        assertEquals(2, testSubjectWhite.getXCoordinate());
        assertEquals(2, testSubjectWhite.getYCoordinate());
    }

    @Test
    public void testWhitePawn_Move_LegalCoordinates_Forward_MoveTwoSpaces_FromStaringPosition_DoesMove() {
        chessBoard.Add(testSubjectWhite, 1, 1);
        testSubjectWhite.Move(MovementType.MOVE, 1, 3);
        assertEquals(1, testSubjectWhite.getXCoordinate());
        assertEquals(3, testSubjectWhite.getYCoordinate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Capture_NotSupported() {
        chessBoard.Add(testSubjectWhite, 1, 1);
        chessBoard.Add(testSubject, 2, 2);
        testSubjectWhite.Move(MovementType.CAPTURE, 2, 2);
    }
}