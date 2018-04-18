package com.by.test.server;

import com.alibaba.fastjson.JSONObject;
import com.by.test.entity.Person;
import com.rabbitmq.client.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

/**
 * Created by root on 2018/3/5.
 */
@ServerEndpoint(value = "/webSocketServer/{userName}/{userLevel}", configurator = GetHttpSessionConfigurator.class)
@Component
public class WebSocketServer {

    private static final Set<WebSocketServer> connections = new CopyOnWriteArraySet<>();

    private String nickname;
    private Integer level;
    private Session session;
    private static HttpSession httpSession;

    private static String getDatetime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    @OnOpen
    public void start(@PathParam("userName") String userName,@PathParam("userLevel") Integer userLevel,Session session, EndpointConfig config) {
        httpSession = (HttpSession) config.getUserProperties().get(
                HttpSession.class.getName());
        this.nickname = userName;
        this.level = userLevel;
        this.session = session;
        connections.add(this);
        String message = String.format("* %s %s", nickname, "加入聊天！");

        broadcast(message,0);
        String QUEUE_NAME = "hello1";
        try {
            //打开连接和创建频道，与发送端一样
            ConnectionFactory factory = new ConnectionFactory();
            //设置MabbitMQ所在主机ip或者主机名
            factory.setHost("127.0.0.1");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            //声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。
            AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println("Waiting for messages. To exit press CTRL+C");

//            DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
//                @Override
//                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                    System.out.println(consumerTag);
//                    System.out.println(envelope.toString());
//                    System.out.println(properties.toString());
//                    System.out.println("消息内容:" + new String(body));
//                    broadcast(new String(body), 0);
//                    try {
//                        TimeUnit.MILLISECONDS.sleep(1);
//                    } catch (InterruptedException e) {
//                    }
//                }
//            };
//            channel.basicConsume(declareOk.getQueue(), true, "RGP订单系统ADD处理逻辑消费者",defaultConsumer);

            //创建队列消费者
            QueueingConsumer consumer = new QueueingConsumer(channel);
            //指定消费队列
            channel.basicConsume(QUEUE_NAME, true, consumer);

            while (true) {
                //nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法）
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String aa = new String(delivery.getBody());

                System.out.println("Received '" + message + "'");

                broadcast(aa,0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnClose
    public void end() {
        connections.remove(this);
        String message = String.format("* %s %s", nickname, "退出聊天！");
        broadcast(message,0);
    }

    @OnMessage
    public void pushMsg(String message) {
        JSONObject jsonObject = JSONObject.parseObject(message);
        broadcast(jsonObject.getString("message"),jsonObject.getInteger("level"));
    }

    @OnError
    public void onError(Throwable t) throws Throwable {

    }

    private static void broadcast(String msg,Integer messageLevel) {
        // 广播形式发送消息
        for (WebSocketServer client : connections) {
            if(messageLevel != 0 && messageLevel != client.level){
                continue;
            }
            try {
                synchronized (client) {
                    client.session.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
                connections.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                    e.printStackTrace();
                }
                String message = String.format("* %s %s", client.nickname, "断开连接");
                broadcast(message,0);
            }
        }
    }

    public void test(){
        Person p = new Person();
        p.setName("ttt");
        update(p);
        System.out.println(p.getName());
    }

    public void update(Person p){
        p.setName("ccc");
        p = new Person();
        p.setName("aaa");
    }
    public static void main(String[] args) {
      WebSocketServer wb = new WebSocketServer();
      wb.test();
    }

}
