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
class ProposedCombination extends Combination {
                
    private boolean valid;

    public ProposedCombination() {
        this.valid = true;
    }

    boolean read() {        
        Message.PROPOSE.write();
        super.readInput();
        this.valid = true;
        if (this.getCombination().length() == 4) {
            for (int i = 0; i < this.getCombination().length(); i++) {
                String aux = this.getCombination().substring(i, i + 1);
                if (!Message.VALID_COLORS.toString().contains(aux)) {
                    Message.WRONG_COLORS.writeln();
                    this.valid = false;
                    break;
                }
            }
        } else {
            Message.WRONG_LENGTH.writeln();
            this.valid = false;            
        }
        return this.valid;
    }

    @Override
    protected void show() {
        GestorIO console = new GestorIO();
        console.out(this.getCombination());
    }
    
    public String ToString() {
        return this.getCombination();
    }

    boolean isValid() {
        return this.valid;
    }

}
