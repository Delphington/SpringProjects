package dev.delphington.controllers;


import dev.delphington.model.Book;
import dev.delphington.service.BookService;
import dev.delphington.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private final BookService bookService;

    @Autowired
    private final PersonService personService;

    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }


    @GetMapping()
    public String index(Model model,
                        @RequestParam(value = "page", required = false) String page,
                        @RequestParam(value = "books_per_page", required = false) String countPerPage,
                        @RequestParam(value = "sort_by_year", required = false) String sortByYear) {

        Integer numberPage;
        Integer numberPerPage;
        List<Book> personList;
        Boolean sort;
        try {
            numberPage = Integer.parseInt(page);
            numberPerPage = Integer.parseInt(countPerPage);
        } catch (RuntimeException e) {
            numberPage = null;
            numberPerPage = null;
        }

        try {
            sort = Boolean.parseBoolean(sortByYear);
        } catch (RuntimeException e) {
            sort = null;
        }

        //Комбо
        if (numberPage != null && numberPerPage != null && sort != null && sort == true) {
            personList = bookService.index(numberPage, numberPerPage, "year");
        } else if (numberPage != null && numberPerPage != null) {
            personList = bookService.index(numberPage, numberPerPage);
        } else if (sort == true) {
            personList = bookService.index("year");
        } else {
            personList = bookService.index();
        }

        model.addAttribute("bookList", personList);

        return "books/index";
    }

    //Страница поиск книги
    @GetMapping("/search")
    public String search(@ModelAttribute("SearchBook") Book book, Model model) {
        System.out.println("====== SEARCH ============");
        model.addAttribute("searchBook", new Book());
        return "books/search";
    }

    @PostMapping("/search")
    public String searchBook(@ModelAttribute("searchBook") Book searchBook, Model model) {
        System.out.println("====== SEARCH BOOK ============");
        System.out.println("searchBook: " + searchBook);

        Optional<Book> bookTemp = bookService.findByName(searchBook.getName());
        if (bookTemp.isPresent()) {
            model.addAttribute("foundBook", bookTemp.get());
        } else {
            model.addAttribute("foundBook", null);
        }

        System.out.println(model);
        return "books/search";
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

        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String createBook(@PathVariable("id") int id, Model model) {
        Book temp = bookService.show(id);
        model.addAttribute("book", temp);
        model.addAttribute("people", personService.index());
        return "books/show";
    }


    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }


    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.show(id));
        return "books/edit";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookService.update(id, book);
        return "redirect:/books";
    }


    //Обработка запроса установки владельца книги
    @PostMapping("/{id}")
    public String update(@RequestParam("personId") int personId,
                         @PathVariable("id") int id) {

        bookService.updateOwner(id, personId);
        return "redirect:/books";
    }
}
