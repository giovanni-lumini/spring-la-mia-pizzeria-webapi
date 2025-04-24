package org.exercise.spring.spring_pizzeria.service;

import java.util.List;
import java.util.Optional;

import org.exercise.spring.spring_pizzeria.model.Ingredient;
import org.exercise.spring.spring_pizzeria.model.Pizza;
import org.exercise.spring.spring_pizzeria.repository.IngredientRepository;
import org.exercise.spring.spring_pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    // findAll
    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    // getById
    public Ingredient getById(Integer id) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(id);
        if (ingredientOptional.isEmpty()) {
            // notFound
        }
        return ingredientOptional.get();
    }

    // save
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    // deleteById
    public void deleteById(Integer id) {
        Ingredient ingredientToDelete = getById(id);
        // per ogni pizza (chiamata linkedPizza) dentro/collegata all'ingrediente
        for (Pizza linkedPizza : ingredientToDelete.getPizze()) {
            // elimino l'ingrediente all'interno della pizza
            linkedPizza.getIngredients().remove(ingredientToDelete);
        }
        ingredientRepository.delete(ingredientToDelete);

    }
}
