package Chess;

public class Tower extends Piece {

    public Tower(String visualCaracter, Color color) {
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
        return (distanceRow == 0 && Math.abs(distanceColumn) > 0) || (Math.abs(distanceRow) > 0 && distanceColumn == 0);
    }

    private boolean hasObstacles(Movement movement, Board board) {
        Square origin = movement.getOrigin();
        Square target = movement.getTarget();
        int distanceRow = origin.distanceRow(target);
        int distanceColumn = origin.distanceColumn(target);
        int distance = this.getDistance(distanceRow, distanceColumn);
        int incrementRow = this.getIncrement(distanceRow);
        int incrementColumn = this.getIncrement(distanceColumn);
        int distanceIndex = 0;
        if (incrementRow != 0) {
            distanceIndex = incrementRow;
        } else {
            distanceIndex = incrementColumn;
        }
        while (Math.abs(distanceIndex) <= Math.abs(distance)) {
            if (incrementRow != 0) {                
                Square nextSquare = board.getSquare(origin.getRow() + distanceIndex, target.getColumn());
                if (origin.hasPieceSameColor(nextSquare))
                    return true;
            }
            else {
               String columnName = board.getColumnName(board.getColumnIndex(target.getColumn()) + distanceIndex);
               Square nextSquare = board.getSquare(origin.getRow(), columnName);
               if (origin.hasPieceSameColor(nextSquare))
                   return true;
            }                
            if (incrementRow != 0)
               distanceIndex += incrementRow;  
            else 
                distanceIndex += incrementColumn;
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

    private int getDistance(int distanceRow, int distanceColumn) {
        int distance = 0;
        if (distanceColumn == 0) {
            distance = distanceRow;
        } else {
            distance = distanceColumn;
        }
        return distance;
    }
      
    @Override
    public Piece clone(Piece piece) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
