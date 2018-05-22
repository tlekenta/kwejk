package pl.edu.wat.pze.kwejk.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.edu.wat.pze.kwejk.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                System.out.println("encode" + charSequence);
                return "";
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                System.out.println("matches" + charSequence);
                System.out.println("matches" + s);
                return true;
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    System.out.println("POPRAWNIE ZALOGOWANO");
                })
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    System.out.println("NIE ZALOGOWANO");
                })
                .defaultSuccessUrl("/?t=poprawniezalogowano")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/?t=poprawniewylogowano")
                .permitAll()
                .and()
                .csrf().disable();
    }
}
