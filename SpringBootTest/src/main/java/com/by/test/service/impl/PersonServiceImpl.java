package com.by.test.service.impl;

import com.by.test.Dao.PersonDao;
import com.by.test.entity.Person;
import com.by.test.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by root on 2018/3/6.
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonDao personDao;


    public int add(Person person){
        return personDao.insert(person);
    }

    public int delete(Integer id){
        return personDao.deleteById(id);
    }
}
