package dev.delphington.controllers;

import dev.delphington.model.Book;
import dev.delphington.model.Person;
import dev.delphington.service.BookService;
import dev.delphington.service.PersonService;
import dev.delphington.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {



    private final PersonService personService;
    private final BookService bookService;
    private final PersonValidator personValidator;


    @Autowired
    public PeopleController(PersonService personService, BookService bookService, PersonValidator personValidator) {
        this.personService = personService;
        this.bookService = bookService;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {
        List<Person> personList = personService.index();
        model.addAttribute("people", personList);
        return "people/index";
    }

    //Обнолвение
    @GetMapping("/new")
    public String createPerson(@ModelAttribute("personId") Person person, Model model) {
        model.addAttribute("personId", person);
        return "people/new";
    }


    @PostMapping()
    public String createPerson(@ModelAttribute("personId") @Valid Person person,
                               BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        personService.save(person);
        return "redirect:/people";
    }


    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        List<Book> listBook = bookService.getBook(id);
        Person person = personService.show(id);

        System.out.println("======================");
        System.out.println("Person_id = " + id + "Разница: " + person.getDaysBetween());
        System.out.println("Books: " + listBook);
        System.out.println("======================");


        model.addAttribute("person", person);
        model.addAttribute("bookList", listBook);





        return "people/show";
    }


    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult, @PathVariable("id") int id) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }

        System.out.println("МЫ ТУТУ");
        personService.update(id, person);
        return "redirect:/people";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personService.show(id));
        return "people/edit";
    }
}