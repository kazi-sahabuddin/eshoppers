package com.sahabuddin.eshoppers.web;

import com.sahabuddin.eshoppers.domain.User;
import com.sahabuddin.eshoppers.dto.UserDTO;
import com.sahabuddin.eshoppers.repository.impl.UserRepositoryImpl;
import com.sahabuddin.eshoppers.service.UserService;
import com.sahabuddin.eshoppers.service.impl.UserServiceImpl;
import com.sahabuddin.eshoppers.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private final Logger LOGGER = LoggerFactory.getLogger(SignupServlet.class);

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
        LOGGER.info("Serving signup page");
        request.getRequestDispatcher("/WEB-INF/signup.jsp").forward(request,response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDTO userDTO = copyParametersTo(request);
        //Map<String, String> errors = vaildate(userDTO);
       var errors = ValidationUtil.getInstance().validate(userDTO);
        if(!errors.isEmpty()){

            request.setAttribute("errors",errors);
            request.setAttribute("userDto",userDTO);
            LOGGER.info("User sent invalid data: {}",userDTO);
            request.getRequestDispatcher("/WEB-INF/signup.jsp").forward(request,response);
        } else if(userService.isNotUniqueUsername(userDTO)){
            LOGGER.info("Username: {} already exists", userDTO.getUsername());
            errors.put("username", "The username already exists. Please use a different username");
            request.setAttribute("errors",errors);
            request.setAttribute("userDto",userDTO);
            request.getRequestDispatcher("/WEB-INF/signup.jsp").forward(request,response);

        } else if(userService.isNotUniqueEmail(userDTO)){
            LOGGER.info("Email: {} already exists", userDTO.getEmail());
            errors.put("email", "The email already exists. Please use a different email");
            request.setAttribute("errors",errors);
            request.setAttribute("userDto",userDTO);
            request.getRequestDispatcher("/WEB-INF/signup.jsp").forward(request,response);
        }
        else {
            LOGGER.info("user is valid creating a new user with: {}", userDTO);
             User user = userService.saveUser(userDTO);
            System.out.println(user);
            response.sendRedirect("/login");
        }

    }

  /* private Map<String, String> vaildate(UserDTO userDTO) {
        var validatorFactory = Validation.buildDefaultValidatorFactory();
        var validator = validatorFactory.getValidator();

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        Map<String, String> errors = new HashMap<>();

        for (ConstraintViolation<UserDTO> violation : violations){
            String path = violation.getPropertyPath().toString();
            if (errors.containsKey(path)){
                String errorMsg = errors.get(path);
                errors.put(path, errorMsg + " <br/> "+violation.getMessage());
            }else{
                errors.put(path,violation.getMessage());
            }
        }

        return errors;
    }
   */

    private UserDTO copyParametersTo(HttpServletRequest request) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(request.getParameter("firstName"));
        userDTO.setLastName(request.getParameter("lastName"));
        userDTO.setPassword(request.getParameter("password"));
        userDTO.setPasswordConfirmed(request.getParameter("passwordConfirmed"));
        userDTO.setEmail(request.getParameter("email"));
        userDTO.setUsername(request.getParameter("username"));
        return userDTO;
    }

}
