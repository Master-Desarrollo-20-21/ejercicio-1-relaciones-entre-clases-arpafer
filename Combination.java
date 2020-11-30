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
abstract class Combination {
    
    private String combination;
    
    protected Combination() {
        this.combination = new String();
        this.combination = "";
    }
    protected abstract void show();
    
    protected String getCombination() {
        return this.combination;
    }
    protected void addColor(String color) {
        this.combination += color;
    }
    protected void readInput() {
        GestorIO console = new GestorIO();
        this.combination = console.inString();
    }
}
