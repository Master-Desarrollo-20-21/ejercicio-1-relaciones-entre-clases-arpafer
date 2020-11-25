package Chess;

public class Square {

    private Piece piece;
    private int row;
    private String column;
    private boolean deadKing;

    public Square() {
      this.deadKing = false;
    }

    public Square(int row, String column) {
        this.row = row;
        this.column = column;
        this.deadKing = false;
    }

    public void put(Piece piece) {
        this.piece = piece;
    }

    public boolean has(Piece piece) {
        return this.piece == piece;
    }

    public void show() {

        GestorIO console = new GestorIO();
        if (this.piece == null) {
            console.out(" " + "  |  ");
        } else {
            this.piece.show();
        }
        /*else if (this.piece.getVisualCharacter().length() == 1) {
            console.out(this.piece.toString() + "  |  ");
        } else if (this.piece.getVisualCharacter().length() == 2) {
            console.out(this.piece.toString() + " |  ");
        }*/
    }

    int distanceRow(Square target) {
        return target.getRow() - this.row;
    }

    int distanceColumn(Square target, Board board) {

        int indexColumn = this.getColumnIndex(this, board);
        int indexColumnTarget = this.getColumnIndex(target, board);
        return indexColumnTarget - indexColumn;
    }

    private int getColumnIndex(Square square, Board board) {
        int index = 0;
        for (String column : board.getColumnsIndex()) {
            if (square.getColumn().equals(column)) {
                return index;
            }
            index++;
        }
        return index;
    }

    boolean hasPieceDistinctColor(Color color) {
        if (this.piece != null) {
            if (this.piece.getColor() != color) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    int getRow() {
        return this.row;
    }

    String getColumn() {
        return this.column;
    }
    
    boolean hasPieceSameColor(Square nextSquare) {
        assert (nextSquare != null);
        if (nextSquare.piece == null) {
            return false;
        } else if (this.piece != null && this.piece.getColor() == nextSquare.piece.getColor()) {
            return true;
        } else {
            return false;
        }

    }

    void movePiece(Square target) {
        // Piece piece = this.piece.clone(this.piece);
        Piece tmp = this.piece;
        GestorIO console = new GestorIO();
        console.out(this.ToString() + "\n");
        this.removePiece();        
        target.checkDeadKing();
        target.removePiece();        
        target.put(tmp);
        console.out(target.ToString() + "\n");
    }

    private void removePiece() {                
        this.piece = null;
    }
    private void checkDeadKing() {
        if (this.piece != null && this.piece.getVisualCharacter().toLowerCase().equals("r")) {
            this.deadKing = true;
        }
    }

    public String ToString() {
        if (this.piece != null) {
            return "piece: " + this.piece.toString() + " row: " + this.row + " column: " + this.column;
        } else {
            return " row: " + this.row + " column: " + this.column;
        }
    }

    boolean hasAnyPiece() {
        return this.piece != null;
    }

    boolean hasPieceDistinctColor(Square target) {
        assert (target != null);
        if (this.piece != null) {
            if (target.piece == null) {
                return false;
            }
            if (this.piece.getColor() != target.piece.getColor()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }   

    int columnToIndex(Board board) {
       int index = 0;
        for (String column : board.getColumnsIndex()) {
            if (this.column.equals(column)) {
                return index + 1;
            }
            index++;
        }
        return index + 1;
    }

    boolean hasDeadKing() {
        return this.deadKing;
    }
}
