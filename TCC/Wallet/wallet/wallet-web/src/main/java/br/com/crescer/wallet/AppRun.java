/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
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

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return (ConfigurableEmbeddedServletContainer container) -> {
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
            container.addErrorPages(error404Page);
        };
    }
}
