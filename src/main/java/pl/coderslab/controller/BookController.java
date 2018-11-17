package pl.coderslab.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    @GetMapping(path = "/")
    public String findAllBooks() {
        return null;
    }

    @GetMapping(path = "/{id:\\d+}")
    public String findBookById(@PathVariable("id") final long id) {
        return null;
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
    public String deleteBook(@PathVariable("id") final long id){
        return null;
    }
}
