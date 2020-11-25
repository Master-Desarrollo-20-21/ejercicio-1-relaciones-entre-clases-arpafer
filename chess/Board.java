package Chess;

import java.util.HashMap;

public class Board {

    private final int NUM_ROWS = 8;
    private final int NUM_COLUMNS = 8;
    private final int NUM_PIECES = 16;

    private Square[] squares;
    private String[] columnIndex = {"a", "b", "c", "d", "e", "f", "g", "h"};
    private String[] whites = {"P0", "P1", "P2", "P3", "P4", "P5", "P6", "P7", "T1", "C1", "A1", "D", "R", "A2", "C2", "T2"};
    private String[] blacks = {"t1", "c1", "a1", "d", "r", "a2", "c2", "t2", "p0", "p1", "p2", "p3", "p4", "p5", "p6", "p7"};
    private HashMap hashPieces;

    String[] getColumnsIndex() {
        return this.columnIndex;
    }

    String[] getWhitePieceNames() {
        return whites;
    }

    String[] getBlackPieceNames() {
        return blacks;
    }

    public String[] getColsIndex() {
        return this.columnIndex;
    }

    public Board(Piece[] pieces) {
        this.hashPieces = new HashMap();
        this.squares = new Square[NUM_ROWS * NUM_COLUMNS];
        for (Piece piece : pieces) {
            this.hashPieces.put(piece.getVisualCharacter(), piece);
        }
    }

    public void initialize() {

        for (int i = 0; i < NUM_ROWS * NUM_COLUMNS; i++) {
            int row = i / NUM_COLUMNS;
            int c = i % NUM_COLUMNS;
            this.squares[i] = new Square(row + 1, this.columnIndex[c]);
        }
        this.setBlackPieces();
        this.setWhitePieces();
    }

    private void setBlackPieces() {
        for (int i = 0; i < this.NUM_COLUMNS * 2; i++) {
            this.squares[i].put((Piece) this.hashPieces.get(this.blacks[i]));
        }
    }

    private void setWhitePieces() {

        for (int i = 48, j = 0; i < 48 + this.NUM_COLUMNS * 2; i++, j++) {
            this.squares[i].put((Piece) this.hashPieces.get(this.whites[j]));
        }
    }

    public void play(Player player) {
        this.show();
        Movement movement = null;
        boolean validMovement = true;
        do {
            movement = player.readMovement(this);
            GestorIO console = new GestorIO();
            if (movement != null) {
                validMovement = movement.valid(this);
                if (!validMovement) {
                    console.out("¡¡ MOVIMIENTO INCORRECTO. INTÉNTELO DE NUEVO !!\n");
                }
            } else {
                console.out("¡¡ LA PIEZA INDICADA YA NO EXISTE SOBRE EL TABLERO. INTÉNTELO DE NUEVO !!\n");
                validMovement = false;
            }
        } while (!validMovement);

        movement.perform(this);
    }

    private void show() {
        GestorIO console = new GestorIO();
        console.out("\n");
        int row = 1;
        for (int i = 0; i < this.NUM_ROWS * this.NUM_COLUMNS; i++) {
            if (i % this.NUM_COLUMNS == 0) {
                console.out("\n");
                console.out(row + "   | ");
                row++;
            }
            this.squares[i].show();
            if (i % this.NUM_COLUMNS == 7) {
                console.out("\n");
                console.out("    ");
                for (int j = 0; j < this.NUM_COLUMNS; j++) {
                    console.out("------");
                }
                // console.out("\n");
            }
        }
        console.out("\n");
        console.out("    ");
        for (int j = 0; j < this.NUM_COLUMNS; j++) {
            console.out("  " + this.columnIndex[j] + "   ");
        }
        console.out("\n\n");
    }

    Square getSquare(int row, String column) {

        int i = 0;
        for (String col : this.columnIndex) {
            if (col.equals(column)) {
                break;
            }
            i++;
        }
        int index = (row - 1) * this.NUM_COLUMNS + i;
        return (Square) this.squares[index];
    }

    Square getSquare(Piece piece) {
        for (Square square : this.squares) {
            if (square.has(piece)) {
                return square;
            }
        }
        return null;
    }

    Piece getPieceByName(String name) {
        return (Piece) this.hashPieces.get(name);
    }

    String getColumnName(int index) {
        int len = this.getColsIndex().length;
        String[] colsIndex = this.getColsIndex();
        for (int i = 0; i < len; i++) {
            if (i == index - 1) {
                return colsIndex[i];
            }
        }
        return null;
    }

    int getColumnIndex(String column) {
        int i = 1;
        for (String c : this.getColsIndex()) {
            if (c.equals(column)) {
                return i;
            }
            i++;
        }
        return i;
    }

    boolean hasWinner() {
        for (Square square : this.squares) {
            if (square.hasDeadKing()) {
                return true;
            }
        }
        return false;
    }
}
