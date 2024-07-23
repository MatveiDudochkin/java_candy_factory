package com.irlix.web_app.controller;

import com.irlix.web_app.dao.ProductDAO;
import com.irlix.web_app.model.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductDAO productDAO;

    @Autowired
    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("products", productDAO.findAll());
        return "product/product";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productDAO.showProduct(id));
        return "product/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("newProduct") Product product) {
        return "product/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("newProduct") @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "product/new";

        productDAO.saveNewProduct(product);
        return "redirect:/product";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("product", productDAO.showProduct(id));
        return "product/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Product product, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "product/edit";

        productDAO.updateProduct(id, product);
        return "redirect:/product";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productDAO.deleteProduct(id);
        return "redirect:/product";
    }
}
