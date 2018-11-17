package pl.coderslab.service.impl;

import pl.coderslab.exception.BookNotFoundException;
import pl.coderslab.model.Book;
import pl.coderslab.service.BookService;

import java.util.List;

public class DataBaseBookService implements BookService {

    @Override
    public List<Book> findAllBooks() {
        return null;
    }

    @Override
    public Book findBookById(long id) throws BookNotFoundException {
        return null;
    }
}
