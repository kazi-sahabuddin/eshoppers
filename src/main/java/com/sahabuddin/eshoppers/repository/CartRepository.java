package com.sahabuddin.eshoppers.repository;

import com.sahabuddin.eshoppers.domain.Cart;
import com.sahabuddin.eshoppers.domain.User;

import java.util.Optional;

public interface CartRepository {

    Optional<Cart> findByUser(User user);

    Cart save(Cart cart);

    Cart update(Cart cart);

}
