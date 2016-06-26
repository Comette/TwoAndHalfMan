/*
 * Crescer-TCC: Wallet
 * by: Douglas Balester, Hedo Eccker e Victor Comette.
 */
package br.com.cwi.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author victo
 */
@SpringBootApplication
@EnableScheduling
public class AppRun {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppRun.class, args);
    }
}
