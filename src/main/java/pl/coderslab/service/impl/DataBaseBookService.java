package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.coderslab.exception.BookNotFoundException;
import pl.coderslab.model.Book;
import pl.coderslab.repository.BookRepository;
import pl.coderslab.service.BookService;

import java.util.List;

@Service
@Primary //bo mamy dwie implementacje BookService
public class DataBaseBookService implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public DataBaseBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAllBooks();
    }

    @Override
    public Book findBookById(final long id) throws BookNotFoundException {
        return bookRepository.findBookById(id);
    }
}
