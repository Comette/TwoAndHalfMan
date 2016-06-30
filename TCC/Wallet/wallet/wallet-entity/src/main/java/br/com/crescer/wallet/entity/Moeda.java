package br.com.crescer.wallet.entity;

/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victo
 */
public enum Moeda {
    AED,
    AFN,
    ALL,
    AMD,
    ANG,
    AOA,
    ARS,
    AUD,
    AWG,
    AZN,
    BAM,
    BBD,
    BDT,
    BGN,
    BHD,
    BIF,
    BMD,
    BND,
    BOB,
    BRL,
    BSD,
    BTC,
    BTN,
    BWP,
    BYR,
    BZD,
    CAD,
    CDF,
    CHF,
    CLF,
    CLP,
    CNY,
    COP,
    CRC,
    CUC,
    CUP,
    CVE,
    CZK,
    DJF,
    DKK,
    DOP,
    DZD,
    EEK,
    EGP,
    ERN,
    ETB,
    EUR,
    FJD,
    FKP,
    GBP,
    GEL,
    GGP,
    GHS,
    GIP,
    GMD,
    GNF,
    GTQ,
    GYD,
    HKD,
    HNL,
    HRK,
    HTG,
    HUF,
    IDR,
    ILS,
    IMP,
    INR,
    IQD,
    IRR,
    ISK,
    JEP,
    JMD,
    JOD,
    JPY,
    KES,
    KGS,
    KHR,
    KMF,
    KPW,
    KRW,
    KWD,
    KYD,
    KZT,
    LAK,
    LBP,
    LKR,
    LRD,
    LSL,
    LTL,
    LVL,
    LYD,
    MAD,
    MDL,
    MGA,
    MKD,
    MMK,
    MNT,
    MOP,
    MRO,
    MTL,
    MUR,
    MVR,
    MWK,
    MXN,
    MYR,
    MZN,
    NAD,
    NGN,
    NIO,
    NOK,
    NPR,
    NZD,
    OMR,
    PAB,
    PEN,
    PGK,
    PHP,
    PKR,
    PLN,
    PYG,
    QAR,
    RON,
    RSD,
    RUB,
    RWF,
    SAR,
    SBD,
    SCR,
    SDG,
    SEK,
    SGD,
    SHP,
    SLL,
    SOS,
    SRD,
    STD,
    SVC,
    SYP,
    SZL,
    THB,
    TJS,
    TMT,
    TND,
    TOP,
    TRY,
    TTD,
    TWD,
    TZS,
    UAH,
    UGX,
    USD,
    UYU,
    UZS,
    VEF,
    VND,
    VUV,
    WST,
    XAF,
    XAG,
    XAU,
    XCD,
    XDR,
    XOF,
    XPD,
    XPF,
    XPT,
    YER,
    ZAR,
    ZMK,
    ZMW,
    ZWL;
    
    public static List<String> getStringPrincipais(){
        List<String> principais = new ArrayList<>();
        principais.add(Moeda.EUR.toString());
        principais.add(Moeda.BRL.toString());
        principais.add(Moeda.JPY.toString());
        principais.add(Moeda.GBP.toString());
        principais.add(Moeda.AUD.toString());
        principais.add(Moeda.CAD.toString());
        principais.add(Moeda.CHF.toString());
        principais.add(Moeda.CNY.toString());
        return principais;
    }
        
    public static List<Moeda> getPrincipais(){
        List<Moeda> principais = new ArrayList<>();
        principais.add(Moeda.EUR);
        principais.add(Moeda.BRL);
        principais.add(Moeda.JPY);
        principais.add(Moeda.GBP);
        principais.add(Moeda.AUD);
        principais.add(Moeda.CAD);
        principais.add(Moeda.CHF);
        principais.add(Moeda.CNY);
        return principais;
    }
}
