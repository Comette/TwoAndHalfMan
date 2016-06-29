package br.com.crescer.wallet.entity;

/**
 *
 * @author douglas.ballester
 */


public enum Periodicidade {
    MENSAL(1),
    TRIMESTRAL(3),
    SEMESTRAL(6),
    ANUAL(12); 
    
    private int numeral;
    
    Periodicidade(int numeral){
        this.numeral = numeral;
    }
    
    public int getNumeral(){
        return this.numeral;
    }
}
