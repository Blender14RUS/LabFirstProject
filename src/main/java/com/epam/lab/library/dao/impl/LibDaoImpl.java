package com.epam.lab.library.dao.impl;

import com.epam.lab.library.dao.LibDao;
import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.Status;
import com.epam.lab.library.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class LibDaoImpl implements LibDao {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Autowired
    NamedParameterJdbcOperations namedParameterJdbcOperations;

    private static final String GET_ALL_ORDER = "SELECT * FROM orders WHERE status = ?";

    @Override
    public List<Order> getAllOrderRequests() {
        return jdbcOperations.query(GET_ALL_ORDER, new BeanPropertyRowMapper<Order>(Order.class), Status.REQUESTED.toString());
    }

    @Override
    public List<Order> getAllOrderGiven() {
        return jdbcOperations.query(GET_ALL_ORDER, new BeanPropertyRowMapper<Order>(Order.class), Status.GIVEN.toString());
    }

    private static final String ADD_USER = "insert into users (id, login) values (nextval('users_seq'), :login)";

    public long createUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new MapSqlParameterSource().addValue("login", user.getLogin());
        namedParameterJdbcOperations.update(ADD_USER, params, keyHolder, new String[]{"id"});
        return keyHolder.getKey().longValue();
    }

//    template.update(con -> {
//        final PreparedStatement pstmt = con.prepareStatement(query, new String[] { "COLUMN_NAME" });
//        pstmt.setLong(1, type.getId());
//        return pstmt;
//    }, keyHolder);
}
