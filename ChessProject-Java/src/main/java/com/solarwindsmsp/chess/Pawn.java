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
        if (MovementType.CAPTURE.equals(movementType)) {
            throw new IllegalArgumentException("Capture not supported!");
        }

        /* Check for legal board position */
        if (!chessBoard.IsLegalBoardPosition(newX, newY)) {
            return;
        }

        /* Check so that we cannot move backwards or on the same Y axis */
        if(!isForwardSpace(newY)) {
            return;
        }

        if (MovementType.MOVE.equals(movementType) && isValidMove(newX, newY)) {
            chessBoard.Add(this, newX, newY);
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

    private boolean isForwardSpace(final int newY) {
        if (this.pieceColor == PieceColor.WHITE && newY > this.yCoordinate) {
            return true;
        } else {
            return this.pieceColor == PieceColor.BLACK && newY < this.yCoordinate;
        }
    }

    /* When we move a pawn, it must be on the same X axis and it should not move too many spaces */
    private boolean isValidMove(final int newX, final int newY) {
        /* First check we are moving on the same X axis */
        if (this.xCoordinate != newX) {
            return false;
        }

        /* Check that the position we want to advance is free */
        if (!chessBoard.isFreePosition(newX, newY)) {
            return false;
        }

        /* Get the total number of spaces we want to advance */
        final int spacesToAdvance = Math.abs(newY - this.yCoordinate);

        /* We can move 2 pieces if we are on a starting position, otherwise only one */
        if (this.pieceColor == PieceColor.WHITE) {
            if (this.yCoordinate == 1 && spacesToAdvance <= 2) {
                return true;
            } else {
                return spacesToAdvance == 1;
            }
        } else {
            if (this.yCoordinate == 6 && spacesToAdvance <= 2) {
                return true;
            } else {
                return spacesToAdvance == 1;
            }
        }
    }
}
