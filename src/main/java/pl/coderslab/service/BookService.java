package pl.coderslab.service;

import pl.coderslab.exception.BookNotFoundException;
import pl.coderslab.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAllBooks();

    Book findBookById(long id) throws BookNotFoundException;
}
