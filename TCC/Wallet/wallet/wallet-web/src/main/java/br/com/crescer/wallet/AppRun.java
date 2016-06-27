/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author victor.ribeiro
 */
@SpringBootApplication
@EnableScheduling
public class AppRun {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppRun.class, args);
    }
}
