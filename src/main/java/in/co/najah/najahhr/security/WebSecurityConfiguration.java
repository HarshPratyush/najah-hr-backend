package in.co.najah.najahhr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    @Autowired
    private void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(userAuthenticationProvider);
    }

    /*
     * To alter any configuration related to WEB-Application please update the
     * configuration. This Authentication provider internally manages the
     * authentication creation mechanism, using the UserRoleFeaturePermissionMapping
     * tables.
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers("/error","/anonymous/**").
                permitAll().anyRequest().authenticated();

        http.formLogin().defaultSuccessUrl("/").loginPage("/login").permitAll()
                .and()
                .logout()
                .permitAll();

        http.logout().logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID", "XSRF-TOKEN");
//        http.csrf().csrfTokenRepository(this.getCsrfTokenRepository());
        http.sessionManagement().enableSessionUrlRewriting(false);
        http.sessionManagement().sessionFixation().newSession();
        http.cors();
        http.headers().frameOptions().disable();
        http.csrf().disable();
    }

    private CsrfTokenRepository getCsrfTokenRepository() {
        CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        tokenRepository.setCookiePath("/");
        return tokenRepository;
    }


}
