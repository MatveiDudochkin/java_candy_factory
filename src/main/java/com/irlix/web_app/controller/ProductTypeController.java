package com.irlix.web_app.controller;

import com.irlix.web_app.dao.ProductTypeDAO;
import com.irlix.web_app.model.ProductType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productType")
public class ProductTypeController {
    private final ProductTypeDAO productTypeDAO;

    @Autowired
    public ProductTypeController(ProductTypeDAO productTypeDAO) {
        this.productTypeDAO = productTypeDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("productsType", productTypeDAO.findAllProductType());
        return "productType/productType";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productTypeDAO.showProductType(id));
        return "productType/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("newProductType") ProductType productType) {
        return "productType/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("newProductType") @Valid ProductType productType, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "productType/new";

        productTypeDAO.saveNewProduct(productType);
        return "redirect:/productType";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("productType", productTypeDAO.showProductType(id));
        return "productType/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("productType") @Valid ProductType productType, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) return "productType/edit";

        productTypeDAO.updateProductType(id, productType);
        return "redirect:/productType";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productTypeDAO.deleteProductType(id);
        return "redirect:/productType";
    }
}
