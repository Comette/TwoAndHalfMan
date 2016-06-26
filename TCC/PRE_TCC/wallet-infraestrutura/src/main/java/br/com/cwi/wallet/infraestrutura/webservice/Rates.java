/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */
package br.com.cwi.wallet.infraestrutura.webservice;

import br.com.cwi.wallet.dominio.model.Cotacao;
import java.util.Date;

/**
 *
 * @author victo
 */
public class Rates {
    private float AED;
    private float AFN;
    private float ALL;
    private float AMD;
    private float ANG;
    private float AOA;
    private float ARS;
    private float AUD;
    private float AWG;
    private float AZN;
    private float BAM;
    private float BBD;
    private float BDT;
    private float BGN;
    private float BHD;
    private float BIF;
    private float BMD;
    private float BND;
    private float BOB;
    private float BRL;
    private float BSD;
    private float BTC;
    private float BTN;
    private float BWP;
    private float BYR;
    private float BZD;
    private float CAD;
    private float CDF;
    private float CHF;
    private float CLF;
    private float CLP;
    private float CNY;
    private float COP;
    private float CRC;
    private float CUC;
    private float CUP;
    private float CVE;
    private float CZK;
    private float DJF;
    private float DKK;
    private float DOP;
    private float DZD;
    private float EEK;
    private float EGP;
    private float ERN;
    private float ETB;
    private float EUR;
    private float FJD;
    private float FKP;
    private float GBP;
    private float GEL;
    private float GGP;
    private float GHS;
    private float GIP;
    private float GMD;
    private float GNF;
    private float GTQ;
    private float GYD;
    private float HKD;
    private float HNL;
    private float HRK;
    private float HTG;
    private float HUF;
    private float IDR;
    private float ILS;
    private float IMP;
    private float INR;
    private float IQD;
    private float IRR;
    private float ISK;
    private float JEP;
    private float JMD;
    private float JOD;
    private float JPY;
    private float KES;
    private float KGS;
    private float KHR;
    private float KMF;
    private float KPW;
    private float KRW;
    private float KWD;
    private float KYD;
    private float KZT;
    private float LAK;
    private float LBP;
    private float LKR;
    private float LRD;
    private float LSL;
    private float LTL;
    private float LVL;
    private float LYD;
    private float MAD;
    private float MDL;
    private float MGA;
    private float MKD;
    private float MMK;
    private float MNT;
    private float MOP;
    private float MRO;
    private float MTL;
    private float MUR;
    private float MVR;
    private float MWK;
    private float MXN;
    private float MYR;
    private float MZN;
    private float NAD;
    private float NGN;
    private float NIO;
    private float NOK;
    private float NPR;
    private float NZD;
    private float OMR;
    private float PAB;
    private float PEN;
    private float PGK;
    private float PHP;
    private float PKR;
    private float PLN;
    private float PYG;
    private float QAR;
    private float RON;
    private float RSD;
    private float RUB;
    private float RWF;
    private float SAR;
    private float SBD;
    private float SCR;
    private float SDG;
    private float SEK;
    private float SGD;
    private float SHP;
    private float SLL;
    private float SOS;
    private float SRD;
    private float STD;
    private float SVC;
    private float SYP;
    private float SZL;
    private float THB;
    private float TJS;
    private float TMT;
    private float TND;
    private float TOP;
    private float TRY;
    private float TTD;
    private float TWD;
    private float TZS;
    private float UAH;
    private float UGX;
    private float USD;
    private float UYU;
    private float UZS;
    private float VEF;
    private float VND;
    private float VUV;
    private float WST;
    private float XAF;
    private float XAG;
    private float XAU;
    private float XCD;
    private float XDR;
    private float XOF;
    private float XPD;
    private float XPF;
    private float XPT;
    private float YER;
    private float ZAR;
    private float ZMK;
    private float ZMW;
    private float ZWL;

    public Rates() {
    }   

    public Cotacao toCotacao() {
        Cotacao cotacao = new Cotacao(0l, new Date(), BRL, EUR, JPY, GBP, AUD, CAD, CHF, CNY);
        return cotacao;
    }
    
    public float getAED() {
        return AED;
    }

    public void setAED(float AED) {
        this.AED = AED;
    }

