package com.irlix.web_app.dao;

import com.irlix.web_app.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OwnerDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OwnerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Owner> getAllOwner() {
        return jdbcTemplate.query("SELECT * FROM owner ", new BeanPropertyRowMapper<>(Owner.class));
    }

    public Owner showOwner(int id) {
        return jdbcTemplate.query("SELECT * FROM owner WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Owner.class)
        ).stream().findAny().orElse(null);
    }

    public void addOwner(Owner owner) {
        jdbcTemplate.update("INSERT INTO owner (name, surname, phone, email) VALUES (?,?,?,?)",
                owner.getName(), owner.getSurname(), owner.getPhone(), owner.getEmail());
    }

    public void updateOwner(int id, Owner owner) {
        jdbcTemplate.update("UPDATE owner SET name=?, surname=?, phone=?, email=? WHERE id=?",
                owner.getName(), owner.getSurname(), owner.getPhone(), owner.getEmail(), id);
    }

    public void deleteOwner(int id) {
        jdbcTemplate.update("DELETE FROM owner WHERE id=?", id);
    }
}
