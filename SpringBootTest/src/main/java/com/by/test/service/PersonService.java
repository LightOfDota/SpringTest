package com.by.test.service;

import com.by.test.Dao.PersonDao;
import com.by.test.entity.Person;

/**
 * Created by root on 2018/3/6.
 */
public interface PersonService {

   public int add(Person person);

    public int delete(Integer id);
}
