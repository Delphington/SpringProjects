package dev.delphington.controllers;


import dev.delphington.dao.BookDAO;
import dev.delphington.model.Book;
import dev.delphington.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private final BookDAO bookDAO;

    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model){
        List<Book> personList = bookDAO.index();
        model.addAttribute("bookList", personList);
        return "books/index";
    }
}
