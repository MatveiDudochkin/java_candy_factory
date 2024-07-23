package com.irlix.web_app.controller;


import com.irlix.web_app.model.Supplier;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.irlix.web_app.dao.SupplierDAO;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
    private final SupplierDAO supplierDAO;

    @Autowired
    public SupplierController(SupplierDAO supplierDAO) {
        this.supplierDAO = supplierDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("suppliers", supplierDAO.getSuppliers());
        return "supplier/supplier";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("supplierShowInfo", supplierDAO.showSupplier(id));
        return "supplier/show";
    }

    @GetMapping("/new")
    public String showNew(@ModelAttribute("newSupplier") Supplier supplier) {
        return "supplier/new";
    }

    @PostMapping()
    public String save(@ModelAttribute("newSupplier") Supplier supplier) {
        supplierDAO.addSupplier(supplier);
        return "redirect:/supplier";
    }

    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable("id") int id, Model model) {
        model.addAttribute("supplier", supplierDAO.showSupplier(id));
        return "supplier/edit";
    }

    @PatchMapping("{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("supplier") @Valid Supplier supplier,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "supplier/edit";
        }
        supplierDAO.updateSupplier(id, supplier);
        return "redirect:/supplier";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        supplierDAO.deleteSupplier(id);
        return "redirect:/supplier";
    }
}
