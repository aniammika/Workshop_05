package pl.coderslab.repository.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;
import pl.coderslab.model.Book;
import pl.coderslab.repository.BookRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcBookRepository extends NamedParameterJdbcDaoSupport implements BookRepository {

    private static final String SELECT_ALL_BOOKS = "select * from book";

    @Override
    public List<Book> findAllBooks() {
        try {

            return getJdbcTemplate().query(SELECT_ALL_BOOKS, bookRowMapper);

        } catch (final DataAccessException e) {
            //logger
            throw e;
        }
    }

    @Override
    public Book findBookById() {
        return null;
    }

    private final RowMapper<Book> bookRowMapper = new RowMapper<Book>() {

        @Override
        public Book mapRow(final ResultSet rs, final int rowNum) throws SQLException {
            final long id = rs.getLong("id");
            final String title = rs.getString("title");
            final String author = rs.getString("author");
            final String publisher = rs.getString("publisher");
            final String isbn = rs.getString("isbn");
            final String type = rs.getString("type");
            return new Book(id, title, author, publisher, isbn, type);
        }
    };
}
