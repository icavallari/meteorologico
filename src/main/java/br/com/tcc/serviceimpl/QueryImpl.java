package br.com.tcc.serviceimpl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import br.com.tcc.service.Query;

@Named("query")
public class QueryImpl implements Query {

    @Inject
    @Named("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long getCount(String sql) {
        try {
            return jdbcTemplate.queryForObject(sql, Long.class);
        } catch (EmptyResultDataAccessException e) {
            return 0L;
        }
    }

    @Override
    public void execute(String sql) {
        jdbcTemplate.execute(sql);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> getListObject(T t, String sql) {

        return (List<T>) jdbcTemplate.query(sql,
            ParameterizedBeanPropertyRowMapper.newInstance(t.getClass()));
    }

    @Override
    public String getSingleValue(String sql) {
        try {
            return jdbcTemplate.queryForObject(sql, Object.class).toString();
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void getTest(String sql) {
        jdbcTemplate.execute(sql);
    }
}
