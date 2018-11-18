package pl.coderslab.repository;

import pl.coderslab.model.Book;

import java.util.List;

public interface BookRepository {

    Book findBookById(long id);

    List<Book> findAllBooks();

    Book addUser(String title, String author, String publisher,  String isbn,  String type);
}
