package Chess;

class Chess {

    final int NUM_PLAYERS = 2;
    final String PLAYER1_TITLE   = "Jugador Blancas";
    final String PLAYER2_TITLE = "Jugador negras";
    final int NUM_PIECES = 32;    
    private Board board;
    private Player[] players;
    private Piece[] pieces = { new Tower("t1", Color.BLACK), new Horse("c1", Color.BLACK), new Bishop("a1", Color.BLACK), new Queen("d", Color.BLACK), 
                               new King("r", Color.BLACK), new Bishop("a2", Color.BLACK), new Horse("c2", Color.BLACK), new Tower("t2", Color.BLACK), 
                               new Pawn("p0", Color.BLACK), new Pawn("p1", Color.BLACK), new Pawn("p2", Color.BLACK), new Pawn("p3", Color.BLACK),
                               new Pawn("p4", Color.BLACK), new Pawn("p5", Color.BLACK), new Pawn("p6", Color.BLACK), new Pawn("p7", Color.BLACK),
                               new Pawn("P0", Color.WHITE), new Pawn("P1", Color.WHITE), new Pawn("P2", Color.WHITE), new Pawn("P3", Color.WHITE),
                               new Pawn("P4", Color.WHITE), new Pawn("P5", Color.WHITE), new Pawn("P6", Color.WHITE), new Pawn("P7", Color.WHITE),
                               new Tower("T1", Color.WHITE), new Horse("C1", Color.WHITE), new Bishop("A1", Color.WHITE), new Queen("D", Color.WHITE), 
                               new King("R", Color.WHITE), new Bishop("A2", Color.WHITE), new Horse("C2", Color.WHITE), new Tower("T2", Color.WHITE)                               
    };
                                      
    private int turn;

    public Chess() {

        int turn = 0;        
        players = new Player[this.NUM_PLAYERS];        
        board = new Board(pieces);
        players[0] = new Player(PLAYER1_TITLE, Color.WHITE);
        players[1] = new Player(PLAYER2_TITLE, Color.BLACK);        
    }

    void play() {

        do {
            board.initialize();
            Player player = null;
            do {
                player = players[this.getTurn()];
                board.play(player);                
            } while (!board.hasWinner());

            this.showWinner(player);
        } while (this.requestOtherPlay().equals("y"));
    }
   
    private int getTurn() {        
        int aux = this.turn;
        this.turn++;
        return aux % 2;        
    }
    
    private void showWinner(Player player) {
        GestorIO gestor = new GestorIO();
        gestor.out("¡¡ENHORABUENA, jugador " + player.getColor() + ". Ha ganado la partida");
    }
    private String requestOtherPlay() {

        GestorIO console = new GestorIO();
        console.out("¿Le gustaría jugar otra vez? Y/n");
        return console.inString().toLowerCase();
    }

    public static void main(String args[]) {

        Chess chess = new Chess();
        chess.play();
    }   
}
