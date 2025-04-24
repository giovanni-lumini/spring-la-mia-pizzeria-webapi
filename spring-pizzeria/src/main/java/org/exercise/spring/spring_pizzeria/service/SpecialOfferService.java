package org.exercise.spring.spring_pizzeria.service;

import org.exercise.spring.spring_pizzeria.model.SpecialOffer;
import org.exercise.spring.spring_pizzeria.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialOfferService {
    @Autowired
    private SpecialOfferRepository specialOfferRepository;

    // save
    public SpecialOffer save(SpecialOffer specialOffer) {
        return specialOfferRepository.save(specialOffer);
    }

    // getById
    public SpecialOffer getById(Integer id) {
        return specialOfferRepository.findById(id).get();
    }

}
