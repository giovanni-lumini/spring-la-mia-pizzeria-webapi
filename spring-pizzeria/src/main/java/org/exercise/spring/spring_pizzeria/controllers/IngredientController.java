package org.exercise.spring.spring_pizzeria.controllers;

import org.exercise.spring.spring_pizzeria.model.Ingredient;
import org.exercise.spring.spring_pizzeria.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    // index
    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredients", ingredientService.findAll());
        return "ingredients/index";
    }

    // show
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ingredient", ingredientService.getById(id));
        return "ingredients/show";
    }

    // create
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create-or-edit";
        }
        ingredientService.save(formIngredient);
        return "redirect:/ingredients";
    }

    // update
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ingredient", ingredientService.getById(id));
        model.addAttribute("edit", true);
        return "ingredients/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create-or-edit";
        }
        ingredientService.save(formIngredient);
        return "redirect:/ingredients";
    }

    // delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        // elimino l'ingrediente (non più presente in nessuna pizza)
        ingredientService.deleteById(id);
        return "redirect:/ingredients";
    }
}
