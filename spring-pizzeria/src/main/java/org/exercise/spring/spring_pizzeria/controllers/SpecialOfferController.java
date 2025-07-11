package org.exercise.spring.spring_pizzeria.controllers;

import org.exercise.spring.spring_pizzeria.model.SpecialOffer;
import org.exercise.spring.spring_pizzeria.service.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/specialoffer")
public class SpecialOfferController {

    @Autowired
    private SpecialOfferService specialOfferService;

    // CREATE

    // la chiamata get è nel controller della pizza

    @PostMapping("/create")
    public String store(@ModelAttribute("specialOffer") SpecialOffer formSpecialOffer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "specialOffer/create-or-edit";
        }
        specialOfferService.save(formSpecialOffer);
        return "redirect:/pizze/" + formSpecialOffer.getPizza().getId();
    }

    // UPDATE
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("specialOffer", specialOfferService.getById(id));
        model.addAttribute("edit", true);

        /*
         * System.out.println(specialOfferRepository.findById(id).get().getInizioOfferta
         * ());
         * System.out.println(specialOfferRepository.findById(id).get().getFineOfferta()
         * );
         */
        return "specialOffer/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            // se c'è qualche errore, torna al form
            return "specialOffer/create-or-edit";
        }
        specialOfferService.save(formSpecialOffer);
        return "redirect:/pizze/" + formSpecialOffer.getPizza().getId();
    }

    // DELETE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        specialOfferService.deleteById(id);
        return "redirect:/pizze";
    }
}
