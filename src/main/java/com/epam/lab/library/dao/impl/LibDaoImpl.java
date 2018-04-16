package com.epam.lab.library.dao.impl;

import com.epam.lab.library.dao.LibDao;
import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LibDaoImpl implements LibDao {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Autowired
    NamedParameterJdbcOperations namedParameterJdbcOperations;

    private static final String GET_ALL_ORDER_BY_STATUS = "SELECT * FROM orders WHERE status = ?";
    private static final String SET_BOOK_STATUS = "UPDATE orders SET status = ? WHERE id = ?";

    @Override
    public List<Order> getAllOrderByStatus(Status status) {
        return jdbcOperations.query(GET_ALL_ORDER_BY_STATUS, new BeanPropertyRowMapper<>(Order.class), status.toString());
    }

    @Override
    public void setBookStatus(Status status, Long id) {
        jdbcOperations.update(SET_BOOK_STATUS, status.toString(), id);
    }
}
