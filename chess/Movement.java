/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import java.util.HashMap;

/**
 *
 * @author Antonio
 */
public class Movement {

    private Piece piece;
    private Square origin;
    private Square target;
    //private String[] columnIndex = {"a", "b", "c", "d", "e", "f", "g", "h"};
    private int NUM_ROWS = 8;
    private int NUM_COLUMNS = 8;

    public Piece getPiece() {
        return this.piece;
    }

    public Square getOrigin() {
        return this.origin;
    }

    public Square getTarget() {
        return this.target;
    }

    public Movement() {

    }

    void read(String[] piecesNames, Board board) {
        assert (board != null && piecesNames != null && piecesNames.length > 0);

        String pieceName = this.capturePiece(piecesNames);
        byte row = this.captureRow();
        String column = this.captureColumn();

        this.piece = (Piece) (board.getPieceByName(pieceName));
        this.origin = board.getSquare(piece);
        this.target = board.getSquare(row, column);
        if (this.origin == null)
            this.piece = null;
       /* if (!this.origin.hasDeadPiece()) {
            GestorIO console = new GestorIO();
            console.out(this.origin.ToString() + "\n");
            console.out(this.target.ToString() + "\n");
        }*/
    }

    private String capturePiece(String[] piecesNames) {
        assert (piecesNames != null && piecesNames.length > 0);
        GestorIO gestor = new GestorIO();
        String pieceName = "";
        do {
            gestor.out("Pieza: ");
            pieceName = gestor.inString();
        } while (!this.pieceValidate(pieceName.trim(), piecesNames));

        return pieceName;
    }

    private boolean pieceValidate(String pieceName, String[] piecesNames) {
        assert (pieceName != null && piecesNames != null);
        for (String piece : piecesNames) {
            if (pieceName.equals(piece)) {
                return true;
            }
        }
        return false;
    }

    private byte captureRow() {
        GestorIO gestor = new GestorIO();
        byte row = 0;
        do {
            gestor.out("Fila destino: ");
            row = gestor.inByte();
        } while (row < 1 || row > 8);
        return row;
    }

    private String captureColumn() {
        GestorIO gestor = new GestorIO();
        String column = "a";
        do {
            gestor.out("Columna destino: ");
            column = gestor.inString();
        } while (!this.validColumn(column));
        return column;
    }

    private boolean validColumn(String column) {
        assert (column != null);
        for (String c : Board.getColumnsIndex()) {
            if (column.equals(c)) {
                return true;
            }
        }
        return false;
    }

    void setOrigin(Square square) {
        assert (square != null);
        this.origin = square;
    }

    void perform(Board board) {
        assert (board != null);
        this.piece.perform(this, board);
    }

    boolean valid(Board board) {
        assert (board != null);
        return this.piece.validate(this, board);
    }

    void setTarget(Square square) {
        assert (square != null);
        this.target = square;
    }

    boolean hasPiece() {
        return this.piece != null;
    }

}
