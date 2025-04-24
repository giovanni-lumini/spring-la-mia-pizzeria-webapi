package org.exercise.spring.spring_pizzeria.controllers;

import java.util.List;

import org.exercise.spring.spring_pizzeria.model.Pizza;
import org.exercise.spring.spring_pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pizze")
public class PizzaRestAdvanceController {

    @Autowired
    private PizzaService pizzaService;

    // INDEX
    @GetMapping
    public List<Pizza> index() {
        return pizzaService.findAll();
    }

    // SHOW

    // CREATE

    // UPDATE

    // DELETE

}