    public float getAFN() {
        return AFN;
    }

    public void setAFN(float AFN) {
        this.AFN = AFN;
    }

    public float getALL() {
        return ALL;
    }

    public void setALL(float ALL) {
        this.ALL = ALL;
    }

    public float getAMD() {
        return AMD;
    }

    public void setAMD(float AMD) {
        this.AMD = AMD;
    }

    public float getANG() {
        return ANG;
    }

    public void setANG(float ANG) {
        this.ANG = ANG;
    }

    public float getAOA() {
        return AOA;
    }

    public void setAOA(float AOA) {
        this.AOA = AOA;
    }

    public float getARS() {
        return ARS;
    }

    public void setARS(float ARS) {
        this.ARS = ARS;
    }

    public float getAUD() {
        return AUD;
    }

    public void setAUD(float AUD) {
        this.AUD = AUD;
    }

    public float getAWG() {
        return AWG;
    }

    public void setAWG(float AWG) {
        this.AWG = AWG;
    }

    public float getAZN() {
        return AZN;
    }

    public void setAZN(float AZN) {
        this.AZN = AZN;
    }

    public float getBAM() {
        return BAM;
    }

    public void setBAM(float BAM) {
        this.BAM = BAM;
    }

    public float getBBD() {
        return BBD;
    }

    public void setBBD(float BBD) {
        this.BBD = BBD;
    }

    public float getBDT() {
        return BDT;
    }

    public void setBDT(float BDT) {
        this.BDT = BDT;
    }

    public float getBGN() {
        return BGN;
    }

    public void setBGN(float BGN) {
        this.BGN = BGN;
    }

    public float getBHD() {
        return BHD;
    }

    public void setBHD(float BHD) {
        this.BHD = BHD;
    }

    public float getBIF() {
        return BIF;
    }

    public void setBIF(float BIF) {
        this.BIF = BIF;
    }

    public float getBMD() {
        return BMD;
    }

    public void setBMD(float BMD) {
        this.BMD = BMD;
    }

    public float getBND() {
        return BND;
    }

    public void setBND(float BND) {
        this.BND = BND;
    }

    public float getBOB() {
        return BOB;
    }

    public void setBOB(float BOB) {
        this.BOB = BOB;
    }

    public float getBRL() {
        return BRL;
    }

    public void setBRL(float BRL) {
        this.BRL = BRL;
    }

    public float getBSD() {
        return BSD;
    }

    public void setBSD(float BSD) {
        this.BSD = BSD;
    }

    public float getBTC() {
        return BTC;
    }

    public void setBTC(float BTC) {
        this.BTC = BTC;
    }

    public float getBTN() {
        return BTN;
    }

    public void setBTN(float BTN) {
        this.BTN = BTN;
    }

    public float getBWP() {
        return BWP;
    }

    public void setBWP(float BWP) {
        this.BWP = BWP;
    }

    public float getBYR() {
        return BYR;
    }

    public void setBYR(float BYR) {
        this.BYR = BYR;
    }

    public float getBZD() {
        return BZD;
    }

    public void setBZD(float BZD) {
        this.BZD = BZD;
    }

    public float getCAD() {
        return CAD;
    }

    public void setCAD(float CAD) {
        this.CAD = CAD;
    }

    public float getCDF() {
        return CDF;
    }

    public void setCDF(float CDF) {
        this.CDF = CDF;
    }

    public float getCHF() {
        return CHF;
    }

    public void setCHF(float CHF) {
        this.CHF = CHF;
    }

    public float getCLF() {
        return CLF;
    }

    public void setCLF(float CLF) {
        this.CLF = CLF;
    }

    public float getCLP() {
        return CLP;
    }

    public void setCLP(float CLP) {
        this.CLP = CLP;
    }

    public float getCNY() {
        return CNY;
    }

    public void setCNY(float CNY) {
        this.CNY = CNY;
    }

    public float getCOP() {
        return COP;
    }

    public void setCOP(float COP) {
        this.COP = COP;
    }

    public float getCRC() {
        return CRC;
    }

    public void setCRC(float CRC) {
        this.CRC = CRC;
    }

    public float getCUC() {
        return CUC;
    }

    public void setCUC(float CUC) {
        this.CUC = CUC;
    }

    public float getCUP() {
        return CUP;
    }

