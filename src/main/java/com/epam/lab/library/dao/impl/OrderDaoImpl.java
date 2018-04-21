package com.epam.lab.library.dao.impl;

import com.epam.lab.library.dao.OrderDao;
import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    private static final String CREATE_ORDER = "INSERT INTO orders (id, user_id, book_id, location, status) " +
            "VALUES (nextval('orders_seq'), :userId, :bookId, :location, :status)";
    private static final String GET_ORDER = "SELECT * FROM orders WHERE id = ?";
    private static final String CHECK_ORDER = "SELECT COUNT(*) FROM orders WHERE (status = 'GIVEN' OR status = 'REQUESTED') AND user_id = ? AND book_id = ?";
    private static final String SET_BOOK_STATUS = "UPDATE orders SET status = ? WHERE id = ?";
    private static final String GET_ALL_ORDER_BY_STATUS = "SELECT * FROM orders WHERE status = ?";
    private static final String GET_ALL_USER_ORDERS = "SELECT * FROM orders WHERE user_id = ?";
    private static final String DELETE_ORDERS_BY_USER_ID = "DELETE FROM orders WHERE user_id = ?";
    private static final String DELETE_REQUEST = "DELETE FROM orders WHERE id = ? AND status = 'REQUESTED'";

    @Autowired
    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public Long createOrder(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new MapSqlParameterSource().addValue("userId", order.getUserId()).addValue("bookId", order.getBookId())
                .addValue("location", order.getLocation()).addValue("status", order.getStatus());
        namedParameterJdbcOperations.update(CREATE_ORDER, params, keyHolder, new String[]{"id"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public Order getOrder(Long id) {
        return namedParameterJdbcOperations.getJdbcOperations().queryForObject(GET_ORDER, new BeanPropertyRowMapper<>(Order.class), id);
    }

    @Override
    public boolean isBookNotRequestedByUser(Long bookId, Long userId) {
        return namedParameterJdbcOperations.getJdbcOperations().queryForObject(CHECK_ORDER, Integer.class, userId, bookId) == 0;
    }

    @Override
    public boolean setBookStatus(Status status, Long id) {
        return namedParameterJdbcOperations.getJdbcOperations().update(SET_BOOK_STATUS, status.toString(), id) > 0;
    }

    @Override
    public List<Order> getAllOrderByStatus(Status status) {
        return namedParameterJdbcOperations.getJdbcOperations().query(GET_ALL_ORDER_BY_STATUS, new BeanPropertyRowMapper<>(Order.class), status.toString());
    }

    @Override
    public List<Order> getAllUserOrders(Long id) {
        return namedParameterJdbcOperations.getJdbcOperations().query(GET_ALL_USER_ORDERS, new BeanPropertyRowMapper<>(Order.class), id);
    }

    @Override
    public void deleteOrdersByUserId(Long id) {
        namedParameterJdbcOperations.getJdbcOperations().update(DELETE_ORDERS_BY_USER_ID, id);
    }

    @Override
    public boolean deleteRequest(Long id) {
        return namedParameterJdbcOperations.getJdbcOperations().update(DELETE_REQUEST, id) > 0;
    }

}
