package com.solarwindsmsp.chess;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 7;
    public static int MAX_BOARD_HEIGHT = 7;
    public static int INVALID_INDEX = -1;

    private Pawn[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
    }

    public void Add(Pawn pawn, int xCoordinate, int yCoordinate) {
        if (IsLegalBoardPosition(xCoordinate, yCoordinate) && isFreePosition(xCoordinate, yCoordinate)) {
            pawn.setChessBoard(this);
            pawn.setXCoordinate(xCoordinate);
            pawn.setYCoordinate(yCoordinate);
            pieces[xCoordinate][yCoordinate] = pawn;
            Remove(xCoordinate, yCoordinate);
        } else {
            pawn.setXCoordinate(INVALID_INDEX);
            pawn.setYCoordinate(INVALID_INDEX);
        }
    }

    public boolean isFreePosition(final int xCoordinate, final int yCoordinate) {
        return pieces[xCoordinate][yCoordinate] == null;
    }

    public boolean IsLegalBoardPosition(final int xCoordinate, final int yCoordinate) {
        if (xCoordinate >= MAX_BOARD_HEIGHT || xCoordinate < 0) {
            return false;
        }
        return yCoordinate < MAX_BOARD_WIDTH && yCoordinate >= 0;
    }

    public void Remove(int xCoordinate, int yCoordinate) {
        pieces[xCoordinate][yCoordinate] = null;
    }
}