    public void setCUP(float CUP) {
        this.CUP = CUP;
    }

    public float getCVE() {
        return CVE;
    }

    public void setCVE(float CVE) {
        this.CVE = CVE;
    }

    public float getCZK() {
        return CZK;
    }

    public void setCZK(float CZK) {
        this.CZK = CZK;
    }

    public float getDJF() {
        return DJF;
    }

    public void setDJF(float DJF) {
        this.DJF = DJF;
    }

    public float getDKK() {
        return DKK;
    }

    public void setDKK(float DKK) {
        this.DKK = DKK;
    }

    public float getDOP() {
        return DOP;
    }

    public void setDOP(float DOP) {
        this.DOP = DOP;
    }

    public float getDZD() {
        return DZD;
    }

    public void setDZD(float DZD) {
        this.DZD = DZD;
    }

    public float getEEK() {
        return EEK;
    }

    public void setEEK(float EEK) {
        this.EEK = EEK;
    }

    public float getEGP() {
        return EGP;
    }

    public void setEGP(float EGP) {
        this.EGP = EGP;
    }

    public float getERN() {
        return ERN;
    }

    public void setERN(float ERN) {
        this.ERN = ERN;
    }

    public float getETB() {
        return ETB;
    }

    public void setETB(float ETB) {
        this.ETB = ETB;
    }

    public float getEUR() {
        return EUR;
    }

    public void setEUR(float EUR) {
        this.EUR = EUR;
    }

    public float getFJD() {
        return FJD;
    }

    public void setFJD(float FJD) {
        this.FJD = FJD;
    }

    public float getFKP() {
        return FKP;
    }

    public void setFKP(float FKP) {
        this.FKP = FKP;
    }

    public float getGBP() {
        return GBP;
    }

    public void setGBP(float GBP) {
        this.GBP = GBP;
    }

    public float getGEL() {
        return GEL;
    }

    public void setGEL(float GEL) {
        this.GEL = GEL;
    }

    public float getGGP() {
        return GGP;
    }

    public void setGGP(float GGP) {
        this.GGP = GGP;
    }

    public float getGHS() {
        return GHS;
    }

    public void setGHS(float GHS) {
        this.GHS = GHS;
    }

    public float getGIP() {
        return GIP;
    }

    public void setGIP(float GIP) {
        this.GIP = GIP;
    }

    public float getGMD() {
        return GMD;
    }

    public void setGMD(float GMD) {
        this.GMD = GMD;
    }

    public float getGNF() {
        return GNF;
    }

    public void setGNF(float GNF) {
        this.GNF = GNF;
    }

    public float getGTQ() {
        return GTQ;
    }

    public void setGTQ(float GTQ) {
        this.GTQ = GTQ;
    }

    public float getGYD() {
        return GYD;
    }

    public void setGYD(float GYD) {
        this.GYD = GYD;
    }

    public float getHKD() {
        return HKD;
    }

    public void setHKD(float HKD) {
        this.HKD = HKD;
    }

    public float getHNL() {
        return HNL;
    }

    public void setHNL(float HNL) {
        this.HNL = HNL;
    }

    public float getHRK() {
        return HRK;
    }

    public void setHRK(float HRK) {
        this.HRK = HRK;
    }

    public float getHTG() {
        return HTG;
    }

    public void setHTG(float HTG) {
        this.HTG = HTG;
    }

    public float getHUF() {
        return HUF;
    }

    public void setHUF(float HUF) {
        this.HUF = HUF;
    }

    public float getIDR() {
        return IDR;
    }

    public void setIDR(float IDR) {
        this.IDR = IDR;
    }

    public float getILS() {
        return ILS;
    }

    public void setILS(float ILS) {
        this.ILS = ILS;
    }

    public float getIMP() {
        return IMP;
    }

    public void setIMP(float IMP) {
        this.IMP = IMP;
    }

    public float getINR() {
        return INR;
    }

    public void setINR(float INR) {
        this.INR = INR;
    }

    public float getIQD() {
        return IQD;
    }

    public void setIQD(float IQD) {
        this.IQD = IQD;
    }

    public float getIRR() {
        return IRR;
    }

    public void setIRR(float IRR) {
        this.IRR = IRR;
    }

