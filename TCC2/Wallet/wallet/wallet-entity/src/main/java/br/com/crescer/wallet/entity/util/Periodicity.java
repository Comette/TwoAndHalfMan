package br.com.crescer.wallet.entity.util;

/**
 *
 * @author douglas.ballester
 */


public enum Periodicity {
    MONTHLY(1),
    QUARTERLY(3),
    BIANNUAL(6),
    YEARLY(12); 
    
    private final int numeral;
    
    Periodicity(int numeral){
        this.numeral = numeral;
    }
    
    public int getNumeral(){
        return this.numeral;
    }
}
