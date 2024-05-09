package com.sahabuddin.eshoppers.web;

import com.sahabuddin.eshoppers.util.SecurityContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutServlet.class);

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Logout request received");
        SecurityContext.logout(request);
        response.sendRedirect("/login?logout=true");
    }
}
