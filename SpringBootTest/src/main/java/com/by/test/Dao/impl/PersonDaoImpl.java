package com.by.test.Dao.impl;

import com.by.test.Dao.PersonDao;
import com.by.test.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by root on 2018/3/5.
 */
@Repository
public class PersonDaoImpl implements PersonDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(Person person) {
        String sql = "insert into person(name,address,age) values (?,?,?)";
        return jdbcTemplate.update(sql,person.getName(),person.getAddress(),person.getAge());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("delete person where id = ?",id);
    }

    @Override
    public int update(Person person) {
//        StringBuffer sb = new StringBuffer("update person set ");
//        if(null != person.getName()){
//            sb.append("name=?,");
//        }
//        if(null != person.getAddress()){
//            sb.append("address=?,");
//        }
//        if(null != person.getAge()){
//            sb.append("age=?,");
//        }
        return 0;
    }

    @Override
    public Person getById(Integer id) {
        String sql = "select * from person where id = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet rs, int i) throws SQLException {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setAddress(rs.getString("address"));
                person.setAge(rs.getInt("age"));
                return person;
            }
        },id);
    }
}
