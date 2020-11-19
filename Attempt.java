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
public class Attempt {
    private int index;
    private final SecretCombination secretCombination;    
    private final ProposedCombination proposedCombination;
    
    private final String ATTEMPT = "Attempt(s)";
       
    public Attempt(int index, SecretCombination secretCombination) {        
        this.index = index;
        this.secretCombination = secretCombination;
        this.proposedCombination = new ProposedCombination();
    }

    public boolean winner() {
        return this.secretCombination.match(this.proposedCombination);
    }   
    public void play() {                       
       while (!this.proposedCombination.input());
    }

    public void showResult() {
        GestorIO console = new GestorIO();
        this.proposedCombination.show(); 
        console.out(" --> "); 
        this.secretCombination.showResult(this.proposedCombination);
    }

    public void showHead() {        
        GestorIO console = new GestorIO();
        console.out("\n" + this.index + " " + this.ATTEMPT + "\n");
        this.secretCombination.show();
    }
}
