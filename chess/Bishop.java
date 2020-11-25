package Chess;

public class Bishop extends Piece {

    public Bishop(String visualCaracter, Color color) {
        super(visualCaracter, color);
    }

    @Override
    public boolean validate(Movement movement, Board board) {
        if ((this.validMovement(movement, board) && !this.hasObstacles(movement, board)) || this.canEat(movement)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validMovement(Movement movement, Board board) {
        Square origin = movement.getOrigin();
        Square target = movement.getTarget();
        int distanceRow = origin.distanceRow(target);
        int distanceColumn = origin.distanceColumn(target, board);
        return Math.abs(distanceRow) == Math.abs(distanceColumn);
    }

    private boolean hasObstacles(Movement movement, Board board) {
        Square origin = movement.getOrigin();
        Square target = movement.getTarget();
        int distanceRow = origin.distanceRow(target);
        int distanceColumn = origin.distanceColumn(target, board);        
        int incrementRow = 1;
        if (distanceRow < 0) {
            incrementRow = -1;            
        }        
        int incrementColumn = 1;
        if (distanceColumn < 0) {
            incrementColumn = -1;         
        }
        int i = origin.getRow() + incrementRow;
        int j = board.getColumnIndex(origin.getColumn()) + incrementColumn;
        int row = incrementRow;
        while (Math.abs(row) <= Math.abs(distanceRow)) {
            String columnName = board.getColumnName(j);
            Square nextSquare = board.getSquare(i, columnName);
            if (origin.hasPieceSameColor(nextSquare)) {
                return true;
            }
            i += incrementRow;
            j += incrementColumn;
            row += Math.abs(incrementRow);
        }
        return false;
    }
       
    @Override
    public Piece clone(Piece piece) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
