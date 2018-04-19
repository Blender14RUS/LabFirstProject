package com.epam.lab.library.web.controller;

import com.epam.lab.library.domain.Book;
import com.epam.lab.library.domain.Location;
import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.Status;
import com.epam.lab.library.service.BookService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

import static com.epam.lab.library.domain.Status.GIVEN;
import static com.epam.lab.library.domain.Status.IN_LIBRARY;

@Controller
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @RequestMapping("/addBook")
    public String addBook(Model model, @RequestParam(value = "id", required = false) Long bookId) {
        LOG.info("addBook ID: " + bookId);
        if (bookId != null) {
            Book book = bookService.getBook(bookId);
            model.addAttribute("book", book);
        }
        return "librarian/addBook";
    }

    @RequestMapping("/books")
    public String viewBooks(Model model, Principal principal, Authentication authentication, @RequestParam(value="bookTitle", defaultValue = "") String bookTitle,
                            @RequestParam(value = "available",required = false) boolean showNotAvailable,
                            @RequestParam(value= "sort",defaultValue = "alphabet") String sortType){
        System.out.println(principal);
        System.out.println(authentication);
        List<Book> books = bookService.getBooks(bookTitle,showNotAvailable,sortType);
        model.addAttribute("books",books);
        return "common/bookList";
    }

    @RequestMapping(value = "/books/view/{id}", method = RequestMethod.POST)
    public String viewBook(Model model, @PathVariable("id") Long id) {
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        return "common/viewBook";
    }

    @RequestMapping(value = "/create-book", method = RequestMethod.POST)
    public String createBook(@RequestParam("title") String title, @RequestParam("year") int year,
                             @RequestParam("author") String author, @RequestParam("available") int available) {
        Book book = bookService.addBook(new Book(null, title, year, available), author);
        return book.getId() == null ? "bookCreationFailure" : "redirect:/books";
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

    @RequestMapping(value = "/request/{id}", method = RequestMethod.POST)
    public String requestBook(@PathVariable("id") Long bookId, @RequestParam("location") Location location) {
        //@RequestParam("userId") Long userId
        Order order = new Order(null, 3L, bookId, location, Status.REQUESTED);
        bookService.requestBook(order);
        return order.getId() == null ? "bookCreationFailure" : "redirect:/my-book";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editBook(Model model, @RequestParam("title") String title, @RequestParam("year") int year,
                           @RequestParam("authors") String authors, @RequestParam("available") int available,
                           @RequestParam("id") Long id) {
        Book book = new Book(id, title, year, available);
        bookService.editBook(book, authors);
        book = bookService.getBook(id);
        model.addAttribute("book", book);
        return "/common/viewBook";
    }

}
