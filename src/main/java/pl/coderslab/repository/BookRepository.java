package pl.coderslab.repository;

import pl.coderslab.model.Book;

import java.util.List;

public interface BookRepository {

    Book findBookById();

    List<Book> findAllBooks();
}
