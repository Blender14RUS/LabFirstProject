package com.epam.lab.library.web.controller;

import com.epam.lab.library.domain.Book;
import com.epam.lab.library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.epam.lab.library.domain.Status.GIVEN;
import static com.epam.lab.library.domain.Status.IN_LIBRARY;

@Controller
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @RequestMapping("/addBook")
    public String addBook(Model model) {
        return "librarian/addBook";
    }

    @RequestMapping("/books")
    public String viewBooks(Model model,@RequestParam(value="bookTitle", defaultValue = "") String bookTitle,
                            @RequestParam(value = "available",required = false) boolean showNotAvailable){
        List<Book> books = bookService.getBooks(bookTitle,showNotAvailable);
        model.addAttribute("books",books);
        return "common/bookList";
    }

    @RequestMapping(value = "/books/view/{id}", method = RequestMethod.POST)
    public String viewBook(@PathVariable("id") Long id) {
        return "common/viewBook";
    }

    @RequestMapping(value = "/create-book", method = RequestMethod.POST)
    public String createBook(@RequestParam("title") String title, @RequestParam("year") int year,
                             @RequestParam("authors") String authors, @RequestParam("available") int available) {
        Book book = bookService.addBook(new Book(title, year, available), authors);
        LOG.info("Add book: " + book.toString());
        return book.getId() == null ? "bookCreationFailure" : "redirect:/common/bookList";
    }

    @RequestMapping(value = "/give/{id}", method = RequestMethod.POST)
    public String giveBook(@PathVariable("id") Long id) {
        bookService.setBookStatus(GIVEN, id);
        return "redirect:/requested-books";
    }

    @RequestMapping(value = "/return/{id}", method = RequestMethod.POST)
    public String returnBook(@PathVariable("id") Long id) {
        bookService.setBookStatus(IN_LIBRARY, id);
        return "redirect:/returned-books";
    }

}
