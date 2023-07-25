package br.com.floricultura.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();


        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("USER")) {
                response.sendRedirect("/floricultura/mensagem");
                return;
            } else if (authority.getAuthority().equals("ADMIN")) {
                response.sendRedirect("/floricultura/admin");
                return;
            }
        }

        response.sendRedirect("/floricultura/login");
    }
}
