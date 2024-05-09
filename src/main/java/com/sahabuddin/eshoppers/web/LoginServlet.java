package com.sahabuddin.eshoppers.web;

import com.sahabuddin.eshoppers.domain.User;
import com.sahabuddin.eshoppers.dto.LoginDTO;
import com.sahabuddin.eshoppers.exceptions.UserNotFoundException;
import com.sahabuddin.eshoppers.repository.impl.UserRepositoryImpl;
import com.sahabuddin.eshoppers.service.UserService;
import com.sahabuddin.eshoppers.service.impl.UserServiceImpl;
import com.sahabuddin.eshoppers.util.SecurityContext;
import com.sahabuddin.eshoppers.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);
    private final UserService userService = new UserServiceImpl( new UserRepositoryImpl());

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Serving Login Page");
        String logout = req.getParameter("logout");
        if (logout != null && Boolean.parseBoolean(logout)) {
            req.setAttribute("message", "You've been successfully logged out");
        }
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

   public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginDTO loginDTO = new LoginDTO(req.getParameter("username"), req.getParameter("password"));
        LOGGER.info("Received Login data: {}", loginDTO);

        var errors = ValidationUtil.getInstance().validate(loginDTO);
        if (!errors.isEmpty()) {
            LOGGER.warn("Failed to sending login form again");
            req.setAttribute("errors", errors);
            LOGGER.warn("Failed to sending login form again after login attempt");
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
            return;
        }

        try{
            login(loginDTO, req);
            LOGGER.info("Successfully logged in, redirecting to home page");
            resp.sendRedirect("/home");
            return;
        } catch (UserNotFoundException e){
            LOGGER.error("incorrect username / password el", e);
            errors.put("username", "Incorrect username or password w");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
    }

    private void login(LoginDTO loginDTO, HttpServletRequest req) throws UserNotFoundException {
        User user = userService.verifyUser(loginDTO);

        SecurityContext.login(req, user);

        //get the old session and invalidate
//        HttpSession oldSession = req.getSession(false);
//        if (oldSession != null) {
//            oldSession.invalidate();
//        }
//
//        HttpSession session = req.getSession(true);
//        session.setAttribute("user", user);

    }
}
