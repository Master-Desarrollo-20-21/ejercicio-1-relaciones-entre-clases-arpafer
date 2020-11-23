package Chess;

public abstract class Piece  {
    
    private String visualCar;
    private Color color;    
    
    protected Piece(String visualCaracter, Color color) {        
        this.config(visualCaracter, color);
    }
    public void set(String character, Color color) {
        this.visualCar = character;
        this.color = color;
    }
    public void config(String visualCaracter, Color color) {
       this.color = color;
       this.visualCar = visualCaracter;
    }
 
    public String getVisualCharacter() {
        return this.visualCar;
    }
    public Color getColor() { 
        return color;
    }

    public String toString() {
        return this.visualCar;
    }

    public void show() {
        GestorIO console = new GestorIO();        
        if (this.visualCar.length() == 1) {
            console.out(this.visualCar + "  |  ");
        } else if (this.visualCar.length() == 2) {
            console.out(this.visualCar + " |  ");
        }
    }  
    public void perform(Movement movement, Board board) {
        Square origin = movement.getOrigin();
        Square target = movement.getTarget();
        origin.movePiece(target); 
    }
    protected boolean canEat(Movement movement) {
        Square origin = movement.getOrigin();
        Square target = movement.getTarget();
        if (origin.hasPieceDistinctColor(target)) {
            return true;
        } else {
            return false;
        }
    }
    public abstract boolean validate(Movement movement, Board board);   
   
    public abstract Piece clone(Piece piece);            
    
}
