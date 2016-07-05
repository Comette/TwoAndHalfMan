/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet.service.webservice;

/**
 *
 * @author victor.ribeiro
 */
public final class WebServiceConfig {
    public static final String APP_ID = "1ae6285574ea49378248204645a03d1d";
    public static final String URL_GET_COTACAO = "https://openexchangerates.org/api/latest.json?app_id=" + APP_ID;
    public static final String URL_GET_POR_DATA = "https://openexchangerates.org/api/historical/";
    public static final String URL_GET_POR_DATA_2 = ".json?app_id=" + APP_ID;
    public static final String USER_AGENT = "Mozilla/5.0";
}
