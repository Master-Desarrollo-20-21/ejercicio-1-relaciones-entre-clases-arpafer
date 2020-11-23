package Chess;

public class King extends Piece {

    public King(String visualCaracter, Color color) {
        super(visualCaracter, color);
    }

    @Override
    public boolean validate(Movement movement, Board board) {
        if ((this.validMovement(movement) && !this.hasObstacles(movement, board)) || this.canEat(movement)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validMovement(Movement movement) {
        Square origin = movement.getOrigin();
        Square target = movement.getTarget();
        int distanceRow = origin.distanceRow(target);
        int distanceColumn = origin.distanceColumn(target);
        boolean movementDiagonal = Math.abs(distanceRow) == Math.abs(distanceColumn) && Math.abs(distanceRow) == 1;
        boolean movementVertical = Math.abs(distanceRow) == 1 && distanceColumn == 0;
        boolean movementHorizontal = distanceRow == 0 && Math.abs(distanceColumn) == 1;
        return movementDiagonal || movementVertical || movementHorizontal;
    }

    private boolean hasObstacles(Movement movement, Board board) {
        Square origin = movement.getOrigin();
        Square target = movement.getTarget();
        int distanceRow = origin.distanceRow(target);
        int distanceColumn = origin.distanceColumn(target);
        if (distanceRow != 0 && distanceColumn != 0) {
            return this.hasObstacleInDiagonal(movement, board);
        } else if (distanceRow != 0) {
            return this.hasObstacleInRow(movement, board);
        } else {
            return this.hasObstacleInColumn(movement, board);
        }
    }
        
    private boolean hasObstacleInDiagonal(Movement movement, Board board) {
        Square origin = movement.getOrigin();
        Square target = movement.getTarget();
        int distanceRow = origin.distanceRow(target);
        int distanceColumn = origin.distanceColumn(target);
        int incrementRow = this.getIncrement(distanceRow);
        int incrementColumn = this.getIncrement(distanceColumn);
        int i = incrementRow;
        int j = incrementColumn;
        while (Math.abs(i) <= Math.abs(distanceRow)) {
            String columnName = board.getColumnName(origin.columnToIndex() + j);
            Square nextSquare = board.getSquare(origin.getRow() + i, columnName);
            if (origin.hasPieceSameColor(nextSquare)) {
                return true;
            }
            i += incrementRow;
            j += incrementColumn;
        }
        return false;
    }

    private boolean hasObstacleInRow(Movement movement, Board board) {
        Square origin = movement.getOrigin();
        Square target = movement.getTarget();
        int distanceRow = origin.distanceRow(target);
        int incrementRow = this.getIncrement(distanceRow);
        int i = incrementRow;
        int j = 1;
        while (Math.abs(i) <= Math.abs(distanceRow)) {
            Square nextSquare = board.getSquare(origin.getRow() + i, origin.getColumn());
            if (origin.hasPieceSameColor(nextSquare)) {
                return true;
            }
            i += incrementRow;
        }
        return false;
    }

    private boolean hasObstacleInColumn(Movement movement, Board board) {
        Square origin = movement.getOrigin();
        Square target = movement.getTarget();
        int distanceColumn = origin.distanceColumn(target);
        int incrementColumn = this.getIncrement(distanceColumn);
        int i = 0;
        int j = incrementColumn;
        while (Math.abs(j) <= Math.abs(distanceColumn)) {
            String columnName = board.getColumnName(origin.columnToIndex() + j);
            Square nextSquare = board.getSquare(origin.getRow(), columnName);
            if (origin.hasPieceSameColor(nextSquare)) {
                return true;
            }
            j += incrementColumn;
        }
        return false;
    }
     private int getIncrement(int distance) {
        int increment = 1;
        if (distance < 0) {
            increment = -1;
        } else if (distance == 0) {
            increment = 0;
        }
        return increment;
    }

   @Override
    public Piece clone(Piece piece) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
