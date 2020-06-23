package com.solarwindsmsp.chess;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 7;
    public static int MAX_BOARD_HEIGHT = 7;

    private Pawn[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];

    }

    public void Add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        //TODO: Check if pawn already exists on the given position

        pawn.setChessBoard(this);
        pawn.setXCoordinate(xCoordinate);
        pawn.setYCoordinate(yCoordinate);
        if (!IsLegalBoardPosition(xCoordinate, yCoordinate)) {
            pawn.setXCoordinate(-1);
            pawn.setYCoordinate(-1);
        } else {
            pieces[xCoordinate][yCoordinate] = pawn;
        }

        /* check if pawn already exists on the given position */
        if (pieces[xCoordinate][yCoordinate] == pawn) {
            pawn.setXCoordinate(xCoordinate + 1);
            pawn.setYCoordinate(yCoordinate + 1);
        }
    }

    public void Remove(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        //TODO: In case of moving a pawn, we need to remove it from the previous position
        if (MovementType.MOVE.equals(true)) {
            xCoordinate +=  1;
            yCoordinate +=  1;
            pawn.setXCoordinate(xCoordinate);
            pawn.setYCoordinate(yCoordinate);
            pieces[xCoordinate][yCoordinate] = pawn;
        }
    }

    public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
        if (xCoordinate >= MAX_BOARD_HEIGHT || xCoordinate < 0) {
            return false;
        }
        return yCoordinate <= MAX_BOARD_WIDTH && yCoordinate >= 0;
    }
}