    public float getISK() {
        return ISK;
    }

    public void setISK(float ISK) {
        this.ISK = ISK;
    }

    public float getJEP() {
        return JEP;
    }

    public void setJEP(float JEP) {
        this.JEP = JEP;
    }

    public float getJMD() {
        return JMD;
    }

    public void setJMD(float JMD) {
        this.JMD = JMD;
    }

    public float getJOD() {
        return JOD;
    }

    public void setJOD(float JOD) {
        this.JOD = JOD;
    }

    public float getJPY() {
        return JPY;
    }

    public void setJPY(float JPY) {
        this.JPY = JPY;
    }

    public float getKES() {
        return KES;
    }

    public void setKES(float KES) {
        this.KES = KES;
    }

    public float getKGS() {
        return KGS;
    }

    public void setKGS(float KGS) {
        this.KGS = KGS;
    }

    public float getKHR() {
        return KHR;
    }

    public void setKHR(float KHR) {
        this.KHR = KHR;
    }

    public float getKMF() {
        return KMF;
    }

    public void setKMF(float KMF) {
        this.KMF = KMF;
    }

    public float getKPW() {
        return KPW;
    }

    public void setKPW(float KPW) {
        this.KPW = KPW;
    }

    public float getKRW() {
        return KRW;
    }

    public void setKRW(float KRW) {
        this.KRW = KRW;
    }

    public float getKWD() {
        return KWD;
    }

    public void setKWD(float KWD) {
        this.KWD = KWD;
    }

    public float getKYD() {
        return KYD;
    }

    public void setKYD(float KYD) {
        this.KYD = KYD;
    }

    public float getKZT() {
        return KZT;
    }

    public void setKZT(float KZT) {
        this.KZT = KZT;
    }

    public float getLAK() {
        return LAK;
    }

    public void setLAK(float LAK) {
        this.LAK = LAK;
    }

    public float getLBP() {
        return LBP;
    }

    public void setLBP(float LBP) {
        this.LBP = LBP;
    }

    public float getLKR() {
        return LKR;
    }

    public void setLKR(float LKR) {
        this.LKR = LKR;
    }

    public float getLRD() {
        return LRD;
    }

    public void setLRD(float LRD) {
        this.LRD = LRD;
    }

    public float getLSL() {
        return LSL;
    }

    public void setLSL(float LSL) {
        this.LSL = LSL;
    }

    public float getLTL() {
        return LTL;
    }

    public void setLTL(float LTL) {
        this.LTL = LTL;
    }

    public float getLVL() {
        return LVL;
    }

    public void setLVL(float LVL) {
        this.LVL = LVL;
    }

    public float getLYD() {
        return LYD;
    }

    public void setLYD(float LYD) {
        this.LYD = LYD;
    }

    public float getMAD() {
        return MAD;
    }

    public void setMAD(float MAD) {
        this.MAD = MAD;
    }

    public float getMDL() {
        return MDL;
    }

    public void setMDL(float MDL) {
        this.MDL = MDL;
    }

    public float getMGA() {
        return MGA;
    }

    public void setMGA(float MGA) {
        this.MGA = MGA;
    }

    public float getMKD() {
        return MKD;
    }

    public void setMKD(float MKD) {
        this.MKD = MKD;
    }

    public float getMMK() {
        return MMK;
    }

    public void setMMK(float MMK) {
        this.MMK = MMK;
    }

    public float getMNT() {
        return MNT;
    }

    public void setMNT(float MNT) {
        this.MNT = MNT;
    }

    public float getMOP() {
        return MOP;
    }

    public void setMOP(float MOP) {
        this.MOP = MOP;
    }

    public float getMRO() {
        return MRO;
    }

    public void setMRO(float MRO) {
        this.MRO = MRO;
    }

    public float getMTL() {
        return MTL;
    }

    public void setMTL(float MTL) {
        this.MTL = MTL;
    }

    public float getMUR() {
        return MUR;
    }

    public void setMUR(float MUR) {
        this.MUR = MUR;
    }

    public float getMVR() {
        return MVR;
    }

    public void setMVR(float MVR) {
        this.MVR = MVR;
    }

    public float getMWK() {
        return MWK;
    }

    public void setMWK(float MWK) {
        this.MWK = MWK;
    }

