package com.sahabuddin.eshoppers.util;

import com.sahabuddin.eshoppers.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SecurityContext {
    public static final String AUTHENTICATED_KEY = "auth.Key";
    public static void login(HttpServletRequest req, User user){
        HttpSession oldSession = req.getSession(false);
        if(oldSession != null){
            oldSession.invalidate();
        }
        HttpSession session = req.getSession(true);
        session.setAttribute(AUTHENTICATED_KEY, user);
    }
    public static void logout(HttpServletRequest req){
        HttpSession session = req.getSession(true);
        session.removeAttribute(AUTHENTICATED_KEY);
    }

    public static User getCurrentUser(HttpServletRequest req){
        HttpSession session = req.getSession(true);
        return (User) session.getAttribute(AUTHENTICATED_KEY);
    }

    public static boolean isAuthenticated(HttpServletRequest req){
        HttpSession session = req.getSession(true);
        return session.getAttribute(AUTHENTICATED_KEY) != null;
    }
}
