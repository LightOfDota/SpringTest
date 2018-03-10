package com.by.test.Controller;

import com.by.test.Dao.PersonDao;
import com.by.test.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 2018/3/5.
 */
@Controller
@RequestMapping("fastjson")
public class FastJsonController {

    private PersonDao personDao;

    @RequestMapping("/test")
    @ResponseBody
    public User test() {
        User user = new User();

        List sons = new ArrayList<>();
        sons.add("大红");
        sons.add("小红");
        user.setId(1);
        user.setUsername("jack");
        user.setPassword("jack123");
        user.setBirthday(new Date());
        user.setSon(sons);

        return user;
    }
}
