package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.exception.BookNotFoundException;
import pl.coderslab.model.Book;
import pl.coderslab.service.BookService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path = "/")
    public List<Book> findAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping(path = "/{id:\\d+}")
    public Book findBookById(@PathVariable("id") final long id) throws BookNotFoundException {
        return bookService.findBookById(id);
    }

    @PostMapping(path = "/")
    public String addBook() {
        return null;
    }

    @PutMapping(path = "/{id:\\d+}")
    public String modifyBook(@PathVariable("id") final long id) {
        return null;
    }

    @DeleteMapping(path = "/{id:\\d+}")
    public String deleteBook(@PathVariable("id") final long id) {
        return null;
    }

    @ExceptionHandler
    public void handleBookNotFoundException(final BookNotFoundException e, HttpServletResponse response) {
        System.out.println("Poleciał wyjątek: " + e);
    }
}
