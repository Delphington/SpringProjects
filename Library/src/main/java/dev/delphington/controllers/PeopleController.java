package dev.delphington.controllers;

import dev.delphington.dao.PersonDAO;
import dev.delphington.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        System.out.println("----" + personList);

        model.addAttribute("people", personList);
        return "people/index";
    }


}
