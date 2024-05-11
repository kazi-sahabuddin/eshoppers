package com.sahabuddin.eshoppers.filter;

import com.sahabuddin.eshoppers.util.SecurityContext;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.Stream;

@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {
    private static final String[] ALLOWED_CONTENTS = {".css", ".js", ".jpg", "home", "login", "signup"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        var requestURI = request.getRequestURI();

        boolean allowed = Stream.of(ALLOWED_CONTENTS).anyMatch(requestURI::contains);

        if (requestURI.equals("/") || allowed || SecurityContext.isAuthenticated(request)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse)servletResponse).sendRedirect("/login");
        }
    }
}