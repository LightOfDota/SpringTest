package com.by.test.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by root on 2018/3/6.
 */
public class ListenerTest implements ServletRequestListener {

    public void requestDestroyed(ServletRequestEvent arg0) {
        System.out.println("aaaa");
    }
    public void requestInitialized(ServletRequestEvent arg0) {
        //将所有request请求都携带上httpSession
        ((HttpServletRequest) arg0.getServletRequest()).getSession();
    }

}
