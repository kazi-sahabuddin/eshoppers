package com.sahabuddin.eshoppers.web;

import com.sahabuddin.eshoppers.domain.Cart;
import com.sahabuddin.eshoppers.domain.User;
import com.sahabuddin.eshoppers.service.CartService;
import com.sahabuddin.eshoppers.util.SecurityContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/add-to-cart")
public class CartServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartServlet.class);

    private final CartService cartService;

    public CartServlet(CartService cartService) {
        this.cartService = cartService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long productId = Long.parseLong(request.getParameter("productId"));
        LOGGER.info("Received request to add cart to product with id {}", productId);

        Cart cart = getCart(request);
        addProductToCart(productId,cart);

        response.sendRedirect("/home");
    }

    private void addProductToCart(Long productId, Cart cart) {
        //Will implement later
    }

    private Cart getCart(HttpServletRequest request) {
        final User currentUser = SecurityContext.getCurrentUser(request);

        return cartService.getCartByUser(currentUser);
    }
}
