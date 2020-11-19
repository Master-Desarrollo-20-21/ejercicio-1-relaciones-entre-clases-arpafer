/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

/**
 *
 * @author Antonio
 */
public class Mastermind {

    private final int NUM_ATTEMPTS = 10;
    private Attempt[] attempts;
    private SecretCombination secretCombination;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Mastermind mastermind = new Mastermind();
        mastermind.play();
               
    }

    public Mastermind() {
        this.attempts = new Attempt[this.NUM_ATTEMPTS];
        this.secretCombination = new SecretCombination();
    } 
    public void play() {
        this.showHead();        
        do {
            this.secretCombination.generate();
            int numAttempt = 0;                               
            do {                                
                attempts[numAttempt] = new Attempt(numAttempt, this.secretCombination);      
                attempts[numAttempt].showHead();
                this.showAcumulatedResults(numAttempt);
                attempts[numAttempt].play();                                
                numAttempt++;
            } while (!attempts[numAttempt - 1].winner() && numAttempt < this.NUM_ATTEMPTS);            
            this.showResult(numAttempt);
        } while (this.wantOtherGame());
    }

    private void showHead() {
        GestorIO console = new GestorIO();
        console.out("----- M A S T E R M I N D -----\n\n");
    }
    
    private boolean wantOtherGame() {
        GestorIO console = new GestorIO();
        console.out("¿RESUME? S/n");
        String respond = console.inString();
        if (respond.equals("S") || respond.equals("s"))
            return true;
        else 
            return false;
    }

    private void showResult(int numAttempt) {
        GestorIO console = new GestorIO();
        String result = "";
        if (attempts[numAttempt - 1].winner())
            result = "won!!!";
        else
            result = "lost. The combination is " + this.secretCombination.get() + " !!!";
        
        console.out("You've " + result + "\n");
    }

    private void showAcumulatedResults(int numAttempt) {
        for (int i = 0; i < numAttempt; i++) {
            this.attempts[i].showResult();
        }
    }
    
        
}
