package com.riardo.grpc.boot.service.a;

import com.riardo.grpc.boot.service.a.service.HelloServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @ClassName : Application
 * @Author : changyp
 * @Date : 2024/10/25 12:42
 * @Description :
 */
public class Application {
    public static void main(String[] args) throws Exception{
        //1.声明端口
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(9000);
        //2.添加服务
        serverBuilder.addService(new HelloServiceImpl());
//        serverBuilder.addService(new OrderServiceImpl());
        //3.创建服务对象
        Server server = serverBuilder.build();
        System.out.println("尝试启动服务器");
        server.start();
        System.out.println("服务器启动成功，正在阻塞等待调用。");
        server.awaitTermination();
    }
}
