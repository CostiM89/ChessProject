package com.solarwindsmsp.chess;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 7;
    public static int MAX_BOARD_HEIGHT = 7;

    private Pawn[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];

    }

    public void Add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        if (pawn.getPieceColor().equals(pieceColor.WHITE)) {
            if (yCoordinate > 0 && yCoordinate < ChessBoard.MAX_BOARD_HEIGHT) {
                yCoordinate = pawn.getYCoordinate() - 1;
            }
            if (yCoordinate == ChessBoard.MAX_BOARD_HEIGHT) {
                yCoordinate = pawn.getYCoordinate() - 2;
            }
            if (xCoordinate > 0 && xCoordinate < ChessBoard.MAX_BOARD_WIDTH) {
                xCoordinate = pawn.getXCoordinate() - 1;
            }
            if (xCoordinate == ChessBoard.MAX_BOARD_WIDTH) {
                xCoordinate = pawn.getXCoordinate() - 2;
            }
        } else {
            if (yCoordinate < ChessBoard.MAX_BOARD_HEIGHT) {
                yCoordinate = pawn.getYCoordinate() + 1;
            }
            if (yCoordinate == 0) {
                yCoordinate = pawn.getYCoordinate() + 2;
            }
            if (xCoordinate < ChessBoard.MAX_BOARD_WIDTH) {
                xCoordinate = pawn.getXCoordinate() + 1;
            }
            if (xCoordinate == 0) {
                xCoordinate = pawn.getXCoordinate() + 2;
            }
        }
        pieces = new Pawn[xCoordinate][yCoordinate];
    }

    public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
        // find if position is legal only for Pawns
        for (Pawn[] pawns : pieces) {
            for (Pawn pawn : pawns) {
                if (PieceColor.WHITE.equals(true)) {
                    if (yCoordinate == ChessBoard.MAX_BOARD_HEIGHT - 6) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    if (xCoordinate == ChessBoard.MAX_BOARD_WIDTH - 1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
