package dev.delphington.controllers;


import dev.delphington.dao.BookDAO;
import dev.delphington.model.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private final BookDAO bookDAO;

    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model) {
        List<Book> personList = bookDAO.index();
        model.addAttribute("bookList", personList);
        return "books/index";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("bookID") Book book, Model model) {
        model.addAttribute("bookList", book);
        return "books/new";
    }


    @PostMapping()
    public String createBook(@ModelAttribute("bookID") @Valid Book book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String createBook(@PathVariable("id") int id, Model model) {
        Book temp = bookDAO.show(id);
        model.addAttribute("book", temp);
        return "books/show";
    }


    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }


    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDAO.update(id, book);
        return "redirect:/books";
    }

}
