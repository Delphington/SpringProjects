package dev.delphington.controllers;

import dev.delphington.dao.BookDAO;
import dev.delphington.dao.PersonDAO;
import dev.delphington.model.Book;
import dev.delphington.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private BookDAO bookDAO;

    @GetMapping()
    public String index(Model model) {
        List<Person> personList = personDAO.index();
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
    public String createPerson(@ModelAttribute("personId") Person person) {
        personDAO.save(person);
        return "redirect:/people";
    }


    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        List<Book> listBook = bookDAO.getBook(id);
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("bookList", listBook);
        return "people/show";
    }


    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        System.out.println("МЫ ТУТУ");
        personDAO.update(id, person);
        return "redirect:/people";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }
}