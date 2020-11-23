package Chess;

public class Pawn extends Piece {

    private boolean firstMovement;

    public Pawn(String visualCaracter, Color color) {
        super(visualCaracter, color);
        this.firstMovement = true;
    }
       
    @Override
    public boolean validate(Movement movement, Board board) {

        if ((this.validMovement(movement) && !this.hasObstacles(movement, board)) || this.canEat(movement)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void perform(Movement movement, Board board) {
        super.perform(movement, board);
        this.firstMovement = false;
    }

    @Override
    protected boolean canEat(Movement movement) {
        Square origin = movement.getOrigin();
        Square target = movement.getTarget();
        int distanciaRow = Math.abs(origin.distanceRow(target));
        int distanciaColumn = Math.abs(origin.distanceColumn(target));
        return distanciaRow == 1 && distanciaColumn == 1 && target.hasPieceDistinctColor(this.getColor());
    }

    private boolean validMovement(Movement movement) {
        Square origin = movement.getOrigin();
        Square target = movement.getTarget();
        int distanciaRow = Math.abs(origin.distanceRow(target));
        return ((distanciaRow == 1) || (this.firstMovement && distanciaRow == 2)) && origin.distanceColumn(target) == 0;
    }

    private boolean hasObstacles(Movement movement, Board board) {
        Square origin = movement.getOrigin();
        Square target = movement.getTarget();
        int distanciaRow = origin.distanceRow(target);
        int increment = 1;
        if (distanciaRow < 0) {
            increment = -1;
        }        
        int i = 0 + increment;
        GestorIO console = new GestorIO();
        // console.out("Increment: " + increment + " i: " + i + " distanceRow: " + distanciaRow + "\n");
        while (Math.abs(i) <= Math.abs(distanciaRow)) {
            Square nextSquare = board.getSquare(origin.getRow() + i, origin.getColumn());
            if (nextSquare != null && nextSquare.hasAnyPiece()) {
                return true;
            }
            i += increment;
        }
        return false;
    }

    @Override
    public Piece clone(Piece piece) {
        return new Pawn(piece.getVisualCharacter(), piece.getColor());        
    }  
   
}
