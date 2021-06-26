package com.poptrip.security.jwt;

import com.poptrip.security.userdetails.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final   JwtUtils jwtUtils;

    private final UserDetailsServiceImpl userDetailsServiceImpl;


    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try{
            String jwt = parseJWT(request);
            if(jwt !=null && jwtUtils.validateJWT(jwt)){

                String username = jwtUtils.extractUsernameFromJWT(jwt);

                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }catch (Exception e){
            logger.error("Cannot set user authentication: "+e.getMessage());
        }

        filterChain.doFilter(request,response);
    }

    private String parseJWT(HttpServletRequest servletRequest){
        String jwtHeader = servletRequest.getHeader("Authorization");

        if(StringUtils.hasText(jwtHeader) && jwtHeader.startsWith("Bearer ")){
            return jwtHeader.substring(7);
        }
        return null;
    }
}
