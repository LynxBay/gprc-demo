package com.riardo.grpc.boot.service.b;

import com.ricardo.grpc.api.HelloProto;
import com.ricardo.grpc.api.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @ClassName : Application
 * @Author : changyp
 * @Date : 2024/10/25 12:41
 * @Description :
 */
public class Application1 {

    public static void main(String[] args) {
        //1.创建通信管道
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();
        try {
            //2.获得代理对象stub
            HelloServiceGrpc.HelloServiceBlockingStub helloService = HelloServiceGrpc.newBlockingStub(channel);
            //3.完成rpc调用
            HelloProto.HelloRequest1.Builder builder = HelloProto.HelloRequest1.newBuilder();

            builder.addName("周可");
            builder.addName("李嘉图");
            builder.addName("Joker");
            builder.addName("Ricardo");
            HelloProto.HelloRequest1 helloRequest1 = builder.build();
            HelloProto.HelloResponse helloResponse = helloService.sayHelloAgain(helloRequest1);
            String result = helloResponse.getResult();
            System.out.println("已完成RPC通信，返回结果："+result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            channel.shutdown();
        }
    }
}
