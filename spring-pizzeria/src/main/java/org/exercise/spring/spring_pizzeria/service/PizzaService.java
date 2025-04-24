package org.exercise.spring.spring_pizzeria.service;

import java.util.List;
import java.util.Optional;

import org.exercise.spring.spring_pizzeria.model.Pizza;
import org.exercise.spring.spring_pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    // findAll
    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    // findById
    public Optional<Pizza> findById(Integer id) {
        return pizzaRepository.findById(id);
    }

    // getById
    public Pizza getById(Integer id) {
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);
        if (pizzaOptional.isEmpty()) {
            // notFound
        }
        return pizzaOptional.get();
    }

    // findByNome
    public List<Pizza> findByNome(String nome) {
        return pizzaRepository.findByNome(nome);
    }

    // save
    public Pizza save(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    // deleteById
    public void deleteById(Integer id) {
        Pizza pizzaToDelete = getById(id);
        pizzaRepository.delete(pizzaToDelete);
    }

}
