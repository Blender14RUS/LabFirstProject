package com.epam.lab.library.web.controller;

import com.epam.lab.library.domain.Book;
import com.epam.lab.library.domain.Location;
import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.Status;
import com.epam.lab.library.service.BookService;
import com.epam.lab.library.service.UserService;
import com.epam.lab.library.service.impl.DataBaseUserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.epam.lab.library.service.impl.LocalizationController;

import java.security.Principal;
import java.util.List;

import static com.epam.lab.library.domain.Status.*;

@Controller
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private DataBaseUserDetailService detailsService;

    @RequestMapping("/lib/requested-books")
    public String librarianRequestsForBooksIssue(Model model,
                                                 @RequestParam(value = "lang", defaultValue = "en_US") String language,
                                                 @RequestParam(value = "lang_changed", defaultValue = "false") boolean lang_changed) {
        List<Order> orders = userService.getAllOrderByStatus(REQUESTED);
        model.addAttribute("orders", orders);
        if(lang_changed) {

            LocalizationController.setLang(language);
        }
        model.addAttribute("language", LocalizationController.getLang());
        return "librarian/requestedBooks";
    }

    @RequestMapping("/lib/returned-books")
    public String librarianGivenBooks(Model model,
                                      @RequestParam(value = "lang", defaultValue = "en_US") String language,
                                      @RequestParam(value = "lang_changed", defaultValue = "false") boolean lang_changed) {
        List<Order> orders = userService.getAllOrderByStatus(GIVEN);
        model.addAttribute("orders", orders);
        if(lang_changed) {

            LocalizationController.setLang(language);
        }
        model.addAttribute("language", LocalizationController.getLang());
        return "librarian/returnedBooks";
    }

    @RequestMapping("/lib/addBook")
    public String addBook(Model model,
                          @RequestParam(value = "id", required = false) Long bookId,
                          @RequestParam(value = "lang", defaultValue = "en_US") String language,
                          @RequestParam(value = "lang_changed", defaultValue = "false") boolean lang_changed) {
        if (bookId != null) {
            Book book = bookService.getBook(bookId);
            model.addAttribute("book", book);
        }
        if(lang_changed) {

            LocalizationController.setLang(language);
        }
        model.addAttribute("language", LocalizationController.getLang());
        return "librarian/addBook";
    }

    @RequestMapping(value = "/books", method ={RequestMethod.POST, RequestMethod.GET})
    public ModelAndView listOfUsers(@RequestParam(required = false) Integer page,
                                    @RequestParam(value = "bookTitle", defaultValue = "") String bookTitle,
                                    @RequestParam(value = "available", required = false) boolean showNotAvailable,
                                    @RequestParam(value = "sort", defaultValue = "alphabet") String sortType,
                                    @RequestParam(value = "lang", defaultValue = "en_US") String language,
                                    @RequestParam(value = "lang_changed", defaultValue = "false") boolean lang_changed) {
        ModelAndView modelAndView = new ModelAndView("common/bookList");
        List<Book> books = bookService.getBooks(bookTitle, showNotAvailable, sortType);
        PagedListHolder<Book> pagedListHolder = new PagedListHolder<>(books);
        int booksByPage = 5;
        pagedListHolder.setPageSize(booksByPage);
        modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
            page = 1;
        }
        modelAndView.addObject("page", page);
        if (page < 1 || page > pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(0);
            modelAndView.addObject("books", pagedListHolder.getPageList());
        } else if (page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page - 1);
            modelAndView.addObject("books", pagedListHolder.getPageList());
        }
        if(lang_changed) {

            LocalizationController.setLang(language);
        }
        modelAndView.addObject("language", LocalizationController.getLang());
        return modelAndView;
    }

    @RequestMapping(value = "/books/view/{id}")
    public String viewBook(Model model,
                           @PathVariable("id") Long id,
                           @RequestParam(value = "lang", defaultValue = "en_US") String language,
                           @RequestParam(value = "lang_changed", defaultValue = "false") boolean lang_changed) {
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        model.addAttribute("role", detailsService.getRole());
        if(lang_changed) {

            LocalizationController.setLang(language);
        }
        model.addAttribute("language", LocalizationController.getLang());
        return "common/viewBook";
    }

    @RequestMapping(value = "/books/create-book", method = RequestMethod.POST)
    public String createBook(Model model,
                             @RequestParam("title") String title, @RequestParam("year") int year,
                             @RequestParam("author") String author, @RequestParam("available") int available,
                             @RequestParam(value = "lang", defaultValue = "en_US") String language,
                             @RequestParam(value = "lang_changed", defaultValue = "false") boolean lang_changed) {
        Book book = bookService.addBook(new Book(null, title, year, available), author);
        if(lang_changed) {

            LocalizationController.setLang(language);
        }
        model.addAttribute("language", LocalizationController.getLang());
        return book.getId() == null ? "bookCreationFailure" : "redirect:/books";
    }

    @RequestMapping(value = "/books/give/{id}", method = RequestMethod.POST)
    public String giveBook(Model model,
                           @PathVariable("id") Long id,
                           @RequestParam(value = "lang", defaultValue = "en_US") String language,
                           @RequestParam(value = "lang_changed", defaultValue = "false") boolean lang_changed) {
        bookService.setBookStatus(GIVEN, id);
        if(lang_changed) {

            LocalizationController.setLang(language);
        }
        model.addAttribute("language", LocalizationController.getLang());
        return "redirect:/lib/requested-books";
    }

    @RequestMapping(value = "/books/return/{id}", method = RequestMethod.POST)
    public String returnBook(Model model,
                             @PathVariable("id") Long id,
                             @RequestParam(value = "lang", defaultValue = "en_US") String language,
                             @RequestParam(value = "lang_changed", defaultValue = "false") boolean lang_changed) {
        bookService.setBookStatus(IN_LIBRARY, id);
        if(lang_changed) {

            LocalizationController.setLang(language);
        }
        model.addAttribute("language", LocalizationController.getLang());
        return "redirect:/lib/returned-books";
    }

    @RequestMapping(value = "/books/request/{id}", method = RequestMethod.POST)
    public String requestBook(@PathVariable("id") Long bookId,
                              @RequestParam("location") Location location, Model model,
                              @RequestParam(value = "lang", defaultValue = "en_US") String language,
                              @RequestParam(value = "lang_changed", defaultValue = "false") boolean lang_changed) {
        Order order = new Order(null, null, bookId, location, Status.REQUESTED);
        Order orderCreated = bookService.requestBook(order);
        if(lang_changed) {

            LocalizationController.setLang(language);
        }
        model.addAttribute("language", LocalizationController.getLang());
        if (orderCreated.getId() == null) {
            model.addAttribute("alreadyRequsted", true);
            Book book = bookService.getBook(bookId);
            model.addAttribute("book", book);
            model.addAttribute("role", detailsService.getRole());
            return "common/viewBook";
        } else {
            return "redirect:/user/orders/";
        }
    }

    @RequestMapping(value = "/books/edit", method = RequestMethod.POST)
    public String editBook(Model model,
                           @RequestParam("title") String title,
                           @RequestParam("year") int year,
                           @RequestParam("authors") String authors,
                           @RequestParam("available") int available,
                           @RequestParam("id") Long id,
                           @RequestParam(value = "lang", defaultValue = "en_US") String language,
                           @RequestParam(value = "lang_changed", defaultValue = "false") boolean lang_changed) {
        Book book = new Book(id, title, year, available);
        bookService.updateBook(book, authors);
        book = bookService.getBook(id);
        model.addAttribute("book", book);
        model.addAttribute("role", detailsService.getRole());
        if(lang_changed) {

            LocalizationController.setLang(language);
        }
        model.addAttribute("language", LocalizationController.getLang());
        return "/common/viewBook";
    }

}
