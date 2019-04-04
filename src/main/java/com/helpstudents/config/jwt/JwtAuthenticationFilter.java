package com.helpstudents.config.jwt;

import com.helpstudents.config.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JvtTokenProvider jvtTokenProvider;
    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String username = null;
        String authToken = null;
        String header = httpServletRequest.getHeader(AppConstants.HEADER_STRING);
        if(header!= null && header.startsWith(AppConstants.TOKEN_PREFIX)){
            authToken = header.replace(AppConstants.TOKEN_PREFIX,"");
            username = jvtTokenProvider.getUsernameFromToken(authToken);
        }else {
            System.out.println("Could not find bearer token");
        }
        if (username!= null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jvtTokenProvider.validateToken(authToken, userDetails)){
                UsernamePasswordAuthenticationToken authentication = jvtTokenProvider.getAuthentication(
                        authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