    public float getMXN() {
        return MXN;
    }

    public void setMXN(float MXN) {
        this.MXN = MXN;
    }

    public float getMYR() {
        return MYR;
    }

    public void setMYR(float MYR) {
        this.MYR = MYR;
    }

    public float getMZN() {
        return MZN;
    }

    public void setMZN(float MZN) {
        this.MZN = MZN;
    }

    public float getNAD() {
        return NAD;
    }

    public void setNAD(float NAD) {
        this.NAD = NAD;
    }

    public float getNGN() {
        return NGN;
    }

    public void setNGN(float NGN) {
        this.NGN = NGN;
    }

    public float getNIO() {
        return NIO;
    }

    public void setNIO(float NIO) {
        this.NIO = NIO;
    }

    public float getNOK() {
        return NOK;
    }

    public void setNOK(float NOK) {
        this.NOK = NOK;
    }

    public float getNPR() {
        return NPR;
    }

    public void setNPR(float NPR) {
        this.NPR = NPR;
    }

    public float getNZD() {
        return NZD;
    }

    public void setNZD(float NZD) {
        this.NZD = NZD;
    }

    public float getOMR() {
        return OMR;
    }

    public void setOMR(float OMR) {
        this.OMR = OMR;
    }

    public float getPAB() {
        return PAB;
    }

    public void setPAB(float PAB) {
        this.PAB = PAB;
    }

    public float getPEN() {
        return PEN;
    }

    public void setPEN(float PEN) {
        this.PEN = PEN;
    }

    public float getPGK() {
        return PGK;
    }

    public void setPGK(float PGK) {
        this.PGK = PGK;
    }

    public float getPHP() {
        return PHP;
    }

    public void setPHP(float PHP) {
        this.PHP = PHP;
    }

    public float getPKR() {
        return PKR;
    }

    public void setPKR(float PKR) {
        this.PKR = PKR;
    }

    public float getPLN() {
        return PLN;
    }

    public void setPLN(float PLN) {
        this.PLN = PLN;
    }

    public float getPYG() {
        return PYG;
    }

    public void setPYG(float PYG) {
        this.PYG = PYG;
    }

    public float getQAR() {
        return QAR;
    }

    public void setQAR(float QAR) {
        this.QAR = QAR;
    }

    public float getRON() {
        return RON;
    }

    public void setRON(float RON) {
        this.RON = RON;
    }

    public float getRSD() {
        return RSD;
    }

    public void setRSD(float RSD) {
        this.RSD = RSD;
    }

    public float getRUB() {
        return RUB;
    }

    public void setRUB(float RUB) {
        this.RUB = RUB;
    }

    public float getRWF() {
        return RWF;
    }

    public void setRWF(float RWF) {
        this.RWF = RWF;
    }

    public float getSAR() {
        return SAR;
    }

    public void setSAR(float SAR) {
        this.SAR = SAR;
    }

    public float getSBD() {
        return SBD;
    }

    public void setSBD(float SBD) {
        this.SBD = SBD;
    }

    public float getSCR() {
        return SCR;
    }

    public void setSCR(float SCR) {
        this.SCR = SCR;
    }

    public float getSDG() {
        return SDG;
    }

    public void setSDG(float SDG) {
        this.SDG = SDG;
    }

    public float getSEK() {
        return SEK;
    }

    public void setSEK(float SEK) {
        this.SEK = SEK;
    }

    public float getSGD() {
        return SGD;
    }

    public void setSGD(float SGD) {
        this.SGD = SGD;
    }

    public float getSHP() {
        return SHP;
    }

    public void setSHP(float SHP) {
        this.SHP = SHP;
    }

    public float getSLL() {
        return SLL;
    }

    public void setSLL(float SLL) {
        this.SLL = SLL;
    }

    public float getSOS() {
        return SOS;
    }

    public void setSOS(float SOS) {
        this.SOS = SOS;
    }

    public float getSRD() {
        return SRD;
    }

    public void setSRD(float SRD) {
        this.SRD = SRD;
    }

    public float getSTD() {
        return STD;
    }

    public void setSTD(float STD) {
        this.STD = STD;
    }

    public float getSVC() {
        return SVC;
    }

    public void setSVC(float SVC) {
        this.SVC = SVC;
    }

