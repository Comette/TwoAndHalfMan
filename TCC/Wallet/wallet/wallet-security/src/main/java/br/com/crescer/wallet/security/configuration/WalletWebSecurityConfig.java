/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet.security.configuration;

import br.com.crescer.wallet.security.service.WalletUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author victor.ribeiro
 */
@Configuration
public class WalletWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    WalletUserDetailService walletUserDetailsService;

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/css/**", "/fonts/**").permitAll()
                .antMatchers("/cadastro/**", "/usuario/**", "/usuarios/**").hasAuthority("ADMINISTRADOR")
                .anyRequest().fullyAuthenticated()
                .and().exceptionHandling().accessDeniedPage("/acesso-negado")
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error").permitAll()
                .and().logout().logoutUrl("/logout")
                .deleteCookies("JSESSIONID").permitAll()
                .and().csrf().disable();
    }

    @Autowired
    public void setDetailsService(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(walletUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
