package org.exercise.spring.spring_pizzeria.repository;

import java.util.List;

import org.exercise.spring.spring_pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    public List<Pizza> findByNome(String nome);

}
