package Chess;

public class Horse extends Piece {

    public Horse(String visualCaracter, Color color) {
        super(visualCaracter, color);
    }

    @Override
    public boolean validate(Movement movement, Board board) {

        if ((this.validMovement(movement, board) && !this.hasObstacles(movement)) || this.canEat(movement)) {
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
        return (Math.abs(distanceRow) == 2 && Math.abs(distanceColumn) == 1) || (Math.abs(distanceRow) == 1 && Math.abs(distanceColumn) == 2);                        
    }

    private boolean hasObstacles(Movement movement) {

        Square origin = movement.getOrigin();
        Square target = movement.getTarget();
        if (origin.hasPieceSameColor(target))
            return true;
        else 
            return false;
    }
        
    @Override
    public Piece clone(Piece piece) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
