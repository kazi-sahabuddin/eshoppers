package com.sahabuddin.eshoppers.service.impl;

import com.sahabuddin.eshoppers.domain.Cart;
import com.sahabuddin.eshoppers.domain.User;
import com.sahabuddin.eshoppers.repository.CartRepository;
import com.sahabuddin.eshoppers.service.CartService;

import java.util.Optional;

public class CartServerImpl implements CartService {
    private final CartRepository cartRepository;
    public CartServerImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    @Override
    public Cart getCartByUser(User user) {
        Optional<Cart> presentCart = cartRepository.findByUser(user);

        return presentCart.orElseGet(()-> creatNerCart(user));
    }

    private Cart creatNerCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return null;
    }
}
