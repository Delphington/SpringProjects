package dev.delphington.controllers;

import dev.delphington.dao.PersonDAO;
import dev.delphington.model.Person;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PersonDAO personDAO;

    @GetMapping()
    public String index(Model model) {
        List<Person> personList = personDAO.index();
        model.addAttribute("people", personList);
        return "people/index";
    }


    @GetMapping("/new")
    public String createPerson(@ModelAttribute("personId") Person person, Model model) {
        model.addAttribute("personId", person);
        return "people/new";
    }


    @PostMapping()
    public String createPerson(@ModelAttribute("personId") Person person) {
        personDAO.save(person);
        return "redirect:/people";
    }

}
