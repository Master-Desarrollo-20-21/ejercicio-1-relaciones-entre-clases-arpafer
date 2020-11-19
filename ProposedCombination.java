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

    private final String PROPOSE = "Propose a combination: ";
    private final String WRONG_LENGTH = "Wrong proposed combination length";
    private final String WRONG_COLORS = "Wrong colors, they must be: rbygop";
    private final String VALID_COLORS = "rbygop";

    private boolean valid;

    public ProposedCombination() {
        this.valid = true;
    }

    boolean input() {
        GestorIO console = new GestorIO();
        console.out(this.PROPOSE);
        this.combination = console.inString();
        this.valid = true;
        if (this.combination.length() == 4) {
            for (int i = 0; i < this.combination.length(); i++) {
                String aux = this.combination.substring(i, i + 1);
                if (!this.VALID_COLORS.contains(aux)) {
                    console.out(WRONG_COLORS + "\n");
                    this.valid = false;
                    break;
                }
            }
        } else {
            console.out(WRONG_LENGTH + "\n");
            this.valid = false;            
        }
        return this.valid;
    }

    @Override
    protected void show() {
        GestorIO console = new GestorIO();
        console.out(this.combination);
    }
    
    public String ToString() {
        return this.combination;
    }

    boolean valid() {
        return this.valid;
    }

}
