package com.solarwindsmsp.chess;

import java.util.ArrayList;
import java.util.List;

public class Pawn {

    private ChessBoard chessBoard;
    private int xCoordinate;
    private int yCoordinate;
    private PieceColor pieceColor;

    public Pawn(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public ChessBoard getChesssBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int value) {
        this.xCoordinate = value;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int value) {
        this.yCoordinate = value;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    private void setPieceColor(PieceColor value) {
        pieceColor = value;
    }

    public void Move(MovementType movementType, int newX, int newY) {
        List<MovementType> moves = new ArrayList<MovementType>();
        moves.clear();
        if (getPieceColor().equals(PieceColor.WHITE)) {
            for (int i=0; i <= newX; i++) {
                if (newY > 0) {
                    moves.add(newY - 1, MovementType.MOVE);
                    newX++;
                    newY++;
                }
                if (newY == ChessBoard.MAX_BOARD_HEIGHT) {
                    moves.add(newY - 2, MovementType.MOVE);
                }
            }
        } else {
            for (int j=0; j < newX; j++) {
                if (newY < ChessBoard.MAX_BOARD_HEIGHT) {
                    moves.add(newY + 1, MovementType.MOVE);
                    newY++;
                    newX++;
                }
                if (newY == 0) {
                    moves.add(newY + 2, MovementType.MOVE);
                }
            }
        }
    }

    @Override
    public String toString() {
        return CurrentPositionAsString();
    }

    protected String CurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate, pieceColor);
    }
}
