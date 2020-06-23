package com.solarwindsmsp.chess;

public class Pawn {

    private ChessBoard chessBoard;
    private int xCoordinate;
    private int yCoordinate;
    private PieceColor pieceColor;

    public Pawn(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public ChessBoard getChessBoard() {
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
        chessBoard = new ChessBoard();
        if (!chessBoard.IsLegalBoardPosition(newX, newY)) {
            return;
        }

        /* cannot move backwards */
        if (this.pieceColor == PieceColor.BLACK && newX < this.xCoordinate) {
            return;
        }

        /* cannot move backwards */
        if (this.pieceColor == PieceColor.WHITE && newX > this.xCoordinate) {
            return;
        }

        if (MovementType.MOVE.equals(movementType)) {
            chessBoard.Add(this, newX, newY, this.pieceColor);
        } else {
            //TODO: implement capture logic
            if (newX > this.xCoordinate && newY == this.yCoordinate + 1 || newY == this.yCoordinate - 1) {
                chessBoard.Remove(this, newX, newY, this.pieceColor);
            } else {
                return;
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
