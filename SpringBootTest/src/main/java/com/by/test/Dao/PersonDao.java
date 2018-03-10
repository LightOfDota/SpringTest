package com.by.test.Dao;

import com.by.test.entity.Person;

/**
 * Created by root on 2018/3/5.
 */
public interface PersonDao {
    public int insert(Person person);

    public int deleteById(Integer id);

    public int update(Person person);

    public Person getById(Integer id);
}
