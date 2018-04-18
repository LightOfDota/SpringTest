package com.by.test.server;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

/**
 * Created by zwhl1234 on 2018/4/18.
 */
public class GetHttpSessionConfigurator extends  Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig config,
                                HandshakeRequest request, HandshakeResponse response) {
        // TODO Auto-generated method stub
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        // ActionContext.getContext().getSession()
        config.getUserProperties().put(HttpSession.class.getName(), httpSession);
    }
}
