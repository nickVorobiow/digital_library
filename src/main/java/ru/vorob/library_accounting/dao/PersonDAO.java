package ru.vorob.library_accounting.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vorob.library_accounting.models.Book;
import ru.vorob.library_accounting.models.Person;


import java.util.List;
import java.util.Optional;


@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM people", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM people WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    //для валидации того, был ли уже создан человек с таким именем, какое вводим в форме
    public Optional<Person> show(String fullname) {
        return jdbcTemplate.query("SELECT * FROM people WHERE fullname=?", new Object[]{fullname}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO people(fullname, yearofbirth) VALUES(?, ?)", person.getFullName(), person.getYearOfBirth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE people SET fullname=?, yearOfBirth=? WHERE id=?", updatedPerson.getFullName(),
                updatedPerson.getYearOfBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM people WHERE id=?", id);
    }


    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM books WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
}
