package cmedina.cirion.tcpipstack.JWTAuthentication;

import cmedina.cirion.tcpipstack.GlobalVariables.LoggerGen;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailsService userDetailService;
    @Autowired
    private final LoggerGen loggerinfo;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Logger log = loggerinfo.getLoggergenerator();

        final String TOKEN = getTokenFromRequest(request);
        String username = null;
        final String rol;

        if (TOKEN == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try{
            username = jwtService.getUsernameFromToken(TOKEN);
            rol = jwtService.getRoleFromToken(TOKEN);
            System.out.println(username);
            System.out.println(rol);
            log.info("The user: " + username + " has role: " + rol + " has validated a JWT Token and the token is: " + TOKEN);
        }catch(Exception error){
            System.out.println("error = " + error);
        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailService.loadUserByUsername(username);

            if (jwtService.isTokenValid(TOKEN, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String TokenHeader = request.getHeader("Authorization");

        if (TokenHeader != null && TokenHeader.startsWith("Bearer ")) {
            return TokenHeader.substring(7);
        }
        return null;
    }

}
