package pl.coderslab.service.impl;

import org.springframework.stereotype.Service;
import pl.coderslab.exception.BookNotFoundException;
import pl.coderslab.model.Book;
import pl.coderslab.service.BookService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemoryBookService implements BookService {

    private List<Book> books;

    public MemoryBookService() {
        books = new ArrayList<>();
        books.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel", "Helion", "programming"));
        books.add(new Book(2l, "9788324627738", "Rusz glowa, Java.", "Sierra Kathy, Bates Bert", "Helion", "programming"));
        books.add(new Book(3l, "9780130819338", "Java 2. Podstawy", "Cay Horstmann, Gary Cornell", "Helion", "programming"));
    }

    public List<Book> getBooks() {return books;}
    public void setBooks(List<Book> books) {this.books = books;}

    @Override
    public List<Book> findAllBooks() {
        return books;
    }

    @Override
    public Book findBookById(long id) throws BookNotFoundException {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException());
    }
}
