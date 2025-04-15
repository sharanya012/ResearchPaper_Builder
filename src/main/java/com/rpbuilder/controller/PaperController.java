package com.rpbuilder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpbuilder.model.Paper;
import com.rpbuilder.service.PaperService;

@Controller
public class PaperController {

    @Autowired
    private PaperService service;

    // Show the form when the user first accesses the page
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // You'll create this later
    }

    @GetMapping("/create")
    public String showForm(Model model) {
        model.addAttribute("paper", new Paper());  // Initialize a new Paper object
        return "ieee_form";  // Returning the form view
    }

    // Save paper after form submission (non-AJAX)
    @PostMapping("/savePaper")
public String submitForm(@ModelAttribute Paper paper, RedirectAttributes redirectAttrs) {
    try {
        service.savePaper(paper);
        redirectAttrs.addFlashAttribute("message", "Paper saved successfully!");
    } catch (Exception e) {
        redirectAttrs.addFlashAttribute("error", "Error saving paper: " + e.getMessage());
    }
    return "redirect:/create";
}


    // AJAX endpoint to save paper without reloading the page
    @PostMapping("/savePaperAjax")
    @ResponseBody
    public String savePaperAjax(@RequestBody Paper paper) {
        // Handle saving the paper with optional sections
        try {
            service.savePaper(paper);
            return "Paper saved successfully!";
        } catch (Exception e) {
            return "Error saving paper: " + e.getMessage();
        }
    }

    // Endpoint to get the paper details by ID (for future use if needed)
    @GetMapping("/getPaper/{id}")
    @ResponseBody
    public Paper getPaper(@PathVariable("id") Long id) {
        return service.getPaperById(id);  // Fetch paper by ID
    }
}
