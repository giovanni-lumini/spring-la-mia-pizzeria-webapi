package org.exercise.spring.spring_pizzeria.controllers;

import java.util.List;

import org.exercise.spring.spring_pizzeria.model.Pizza;
import org.exercise.spring.spring_pizzeria.model.SpecialOffer;
import org.exercise.spring.spring_pizzeria.service.IngredientService;
import org.exercise.spring.spring_pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/pizze")
public class PizzaController {

    @Autowired
    PizzaService pizzaService;

    @Autowired
    IngredientService ingredientService;

    // INDEX
    @GetMapping
    public String index(Model model) {
        List<Pizza> pizze = pizzaService.findAll();
        model.addAttribute("pizze", pizze);
        return "pizze/index";
    }

    // SHOW
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Pizza pizza = pizzaService.getById(id);
        model.addAttribute("pizza", pizza);
        return "pizze/show";
    }

    // SEARCH BY NAME
    @GetMapping("/nomePizza")
    // http://localhost:8080/pizze/nomePizza?nome=margherita
    public String nomePizza(@RequestParam(name = "nome") String nome, Model model) {
        List<Pizza> pizze = pizzaService.findByNome(nome);
        model.addAttribute("pizze", pizze);
        return "pizze/index";
    }

    // CREATE
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientService.findAll());
        return "pizze/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizze, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientService.findAll());
            return "pizze/create";
        }
        pizzaService.save(formPizze);
        return "redirect:/pizze";
    }

    // UPDATE
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", pizzaService.getById(id));
        model.addAttribute("ingredients", ingredientService.findAll());
        return "pizze/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid @ModelAttribute("pizza") Pizza formPizze, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientService.findAll());
            return "pizze/edit";
        }
        pizzaService.save(formPizze);
        return "redirect:/pizze";
    }

    // DELETE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        pizzaService.deleteById(id);
        return "redirect:/pizze";
    }

    // ONE TO MANY
    // CREATE
    // metodo per inserire l'offerta speciale della pizza (qui è un get)
    @GetMapping("/{id}/specialoffer/create")
    public String specialOffer(@PathVariable("id") Integer id, Model model) {
        SpecialOffer specialOffer = new SpecialOffer();
        specialOffer.setPizza(pizzaService.getById(id));
        model.addAttribute("specialOffer", specialOffer);
        return "specialOffer/create-or-edit";
    }
}
