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
               
    public Attempt(int index, SecretCombination secretCombination) {        
        this.index = index;
        this.secretCombination = secretCombination;
        this.proposedCombination = new ProposedCombination();
    }

    public boolean winner() {
        return this.secretCombination.match(this.proposedCombination);
    }   
    public void play() {                       
       while (!this.proposedCombination.read()) {
           
       }
    }

    public void showResult() {        
        this.proposedCombination.show(); 
        Message.ARROW.write();
        this.secretCombination.showResult(this.proposedCombination);
    }

    public void showHead() {        
        
        Message.ATTEMP.writeHeadAttempt(index);
        this.secretCombination.show();
    }
}
