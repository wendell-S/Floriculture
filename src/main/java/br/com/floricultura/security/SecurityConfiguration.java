package br.com.floricultura.security;

import br.com.floricultura.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private SSUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsServiceBean(){
        return new SSUserDetailsService(userRepository);
    }

    private static final String[] PUBLIC_MATCHERS = {
            "/floricultura/login",
            "/floricultura/mandar-flor",
            "/floricultura/ver-flores",
            "/floricultura/perfil",
            "/floricultura/sobre-Nos",
            "/floricultura/registration",
            "/floricultura/mensagem",
            "/floricultura/admin",
            "/floricultura/api/flowers"

    };

    private static final String[] USER_MATCHERS = {
            "/floricultura/mandar-flor",
            "/floricultura/ver-flores",
            "/floricultura/perfil",
            "/floricultura/sobre-Nos",
            "/floricultura/mensagem"
    };

    private static final String[] ADMIN_MATCHER = {
            "/floricultura/admin"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers(USER_MATCHERS).hasAuthority("USER")
                .antMatchers(ADMIN_MATCHER).hasAuthority("ADMIN")
                .antMatchers("/css/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/floricultura/login").permitAll().successHandler(customAuthenticationSuccessHandler())
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/floricultura/logout"))
                .logoutSuccessUrl("/floricultura/login").permitAll();
    }

    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("password"))
                .authorities("USER");
        auth.userDetailsService(userDetailsServiceBean())
                .passwordEncoder(passwordEncoder());
    }




}
