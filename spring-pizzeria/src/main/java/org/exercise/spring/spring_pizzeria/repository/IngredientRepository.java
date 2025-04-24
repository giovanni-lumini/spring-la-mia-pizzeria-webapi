package org.exercise.spring.spring_pizzeria.repository;

import org.exercise.spring.spring_pizzeria.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}
