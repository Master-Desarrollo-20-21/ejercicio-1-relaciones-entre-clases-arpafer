package Chess;

import java.util.HashMap;

public class Player {

    private String name;    
    private Color color;
    
    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }
    
    public Color getColor() { return this.color; }
    
    public Movement readMovement(Board board) {    
        
        String[] piecesNames;
        if (this.color == Color.WHITE)
           piecesNames = board.getWhitePieceNames();
        else
           piecesNames = board.getBlackPieceNames();       
        GestorIO gestor = new GestorIO();
        gestor.out(this.name + ": indique la pieza, la fila y columna donde quiere mover.\n" );                        
        Movement movement = new Movement();
        movement.read(piecesNames, board);      
        if (movement.hasPiece())
           return movement;
        else 
           return null;
    }               
}