    public float getSYP() {
        return SYP;
    }

    public void setSYP(float SYP) {
        this.SYP = SYP;
    }

    public float getSZL() {
        return SZL;
    }

    public void setSZL(float SZL) {
        this.SZL = SZL;
    }

    public float getTHB() {
        return THB;
    }

    public void setTHB(float THB) {
        this.THB = THB;
    }

    public float getTJS() {
        return TJS;
    }

    public void setTJS(float TJS) {
        this.TJS = TJS;
    }

    public float getTMT() {
        return TMT;
    }

    public void setTMT(float TMT) {
        this.TMT = TMT;
    }

    public float getTND() {
        return TND;
    }

    public void setTND(float TND) {
        this.TND = TND;
    }

    public float getTOP() {
        return TOP;
    }

    public void setTOP(float TOP) {
        this.TOP = TOP;
    }

    public float getTRY() {
        return TRY;
    }

    public void setTRY(float TRY) {
        this.TRY = TRY;
    }

    public float getTTD() {
        return TTD;
    }

    public void setTTD(float TTD) {
        this.TTD = TTD;
    }

    public float getTWD() {
        return TWD;
    }

    public void setTWD(float TWD) {
        this.TWD = TWD;
    }

    public float getTZS() {
        return TZS;
    }

    public void setTZS(float TZS) {
        this.TZS = TZS;
    }

    public float getUAH() {
        return UAH;
    }

    public void setUAH(float UAH) {
        this.UAH = UAH;
    }

    public float getUGX() {
        return UGX;
    }

    public void setUGX(float UGX) {
        this.UGX = UGX;
    }

    public float getUSD() {
        return USD;
    }

    public void setUSD(float USD) {
        this.USD = USD;
    }

    public float getUYU() {
        return UYU;
    }

    public void setUYU(float UYU) {
        this.UYU = UYU;
    }

    public float getUZS() {
        return UZS;
    }

    public void setUZS(float UZS) {
        this.UZS = UZS;
    }

    public float getVEF() {
        return VEF;
    }

    public void setVEF(float VEF) {
        this.VEF = VEF;
    }

    public float getVND() {
        return VND;
    }

    public void setVND(float VND) {
        this.VND = VND;
    }

    public float getVUV() {
        return VUV;
    }

    public void setVUV(float VUV) {
        this.VUV = VUV;
    }

    public float getWST() {
        return WST;
    }

    public void setWST(float WST) {
        this.WST = WST;
    }

    public float getXAF() {
        return XAF;
    }

    public void setXAF(float XAF) {
        this.XAF = XAF;
    }

    public float getXAG() {
        return XAG;
    }

    public void setXAG(float XAG) {
        this.XAG = XAG;
    }

    public float getXAU() {
        return XAU;
    }

    public void setXAU(float XAU) {
        this.XAU = XAU;
    }

    public float getXCD() {
        return XCD;
    }

    public void setXCD(float XCD) {
        this.XCD = XCD;
    }

    public float getXDR() {
        return XDR;
    }

    public void setXDR(float XDR) {
        this.XDR = XDR;
    }

    public float getXOF() {
        return XOF;
    }

    public void setXOF(float XOF) {
        this.XOF = XOF;
    }

    public float getXPD() {
        return XPD;
    }

    public void setXPD(float XPD) {
        this.XPD = XPD;
    }

    public float getXPF() {
        return XPF;
    }

    public void setXPF(float XPF) {
        this.XPF = XPF;
    }

    public float getXPT() {
        return XPT;
    }

    public void setXPT(float XPT) {
        this.XPT = XPT;
    }

    public float getYER() {
        return YER;
    }

    public void setYER(float YER) {
        this.YER = YER;
    }

    public float getZAR() {
        return ZAR;
    }

    public void setZAR(float ZAR) {
        this.ZAR = ZAR;
    }

    public float getZMK() {
        return ZMK;
    }

    public void setZMK(float ZMK) {
        this.ZMK = ZMK;
    }

    public float getZMW() {
        return ZMW;
    }

    public void setZMW(float ZMW) {
        this.ZMW = ZMW;
    }

    public float getZWL() {
        return ZWL;
    }

    public void setZWL(float ZWL) {
        this.ZWL = ZWL;
    }    
    
}
