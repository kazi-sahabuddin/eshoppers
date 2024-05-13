package com.sahabuddin.eshoppers.service;

import com.sahabuddin.eshoppers.domain.Cart;
import com.sahabuddin.eshoppers.domain.User;

public interface CartService {
    Cart getCartByUser(User userName);
}
