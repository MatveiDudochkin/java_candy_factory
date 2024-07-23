package com.irlix.web_app.controller;

import com.irlix.web_app.dao.OwnerDAO;
import com.irlix.web_app.model.Owner;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerDAO ownerDAO;

    @Autowired
    public OwnerController(OwnerDAO ownerDAO) {
        this.ownerDAO = ownerDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("owners", ownerDAO.getAllOwner());
        return "owner/ownerShow";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("ownerShowInfo", ownerDAO.showOwner(id));
        return "owner/showInfo";
    }

    @GetMapping("/new")
    public String showNew(@ModelAttribute("newOwner") Owner owner) {
        return "owner/newOwner";
    }

    @PostMapping()
    public String save(@ModelAttribute("newOwner") Owner owner) {
        ownerDAO.addOwner(owner);
        return "redirect:/owner";
    }

    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable("id") int id, Model model) {
        model.addAttribute("owner", ownerDAO.showOwner(id));
        return "owner/editOwner";
    }

    @PatchMapping("{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("owner") @Valid Owner owner,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "owner/editOwner";
        }
        ownerDAO.updateOwner(id, owner);
        return "redirect:/owner";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        ownerDAO.deleteOwner(id);
        return "redirect:/owner";
    }
}
