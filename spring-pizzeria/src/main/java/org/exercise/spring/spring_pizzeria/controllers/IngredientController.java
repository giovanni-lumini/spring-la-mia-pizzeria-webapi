package org.exercise.spring.spring_pizzeria.controllers;

import org.exercise.spring.spring_pizzeria.model.Ingredient;
import org.exercise.spring.spring_pizzeria.model.Pizza;
import org.exercise.spring.spring_pizzeria.repository.IngredientRepository;
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
    private IngredientRepository ingredientRepository;

    // index
    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "ingredients/index";
    }

    // show
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ingredient", ingredientRepository.findById(id).get());
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
        ingredientRepository.save(formIngredient);
        return "redirect:/ingredients";
    }

    // update
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ingredient", ingredientRepository.findById(id).get());
        model.addAttribute("edit", true);
        return "ingredients/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create-or-edit";
        }
        ingredientRepository.save(formIngredient);
        return "redirect:/ingredients";
    }

    // delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Ingredient ingredientToDelete = ingredientRepository.findById(id).get();

        // per ogni pizza (chiamata linkedPizza) dentro/collegata all'ingrediente
        for (Pizza linkedPizza : ingredientToDelete.getPizze()) {
            // elimino l'ingrediente all'interno della pizza
            linkedPizza.getIngredients().remove(ingredientToDelete);
        }
        // elimino l'ingrediente (non più presente in nessuna pizza)
        ingredientRepository.delete(ingredientToDelete);
        return "redirect:/ingredients";
    }

}
