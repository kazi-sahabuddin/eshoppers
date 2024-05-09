package com.sahabuddin.eshoppers.web;

import java.io.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.sahabuddin.eshoppers.dto.ProductDTO;
import com.sahabuddin.eshoppers.repository.impl.DummyProductRepositoryImpl;
import com.sahabuddin.eshoppers.service.ProductService;
import com.sahabuddin.eshoppers.service.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "Home", value = "/home")
public class HomeServlet extends HttpServlet {


    public void init() {

    }

    private final ProductService productService = new ProductServiceImpl(new DummyProductRepositoryImpl());
    private final Logger LOGGER = LoggerFactory.getLogger(HomeServlet.class);
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.info("Serving Home page");
        List<ProductDTO> allProducts = productService.findAllProductSortedByName();
        LOGGER.info("Total product found {}", allProducts.size());

        request.setAttribute("products", allProducts);
        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request,response);

    }


    public void destroy() {
    }
}