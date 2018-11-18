package pl.coderslab.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pl.coderslab.model.Book;
import pl.coderslab.repository.BookRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcBookRepository extends NamedParameterJdbcDaoSupport implements BookRepository {

    private static final String SELECT_ALL_BOOKS = "select * from book";

    private static final String SELECT_BOOK_BY_ID = "select * from book b where b.id = :id";
    //:id to feature z NamedParameterJdbcDaoSupport, zamiast znaku zapytania używa się :
    private static final String INSERT_BOOK = "insert into book(title, author, publisher, isbn, type) values(:t, :a, :p, :p, :i, :ty)";

    @Autowired
    public JdbcBookRepository(final DataSource dataSource){
        setDataSource(dataSource);
    }

    @Override
    public Book addUser(String title, String author, String publisher,  String isbn,  String type) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("t", title);
        parameters.addValue("a", author);
        parameters.addValue("p", publisher);
        parameters.addValue("i", isbn);
        parameters.addValue("ty", type);

        try {

            getNamedParameterJdbcTemplate().update(INSERT_BOOK, parameters, keyHolder);

            // @formatter:off
            long id = Optional.ofNullable(keyHolder.getKey())
                    .map(Number::longValue)
                    .orElseThrow(() -> new DataAccessException("Cannot add user to database") {});
            // @formatter:on

            throw new DataAccessException("Cannot add user to database") {};

            // return new User(id, name, surname, age);
        } catch (final DataAccessException e) {
            //logger
            throw e;
        }
    }

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
    public Book findBookById(final long id) {

        final Map<String, Long> parameters = Collections.singletonMap("id", id);

        try {

            return getNamedParameterJdbcTemplate().queryForObject(SELECT_BOOK_BY_ID, parameters, bookRowMapper);

        } catch(final DataAccessException e) {
            // logowanie
            throw e;
        }
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
